package com.example.alarmtest.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.alarmtest.R;

/**
 * Created by mingchunhu on 2014/11/5.
 */
public class BitmapDecodeUtil {
    private static final String TAG = "BitmapDecodeUtil";

    public static void decode(Resources resources) {
        Bitmap bitmap = BitmapFactory.decodeResource(resources, R.drawable.splash_default);

        Log.d(TAG, "bitmap:" + bitmap.getRowBytes() * bitmap.getHeight());
    }
}
