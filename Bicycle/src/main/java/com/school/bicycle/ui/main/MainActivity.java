package com.school.bicycle.ui.main;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.PolylineOptions;
import com.google.gson.Gson;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.school.bicycle.R;
import com.school.bicycle.entity.BaseResult;
import com.school.bicycle.entity.CheckJumpStatus;
import com.school.bicycle.entity.DayleaseList;
import com.school.bicycle.entity.GetBikeMapList;
import com.school.bicycle.entity.Lockstatus;
import com.school.bicycle.entity.QueryBikeListByDate;
import com.school.bicycle.entity.SharedBikeList;
import com.school.bicycle.entity.UpDate;
import com.school.bicycle.entity.UploadLocation;
import com.school.bicycle.entity.ValidateUser;
import com.school.bicycle.global.Apis;
import com.school.bicycle.global.BaseActivity;
import com.school.bicycle.global.L;
import com.school.bicycle.global.UserService;
import com.school.bicycle.ui.Details.DetailsActivity;
import com.school.bicycle.ui.FaultActivity;
import com.school.bicycle.ui.InformationActivity;
import com.school.bicycle.ui.Ivfriends.IvfriendsActivity;
import com.school.bicycle.ui.Mycoupon_Activity;
import com.school.bicycle.ui.OverPayActivity;
import com.school.bicycle.ui.TimeCountDownTextView;
import com.school.bicycle.ui.User_Activity;
import com.school.bicycle.ui.ZhiDaoActivity;
import com.school.bicycle.ui.ZxingActivity;
import com.school.bicycle.ui.authentication.RealnameActivity;
import com.school.bicycle.ui.eposit.DepositActivity;
import com.school.bicycle.ui.lockclose.LockcloseActivity;
import com.school.bicycle.ui.longtimeLease.LongTimeLeaseActivity;
import com.school.bicycle.ui.mybicycle.MyBicycleActivity;
import com.school.bicycle.ui.mywallet.Mywallet_activity;
import com.school.bicycle.ui.pay.PayActivity;
import com.school.bicycle.ui.register.RegisterActivity;
import com.school.bicycle.ui.result.ResultActivity;
import com.school.bicycle.ui.setup.Setup_Activity;
import com.school.bicycle.ui.usebicycle.UseBicycleActivity;
import com.school.bicycle.utils.BindPushUtils;
import com.school.bicycle.utils.HighlightWeekendsDecorator;
import com.school.bicycle.utils.MySelectorDecorator;
import com.school.bicycle.utils.MySelectorDecorators;
import com.school.bicycle.utils.OneDayDecorator;
import com.school.bicycle.utils.SelectDecorator;
import com.umeng.socialize.UMShareAPI;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends BaseActivity implements IMainView,
        AMapLocationListener,
        NavigationView.OnNavigationItemSelectedListener,
        AMap.InfoWindowAdapter,
        AMap.OnMapClickListener,
        AMap.OnMarkerClickListener,
        AMap.OnInfoWindowClickListener,
        AMap.OnCameraChangeListener,
        LocationSource {

    @BindView(R.id.map)
    MapView mMapView;
    @BindView(R.id.fab_refresh)
    ImageView floatingActionButton;
    @BindView(R.id.btn_use)
    Button btnUse;
    AMap aMap;
    @BindView(R.id.fab_qr)
    ImageView fabQr;
    @BindView(R.id.tb_main)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.tv_use)
    TextView tvUse;
    @BindView(R.id.use_no)
    TextView useNo;
    @BindView(R.id.countdown)
    TimeCountDownTextView countdown;
    @BindView(R.id.kaluli)
    TextView kaluli;
    @BindView(R.id.length_biycile)
    TextView lengthBiycile;
    @BindView(R.id.state_0)
    RelativeLayout state0;
    @BindView(R.id.finish_usecar)
    TextView finishUsecar;
    @BindView(R.id.saoma)
    LinearLayout saoma;
    @BindView(R.id.useing_biycle_lay)
    RelativeLayout useingBiycleLay;
    @BindView(R.id.dinwgei)
    ImageView dinwgei;
    private ImageView iv_pull;
    private LinearLayout ll_detail;

    private IMainPresenter iMainPresenter;
    private ImageView headImg;
    private TextView name, score, finish_usecar;
    double lat;//获取纬度
    double lon;//获取经度
    double zhonglat;//获取纬度
    double zhonglon;//获取经度
    private AMapLocationClient mlocationClient;
    public AMapLocationClient mLocationClient = null;
    TelephonyManager tm;
    String DEVICE_ID;
    ValidateUser v;
    private Timer mTimer;
    private boolean isbiycle = false;

    //以前的定位点
    private LatLng oldLatLng;
    //是否是第一次定位
    private boolean isFirstLatLng;
    private OnLocationChangedListener mListener;


    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (mListener != null && amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //可在其中解析amapLocation获取相应内容。
//                        double locationType = amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                lat = amapLocation.getLatitude();//获取纬度
                lon = amapLocation.getLongitude();//获取经度
                LatLng newLatLng;
                if (isFirstLatLng) {
                    isFirstLatLng = false;
                    newLatLng = new LatLng(lat, lon);
                    oldLatLng = newLatLng;
                    cameraUpdate = CameraUpdateFactory
                            .newCameraPosition(new CameraPosition(new LatLng(lat, lon), 16, 0, 0));
                    aMap.moveCamera(cameraUpdate);
                }


                if (new UserService(MainActivity.this).getState().equals("1")) {

                    newLatLng = new LatLng(lat, lon);
                    cameraUpdate = CameraUpdateFactory
                            .newCameraPosition(new CameraPosition(new LatLng(lat, lon), 16, 0, 0));
                    aMap.moveCamera(cameraUpdate);
                    L.d("=====new old", oldLatLng.latitude + " and " + newLatLng.latitude + "");
                    //位置有变化
                    if (newLatLng != null && oldLatLng != null && oldLatLng != newLatLng) {
                        Log.d("定位获得的经纬度main=", " latitude: " + lat + " longitude :" + lon);
                        Log.d("两点的距离", getDistance(oldLatLng, newLatLng) + "");
                        L.d("=====aMap=====" + aMap.getMyLocationStyle().getMyLocationType() + "");
//                        showShort("两点的距离" + getDistance(oldLatLng, newLatLng));
                        if (getDistance(oldLatLng, newLatLng) > 0 && getDistance(oldLatLng, newLatLng) < 100) {
                            if (checkJumpStatus.getLock_status() == 0) {
                            if (lon != 0.0) {
                                Log.d("上传=", "latitude:" + lat + "longitude" + lon);
                                setUpMap(oldLatLng, newLatLng);
                                new UserService(MainActivity.this).setLatLon(lat + "," + lon);
                                oldLatLng = newLatLng;
                                Message message = new Message();
                                message.what = 1;
                                doActionHandler.sendMessage(message);
                            }
                            } else {
                                oldLatLng = newLatLng;
                            }
                        } else {
                            oldLatLng = newLatLng;
                        }
                    }
                }
                Log.d("我在不停地定位Mainacvitity=", "latitude:" + lat + "longitude" + lon);

            }

            if (checkJumpStatus.getBike_status() == 0) {
                mLocationClient.stopLocation();
            }

            if (checkJumpStatus.getBike_status() == 4 && new UserService(MainActivity.this).getState().equals("0")) {
                mLocationClient.stopLocation();
            }

        }

    }

    //通过经纬度计算距离
    public double getDistance(LatLng start, LatLng end) {
        double lon1 = (Math.PI / 180) * start.longitude;
        double lon2 = (Math.PI / 180) * end.longitude;
        double lat1 = (Math.PI / 180) * start.latitude;
        double lat2 = (Math.PI / 180) * end.latitude;
        // 地球半径
        double R = 6371;
        // 两点间距离 km，如果想要米的话，结果*1000就可以了
        double d = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1)) * R;
        return d * 1000;
    }


    private LinearLayout head;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        ButterKnife.bind(this);
        mMapView.onCreate(savedInstanceState);// 此方法必须重写
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        headImg = (ImageView) headerView.findViewById(R.id.iv_header);
        name = (TextView) headerView.findViewById(R.id.tv_name);
        score = (TextView) headerView.findViewById(R.id.tv_score);
        countDownView = (TimeCountDownTextView) findViewById(R.id.countdown);
        countdown1 = (TextView) findViewById(R.id.countdown1);
        head = (LinearLayout) headerView.findViewById(R.id.head);
        isFirstLatLng = true;
        initview();
        initClickListener();
        UpdateInfo();
        String first = getIntent().getStringExtra("first");
        if (first != null) {
            if (!first.isEmpty() && first.equals("1")) {
                startActivity(ZhiDaoActivity.class);
            }
        }
