package com.hoot.plugin;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.example.mingchunhu.pluginlib.R;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Dictionary;
import java.util.HashMap;

import dalvik.system.DexClassLoader;

/**
 * 代理类，负责调起插件Activity，宿主app需引用该类
 */
public class PluginActivity extends Activity {
    private static final String TAG = "ProxyActivity";

    public static final String FROM = "extra.from";
    public static final int FROM_EXTERNAL = 0;
    public static final int FROM_INTERNAL = 1;

    public static final String EXTRA_DEX_PATH = "extra.dex.path";
    public static final String EXTRA_CLASS = "extra.class";

    protected static AssetManager mAssetManager;
    protected static Resources mResources;
    protected static Resources.Theme mTheme;

    private String mClass;
    private String mDexPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugin);
        mDexPath = getIntent().getStringExtra(EXTRA_DEX_PATH);
        loadResource(mDexPath);
        mClass = getIntent().getStringExtra(EXTRA_CLASS);

        Log.d(TAG, "mClass=" + mClass + " mDexPath=" + mDexPath);
        //launch target Activity
        if (mClass == null) {
            launchTargetActivity();
        } else {
            launchTargetActivity(mClass);
        }
    }

    protected void launchTargetActivity() {
        PackageInfo packageInfo = getPackageManager().getPackageArchiveInfo(
                mDexPath, 1);
        if ((packageInfo.activities != null)
                && (packageInfo.activities.length > 0)) {
            String activityName = packageInfo.activities[0].name;
            mClass = activityName;
            launchTargetActivity(mClass);
        }
    }

    protected void launchTargetActivity(final String className) {
        Log.d(TAG, "start launchTargetActivity, className=" + className);
        File dexOutputDir = this.getDir("dex", 0);
        final String dexOutputPath = dexOutputDir.getAbsolutePath();
        ClassLoader localClassLoader = ClassLoader.getSystemClassLoader();
        DexClassLoader dexClassLoader = new DexClassLoader(mDexPath,
                dexOutputPath, null, localClassLoader);
        try {
            Class<?> localClass = dexClassLoader.loadClass(className);
            Constructor<?> localConstructor = localClass
                    .getConstructor(new Class[] {});
            Object instance = localConstructor.newInstance(new Object[] {});
            Log.d(TAG, "instance = " + instance);

            injectContext2Target(localClass, instance);
            callTargetOnCreate(localClass, instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void callTargetOnCreate(Class<?> localClass, Object instance) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method onCreate = localClass.getDeclaredMethod("onCreate",
                new Class[] { Bundle.class });
        onCreate.setAccessible(true);
        Bundle bundle = new Bundle();
        bundle.putInt(FROM, FROM_EXTERNAL);
        onCreate.invoke(instance, new Object[] { bundle });
    }

    private void injectContext2Target(Class<?> localClass, Object instance) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method setProxy = localClass.getMethod("setProxy",
                new Class[] { Activity.class });
        setProxy.setAccessible(true);
        setProxy.invoke(instance, new Object[] { this });
    }

    private void loadResource(String dexPath) {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, dexPath);
            mAssetManager = assetManager;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Resources superRes = super.getResources();
        mResources = new Resources(mAssetManager, superRes.getDisplayMetrics(),
                superRes.getConfiguration());
        mTheme = mResources.newTheme();
        mTheme.setTo(super.getTheme());
    }

    @Override
    public AssetManager getAssets() {
        return mAssetManager == null ? super.getAssets() : mAssetManager;
    }

    @Override
    public Resources getResources() {
        return mResources == null ? super.getResources() : mResources;
    }
}
