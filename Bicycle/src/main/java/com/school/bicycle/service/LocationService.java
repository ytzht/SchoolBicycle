package com.school.bicycle.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.school.bicycle.R;
import com.school.bicycle.ui.main.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/8/2.
 */

public class LocationService extends Service implements AMapLocationListener {


    //声明mLocationOption对象， 定位参数
    public AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
    //标识，用于判断是否只显示一次定位信息和用户重新定位
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    private Timer mTimer = null;
    private TimerTask mTimerTask = null;
    private boolean isStop = false;
    public String strIsLogin = "1";
    private Intent intent = null;
    String st;
    private double latitude;
    private double longitude;


    @Override
    public void onCreate() {
        super.onCreate();
        intent = new Intent("com.example.broadcast");
        Intent startIntent = new Intent(this, jihuoservice.class);
        startService(startIntent);
    }


    private void init() {
        //初始化定位
        mLocationClient = new AMapLocationClient(this);
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        //设置精度模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        mLocationOption.setInterval(3000);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //启动定位
    }


    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        // TODO Auto-generated method stub
        latitude = aMapLocation.getLatitude();
        longitude = aMapLocation.getLongitude();
        Log.d("我在不停地定位LocationService=", "latitude:" + latitude + "longitude" + longitude);
    }

    private final static int GRAY_SERVICE_ID = 1001;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        Toast.makeText(getApplicationContext(), "onStartCommand() executed",Toast.LENGTH_SHORT).show();
        init();
        // 触发定时器
        if (!isStop) {
            startTimer();
        }
        Notification.Builder builder = new Notification.Builder(this.getApplicationContext()); //获取一个Notification构造器
        Intent nfIntent = new Intent(this, MainActivity.class);
        nfIntent.putExtra("bike_number","");
// 设置PendingIntent

        builder.setContentIntent(PendingIntent.getActivity(this, 0, nfIntent, 0)).
                setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher)).
                setContentTitle("校易行").
                setSmallIcon(R.mipmap.ic_launcher).setContentText("正在运行中")
                .setWhen(System.currentTimeMillis());
        Notification notification = builder.build(); // 获取构建好的Notification
        notification.defaults = Notification.DEFAULT_SOUND; //设置为默认的声音
        startForeground(110, notification);// 开始前台服务

        return Service.START_STICKY;
//        return super.onStartCommand(intent, flags, startId);
    }

    private void startTimer() {
        // TODO Auto-generated method stub
        isStop = true;//定时器启动后，修改标识，关闭定时器的开关
        if (mTimer == null) {
            mTimer = new Timer();
        }
        if (mTimerTask == null) {
            mTimerTask = new TimerTask() {
                @Override
                public void run() {
                    do {
                        try {
                            if (strIsLogin == "1") {
                                init();
                                Message message = new Message();
                                message.what = 1;
                                handler.sendMessage(message);
                                mLocationClient.startLocation();
                            }
                            Thread.sleep(5000);//5秒后再次执行
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            return;
                        }
                    } while (isStop);
                }
            };
        }
        if (mTimer != null && mTimerTask != null) {
            mTimer.schedule(mTimerTask, 0);//执行定时器中的任务
        }
    }


    /**
     * 处理接fasong的数据
     */
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    st = String.valueOf((float) (latitude)) + "," + String.valueOf((float) (longitude));
                    intent.putExtra("Str", st);
                    sendBroadcast(intent);
                    break;
            }
            super.handleMessage(msg);
        }
    };


    private void stopTimer() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        if (mTimerTask != null) {
            mTimerTask.cancel();
            mTimerTask = null;
        }
        isStop = false;//重新打开定时器开关
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        Toast.makeText(getApplicationContext(), "onDestroy() executed", Toast.LENGTH_SHORT).show();
        stopForeground(true);// 停止前台服务--参数：表示是否移除之前的通知
        // 停止定时器
        if (isStop) {
            stopTimer();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