//        initmap();
//        LatLng target = new LatLng(lon, lat);
//        initgetBikeMapList(target);


    }

    long recLen = 0;
    TimeCountDownTextView countDownView;
    TextView countdown1;
    long mHour, mMinute, mSecond;
    long maxTime = 600 * 1000;//设置倒计时的时长！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！

    private void initTimeDown() {


        final UserService service = new UserService(MainActivity.this);
        Date date = new Date();
        long time = date.getHours() * 3600 * 1000 + date.getMinutes() * 60000 + date.getSeconds() * 1000;

        long betweenTime = time - service.getBikeNumberTime(bike_number);
        L.d("锁 betweenTime" + betweenTime);
        if (betweenTime < 0) {
            betweenTime = 0 - betweenTime;
        }
        if (betweenTime > maxTime) {
            L.d("1上次记录的时间比现在多十分钟以上，不管锁的状态反正是正计时");
            if (service.getBikeNumberTime(bike_number) == 0) {
                recLen = 0;//不太可能出现的情况，除非清掉了sp，如果出现只能从0开始计时了
            } else {
                recLen = betweenTime - maxTime;
            }
            initTime();//开始正计时
        } else {//分两种情况
            if (checkJumpStatus != null) {
                if (checkJumpStatus.getLock_status() == 1) {//关的锁
                    betweenTime = maxTime - betweenTime;
                    L.d("2锁是锁住的，本次用车不是第一次显示，开始从 " + betweenTime + " 倒计时，倒计时结束开始正计时");
                    downTime(betweenTime);
                } else {//开的锁，开始正计时
                    L.d("3锁是打开的,这时候应该开始正计时，时间从记录的 " + recLen + " 开始，如果没记录，从现在0开始");
                    initTime();//开始正计时
                }
            }

        }

    }

    private boolean timeIsRun = false;

    private void downTime(long during_time) {
        countDownView.setVisibility(View.VISIBLE);
        countdown1.setVisibility(View.GONE);
        countDownView.setCountDownTimes(during_time);//10min
        countDownView.setOnCountDownFinishListener(new TimeCountDownTextView.onCountDownFinishListener() {
            @Override
            public void onFinish() {
                //倒计时结束
                initTime();//开始正计时
            }
        });
        countDownView.start();//开始倒计时
    }

    //用车计时
    private void initTime() {
        countDownView.setVisibility(View.GONE);
        countdown1.setVisibility(View.VISIBLE);

        if (!timeIsRun) {//保证正计时一秒一秒的加
            timeIsRun = true;
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    recLen = recLen + 1000;
                    mHour = recLen / (1000 * 60 * 60);
                    mMinute = recLen % (1000 * 60 * 60) / (1000 * 60);
                    mSecond = (recLen % (1000 * 60)) / 1000;

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            String min;
                            if (mMinute < 10) {
                                min = "0" + mMinute;
                            } else {
                                min = mMinute + "";
                            }

                            String sec;
                            if (mSecond < 10) {
                                sec = "0" + mSecond;
                            } else {
                                sec = "" + mSecond;
                            }
                            if (mHour == 0) {
                                countdown1.setText(Html.fromHtml(String.format("%1$s:%2$s", min, sec)));
                            } else {
                                countdown1.setText(Html.fromHtml(String.format("%1$s:%2$s:%3$s", mHour, min, sec)));
                            }
                            L.d(countdown1.getText().toString());
                        }
                    });

                }
            }, 1000, 1000);       // timeTask
        }
    }

    //更新界面
    private void initview() {
        iv_pull = (ImageView) findViewById(R.id.iv_pull);
        ll_detail = (LinearLayout) findViewById(R.id.ll_detail);
        RelativeLayout state_0 = (RelativeLayout) findViewById(R.id.state_0);
        finish_usecar = (TextView) findViewById(R.id.finish_usecar);
        RelativeLayout useing_biycle_lay = (RelativeLayout) findViewById(R.id.useing_biycle_lay);
        lengthBiycile.setText("0" + "米");
        kaluli.setText("0" + "卡");

        if (new UserService(MainActivity.this).getState().equals("1")) {
            state_0.setVisibility(View.GONE);
            useing_biycle_lay.setVisibility(View.VISIBLE);
            toolbar.setTitle("用车中");
            // init timer
            mTimer = new Timer();
            if (checkJumpStatus != null) {
                if (checkJumpStatus.getBike_status() == 1) {
                    showOneCar(checkJumpStatus.getBike_number());
                }
            }
            // start timer task
        } else if (new UserService(MainActivity.this).getState().equals("0")) {
            state_0.setVisibility(View.VISIBLE);
            useing_biycle_lay.setVisibility(View.GONE);
            toolbar.setTitle("校易行");
        }
        if (new UserService(MainActivity.this).getCookie().equals("0")) {
            name.setText("未登录");
            score.setText("");
            new UserService(MainActivity.this).setxinyongfen("0");
//            startActivity(RegisterActivity.class);
        } else {
            if (new UserService(MainActivity.this).getValidateUser().equals("1")) {
                if (null != v) {
                    if (v.getCode() == 1) {
                        name.setText(v.getName());
                        score.setText("信用分：" + v.getBody().getCredit_score() + "");
                        new UserService(MainActivity.this).setxinyongfen(v.getBody().getCredit_score() + "");
                    }
                }

            }
        }


    }


    /**
     * 绘制两个坐标点之间的线段,从以前位置到现在位置
     */
    private void setUpMap(LatLng oldData, LatLng newData) {

        // 绘制一个大地曲线
        aMap.addPolyline((new PolylineOptions())
                .add(oldData, newData)
                .geodesic(true).color(getResources().getColor(R.color.org)));
//        L.d("我在画线！！！！");
//        showShort("画线！！！！！！！");

    }

    /**
     * 实时上传位置
     */
    private Handler doActionHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int msgId = msg.what;
            switch (msgId) {
                case 1:
                    String url = Apis.Base + Apis.uploadLocation + lon + "," + lat + "&bike_number=" + bike_number;
                    String cookie = new UserService(MainActivity.this).getCookie();
                    OkHttpUtils
                            .get()//请求方式
                            .addHeader("cookie", cookie)
                            .url(url)//地址
                            .build()//创建请求
                            .execute(new StringCallback() {//回调
                                @Override
                                public void onError(Call call, Exception e, int id) {

                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    Log.d("实时上传", response);
                                    UploadLocation uploadLocation = gson.fromJson(response, UploadLocation.class);
                                    if (uploadLocation.getCode() == 1) {
//                                        showShort("上传成功");

                                        String distance = uploadLocation.getDistance();
                                        recLen = Long.parseLong(uploadLocation.getTime()) * 1000;
                                        distance = distance.substring(0, distance.indexOf("."));
                                        lengthBiycile.setText("" + distance + "米");
                                        kaluli.setText("" + uploadLocation.getCalories() + "卡");
                                        tvUse.setText("用车中" + checkJumpStatus.getBike_number() + "");
                                    } else {
                                        tvUse.setText("车已锁");
//                                        showShort("上传失败");
                                    }
                                }
                            });
                    break;
                default:
                    break;
            }
        }
    };

    //验证跳过登录
    private void initvalidateUser() {
        tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        DEVICE_ID = tm.getDeviceId();
        Log.d("DEVICE_ID", DEVICE_ID);
        Log.d("cookie", new UserService(MainActivity.this).getCookie());
        String url = Apis.Base +
                Apis.validateUser
                + DEVICE_ID;


        OkHttpUtils.get()
                .url(url)
                .addHeader("cookie", new UserService(MainActivity.this).getCookie())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.d("onError", e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("验证跳过登录response", response);
                        v = gson.fromJson(response, ValidateUser.class);
                        if (v.getCode() == 1) {
                            new UserService(MainActivity.this).setValidateUser("1");
                        } else {
                            new UserService(MainActivity.this).setValidateUser("0");
                            new UserService(MainActivity.this).setCookie("0");
                            new UserService(MainActivity.this).setState("0");
                            name.setText("未登录");
                            score.setText("");
                            startActivity(RegisterActivity.class);
                        }
                    }
                });

    }

    //初始化设置地图
    private void initmap() {
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        aMap.setInfoWindowAdapter(this);
        aMap.setOnInfoWindowClickListener(this);
        aMap.setOnCameraChangeListener(this);
        aMap.setOnMarkerClickListener(this);
        aMap.setOnMapClickListener(this);
        //定位
        aMap.setLocationSource(this);// 设置定位监听
        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        // 设置定位的类型为定位模式 ，可以由定位 LOCATION_TYPE_LOCATE、跟随 LOCATION_TYPE_MAP_FOLLOW 或地图根据面向方向旋转 LOCATION_TYPE_MAP_ROTATE
        iMainPresenter = new MainPresenterCompl(getBaseContext(), this);
        iMainPresenter.initLocation(aMap);
        iMainPresenter.initUISettings(aMap);
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE); //定位一次，且将视角移动到地图中心点。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(false);//设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.moveCamera(CameraUpdateFactory.zoomTo(16));//显示地图等级15级

    }


    //初始化连续定位地图
    private void lianxumap() {
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        aMap.setInfoWindowAdapter(this);
        aMap.setOnInfoWindowClickListener(this);
        aMap.setOnCameraChangeListener(this);
        aMap.setOnMarkerClickListener(this);
        aMap.setOnMapClickListener(this);
        //定位
        aMap.setLocationSource(this);// 设置定位监听
        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        // 设置定位的类型为定位模式 ，可以由定位 LOCATION_TYPE_LOCATE、跟随 LOCATION_TYPE_MAP_FOLLOW 或地图根据面向方向旋转 LOCATION_TYPE_MAP_ROTATE
        iMainPresenter = new MainPresenterCompl(getBaseContext(), this);
        iMainPresenter.initLocation(aMap);
        iMainPresenter.initUISettings(aMap);
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类
        myLocationStyle.interval(1000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);
        // 设置定位的类型为定位模式 ，可以由定位 LOCATION_TYPE_LOCATE、跟随 LOCATION_TYPE_MAP_FOLLOW 或地图根据面向方向旋转 LOCATION_TYPE_MAP_ROTATE
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(false);//设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
//        aMap.moveCamera(CameraUpdateFactory.zoomTo(17));//显示地图等级15级

        if (mLocationClient != null)
            mlocationClient.startLocation();

    }

    //获取周围单车位置列表
    private void initgetBikeMapList(LatLng target) {

        if (target.latitude != 0.0) {


            String url = Apis.Base +
                    "order/getBikeMapList?locations="
                    + target.longitude + "," + target.latitude;
            Log.d("经纬度=", target.longitude + "," + target.latitude + "   " + url);
            String cookie = new UserService(MainActivity.this).getCookie();
            OkHttpUtils.get()
                    .url(url)
                    .addHeader("cookie", cookie)

                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Log.d("获取周围单车位置列表response", response);
                            GetBikeMapList g = gson.fromJson(response, GetBikeMapList.class);
                            //清除地图中的mark点
                            AMap aMap = mMapView.getMap();
                            aMap.clear();
                            if (g.getCode() != 0) {
                                //循环添加自定义点mark
                                for (int i = 0; i < g.getBody().size(); i++) {
                                    LatLng latLng = new LatLng(g.getBody().get(i).getLat(), g.getBody().get(i).getLog());
                                    MarkerOptions markerOption = new MarkerOptions();
                                    markerOption.position(latLng);
                                    markerOption.title("自行车").snippet("自行车");
                                    markerOption.draggable(false);//设置Marker可拖动
                                    if (g.getBody().get(i).getColor().equals("yellow")) {
                                        markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                                                .decodeResource(getResources(), R.drawable.ico_yellow)));
                                    } else if (g.getBody().get(i).getColor().equals("green")) {
                                        markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                                                .decodeResource(getResources(), R.drawable.ico_green)));
                                    } else if (g.getBody().get(i).getColor().equals("blue")) {
                                        markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                                                .decodeResource(getResources(), R.drawable.ico_blue)));
                                    }
                                    // 将Marker设置为贴地显示，可以双指下拉地图查看效果
                                    markerOption.setFlat(true);//设置marker平贴地图效果
                                    markerOption.visible(true);
                                    Marker marker = aMap.addMarker(markerOption.position(latLng));
                                    marker.setObject(g.getBody().get(i));

                                }
                            }
                        }

                    });
        }
    }

    private RotateAnimation myAnimation_Rotate;

    //点击事件
    private void initClickListener() {
        //立即用车点击
        btnUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (new UserService(MainActivity.this).getCookie().equals("0")) {
                    startActivity(RegisterActivity.class);
                } else {
                    if (v != null) {
                        if (v.getCode() == 0) {
                            startActivity(RegisterActivity.class);
                        } else {
                            if (v.getVerify_status() == 0 || v.getVerify_status() == 3) {
                                startActivity(RealnameActivity.class);
                            } else if (v.getVerify_status() == 2) {
                                showShort("正在审核中，请稍后..");
                            } else {
//                                if (v.getDeposit_status() == 0) {
//                                    startActivity(DepositActivity.class);
//                                } else {
                                if (checkJumpStatus.getCode() == 1) {
                                    if (checkJumpStatus.getBike_status() == 4) {
                                        showOneCar(checkJumpStatus.getBike_number());
                                    } else {
                                        startActivity(UseBicycleActivity.class, "lat", lat + "", "lon", lon + "");
                                    }
                                }

//                                }
                            }
                        }

                    }
                }
            }
        });

        //用车状态下扫码
        saoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkJumpStatus != null) {
                    if (checkJumpStatus.getBike_status() == 1 || checkJumpStatus.getBike_status() == 2) {
                        if (!new UserService(MainActivity.this).getshowAlert().equals("0")) {
                            String location = lon + "," + lat;
                            Log.d("location===", location);
                            startActivity(ZxingActivity.class, "location", location, "status", "1");
                        } else {
                            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.msg_alert, null, false);
                            final Dialog dialog = new AlertDialog.Builder(MainActivity.this).setView(view).setCancelable(false).show();
                            TextView msg_txt = (TextView) view.findViewById(R.id.msg_txt);
                            TextView msg_btn_l = (TextView) view.findViewById(R.id.msg_btn_l);
                            TextView msg_btn_r = (TextView) view.findViewById(R.id.msg_btn_r);
                            final CheckBox cb_msg = (CheckBox) view.findViewById(R.id.cb_msg);
                            msg_txt.setText("结束用车前可多次开关锁，车辆回归原处才能结束用车，由用户不当操作造成的财产损失将追究法律责任。");
                            msg_btn_l.setText("返回");
                            msg_btn_r.setText("确定");
                            msg_btn_l.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (dialog.isShowing()) {
                                        dialog.dismiss();
                                    }
                                }
                            });
                            msg_btn_r.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (dialog.isShowing()) {
                                        dialog.dismiss();
                                    }
                                    String location = lon + "," + lat;
                                    Log.d("location===", location);
                                    startActivity(ZxingActivity.class, "location", location, "status", "1");
                                }
                            });
                            cb_msg.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (cb_msg.isChecked()) {
                                        new UserService(MainActivity.this).setshowAlert("1");
                                    } else {
                                        new UserService(MainActivity.this).setshowAlert("0");
                                    }
                                }
                            });
                        }
                    } else {
                        String location = lon + "," + lat;
                        Log.d("location===", location);
                        startActivity(ZxingActivity.class, "location", location, "status", "1");
                    }
                }


            }
        });

        //侧边栏头像部分点击
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (new UserService(MainActivity.this).getCookie().equals("0")) {
                    startActivity(RegisterActivity.class);
                } else {
                    startActivity(User_Activity.class);
                }

            }
        });

        //未用车状态下刷新
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAnimation_Rotate = new RotateAnimation(0, 720,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
                myAnimation_Rotate.setDuration(500);
                myAnimation_Rotate.setFillEnabled(true);
                myAnimation_Rotate.setFillAfter(true);
                floatingActionButton.startAnimation(myAnimation_Rotate);
                L.d("未用车状态下刷新" + zhonglat + "纬度", zhonglon + "经度");
                LatLng latLng = new LatLng(zhonglat, zhonglon);
                initgetBikeMapList(latLng);
            }
        });


        dinwgei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //重新定位并重新请求当前位置周边车辆信息
                new UserService(MainActivity.this).setShowOneMark("0");
                cameraUpdate = CameraUpdateFactory
                        .newCameraPosition(new CameraPosition(new LatLng(lat, lon), 16, 0, 0));
                if (aMap != null) aMap.moveCamera(cameraUpdate);
            }
        });

        //未用车状态下扫码
        fabQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (new UserService(MainActivity.this).getCookie().equals("0")) {
                    startActivity(RegisterActivity.class);
                } else {
                    if (v != null) {
                        if (v.getCode() == 0) {
                            startActivity(RegisterActivity.class);
                        } else {
                            if (v.getVerify_status() == 0 || v.getVerify_status() == 3) {
                                startActivity(RealnameActivity.class);
                            } else if (v.getVerify_status() == 2) {
                                showShort("正在审核中，请稍后..");
                            } else {
//                                if (v.getDeposit_status() == 0) {
//                                    startActivity(DepositActivity.class);
//                                } else {
                                String location = lon + "," + lat;
                                Log.d("location===", location);
                                if (checkJumpStatus != null) {
                                    if (checkJumpStatus.getBike_status() == 0) {
                                        startActivity(ZxingActivity.class, "location", location, "status", "0");
                                    } else {
                                        startActivity(ZxingActivity.class, "location", location, "status", "1");
                                    }
                                }
//                                }
                            }
                        }
                    }

                }
            }
        });
        //用车点击隐藏
        iv_pull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ll_detail.getVisibility() == View.GONE) {
                    ll_detail.setVisibility(View.VISIBLE);
                } else {
                    ll_detail.setVisibility(View.GONE);
                }
            }
        });
        //用车状态下结束用车
        finish_usecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserService s = new UserService(MainActivity.this);
                if (checkJumpStatus != null) {
                    if (s.getAlert().equals("0")) {
                        showTips();     //出弹窗吧
                    } else {
                        //已经勾过不再提示，直接跳
//                    mTimer.cancel();

                        if (checkJumpStatus.getBike_status() == 1) {
                            //日租
                            bike_number = checkJumpStatus.getBike_number();
                            startActivity(LockcloseActivity.class, "bike_number", bike_number, "location",
                                    lon + "," + lat, "status", "1");
                        } else if (checkJumpStatus.getBike_status() == 2) {
                            //时租
                            bike_number = checkJumpStatus.getBike_number();
                            startActivity(LockcloseActivity.class, "bike_number", bike_number, "location",
                                    lon + "," + lat, "status", "2");
                        } else if (checkJumpStatus.getBike_status() == 4) {
                            //长租
                            String url = Apis.Base + Apis.overLongLeaseBike;
                            OkHttpUtils
                                    .post()
                                    .url(url)
                                    .addHeader("cookie", new UserService(MainActivity.this).getCookie())
                                    .addParams("bike_number", checkJumpStatus.getBike_number())
                                    .build()
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onError(Call call, Exception e, int id) {

                                        }

                                        @Override
                                        public void onResponse(String response, int id) {
                                            Log.d("response提示锁状态", response);
                                            Lockstatus lockstatus = gson.fromJson(response, Lockstatus.class);
                                            if (lockstatus.getCode() == 1) {
                                                showTips();
                                            } else {
                                                showShort(lockstatus.getMsg());
                                            }
                                        }
                                    });
                        }
                    }
                }
            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 创建退出对话框
            android.app.AlertDialog isExit = new android.app.AlertDialog.Builder(this).create();
            // 设置对话框标题
            isExit.setTitle("系统提示");
            // 设置对话框消息
            isExit.setMessage("确定要退出吗");
            // 添加选择按钮并注册监听
            isExit.setButton("确定", listener);
            isExit.setButton2("取消", listener);
            // 显示对话框
            isExit.show();

        }

        return false;

    }

    /**
     * 监听对话框里面的button点击事件
     */
    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case android.app.AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序
                    new UserService(MainActivity.this).setState("0");
                    finish();
                    break;
                case android.app.AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框
                    break;
                default:
                    break;
            }
        }
    };

    //显示停车提示
    private void showTips() {
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.msg_lockclose_alert, null, false);
        final Dialog dialog = new AlertDialog.Builder(MainActivity.this).setView(view).setCancelable(false).show();
        TextView msg_txt = (TextView) view.findViewById(R.id.msg_txt);
        TextView msg_btn_share = (TextView) view.findViewById(R.id.msg_btn_share);
        TextView msg_btn_over = (TextView) view.findViewById(R.id.msg_btn_over);
        ImageView close_showtips = (ImageView) view.findViewById(R.id.close_showtips);
        if (checkJumpStatus != null) {
            if (checkJumpStatus.getBike_status() == 4) {
                close_showtips.setVisibility(View.VISIBLE);
                msg_btn_over.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = Apis.Base + Apis.overLongLeaseBike;
                        OkHttpUtils
                                .post()
                                .url(url)
                                .addHeader("cookie", new UserService(MainActivity.this).getCookie())
                                .addParams("bike_number", checkJumpStatus.getBike_number())
                                .build()
                                .execute(new StringCallback() {
                                    @Override
                                    public void onError(Call call, Exception e, int id) {

                                    }

                                    @Override
                                    public void onResponse(String response, int id) {
                                        Log.d("response提示锁状态", response);
//
                                        Lockstatus lockstatus = gson.fromJson(response, Lockstatus.class);
                                        if (lockstatus.getCode() == 1) {
                                            new UserService(MainActivity.this).setState("0");
                                            initview();
                                            LatLng latLng = new LatLng(lon, lat);
                                            initgetBikeMapList(latLng);
                                            if (dialog.isShowing()) dialog.dismiss();
                                        } else {
                                            showShort(lockstatus.getMsg());
                                        }
                                    }
                                });
                    }
                });
                //关闭弹窗
                close_showtips.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }
                    }
                });

                msg_btn_share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = Apis.Base + Apis.overLongLeaseBike;
                        OkHttpUtils
                                .post()
                                .url(url)
                                .addHeader("cookie", new UserService(MainActivity.this).getCookie())
                                .addParams("bike_number", checkJumpStatus.getBike_number())
                                .build()
                                .execute(new StringCallback() {
                                    @Override
                                    public void onError(Call call, Exception e, int id) {

                                    }

                                    @Override
                                    public void onResponse(String response, int id) {
                                        Log.d("response提示锁状态", response);
                                        Lockstatus lockstatus = gson.fromJson(response, Lockstatus.class);
                                        if (lockstatus.getCode() == 1) {
                                            if (lockstatus.getLock_status() == 1) {
                                                String cookie;
                                                cookie = new UserService(MainActivity.this).getCookie();
                                                String url = Apis.Base + Apis.sharedBikeList;
                                                format = new SimpleDateFormat("yyyy-MM-dd");
                                                OkHttpUtils
                                                        .post()
                                                        .url(url)
                                                        .addHeader("cookie", cookie)
                                                        .build()
                                                        .execute(new StringCallback() {
                                                            @Override
                                                            public void onError(Call call, Exception e, int id) {
                                                            }

                                                            @Override
                                                            public void onResponse(String response, int id) {
                                                                L.d(response);
                                                                SharedBikeList d = gson.fromJson(response, SharedBikeList.class);
                                                                if (d.getCode() == 1) {
                                                                    unList.clear();
                                                                    canList.clear();
                                                                    showAlertshare(d.getBody());
                                                                    if (dialog.isShowing())
                                                                        dialog.dismiss();

                                                                } else {
                                                                    showShort(d.getMsg());
                                                                }

                                                            }
                                                        });
                                            }
                                        } else {
                                            showShort("请上锁！");
                                        }
                                    }
                                });
                    }
                });
            } else {

                msg_txt.setText("车辆归回原处才可结束用车，由用户不当操作造成的财产损失将追究法律责任。");
                msg_btn_share.setText("确定");
                msg_btn_over.setText("返回");
                close_showtips.setVisibility(View.GONE);
                //确定点击
                msg_btn_share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bike_number = checkJumpStatus.getBike_number();
                        if (checkJumpStatus.getBike_status() == 1) {
                            startActivity(LockcloseActivity.class, "bike_number", bike_number, "location",
                                    lon + "," + lat, "status", "1");
                        } else {
                            startActivity(LockcloseActivity.class, "bike_number", bike_number, "location",
                                    lon + "," + lat, "status", "2");
                        }

                        if (dialog.isShowing()) dialog.dismiss();
                    }
                });

                msg_btn_over.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (dialog.isShowing()) dialog.dismiss();
                    }
                });
            }
        }
    }

    List<Date> unList = new ArrayList<>();
    List<SharedBikeList.BodyBean> canList = new ArrayList<>();
    private CheckBox cal_cb;
    private AlertDialog dialog;

    private void setClickCalendar() {
        myCalendar.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(MaterialCalendarView widget, final CalendarDay date, boolean selected) {
                if (selected) {
                    myCalendar.setDateSelected(date, true);
                    if (unList.size() > 0) {
                        for (int i = 0; i < unList.size(); i++) {
                            if (unList.get(i).getTime() == date.getDate().getTime()) {
                                myCalendar.setDateSelected(date, false);
                                showShort("不可取消");
                            }
                        }
                    }
                } else {
                    int q = 0;

                    if (canList.size() > 0) {


                        for (int i = 0; i < canList.size(); i++) {

                            try {
                                if (format.parse(canList.get(i).getStart_time()).getTime() == date.getDate().getTime()) {
                                    q = 1;
                                    String cookie;
                                    cookie = new UserService(MainActivity.this).getCookie();
                                    OkHttpUtils.post()
                                            .url(Apis.Base + Apis.CancelShareMyBike)
                                            .addParams("sids", canList.get(i).getSid() + "")
                                            .addHeader("cookie", cookie).build().execute(new StringCallback() {
                                        @Override
                                        public void onError(Call call, Exception e, int id) {

                                        }

                                        @Override
                                        public void onResponse(String response, int id) {
                                            BaseResult result = gson.fromJson(response, BaseResult.class);
                                            if (result.getCode() == 1) {
                                                myCalendar.setDateSelected(date, false);
                                                showShort(result.getMsg());
                                            } else {
                                                showShort(result.getMsg());
                                            }
                                        }
                                    });

                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                    if (q == 0)
                        myCalendar.setDateSelected(date, false);
                }

            }
        });
    }

    private void showAlertshare(List<SharedBikeList.BodyBean> list) {

        View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.mybiycle_calendar, null, false);
        dialog = new AlertDialog.Builder(MainActivity.this).setView(view).setCancelable(false).show();
        TextView tv_okmain = (TextView) view.findViewById(R.id.tv_ok);
        TextView tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
        cal_cb = (CheckBox) view.findViewById(R.id.cal_cb);
        dialog.setCancelable(true);
        myCalendar = (MaterialCalendarView) view.findViewById(R.id.calendar_md);
        myCalendarInit();//初始化日历

        if (list != null) {
            for (int j = 0; j < list.size(); j++) {
                try {
                    Date date = format.parse(list.get(j).getStart_time());
                    if (list.get(j).getLease_status() == 0) {
                        canList.add(list.get(j));
                        myCalendar.setDateSelected(date, true);
                    } else {
                        unList.add(date);
                        myCalendar.addDecorators(new MySelectorDecorators(MainActivity.this),
                                new SelectDecorator(MainActivity.this, date));
                    }


                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        setClickCalendar();

        tv_okmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cal_cb.isChecked()) {
                    List<CalendarDay> selectedDates = myCalendar.getSelectedDates();

                    String s = "";
                    if (selectedDates.size() > 0) {
                        for (int i = 0; i < selectedDates.size(); i++) {
                            String format = new SimpleDateFormat("yyyy-MM-dd").format(selectedDates.get(i).getDate());
                            Log.d(TAG, "onClick: " + format);
                            if (selectedDates.size() == i + 1) {
                                s = s + format;
                            } else {
                                s = s + format + ",";
                            }
                        }

                        Log.d(TAG, s);
                        String cookie;
                        cookie = new UserService(MainActivity.this).getCookie();
//                        Map<String, String> map = new HashMap<>();
//                        map.put("share_date", s);
                        OkHttpUtils.post()
                                .addHeader("cookie", cookie)
                                .addParams("share_date", s)
                                .url(Apis.Base + Apis.StartShareMyBike)
                                .build()
                                .execute(new StringCallback() {
                                    @Override
                                    public void onError(Call call, Exception e, int id) {

                                    }

                                    @Override
                                    public void onResponse(String response, int id) {
                                        Log.d(TAG, "onResponse: " + response);
                                        BaseResult result = (new Gson()).fromJson(response, BaseResult.class);
                                        if (result.getCode() == 1) {
                                            showShort(result.getMsg());
                                            if (dialog.isShowing()) dialog.dismiss();
                                            checkJumpStatus();
                                        } else {
                                            showShort(result.getMsg());
                                        }
                                    }
                                });
                    } else {
                        showShort("请选择日期");
                    }
                } else {
                    new AlertDialog.Builder(MainActivity.this).setTitle("提示").setMessage("请仔细阅读共享协议，如果同意请勾选以继续")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).show();
                }


            }
        });

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing()) dialog.dismiss();
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        new UserService(MainActivity.this).setShowOneMark("0");
        mMapView.onDestroy();
