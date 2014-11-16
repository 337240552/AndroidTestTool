package com.example.alarmtest.util;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.alarmtest.MainActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;

/**
 * 类说明：
 * 
 * @author mingchunhu
 * @date 2014年8月25日
 * @version 1.0
 */
public class AppUsageStatManager {

	private static final String TAG = "AppUsageStatManager";

	
	public static void getAllPkgUsageStats(List<String> whiteListPkg) {
	    try {  
	        Object oIUsageStats = getIUsageStatsIntace();  
	  
	        // PkgUsageStats[] oPkgUsageStatsArray =  
	        // mUsageStatsService.getAllPkgUsageStats();  
	        Class<?> cIUsageStatus = Class  
	                .forName("com.android.internal.app.IUsageStats");  
	        Method mGetAllPkgUsageStats = cIUsageStatus.getMethod(  
	                "getAllPkgUsageStats", (Class[]) null);  
	        Object[] oPkgUsageStatsArray = (Object[]) mGetAllPkgUsageStats  
	                .invoke(oIUsageStats, (Object[]) null);
	        Class<?> cPkgUsageStats = Class  
	                .forName("com.android.internal.os.PkgUsageStats");  
	  
	        StringBuffer sb = new StringBuffer();  
	        sb.append("nerver used : ");
	        
	        Map<String, Boolean> whiteList = getWhitListMap(whiteListPkg);
	        for (Object pkgUsageStats : oPkgUsageStatsArray) {  
	            // get pkgUsageStats.packageName, pkgUsageStats.launchCount,  
	            // pkgUsageStats.usageTime  
	            String packageName = (String) cPkgUsageStats.getDeclaredField(  
	                    "packageName").get(pkgUsageStats);
	            if (packageName == null || (whiteList != null && whiteList.get(packageName) == null)) {
	            	continue;
	            }
	            int launchCount = cPkgUsageStats  
	                    .getDeclaredField("launchCount").getInt(pkgUsageStats);  
	            long usageTime = cPkgUsageStats.getDeclaredField("usageTime")  
	                    .getLong(pkgUsageStats);
	            Map m = (Map) cPkgUsageStats.getDeclaredField("componentResumeTimes").get(pkgUsageStats);
	            if (launchCount > 0)  
	                Log.d(TAG, "[getPkgUsageStats] "+ packageName + "  count: "  
	                        + launchCount + "  time:  " + usageTime + ", resumeTimes:" + m);  
	            else {  
	                sb.append(packageName + "; ");  
	            }  
	        }  
	        Log.d(TAG, "[getPkgUsageStats] " + sb.toString());  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    } 
	}
	
	public static void getPkgUsageStats(String pkg) {
		try {
			ComponentName cm = getComponentName(pkg);
			Log.i(TAG, "cm:" + cm);
			if (cm == null) {
				return;
			}
			
			Object oIUsageStats = getIUsageStatsIntace();
			 // PkgUsageStats[] oPkgUsageStatsArray =  
	        // mUsageStatsService.getPkgUsageStats(Component);  
	        Class<?> cIUsageStatus = Class  
	                .forName("com.android.internal.app.IUsageStats");  
	        Method mGetPkgUsageStats = cIUsageStatus.getMethod(  
	                "getPkgUsageStats", ComponentName.class);
	        
	        Object pkgUsageStats = mGetPkgUsageStats.invoke(oIUsageStats, cm);
	        if (pkgUsageStats == null) {
	        	return;
	        }
	        
	        Class<?> cPkgUsageStats = Class  
	                .forName("com.android.internal.os.PkgUsageStats");
	        
	        String packageName = (String) cPkgUsageStats.getDeclaredField(  
                    "packageName").get(pkgUsageStats);
            int launchCount = cPkgUsageStats  
                    .getDeclaredField("launchCount").getInt(pkgUsageStats);  
            long usageTime = cPkgUsageStats.getDeclaredField("usageTime")  
                    .getLong(pkgUsageStats);
            
            Log.i(TAG, "pkg:" + packageName + ", launch count:" + launchCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		  
        
	}
	
	private static ComponentName getComponentName(String packageName) {
		PackageManager pm = MainActivity.context.getPackageManager();  
		// 解析所有可执行的应用  
		Intent it = new Intent(Intent.ACTION_MAIN);
		it.setPackage(packageName);
		it.addCategory(Intent.CATEGORY_LAUNCHER);  
		List<ResolveInfo> ris = pm.queryIntentActivities(it, 0);
		if (ris != null && !ris.isEmpty()) {
			ActivityInfo ai = ris.get(0).activityInfo;
			return new ComponentName(ai.packageName, ai.name);
		}
		return null;
	}
	
	private static Object getIUsageStatsIntace() throws ClassNotFoundException,
			NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		Class<?> cServiceManager = Class  
		        .forName("android.os.ServiceManager");  
		Method mGetService = cServiceManager.getMethod("getService",  
		        java.lang.String.class);  
		Object oRemoteService = mGetService.invoke(null, "usagestats");  
  
		// IUsageStats oIUsageStats =  
		// IUsageStats.Stub.asInterface(oRemoteService)  
		Class<?> cStub = Class  
		        .forName("com.android.internal.app.IUsageStats$Stub");  
		Method mUsageStatsService = cStub.getMethod("asInterface",  
		        android.os.IBinder.class);  
		Object oIUsageStats = mUsageStatsService.invoke(null,  
		        oRemoteService);
		return oIUsageStats;
	}
	
	private static Map<String, Boolean> getWhitListMap(List<String> pkgs) {
		if (pkgs == null) {
			return null;
		}
		Map<String, Boolean> whiteListMap = new HashMap<String, Boolean>();
		for (String pkg : pkgs) {
			whiteListMap.put(pkg, true);
		}
		return whiteListMap;
	}
}
