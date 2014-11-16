package com.example.alarmtest;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;

/**
 * 插件Activity接口
 * 
 * @author albertzhong
 */
public interface IPluginActivity {
	public void ISetOutHandler(Handler handler);

	public Handler IGetInHandler();

//	public void ISetContext(Activity context);

	public void IOnCreate(Bundle savedInstanceState);

	public void ISetIntent(Intent intent);

	public boolean IOnKeyDown(int keyCode, KeyEvent event);

	public boolean IOnKeyUp(int keyCode, KeyEvent event);

	public boolean IOnKeyMultiple(int keyCode, int repeatCount, KeyEvent event);

	public View IGetContentView();
	
//	public void ISetPath(String path);

	public void IOnResume();

	public void IOnStart();

	public void IOnPause();

	public void IOnStop();

	public void IOnDestroy();

	public void IOnRestart();
	
//	public void ISetClassLoader(ClassLoader classLoader);
	
	public void IInit(String path, Activity context, ClassLoader classLoader, PackageInfo packageInfo );
	
//	public ClassLoader IGetClassLoader();
	
	public Resources IGetResource();
}