//        Intent stopIntent = new Intent(this, LocationService.class);
//        stopService(stopIntent);
//        unregisterReceiver(receiver);
        deactivate();
//        mTimer.cancel();
    }

    private static final String BroadcastAction = "com.example.broadcast";
    /**
     * 创建 BroadcastReceiver
     */
//    String str;
//    BroadcastReceiver receiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            if (BroadcastAction.equals(intent.getAction())) {
//                Log.i("FSD", "get the broadcast from Service...");
//                str = intent.getStringExtra("Str");
//                Log.i("FSD", str);
//                mHandler.sendMessage(mHandler.obtainMessage());
//                lon = Double.parseDouble(str.substring(str.indexOf(",") + 1));
//                lat = Double.parseDouble(str.substring(0, str.indexOf(",")));
//                Log.d("定位获得的经纬度service=", " latitude: " + lat + " longitude :" + lon);
//                if (new UserService(MainActivity.this).getState().equals("1")) {
//                    LatLng newLatLng = new LatLng(lat, lon);
//                    if (isFirstLatLng) {
//                        //记录第一次的定位信息
//                        oldLatLng = newLatLng;
//                        isFirstLatLng = false;
//                    }
//
//                    //位置有变化
//                    if (newLatLng != null && oldLatLng != null && oldLatLng != newLatLng) {
//                        Log.d("定位获得的经纬度qingqiu=", " latitude: " + lat + " longitude :" + lon);
//                        cameraUpdate = CameraUpdateFactory
//                                .newCameraPosition(new CameraPosition(new LatLng(lat, lon), 15, 0, 0));
//                        aMap.moveCamera(cameraUpdate);
//                        if (getDistance(oldLatLng, newLatLng) > 0 && getDistance(oldLatLng, newLatLng) < 1000) {
////                            if (checkJumpStatus.getLock_status() == 0) {
//                                if (lon != 0.0) {
//                                    setUpMap(oldLatLng, newLatLng);
//                                    new UserService(MainActivity.this).setLatLon(lat + "," + lon);
//                                    oldLatLng = newLatLng;
//                                    Message message = new Message();
//                                    message.what = 1;
//                                    doActionHandler.sendMessage(message);
////                                }
//                            }
//                        }
//                    }
//                }
//
//            } else {
//                Log.i("FSD", "the action is not intent.getAction");
//            }
//        }
//    };

    /**
     * 处理 广播接收到的数据
     */
    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @SuppressLint("HandlerLeak")
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };

    /**
     * 停止定位
     */
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();

        if (new UserService(MainActivity.this).getCookie().equals("0")) {

        } else {
            initvalidateUser();

        }
        checkJumpStatus();
        initview();
        BindPushUtils.bind(getBaseContext());//保存绑定推送
