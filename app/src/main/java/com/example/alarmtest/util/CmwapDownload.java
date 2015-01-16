	package com.example.alarmtest.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;
	/** 
 * 类说明：   
 * @author  mingchunhu
 * @date    2014年10月15日
 * @version 1.0
 */
public class CmwapDownload {
	private static final String TAG = "CMWAPDownload";

	public static void download(ImageView view) {
		InputStream is = null;
		HttpURLConnection conn = null;
		
		long start = System.currentTimeMillis();
		try {
			Log.v(TAG, "start to download...");
			URL url = new URL("http://111.13.100.92/img/baidu_logo.gif");
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.0.0.172", 80));
			conn = (HttpURLConnection) url.openConnection(/*proxy*/);
			conn.setDoInput(true);
			conn.connect();
			is = conn.getInputStream();
			Bitmap bitmap = BitmapFactory.decodeStream(is);
			Log.v(TAG, "bitmap:" + bitmap.getRowBytes());
			
			if (view != null) {
				view.setImageBitmap(bitmap);
			}
		} catch (MalformedURLException e) {
            Log.e(TAG, "", e);
			e.printStackTrace();
		} catch (IOException e) {
            Log.e(TAG, "", e);
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {}
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
		Log.v(TAG, "time consume:" + (System.currentTimeMillis() - start));
	}
	
	public static void download2() {
		InputStream is = null;
		HttpURLConnection conn = null;
		
		long start = System.currentTimeMillis();
		try {
			Log.v(TAG, "start to download...");
			URL url = new URL("http://10.0.0.172/img/baidu_logo.gif");  
			conn = (HttpURLConnection) url.openConnection();  
			conn.setRequestProperty("X-Online-Host", "www.baidu.com"); 
			conn.setDoInput(true);
			conn.connect();
			is = conn.getInputStream();
			Bitmap bitmap = BitmapFactory.decodeStream(is);
			Log.v(TAG, "bitmap:" + bitmap.getRowBytes());
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {}
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
		Log.v(TAG, "time consume:" + (System.currentTimeMillis() - start));
	}
}
