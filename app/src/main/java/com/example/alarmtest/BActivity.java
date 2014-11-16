	package com.example.alarmtest;

import java.net.InetAddress;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
	/** 
 * 类说明：   
 * @author  mingchunhu
 * @date    2014年6月25日
 * @version 1.0
 */
public class BActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		TextView tv = new TextView(getApplicationContext());
		tv.setText("TextView");
		setContentView(tv);
		
		GarbageScan scan = new GarbageScan();
		scan.scan();
	}
}