//        IntentFilter filter = new IntentFilter();
//        filter.addAction(BroadcastAction);
//        registerReceiver(receiver, filter);
//
//        Intent startIntent = new Intent(this, LocationService.class);
//        startService(startIntent);
        if (aMap != null) {
            cameraUpdate = CameraUpdateFactory
                    .newCameraPosition(new CameraPosition(new LatLng(lat, lon), 16, 0, 0));
            aMap.moveCamera(cameraUpdate);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    QueryBikeListByDate queryBikeListByDate;
    String bikenum;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        L.d("onnewIntent");
        queryBikeListByDate = new QueryBikeListByDate();
        bikenum = intent.getStringExtra("bike_number");

//        if (bikenum == null) {
//
//        } else {
//            if (!bikenum.isEmpty()) {
//                showOneCar(bikenum);
//            }
//        }
    }

    boolean isWifi = false;
    private DownloadManager downloadManager;
    private UpDate upDate;

    //更新apk 相关
    private void UpdateInfo() {
        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        isWifi = NetworkInfo.State.CONNECTED == ((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE)).getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        try {
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            final PackageInfo packInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            //获取到versionNum 用vName接收, downloadUrl = http://file.cishan123.org/yst_1.7.apk

            OkHttpUtils.get().url(Apis.Base + Apis.UpDate).build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {


                }

                @Override
                public void onResponse(String response, int id) {
                    upDate = (new Gson()).fromJson(response, UpDate.class);
                    if (upDate.getCode() == 1) {
                        String downloadUrl = Apis.Basedown + upDate.getBody().getFile_url();
//                        L.d("下载地址==",downloadUrl);
                        if (!packInfo.versionName.equals(upDate.getBody().getVersion())) {
                            download(downloadUrl, upDate.getBody().getVersion());
                        }

                    } else {
                        showShort(upDate.getMsg());
                    }


                }
            });

