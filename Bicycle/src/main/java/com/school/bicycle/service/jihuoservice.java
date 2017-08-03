package com.school.bicycle.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2017/8/3.
 */

public class jihuoservice extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Intent startIntent = new Intent(this, LocationService.class);
        startService(startIntent);
        Log.d("我在不停地定位LocationService=", "激活定位");
    }

    private final static int GRAY_SERVICE_ID = 1001;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        startForeground(GRAY_SERVICE_ID, new Notification());
//        stopForeground(true);
//        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }


}
