	package com.example.alarmtest.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
	/** 
 * 类说明：   
 * @author  mingchunhu
 * @date    2014年10月10日
 * @version 1.0
 */
public class AlarmUtil {
	public static final String TEST_RECEIVER = "TestReceiver";
	private static final int REQUEST_CODE = 1000;

	public static void createAlarm(Context context) {
		Intent intent = new Intent("test_action");
		PendingIntent sender = PendingIntent.getBroadcast(context, REQUEST_CODE, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		Log.v(TEST_RECEIVER, "sender:" + sender);
		
		AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//		am.cancel(sender);
		am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 100 * 1000, 30 * 1000, sender);
	}
}