//            packInfo.versionName;
            //下面执行网络操作访问接口的目前最新版apk版本信息，如我这里https://api.cishan123.org/v2.2/api/AutoUpdate/UpdateInfoNew?type=yst&version=1.2


        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    //更新apk 相关
    public void download(String downloadUrl, String vName) {
        if (isWifi) {
            //wifi下自动下载最新版本，检测目录下是否已经下载好
            String SDPATH = Environment.getExternalStorageDirectory().getPath() + "/xyx/校易行" + vName + ".apk";//重命名，用来判断下载过没
            if (new File(SDPATH).exists()) {
                //安装SDPATH的文件
                InatallDialog(SDPATH);
            } else {
                Toast.makeText(this, "正在后台下载，请稍后...", Toast.LENGTH_SHORT).show();
                DownLoadAPK.downloadAPK(downloadManager, downloadUrl, "校易行" + vName, "");
            }
        } else {
            myDialog(downloadManager, downloadUrl, vName);
        }
    }

    //更新apk 相关
    public void InatallDialog(final String SDPATH) {
        new AlertDialog.Builder(this).setTitle("新版本提醒")//对话框标题
                .setMessage("已下载完成最新版本，是否现在安装？")//对话框提示正文
                .setIcon(R.mipmap.ic_launcher)//对话框标题上的图片
                .setNegativeButton("暂不升级", new DialogInterface.OnClickListener() {
                    @Override//取消按钮
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "请尽快更新", Toast.LENGTH_SHORT).show();
                    }
                }).setPositiveButton("立即安装", new DialogInterface.OnClickListener() {
            @Override//确定按钮
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse("file://" + SDPATH), "application/vnd.android.package-archive");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }).setCancelable(false)//点击其他区域关闭对话框
                .show();
    }

    //更新apk 相关
    public void myDialog(final DownloadManager downloadManager, final String url, final String vName) {
        new AlertDialog.Builder(this).setTitle("新版本提醒")//对话框标题
                .setMessage(upDate.getBody().getContent())//对话框提示正文
                .setIcon(R.mipmap.ic_launcher)//对话框标题上的图片
                .setNegativeButton("暂不升级", new DialogInterface.OnClickListener() {
                    @Override//取消按钮
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "请尽快更新", Toast.LENGTH_SHORT).show();
                    }
                }).setPositiveButton("立即升级", new DialogInterface.OnClickListener() {
            @Override//确定按钮
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "正在后台下载，请稍后...", Toast.LENGTH_SHORT).show();
                DownLoadAPK.downloadAPK(downloadManager, url, "校易行" + vName, "");
            }
        }).setCancelable(false)//点击其他区域关闭对话框
                .show();
    }

    CheckJumpStatus checkJumpStatus;


    //跳转状态
    private void checkJumpStatus() {

        String url = Apis.Base + Apis.checkJumpStatus;
        String cookie = new UserService(MainActivity.this).getCookie();
        L.d(cookie);
        OkHttpUtils
                .get()
                .url(url)
                .addHeader("cookie", cookie)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public String parseNetworkResponse(Response response, int id) throws IOException {
//                        L.d("code===测试",response.code()+"");
                        if (response.code() == 401) {
                            new UserService(MainActivity.this).setCookie("0");
                        }

                        return super.parseNetworkResponse(response, id);
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("response跳转状态", response);
                        checkJumpStatus = gson.fromJson(response, CheckJumpStatus.class);
                        if (new UserService(MainActivity.this).getCookie().equals("0")) {
                            AMap aMap = mMapView.getMap();
                            initmap();
                            aMap.clear();
                            initview();
                            LatLng target = new LatLng(lat, lon);
                            initgetBikeMapList(target);
                        } else {
                            if (checkJumpStatus.getBike_status() == 0) {
                                AMap aMap = mMapView.getMap();
                                aMap.clear();
                                isbiycle = false;
                                new UserService(MainActivity.this).setState("0");
                                new UserService(MainActivity.this).setisgetbiycle("0");
                                //未租车状态
                                if (new UserService(MainActivity.this).getShowOneMark().equals("1")) {
                                    initmap();
                                    initview();
                                    if (bikenum == null) {
                                        initmap();
                                        aMap.clear();
                                        initview();
                                        LatLng target = new LatLng(lat, lon);
                                        initgetBikeMapList(target);
                                    } else {
                                        if (!bikenum.isEmpty()) {
                                            showOneCar(bikenum);
                                        }
                                    }
                                } else {
                                    initmap();
                                    aMap.clear();
                                    initview();
                                    LatLng target = new LatLng(lat, lon);
                                    initgetBikeMapList(target);
                                }
//                            mLocationClient.startLocation();
                            } else if (checkJumpStatus.getBike_status() == 1) {
                                new UserService(MainActivity.this).setShowOneMark("1");
                                new UserService(MainActivity.this).setState("1");
                                new UserService(MainActivity.this).setisgetbiycle("1");
                                bike_number = checkJumpStatus.getBike_number();
                                //日租中
                                isbiycle = true;
                                tvUse.setText("用车中" + checkJumpStatus.getBike_number() + "");
                                initview();
                                lianxumap();
                                showOneCar(checkJumpStatus.getBody().get(0).getNumber());
                                countdown.setVisibility(View.GONE);
                                countdown1.setVisibility(View.GONE);

                            } else if (checkJumpStatus.getBike_status() == 2) {

                                isbiycle = true;
                                showOneCar(checkJumpStatus.getBody().get(0).getNumber());
                                new UserService(MainActivity.this).setShowOneMark("1");
                                new UserService(MainActivity.this).setState("1");
                                new UserService(MainActivity.this).setisgetbiycle("1");
                                bike_number = checkJumpStatus.getBike_number();
                                lianxumap();
                                tvUse.setText("用车中" + checkJumpStatus.getBike_number() + "");
                                countdown.setVisibility(View.GONE);
                                countdown1.setVisibility(View.GONE);
                                //时租中
                                initview();
                                if ((new UserService(MainActivity.this).getBikeNumber()).equals(bike_number)) {
                                    L.d("锁  又是这个车");
                                } else {
                                    new UserService(MainActivity.this).setBikeNumber(bike_number);
                                    L.d("锁  新车是 " + bike_number);
                                }

                                initTimeDown();
                            } else if (checkJumpStatus.getBike_status() == 3) {
                                AMap aMap = mMapView.getMap();
                                aMap.clear();
                                new UserService(MainActivity.this).setisgetbiycle("0");
                                //时租付款
                                initmap();
                                startActivity(OverPayActivity.class);
                            } else if (checkJumpStatus.getBike_status() == 4) {
                                new UserService(MainActivity.this).setisgetbiycle("1");
                                bike_number = checkJumpStatus.getBike_number();
                                countdown.setVisibility(View.GONE);
                                countdown1.setVisibility(View.GONE);
                                if (checkJumpStatus.getLock_status() == 1) {
                                    AMap aMap = mMapView.getMap();
                                    aMap.clear();
                                    initmap();
                                    new UserService(MainActivity.this).setState("0");
                                } else {
                                    if (checkJumpStatus.getIsshare() == 1) {
                                        //已经租出去了
                                    } else {
                                        tvUse.setText("用车中" + checkJumpStatus.getBike_number() + "");
                                        lianxumap();
                                        new UserService(MainActivity.this).setState("1");
                                        showOneCar(checkJumpStatus.getBike_number());
                                    }

                                }
                                initview();
                            }
                        }
                    }
                });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
        deactivate();
    }

    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(this);
            AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            mLocationOption.setOnceLocation(false);
            /*
             * 设置是否优先返回GPS定位结果，如果30秒内GPS没有返回定位结果则进行网络定位
             * 注意：只有在高精度模式下的单次定位有效，其他方式无效
             */
            mLocationOption.setGpsFirst(true);
            // 设置发送定位请求的时间间隔,最小值为1000ms,10秒更新一次定位信息
            mLocationOption.setInterval(10000);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();
        }
        mlocationClient.startLocation();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override//侧边栏
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.my_bicycle) {
            if (new UserService(MainActivity.this).getCookie().equals("0")) {
                startActivity(RegisterActivity.class);
            } else {
                if (v != null) {
                    if (v.getCode() == 0) {
                        startActivity(RegisterActivity.class);
                    } else {
                        if (v.getVerify_status() == 0 || v.getVerify_status() == 3) {
                            startActivity(RealnameActivity.class);
                        } else if (v.getVerify_status() == 2) {
                            showShort("正在审核中，请稍后...");
                        } else {
//                            if (v.getDeposit_status() == 0) {
//                                startActivity(DepositActivity.class);
//                            } else {
                            startActivity(MyBicycleActivity.class);
//                            }
                        }
                    }
                }
            }

        } else if (id == R.id.my_wallet) {
            if (new UserService(MainActivity.this).getCookie().equals("0")) {
                startActivity(RegisterActivity.class);
            } else {


                if (v != null) {
                    if (v.getCode() == 0) {
                        startActivity(RegisterActivity.class);
                    } else {

                        if (v.getVerify_status() == 0 || v.getVerify_status() == 3) {
                            startActivity(RealnameActivity.class);
                        } else if (v.getVerify_status() == 2) {
                            showShort("正在审核中，请稍后..");
                        } else {
//                            if (v.getDeposit_status() == 0) {
//                                startActivity(DepositActivity.class);
//                            } else {
                            startActivity(Mywallet_activity.class);
//                            }
                        }
//                        if (v.getVerify_status() == 0) {
//                            startActivity(RealnameActivity.class);
//                        } else {
//                            if (v.getDeposit_status() == 0) {
//                                startActivity(DepositActivity.class);
//                            } else {
//                                startActivity(Mywallet_activity.class);
//                            }
//                        }
                    }
                }
            }


        } else if (id == R.id.my_invitation) {
            if (new UserService(MainActivity.this).getCookie().equals("0")) {
                startActivity(RegisterActivity.class);
            } else {


                if (v != null) {
                    if (v.getCode() == 0) {
                        startActivity(RegisterActivity.class);
                    } else {

                        if (v.getVerify_status() == 0 || v.getVerify_status() == 3) {
                            startActivity(RealnameActivity.class);
                        } else if (v.getVerify_status() == 2) {
                            showShort("正在审核中，清稍后..");
                        } else {
//                            if (v.getDeposit_status() == 0) {
//                                startActivity(DepositActivity.class);
//                            } else {
                            startActivity(IvfriendsActivity.class, "num", v.getBody().getPhone());
//                            }
                        }
//                        if (v.getVerify_status() == 0) {
//                            startActivity(RealnameActivity.class);
//                        } else {
//                            if (v.getDeposit_status() == 0) {
//                                startActivity(DepositActivity.class);
//                            } else {
//                                startActivity(IvfriendsActivity.class);
//                            }
//                        }
                    }
                }

            }

        } else if (id == R.id.my_fault) {
            if (new UserService(MainActivity.this).getCookie().equals("0")) {
                startActivity(RegisterActivity.class);
            } else {

                if (v != null) {
                    if (v.getCode() == 0) {
                        startActivity(RegisterActivity.class);
                    } else {

                        if (v.getVerify_status() == 0 || v.getVerify_status() == 3) {
                            startActivity(RealnameActivity.class);
                        } else if (v.getVerify_status() == 2) {
                            showShort("正在审核中，清稍后..");
                        } else {
//                            if (v.getDeposit_status() == 0) {
//                                startActivity(DepositActivity.class);
//                            } else {
                            startActivity(FaultActivity.class);
//                            }
                        }
//                        if (v.getVerify_status() == 0) {
//                            startActivity(RealnameActivity.class);
//                        } else {
//                            if (v.getDeposit_status() == 0) {
//                                startActivity(DepositActivity.class);
//                            } else {
//                                startActivity(FaultActivity.class);
//                            }
//                        }
                    }
                }
            }


        } else if (id == R.id.my_tel) {
            if (new UserService(MainActivity.this).getCookie().equals("0")) {
                startActivity(RegisterActivity.class);
            } else {

                if (v != null) {
                    if (v.getCode() == 0) {
                        startActivity(RegisterActivity.class);
                    } else {
//                        if (v.getVerify_status() == 0 || v.getVerify_status() == 3) {
//                            startActivity(RealnameActivity.class);
//                        } else if (v.getVerify_status() == 2) {
//                            showShort("正在审核中，清稍后..");
//                        } else {
//                            if (v.getDeposit_status() == 0) {
//                                startActivity(DepositActivity.class);
//                            } else {
                        call("0535-2105657");
//                            }
//                        }

//                        if (v.getVerify_status() == 0) {
//                            startActivity(RealnameActivity.class);
//                        } else {
//                            if (v.getDeposit_status() == 0) {
//                                startActivity(DepositActivity.class);
//                            } else {
//                                call("0535-2105657");
//                            }
//                        }
                    }
                }
            }

        } else if (id == R.id.my_news) {
            if (new UserService(MainActivity.this).getCookie().equals("0")) {
                startActivity(RegisterActivity.class);
            } else {


                if (v != null) {
                    if (v.getCode() == 0) {
                        startActivity(RegisterActivity.class);
                    } else {

                        if (v.getVerify_status() == 0 || v.getVerify_status() == 3) {
                            startActivity(RealnameActivity.class);
                        } else if (v.getVerify_status() == 2) {
                            showShort("正在审核中，清稍后..");
                        } else {
//                            if (v.getDeposit_status() == 0) {
//                                startActivity(DepositActivity.class);
//                            } else {
                            startActivity(InformationActivity.class);
//                            }
                        }
//                        if (v.getVerify_status() == 0) {
//                            startActivity(RealnameActivity.class);
//                        } else {
//                            if (v.getDeposit_status() == 0) {
//                                startActivity(DepositActivity.class);
//                            } else {
//                                startActivity(InformationActivity.class);
//                            }
//                        }
                    }
                }

            }

        } else if (id == R.id.my_set) {
            if (new UserService(MainActivity.this).getCookie().equals("0")) {
                startActivity(RegisterActivity.class);
            } else {

                if (v != null) {
                    if (v.getCode() == 0) {
                        startActivity(RegisterActivity.class);
                    } else {


//                        if (v.getVerify_status() == 0 || v.getVerify_status() == 3) {
//                            startActivity(RealnameActivity.class);
//                        } else if (v.getVerify_status() == 2) {
//                            showShort("正在审核中，清稍后..");
//                        } else {
//                            if (v.getDeposit_status() == 0) {
//                                startActivity(DepositActivity.class);
//                            } else {
                        startActivity(Setup_Activity.class);
//                            }
//                        }
//                        if (v.getVerify_status() == 0) {
//                            startActivity(RealnameActivity.class);
//                        } else {
//                            if (v.getDeposit_status() == 0) {
//                                startActivity(DepositActivity.class);
//                            } else {
//                                startActivity(Setup_Activity.class);
//                            }
//                        }
                    }
                }
            }


        } else if (id == R.id.my_kaquan) {
            if (new UserService(MainActivity.this).getCookie().equals("0")) {
                startActivity(RegisterActivity.class);
            } else {

                if (v != null) {
                    if (v.getCode() == 0) {
                        startActivity(RegisterActivity.class);
                    } else {
                        if (v.getVerify_status() == 0 || v.getVerify_status() == 2) {
                            startActivity(RealnameActivity.class);
                        } else {
//                            if (v.getDeposit_status() == 0) {
//                                startActivity(DepositActivity.class);
//                            } else {
                            startActivity(Mycoupon_Activity.class);
//                            }
                        }
                    }
                }
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 调用拨号界面
     *
     * @param phone 电话号码
     */
    private void call(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    View infoWindow = null;

    private CameraUpdate cameraUpdate;

    //显示选中车辆点
    private void showOneCar(final String bike_number) {
        new UserService(MainActivity.this).setShowOneMark("1");
        String url = Apis.Base + Apis.checkBikeByNumber;
        String cookie = new UserService(MainActivity.this).getCookie();
        OkHttpUtils
                .post()
                .url(url)
                .addHeader("cookie", cookie)
                .addParams("bike_number", bike_number)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        L.d(response);
                        GetBikeMapList getBikeMapList = gson.fromJson(response, GetBikeMapList.class);
                        if (new UserService(MainActivity.this).getState().equals("1") && checkJumpStatus.getBike_status() != 4) {
                            AMap aMap = mMapView.getMap();
                            aMap.clear();
                        } else {
//                            AMap aMap = mMapView.getMap();
//                            aMap.clear();
//                            LatLng latLng = new LatLng(zhonglat, zhonglon);
//                            initgetBikeMapList(latLng);
                        }
                        if (checkJumpStatus.getBike_status() == 4 && checkJumpStatus.getLock_status() == 0) {
                            AMap aMap = mMapView.getMap();
                            aMap.clear();
                        }

                        if (getBikeMapList.getCode() == 0) {


                        } else {
                            LatLng latLng = new LatLng(getBikeMapList.getBody().get(0).getLat(),
                                    getBikeMapList.getBody().get(0).getLog());
                            MarkerOptions markerOption = new MarkerOptions();
                            markerOption.position(latLng);
                            markerOption.title("自行车").snippet("自行车");
                            markerOption.draggable(false);//设置Marker可拖动
                            if (getBikeMapList.getBody().get(0).getColor().equals("yellow")) {
                                markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                                        .decodeResource(getResources(), R.drawable.ico_yellow)));
                            } else if (getBikeMapList.getBody().get(0).getColor().equals("green")) {
                                markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                                        .decodeResource(getResources(), R.drawable.ico_green)));
                            } else if (getBikeMapList.getBody().get(0).getColor().equals("red")) {
                                markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                                        .decodeResource(getResources(), R.drawable.ico_red)));
                            } else if (getBikeMapList.getBody().get(0).getColor().equals("blue")) {
                                markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                                        .decodeResource(getResources(), R.drawable.ico_blue)));
                            }
                            // 将Marker设置为贴地显示，可以双指下拉地图查看效果
                            markerOption.setFlat(true);//设置marker平贴地图效果
                            markerOption.visible(true);
                            if (!getBikeMapList.getBody().get(0).getColor().equals("red")||bike_number ==checkJumpStatus.getBike_number()) {
                                Marker marker = aMap.addMarker(markerOption.position(latLng));
                                marker.setObject(getBikeMapList.getBody().get(0));
                                cameraUpdate = CameraUpdateFactory
                                        .newCameraPosition(new CameraPosition(new LatLng(getBikeMapList.getBody().get(0).getLat(),
                                                getBikeMapList.getBody().get(0).getLog()), 16, 0, 0));
                                aMap.moveCamera(cameraUpdate);
                                if (checkJumpStatus.getBike_status() == 0 || new UserService(MainActivity.this).getShowOneMark().equals("1")) {
                                    marker.showInfoWindow();
                                    currentMarker = marker;
                                }

                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);  //先得到构造器
                                builder.setTitle("提示"); //设置标题
                                builder.setMessage("该车辆已被预约，请点击“确定”查看可用车辆"); //设置内容
