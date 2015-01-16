package com.example.alarmtest.util;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by mingchunhu on 2014/11/16.
 */
public class GpsHandler {
    private LocationManager mLocManager;
    long start = System.currentTimeMillis();
    public void regist(Context context) {
        mLocManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        mLocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000, 8, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.i("location", "takes:" + (System.currentTimeMillis() - start));
                if (location != null) {
                    StringBuffer sb = new StringBuffer();
                    sb.append("实时的位置信息：\n经度：");
                    sb.append(location.getLongitude());
                    sb.append("\n纬度：");
                    sb.append(location.getLatitude());
                    sb.append("\n高度：");
                    sb.append(location.getAltitude());
                    sb.append("\n速度：");
                    sb.append(location.getSpeed());
                    sb.append("\n方向：");
                    sb.append(location.getBearing());
                    sb.append("\n精度：");
                    sb.append(location.getAccuracy());
                    Log.i("location", sb.toString());
                }
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
                Log.i("location", s + ", i;" + i + ", " + bundle);
            }

            @Override
            public void onProviderEnabled(String s) {
                Log.i("location", "onProviderEnabled;" + s );
            }

            @Override
            public void onProviderDisabled(String s) {
                Log.i("location", "onProviderDisabled;" + s);
            }
        });
    }
}
