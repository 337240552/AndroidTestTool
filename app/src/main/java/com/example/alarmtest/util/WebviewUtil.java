	package com.example.alarmtest.util;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

    /**
 * 类说明：   
 * @author  mingchunhu
 * @date    2014年10月15日
 * @version 1.0
 */
public class WebviewUtil {
	protected static final String TAG = "WebviewUtil";

	public static WebView showWebView(Context context, ViewGroup container) {
		WebView view = new WebView(context);
		view.setWebChromeClient(new WebChromeClient(){
			@Override
			public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
				Log.i(TAG, "msg:" + consoleMessage.message());
				return super.onConsoleMessage(consoleMessage);
			}
		});
        view.getSettings().setJavaScriptEnabled(true);

        view.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.v(TAG, "url:" + url);
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
                Log.d(TAG, "loadResource: url:" + url);
            }
        });


		container.removeAllViews();
		container.addView(view);
//		view.loadUrl("file:///android_asset/geo.html");
        view.loadUrl("http://info.3g.qq.com/g/s?id=ent_20150106023125&aid=ent_ss&g_f=23792");
        return view;
	}
}
