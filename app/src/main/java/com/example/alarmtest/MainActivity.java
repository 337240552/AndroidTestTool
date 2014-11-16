package com.example.alarmtest;

import java.net.URL;
import java.net.URLDecoder;
import java.util.Calendar;

import com.coolcloud.uac.android.api.util.Url;
import com.example.alarmtest.util.AlarmUtil;
import com.example.alarmtest.util.BitmapDecodeUtil;
import com.example.alarmtest.util.CmwapDownload;
import com.example.alarmtest.util.DateUtil;
import com.example.alarmtest.util.WebviewUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static final String action = "test_action";
	private static final String TAG = "MainActivity";
//	private NotificationManager nm;
	public static Context context ;
	private TextView mTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Log.i(TAG, "onCreate:" + getTaskId());
//		Debug.startMethodTracing("test");
		context = this;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        BitmapDecodeUtil.decode(getResources());
		Log.v(TAG, "current:"   + DateUtil.format(calendar.getTimeInMillis()) + "xxx" );
		mTextView = (TextView) findViewById(R.id.textView1);
        String orig = "http://www.baidu.com/?a=11&b=22";
        String encode2 =  Uri.encode(Uri.encode(orig));
        Log.v(TAG, "first:" + Uri.encode(orig) + ", " + encode2);

        Log.v(TAG, "decode:" + Uri.decode(encode2));
        WebviewUtil.showWebView(this, (ViewGroup)findViewById(R.id.container));
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	}
	 
	@SuppressLint("NewApi")
	public void startLogin(View v){;
		Intent intent = new Intent();
		intent.setComponent(new ComponentName("com.yulong.android.coolmart", "com.tencent.assistant.link.LinkProxyActivity"));
		intent.setData(Uri.parse("coolmart://specialtopic/"));
		
		ComponentName comp = intent.resolveActivity(getPackageManager());
		if (comp != null) {
			startActivity(intent);
		}
	}
	
	public void startBActivity(View v) {
		mTextView.layout(mTextView.getLeft(), mTextView.getTop() + 10, mTextView.getRight(), mTextView.getBottom() - 10);
		getWindow().getDecorView().invalidate();
		findViewById(R.id.textView1).scrollBy(-10, -10);
	}
	
	public void setAlarm(View v) {
		AlarmUtil.createAlarm(this);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		System.out.println("xxx onPause");
//		Debug.stopMethodTracing();
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		System.out.println("xxx onStop");
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		System.out.println("xxx onDestroy");
	}
}
