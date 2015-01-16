package com.example.alarmtest;

import java.io.File;
import java.io.IOException;

import com.hoot.plugin.PluginActivity;
import com.hoot.recyclerview.RecyclerViewActivity;
import com.example.alarmtest.util.AlarmUtil;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends Activity {
	private static final String action = "test_action";
    public static final String TAG = "MainActivity";
//	private NotificationManager nm;
	public static Context context ;
	private TextView mTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.textView1);
        tv.setText(Html.fromHtml("<i><small><font color=\"#ff00ff\"> Competitor ID:  </font></small></i>" + "<strong><font color=\"#ff0000\">" + 22222 + "</font></strong>"));
//        WebviewUtil.showWebView(this, (ViewGroup)findViewById(R.id.root));
//        startPluginMainActivity();
        startRecycleView();
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	}

    public void startRecycleView() {
        startActivity(new Intent(this, RecyclerViewActivity.class));
    }

	public void startPluginMainActivity() {
        Intent intent = new Intent(this, PluginActivity.class);
        intent.putExtra(PluginActivity.EXTRA_DEX_PATH, "/mnt/sdcard/plugin/app-debug.apk");
        startActivity(intent);
    }
	
	public void startBActivity(View v) {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "/tencent/navigation/downloads");
            Log.v(TAG + "xxx", "exist:" + file.exists() + ", " +  file);
            boolean ret = file.mkdirs();
            Log.v(TAG + "xxx", "xxxxxxxxxxx ret:" + ret);
            file.createNewFile();
        } catch (IOException e) {
            Log.v(TAG+ "xxx", e.getMessage());
            e.printStackTrace();
        }
//
//		mTextView.layout(mTextView.getLeft(), mTextView.getTop() + 10, mTextView.getRight(), mTextView.getBottom() - 10);
//		getWindow().getDecorView().invalidate();
//		findViewById(R.id.textView1).scrollBy(-10, -10);
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
