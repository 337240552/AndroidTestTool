	package com.example.alarmtest;

import com.example.alarmtest.util.AlarmUtil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.util.Log;
import android.widget.Toast;
	/** 
 * 类说明：   
 * @author  mingchunhu
 * @date    2014年4月24日
 * @version 1.0
 */
public class TestReceiver extends BroadcastReceiver{
	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println(intent.getAction() + ", count:" + intent.getIntExtra("time", -1));
		Toast.makeText(context, "测试" + Binder.getCallingPid(), Toast.LENGTH_SHORT).show();;
		android.util.Log.v(AlarmUtil.TEST_RECEIVER, "uid:" + Binder.getCallingPid());
	}
}
