package com.hoot.plugin;

import java.util.HashMap;

/**
 * Created by mingchunhu on 2015/1/14.
 */
public class PluginManager {
    private HashMap<String, PluginInfo> mPluginCache;
    private static PluginManager mPluginManager;

    private PluginManager(){
        mPluginCache = new HashMap<>();
    }

    public static final synchronized PluginManager getInstance() {
        if (mPluginManager == null) {
            mPluginManager = new PluginManager();
        }
        return mPluginManager;
    }

    public PluginInfo getPluginInfo(String packageName) {
        return mPluginCache.get(packageName);
    }

    public PluginInfo getPluginInfoByPath(String pluginPath) {
        return null;
    }

    private void addPlugin2Cache(PluginInfo info) {
        mPluginCache.put(info.pacakgeName, info);
    }

    /**
     * pasrse all plugin
     */
    private void init() {

    }
}
