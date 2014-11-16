package com.example.alarmtest.util;

import java.text.SimpleDateFormat;

/**
 * Created by mingchunhu on 2014/11/5.
 */
public class DateUtil  {
    public static String format( long time ) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format( time );
    }
}