//                                builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可
                                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() { //设置确定按钮
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        bikenum = "";
                                        dialog.dismiss(); //关闭dialog
                                        if (new UserService(MainActivity.this).getCookie().equals("0")) {
                                            startActivity(RegisterActivity.class);
                                        } else {
                                            if (v != null) {
                                                if (v.getCode() == 0) {
                                                    startActivity(RegisterActivity.class);
                                                } else {
                                                    if (v.getVerify_status() == 0 || v.getVerify_status() == 3) {
                                                        startActivity(RealnameActivity.class);
                                                    } else if (v.getVerify_status() == 2) {
                                                        showShort("正在审核中，请稍后..");
                                                    } else {
//                                if (v.getDeposit_status() == 0) {
//                                    startActivity(DepositActivity.class);
//                                } else {
                                                        if (checkJumpStatus.getCode() == 1) {
                                                            if (checkJumpStatus.getBike_status() == 4) {
                                                                showOneCar(checkJumpStatus.getBike_number());
                                                            } else {
                                                                startActivity(UseBicycleActivity.class, "lat", lat + "", "lon", lon + "");
                                                            }
                                                        }

//                                }
                                                    }
                                                }

                                            }
                                        }

                                    }
                                });
                                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { //设置取消按钮
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        bikenum = "";
                                        dialog.dismiss();
                                    }
                                });
                                builder.create().show();
