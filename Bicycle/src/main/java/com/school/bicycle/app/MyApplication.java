package com.school.bicycle.app;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.school.bicycle.global.UserService;
import com.school.bicycle.http.APIFactory;
import com.school.bicycle.utils.PreferencesUtils;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.MemoryCookieStore;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;
import com.zhy.http.okhttp.cookie.store.SerializableHttpCookie;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;


/**
 * Created by zht on 2017/04/08 9:09
 */

public class MyApplication extends MultiDexApplication {

    public static Context aContext;
    public PreferencesUtils preferencesUtils;

    @Override
    public void onCreate() {
        super.onCreate();

//        com.wanjian.sak.LayoutManager.init(this);

//        initCLog();
//
//        initAnalytics();
//
        initRetrofit();
//
//        initPush();
//
        initShare();

        initokhttp(getApplicationContext(), "a", "a");

        preferencesUtils = new PreferencesUtils(this);
        ZXingLibrary.initDisplayOpinion(this);
    }

    public static void initokhttp(Context context, String key, String value) {
        if (!key.equals("a")) new UserService(context).setCookie(key, value);
        CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(context));
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .addInterceptor(new LoggerInterceptor("TAG====="))
                .cookieJar(cookieJar)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }

    private void initShare() {
        UMShareAPI.get(this);
        PlatformConfig.setWeixin("wx71fd0a9986ec271f", "0df892f43906044db30c6efbb2f259ef");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
    }

//
//    private void initShare() {
//        UMShareAPI.get(this);
//        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
//        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
//    }
//
//    private void initPush() {
//        //如需手动获取device token，可以调用mPushAgent.getRegistrationId()方法
//        PushAgent mPushAgent = PushAgent.getInstance(this);
//        //注册推送服务，每次调用register方法都会回调该接口
//        mPushAgent.register(new IUmengRegisterCallback() {
//
//            @Override
//            public void onSuccess(String deviceToken) {
//                //注册成功会返回device token
//                L.d("deviceToken " + deviceToken);
//            }
//
//            @Override
//            public void onFailure(String s, String s1) {
//                L.e("push onFailure " + s + " " + s1);
//            }
//        });
//        mPushAgent.setDebugMode(BuildConfig.DEBUG);
//
//        //自定义通知打开动作
//        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
//            @Override
//            public void dealWithCustomAction(Context context, UMessage msg) {
//                Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
//            }
//        };
//        mPushAgent.setNotificationClickHandler(notificationClickHandler);
//
////        为免过度打扰用户，SDK默认在“23:00”到“7:00”之间收到通知消息时不响铃，不振动，不闪灯。如果需要改变默认的静音时间，可以使用以下接口：
////        mPushAgent.setNoDisturbMode(23, 0, 7, 0);
////        mPushAgent.setNoDisturbMode(0, 0, 0, 0);
//
////        默认情况下，同一台设备在1分钟内收到同一个应用的多条通知时，不会重复提醒，同时在通知栏里新的通知会替换掉旧的通知。可以通过如下方法来设置冷却时间：
////        mPushAgent.setMuteDurationSeconds(int seconds);
//
////        通知栏可以设置最多显示通知的条数，当有新通知到达时，会把旧的通知隐藏。可以设置为0~10之间任意整数。当参数为0时，表示不合并通知。
////        mPushAgent.setDisplayNotificationNumber(int number);
//
////        客户端控制通知到达响铃、震动、呼吸灯
////        MsgConstant.NOTIFICATIONPLAYSERVER（服务端控制）
////        MsgConstant.NOTIFICATIONPLAYSDKENABLE（客户端允许）
////        MsgConstant.NOTIFICATIONPLAYSDKDISABLE（客户端禁止）
//        mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SERVER); //声音
//        mPushAgent.setNotificationPlayLights(MsgConstant.NOTIFICATION_PLAY_SERVER);//呼吸灯
//        mPushAgent.setNotificationPlayVibrate(MsgConstant.NOTIFICATION_PLAY_SERVER);//振动
//
////        图标    24*24px在drawable-mdpi 下    64*64px在drawable-mdpi 下， 128*128px 在drawable-xhdpi 中。
////        制作图标时， 注意图片各边留一个像素的透明。 建议使用黑白图标。
//
//    }
//
//
//


    private void initRetrofit() {

        aContext = this;
        //初始化网络请求工具
        APIFactory.getInstance().init(this);

    }
//
//    private void initAnalytics() {
//        MobclickAgent.setDebugMode(true);
//
//    }
//
//
//    private void initCLog() {
//        String environment = "";
//
//        if (environment.equals("production")) {
//            CLog.setLogLevel(CLog.LEVEL_ERROR);
//        } else if (environment.equals("beta")) {
//            CLog.setLogLevel(CLog.LEVEL_WARNING);
//        } else {
//            // development
//            CLog.setLogLevel(CLog.LEVEL_VERBOSE);
//        }
//
//        CubeDebug.DEBUG_IMAGE = true;
//        PtrFrameLayout.DEBUG = false;
//
//        ImageLoaderFactory.setDefaultImageReSizer(DemoDuiTangImageReSizer.getInstance());
//        ImageLoaderFactory.setDefaultImageLoadHandler(new PtrImageLoadHandler());
//        String dir = "request-cache";
//        // ImageLoaderFactory.init(this);
//        RequestCacheManager.init(this, dir, 1024 * 10, 1024 * 10);
//        Cube.onCreate(this);
//
//    }
//


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


}
