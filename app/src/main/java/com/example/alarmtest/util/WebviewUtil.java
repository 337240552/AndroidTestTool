	package com.example.alarmtest.util;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
	/** 
 * 类说明：   
 * @author  mingchunhu
 * @date    2014年10月15日
 * @version 1.0
 */
public class WebviewUtil {
	protected static final String TAG = "WebviewUtil";

	public static void showWebView(Context context, ViewGroup container) {
		WebView view = new WebView(context);
		view.setWebChromeClient(new WebChromeClient(){
			@Override
			public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
				Log.i(TAG, "msg:" + consoleMessage.message());
				return super.onConsoleMessage(consoleMessage);
			}
		});
		container.removeAllViews();
		container.addView(view);
//		view.loadUrl("file:///android_asset/geo.html");
        view.loadUrl("http://m.sogou.com/web/searchList.jsp?pid=sogou-wsse-e1021d43911ca2c1&keyword=%26%2322269%3B%26%2336275%3Bvs%26%2326032%3B%26%2335199%3B%26%2320848%3B");
	}
}