//                                showShort("该车辆已经预约，请点击立即用车查看其它车辆！");
                            }
                        }
                    }
                });

    }


    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }


    public static String bike_number;

    public void render(Marker marker, View view) {
        final GetBikeMapList.BodyBean data = (GetBikeMapList.BodyBean) marker.getObject();
        //如果想修改自定义Infow中内容，请通过view找到它并修改
        TextView tv_time_info;
        TextView tv_timerent_info;
        TextView tv_dayrent_info;
        TextView tv_tirentbt_info;
        TextView tv_darentbt_info;
        TextView tv_longrent_info;
        TextView tv_lorentbt_info;
        TextView tv_distance_info;
        TextView longrent_info;

        if (data.getMybike() == 1 && data.getColor().equals("blue") && checkJumpStatus.getBike_status() == 4) {

            tv_timerent_info = (TextView) view.findViewById(R.id.tv_timerent_info);
            tv_dayrent_info = (TextView) view.findViewById(R.id.tv_dayrent_info);
            tv_longrent_info = (TextView) view.findViewById(R.id.tv_longrent_info);
            tv_tirentbt_info = (TextView) view.findViewById(R.id.tv_tirentbt_info);
            tv_darentbt_info = (TextView) view.findViewById(R.id.tv_darentbt_info);
            tv_lorentbt_info = (TextView) view.findViewById(R.id.tv_lorentbt_info);
            tv_distance_info = (TextView) view.findViewById(R.id.tv_distance_info);
            TextView tv_bicyclenum_info = (TextView) view.findViewById(R.id.tv_bicyclenum_info);
            tv_tirentbt_info.setText("共享收入");
            tv_darentbt_info.setText("设置共享");
            tv_bicyclenum_info.setText("山地车:" + data.getNumber() + "");
            tv_lorentbt_info.setVisibility(View.GONE);
            tv_bicyclenum_info.setText("车牌号：" + data.getNumber() + "");
//            tv_time_info.setText("在租时段" + data.getValid_time() + "");
            tv_timerent_info.setText("时租：" + (data.getLease_info().get时租() / 2) + "元/半小时");
            tv_dayrent_info.setText("日租：" + data.getLease_info().get日租() + "元/天");
            tv_distance_info.setText("地点：" + data.getAddress());
//            tv_time_info.setText("山地车:" + data.getNumber() + "");

//            String valid_time = " ";
//            for (int a = 0; a < data.getValid_time().size(); a++) {
//                valid_time = valid_time + data.getValid_time().get(a).toString();
//                tv_dayrent_info.setText("共享时段:" + valid_time + "");
//            }
            tv_tirentbt_info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(DetailsActivity.class);
                }
            });
            tv_darentbt_info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String url = Apis.Base + Apis.overLongLeaseBike;
                    OkHttpUtils
                            .post()
                            .url(url)
                            .addHeader("cookie", new UserService(MainActivity.this).getCookie())
                            .addParams("bike_number", checkJumpStatus.getBike_number())
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {

                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    Log.d("response提示锁状态", response);
                                    Lockstatus lockstatus = gson.fromJson(response, Lockstatus.class);
                                    if (lockstatus.getCode() == 1) {
                                        if (lockstatus.getLock_status() == 1) {
                                            String cookie;
                                            cookie = new UserService(MainActivity.this).getCookie();
                                            String url = Apis.Base + Apis.dayLeaseLists;
                                            format = new SimpleDateFormat("yyyy-MM-dd");
                                            OkHttpUtils
                                                    .post()
                                                    .url(url)
                                                    .addHeader("cookie", cookie)
                                                    .build()
                                                    .execute(new StringCallback() {
                                                        @Override
                                                        public void onError(Call call, Exception e, int id) {
                                                        }

                                                        @Override
                                                        public void onResponse(String response, int id) {
                                                            L.d(response);
                                                            SharedBikeList d = gson.fromJson(response, SharedBikeList.class);
                                                            if (d.getCode() == 1) {
                                                                unList.clear();
                                                                canList.clear();
                                                                showAlertshare(d.getBody());
                                                            } else {
                                                                showShort(d.getMsg());
                                                            }

                                                        }
                                                    });
                                        }
                                    } else {
                                        showShort(lockstatus.getMsg());
                                    }
                                }
                            });

                }
            });


        } else {
            //绿色 黄色车显示的infowindows
            /*
      自定义infowinfow窗口
     */
            TextView tv_bicyclenum_info = (TextView) view.findViewById(R.id.tv_bicyclenum_info);
            tv_timerent_info = (TextView) view.findViewById(R.id.tv_timerent_info);
            tv_dayrent_info = (TextView) view.findViewById(R.id.tv_dayrent_info);
            tv_longrent_info = (TextView) view.findViewById(R.id.tv_longrent_info);
            tv_distance_info = (TextView) view.findViewById(R.id.tv_distance_info);
            longrent_info = (TextView) view.findViewById(R.id.longrent_info);

            tv_tirentbt_info = (TextView) view.findViewById(R.id.tv_tirentbt_info);
            tv_darentbt_info = (TextView) view.findViewById(R.id.tv_darentbt_info);
            tv_lorentbt_info = (TextView) view.findViewById(R.id.tv_lorentbt_info);
            tv_tirentbt_info.setText("时租");
            tv_darentbt_info.setText("日租");
            tv_lorentbt_info.setText("长租");
            tv_distance_info.setText("地点：" + data.getAddress());
            tv_lorentbt_info.setVisibility(View.VISIBLE);
            tv_bicyclenum_info.setText("车牌号：" + data.getNumber() + "");
//            tv_time_info.setText("在租时段" + data.getValid_time() + "");
            tv_timerent_info.setText("时租：" + (data.getLease_info().get时租() / 2) + "元/半小时");
            tv_dayrent_info.setText("日租：" + data.getLease_info().get日租() + "元/天");
            if (!data.getColor().equals("yellow")) {
                tv_longrent_info.setText("长租：");
                longrent_info.setText(data.getLease_info().get月租() + "元/月\n"
                        + data.getLease_info().get季租() + "元/3个月\n"
                        + data.getLease_info().get半年租() + "元/半年\n"
                        + data.getLease_info().get年租() + "元/一年");
            }


            if (data.getColor().equals("blue")) {
                tv_tirentbt_info.setTextColor(this.getResources().getColor(R.color.blackSec));
                tv_darentbt_info.setTextColor(this.getResources().getColor(R.color.org));
                tv_lorentbt_info.setTextColor(this.getResources().getColor(R.color.blackSec));
            } else {
                if (data.getType().equals("2")) {
                    tv_tirentbt_info.setTextColor(this.getResources().getColor(R.color.org));
                    tv_darentbt_info.setTextColor(this.getResources().getColor(R.color.org));
                    tv_lorentbt_info.setTextColor(this.getResources().getColor(R.color.blackSec));
                } else {
                    tv_tirentbt_info.setTextColor(this.getResources().getColor(R.color.org));
                    tv_darentbt_info.setTextColor(this.getResources().getColor(R.color.org));
                    tv_lorentbt_info.setTextColor(this.getResources().getColor(R.color.org));
                    if (data.getLease_info().get月租() == null) {
                        tv_lorentbt_info.setTextColor(this.getResources().getColor(R.color.blackSec));
                    }
                }
            }


            //长租点击事件
            tv_lorentbt_info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (checkJumpStatus.getBike_status() == 4) {
                        showShort("您已经是长租用户");
                    } else {
                        if (data.getType().equals("2")) {
                            showShort("双人车不可长租");
                        } else {
                            if (checkJumpStatus.getBike_status() != 0) {
                                showShort("处于用车状态");
                            } else {
                                if (data.getLease_info().get月租() != null) {
                                    startActivity(LongTimeLeaseActivity.class, "biyclenum", data.getNumber(),
                                            "deposit_status", v.getDeposit_status() + "");
                                }

                            }

                        }

                    }
                }
            });
            //时租点击事件
            tv_tirentbt_info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (v != null) {
                        if (v.getDeposit_status() != 1) {
                            startActivity(DepositActivity.class);
                        } else {
                            // TODO: 2017/8/16 未写
                            if (checkJumpStatus.getBike_status() == 4) {
                                showShort("您是长租用户");
                            } else {
                                if (new UserService(MainActivity.this).getState().equals("1")) {
                                    showShort("处于用车状态");
                                } else {
                                    if (data.getColor().equals("green") || data.getColor().equals("yellow")) {
                                        bike_number = data.getNumber();
                                        startActivity(ResultActivity.class, "type", "time", "bike_number", bike_number);
                                    }
                                }


                            }
                        }
                    } else {
                        startActivity(RegisterActivity.class);
                    }


                }
            });
            //日租点击事件
            tv_darentbt_info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (checkJumpStatus.getBike_status() == 4) {
                        showShort("您是长租用户");
                    } else {
                        String url = Apis.Base + Apis.dayLeaseList;
                        String cookie = new UserService(MainActivity.this).getCookie();
                        format = new SimpleDateFormat("yyyy-MM-dd");
                        bike_number = data.getNumber();
                        OkHttpUtils
                                .post()
                                .url(url)
                                .addHeader("cookie", cookie)
                                .addParams("bike_number", bike_number)
                                .build()
                                .execute(new StringCallback() {
                                    @Override
                                    public void onError(Call call, Exception e, int id) {

                                    }

                                    @Override
                                    public void onResponse(String response, int id) {
                                        L.d(response);
                                        DayleaseList d = gson.fromJson(response, DayleaseList.class);
                                        if (d.getCode() == 1) {
                                            if (checkJumpStatus.getBike_status() == 2) {
                                                L.d("=====data ", new Date().getYear() + "-" + new Date().getMonth() + "-" + new Date().getDay());
                                                d.getBody().add(new Date().getYear() + "-" + new Date().getMonth() + "-" + new Date().getDay());
                                            }
                                            showAlert(data.getNumber(), d.getBody());
                                        } else {
                                            showShort(d.getMsg());
                                        }

                                    }
                                });
                    }
                }
            });
        }


    }

    //获取弹窗窗口
    @Override
    public View getInfoContents(Marker marker) {
        if (infoWindow == null) {
//            GetBikeMapList.BodyBean data = (GetBikeMapList.BodyBean) marker.getObject();
//            if (checkJumpStatus.getBike_status() == 4 && data.getColor().equals("blue")) {
//                infoWindow = LayoutInflater.from(this).inflate(
//                        R.layout.custom_bicycle_window, null);
//            } else {
            infoWindow = LayoutInflater.from(this).inflate(
                    R.layout.custom_info_window, null);
//            }
        }
        render(marker, infoWindow);
        return infoWindow;

    }

    Marker currentMarker;

    //mark点击事件
    @Override
    public boolean onMarkerClick(Marker marker) {
        currentMarker = marker;
        Log.d("mark dian", "true");
        final GetBikeMapList.BodyBean data = (GetBikeMapList.BodyBean) marker.getObject();
        if (!new UserService(MainActivity.this).getCookie().equals("0")) {
            if (v != null) {
                if (v.getCode() == 1) {
                    if (v.getVerify_status() == 1) {
//                        if (v.getDeposit_status() == 1) {
                        if (data.getColor().equals("blue")) {
                            getInfoContents(marker);
                            marker.showInfoWindow();
                        } else {
                            getInfoContents(marker);
                            marker.showInfoWindow();
                        }
//                        } else {
//                            startActivity(DepositActivity.class);
//                        }
                    } else if (v.getVerify_status() == 2) {
                        showShort("正在审核中，请稍候..");
                    } else {
                        startActivity(RealnameActivity.class);
                    }
                }
            }
        } else {
            startActivity(RegisterActivity.class);
        }
        return true;
    }


    //地图中心移动结束
    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        LatLng target = cameraPosition.target;
        zhonglat = target.latitude;
        zhonglon = target.longitude;
        Log.d("onCameraChange", target.latitude + "jinjin------" + target.longitude);
        if (new UserService(MainActivity.this).getState().equals("1")) {
//            lianxumap();
        } else {
            String showOneMark = new UserService(MainActivity.this).getShowOneMark();
            if (showOneMark.equals("1")) {
//                initview();
            } else if (showOneMark.equals("0")) {
                initgetBikeMapList(target);
            }

        }


    }


    private static final String TAG = "=====";
    List<CalendarDay> selectedDates;
    List<Date> unlist = new ArrayList<>();
    DateFormat format;

    //日租日历
    private void showAlert(final String i, List<String> list) {

        View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.useday_calendar, null, false);
        dialog = new AlertDialog.Builder(MainActivity.this).setView(view).setCancelable(false).show();
        TextView tv_ok = (TextView) view.findViewById(R.id.tv_ok);
        tv_dates = (TextView) view.findViewById(R.id.tv_dates);
        dialog.setCancelable(true);
        myCalendar = (MaterialCalendarView) view.findViewById(R.id.calendar_md);
        myCalendarInit();//初始化日历
        if (list != null) {
            for (int j = 0; j < list.size(); j++) {
                try {
                    Date date = format.parse(list.get(j));
                    unlist.add(date);
                    myCalendar.addDecorators(new MySelectorDecorators(MainActivity.this), new SelectDecorator(MainActivity.this, date));

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedDates = myCalendar.getSelectedDates();
                String s = "";
                if (selectedDates.size() > 0) {
                    for (int i = 0; i < selectedDates.size(); i++) {
                        String format = new SimpleDateFormat("yyyy-MM-dd").format(selectedDates.get(i).getDate());
                        Log.d(TAG, "onClick: " + format);
                        if (selectedDates.size() == i + 1) {
                            s = s + format;
                        } else {
                            s = s + format + ",";
                        }
                    }

                    if (v != null) {
                        if (v.getDeposit_status() != 1) {
                            startActivity(DepositActivity.class);
                        } else {
                            startActivity(PayActivity.class, "dates", s, "bike_number", i);
                        }
                    }

                    Log.d(TAG, s);
                    if (dialog.isShowing())
                        dialog.dismiss();
                } else {
                    showShort("请选择日期");
                }
            }
        });


    }

    private MaterialCalendarView myCalendar;
    private TextView tv_dates;

    public void myCalendarInit() {
        myCalendar.setSelectionMode(MaterialCalendarView.SELECTION_MODE_MULTIPLE);
        myCalendar.setHeaderTextAppearance(R.style.TextAppearance_AppCompat_Small);
        myCalendar.setDateTextAppearance(R.style.TextAppearance_AppCompat_Small);
        myCalendar.setWeekDayTextAppearance(R.style.TextAppearance_AppCompat_Small);
        myCalendar.setSelected(false);
        myCalendar.setEnabled(false);
        myCalendar.setClickable(false);
        CalendarDay today = CalendarDay.today();
        myCalendar.state().edit()
                .setMinimumDate(CalendarDay.today())
                .setMaximumDate(CalendarDay.from(today.getYear(), today.getMonth() + 1, today.getDay() - 2))
                .commit();
        myCalendar.setShowOtherDates(MaterialCalendarView.SHOW_OTHER_MONTHS);
//        init(myCalendar.getCurrentDate().getYear(), myCalendar.getCurrentDate().get月租() + 1, list);
        myCalendar.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
//                signDataInit(date.getYear(), date.get月租() + 1);
            }
        });
        myCalendar.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(MaterialCalendarView widget, CalendarDay date, boolean selected) {

                selectedDates = myCalendar.getSelectedDates();

                if (widget.getSelectedDates().size() > 5) {

                    new AlertDialog.Builder(MainActivity.this).setTitle("提示").setMessage("天数选择超限")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).show();
                    myCalendar.setDateSelected(date, false);

                } else {

                    if (selected) {
                        myCalendar.setDateSelected(date, true);
                        if (unlist.size() > 0) {
                            for (int i = 0; i < unlist.size(); i++) {
                                if (unlist.get(i).getTime() == date.getDate().getTime()) {
                                    myCalendar.setDateSelected(date, false);

                                }
                            }
                        }


                    } else {
                        myCalendar.setDateSelected(date, false);
                    }

                    List<CalendarDay> selectedDates = myCalendar.getSelectedDates();

                    if (selectedDates.size() > 0) {
                        String s = "";
                        for (int i = 0; i < selectedDates.size(); i++) {
                            String format = new SimpleDateFormat("yyyy-MM-dd").format(selectedDates.get(i).getDate());
                            Log.d(TAG, "onClick: " + format);
                            if (selectedDates.size() == i + 1) {
                                s = s + format;
                            } else {
                                s = s + format + ",";
                            }
                        }

                        Log.d(TAG, "onDateSelected: " + s);
                        tv_dates.setText(s);

                    } else {
                        tv_dates.setText("");
                    }
                }
//                init(myCalendar.getCurrentDate().getYear(), myCalendar.getCurrentDate().get月租() + 1, list);

            }
        });
        OneDayDecorator oneDayDecorator = new OneDayDecorator();//今天
        myCalendar.addDecorators(new MySelectorDecorator(this), new HighlightWeekendsDecorator(this), oneDayDecorator);

    }

    //infowindow隐藏
    @Override
    public void onInfoWindowClick(Marker marker) {
        Log.d("info hide", "true");
        if (currentMarker.isInfoWindowShown()) {
            currentMarker.hideInfoWindow();//这个是隐藏infowindow窗口的方法
        }
    }


    @Override
    public void onCameraChange(CameraPosition cameraPosition) {

    }

    @Override
    public void onMapClick(LatLng latLng) {
//        showShort("地图被点击了");
        Log.d("map dian", "true");
        if (currentMarker != null) {
            currentMarker.hideInfoWindow();//这个是隐藏infowindow窗口的方法
        }
    }
}
