package com.example.alarmtest;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import android.os.Environment;
	/** 
 * 类说明：   
 * @author  mingchunhu
 * @date    2014年8月26日
 * @version 1.0
 */
public class GarbageScan {
	class CacheInfo {
		boolean mIsApk;
//		String mPkgName;//相当于主键
//		LocalApkInfo mDirOwnPkgInfo;//这个垃圾文件所属应用信息,如果这个值为空则说明文件已经被删除
		String mPath;//文件路径
		long mSize;
		long mLastModifiedTime;
	}
	
	class AppCacheInfo {
		String mPkgName;//
		long mTotalSize;
		List<CacheInfo> mCacheInfo;
	}
	
	public Map<String, String> getAppCacheDirMap() {
		HashMap<String, String> dir2PkgMap = new HashMap<String, String>();
		dir2PkgMap.put("wandoujia", "com.wandoujia.phoenix2");
		dir2PkgMap.put("qqdownloader", "com.tencent.android.qqdownloader");
		dir2PkgMap.put("qqplaza", "com.tencent.android.qqplaza");
		dir2PkgMap.put("qqplaza", "com.tencent.android.qqplaza");
		dir2PkgMap.put("DCIM/.thumbnails", "com.android.Camera");//相机缓存
		dir2PkgMap.put("Coolmart", "com.yulong.android.coolmart");
		
		return dir2PkgMap;
	}
	
	public void getIgnoreDir() {
		ArrayList<String> ignore = new ArrayList<String>();
		ignore.add("pictures");
	}
	
	public void scan() {
		File root = getScanDir();
		File[] sdcardRoots = root.listFiles(); 
		List<CacheInfo> caches = scanDir(root);
		System.out.println(caches);
	}
	
	public List<CacheInfo> scanDir(File root) {
		if (root == null) {
			return null;
		}
		
		File[] files = root.listFiles(mFilter);
		if (files == null) {
			return null;
		}
		ArrayList<CacheInfo> scannedFiles = new ArrayList<CacheInfo>();
		long total = 0L;
		for (File f : files) {
			if (f.isFile()) {
				CacheInfo info = new CacheInfo();
				info.mPath = f.getAbsolutePath();
				if (info.mPath.toLowerCase().endsWith("apk")) {
					info.mIsApk = true;
				}
				info.mLastModifiedTime = f.lastModified();
				info.mSize = f.length();
				total += info.mSize;
				scannedFiles.add(info);
			} else if (f.isDirectory()) {
				scanDir(f);
			}
		}
		return scannedFiles;
	}
	
	private FilenameFilter mFilter = new FilenameFilter() {
		
		@Override
		public boolean accept(File dir, String filename) {
			
			return true;
		}
	};
	
	public static File getScanDir() {
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			return Environment.getExternalStorageDirectory();
		} else {
			return Environment.getDataDirectory();
		}
	}
}
