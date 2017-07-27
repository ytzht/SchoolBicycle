package com.school.bicycle.ui.main;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.school.bicycle.R;
import com.school.bicycle.entity.GetBikeMapList;
import com.school.bicycle.entity.ValidateUser;
import com.school.bicycle.global.Apis;
import com.school.bicycle.global.BaseActivity;
import com.school.bicycle.global.L;
import com.school.bicycle.global.UserService;
import com.school.bicycle.ui.FaultActivity;
import com.school.bicycle.ui.InformationActivity;
import com.school.bicycle.ui.Ivfriends.IvfriendsActivity;
import com.school.bicycle.ui.TimeCountDownTextView;
import com.school.bicycle.ui.User_Activity;
import com.school.bicycle.ui.ZxingActivity;
import com.school.bicycle.ui.authentication.RealnameActivity;
import com.school.bicycle.ui.lockclose.LockcloseActivity;
import com.school.bicycle.ui.longtimeLease.LongTimeLeaseActivity;
import com.school.bicycle.ui.mybicycle.MyBicycleActivity;
import com.school.bicycle.ui.mywallet.Mywallet_activity;
import com.school.bicycle.ui.pay.PayActivity;
import com.school.bicycle.ui.register.RegisterActivity;
import com.school.bicycle.ui.result.ResultActivity;
import com.school.bicycle.ui.setup.Setup_Activity;
import com.school.bicycle.ui.usebicycle.UseBicycleActivity;
import com.school.bicycle.utils.HighlightWeekendsDecorator;
import com.school.bicycle.utils.MySelectorDecorator;
import com.school.bicycle.utils.OneDayDecorator;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class MainActivity extends BaseActivity implements IMainView,
        NavigationView.OnNavigationItemSelectedListener, AMap.InfoWindowAdapter,
        AMap.OnMarkerClickListener, AMap.OnInfoWindowClickListener, AMap.OnCameraChangeListener {

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
    //    @BindView(R.id.iv_pull)
    private ImageView iv_pull;
    //    @BindView(R.id.ll_detail)
    private LinearLayout ll_detail;
    private RelativeLayout useing_biycle_lay;
    private LinearLayout saoma;
    private RelativeLayout state_0;
    private IMainPresenter iMainPresenter;
    private ImageView headImg;
    private TextView name, score, finish_usecar;
    double lat;//获取纬度
    double lon;//获取经度
    AMapLocation aMapLocation;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    private ImageView dialog_close;
    //初始化dialog
    AlertDialog.Builder paydialog;
    Dialog dialog;
    View pay_lay;
    TelephonyManager tm;
    String DEVICE_ID;
    ValidateUser v;


    public AMapLocationListener mLocationListener = new AMapLocationListener() {

        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            // TODO Auto-generated method stub
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    //可在其中解析amapLocation获取相应内容。
                    double locationType = amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                    lat = amapLocation.getLatitude();//获取纬度
                    lon = amapLocation.getLongitude();//获取经度
                    Log.e("经纬度=", "locationType:" + locationType + ",latitude:" + lat + "longitude" + lon);
                    initgetBikeMapList();
                    mLocationClient.stopLocation();
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                }
            }
        }
    };


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
//        iMainPresenter.downloadMap(MainActivity.this, aMap);
        initview();
        initClickListener();
        initmap();
        initvalidateUser();


    }
    int recLen = 0;
    TimeCountDownTextView countDownView;
    long mMinute, mSecond;

    private void initTimeDown() {
        countDownView = (TimeCountDownTextView) findViewById(R.id.countdown);
        countDownView.setCountDownTimes(600000);//10min
        countDownView.setOnCountDownFinishListener(new TimeCountDownTextView.onCountDownFinishListener() {
            @Override
            public void onFinish() {
                //倒计时结束
                initTime();//开始正计时
            }
        });
        countDownView.start();//开始倒计时
    }

    private void initTime() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                recLen = recLen + 1000;
                mMinute = recLen / (1000 * 60);
                mSecond = (recLen % (1000 * 60)) / 1000;

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        String min;
                        if (mMinute<10){
                            min = "0"+mMinute;
                        }else {
                            min = mMinute+"";
                        }

                        String sec;
                        if (mSecond<10){
                            sec = "0"+mSecond;
                        }else {
                            sec = "" + mSecond;
                        }

                        countDownView.setText(Html.fromHtml(String.format("%1$s:%2$s", min, sec)));
                        L.d(countDownView.getText().toString());
                    }
                });

            }
        }, 1000, 1000);       // timeTask

    }

    //更新界面
    private void initview() {

        iv_pull = (ImageView) findViewById(R.id.iv_pull);
        ll_detail = (LinearLayout) findViewById(R.id.ll_detail);
        state_0 = (RelativeLayout) findViewById(R.id.state_0);
        finish_usecar = (TextView) findViewById(R.id.finish_usecar);
        useing_biycle_lay = (RelativeLayout) findViewById(R.id.useing_biycle_lay);
        saoma = (LinearLayout) findViewById(R.id.saoma);

        if (new UserService(MainActivity.this).getState().equals("1")) {
            state_0.setVisibility(View.GONE);
            useing_biycle_lay.setVisibility(View.VISIBLE);
            toolbar.setTitle("用车中");
            initTimeDown();
        } else if (new UserService(MainActivity.this).getState().equals("0")) {
            state_0.setVisibility(View.VISIBLE);
            useing_biycle_lay.setVisibility(View.GONE);
            toolbar.setTitle("首页");
        }

        if (new UserService(MainActivity.this).getValidateUser().equals("0")) {
            name.setText("未登录");
            score.setText("");
        } else {
            if (v != null) {
                name.setText(v.getBody().getPhone());
                if (v.getBody().getStatus() == 1) {
                    score.setText("手机已认证");
                } else {
                    score.setText("手机未认证");
                }
            } else {
                name.setText("未登录");
                score.setText("");
            }

        }


    }

    //验证跳过登录
    private void initvalidateUser() {
        tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        DEVICE_ID = tm.getDeviceId();
        String url = Apis.Base +
                Apis.validateUser
                + DEVICE_ID;

        OkHttpUtils.get()
                .url(url)
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
                            name.setText(v.getBody().getPhone());
                            if (v.getBody().getStatus() == 1) {
                                score.setText("手机已认证");
                            } else {
                                score.setText("手机未认证");
                            }
                        } else {
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
        iMainPresenter = new MainPresenterCompl(getBaseContext(), this);
        iMainPresenter.initLocation(aMap);
        iMainPresenter.initUISettings(aMap);
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE); //定位一次，且将视角移动到地图中心点。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.moveCamera(CameraUpdateFactory.zoomTo(15));//显示地图等级15级
        aMap.setInfoWindowAdapter(this);
        aMap.setOnCameraChangeListener(this);
        aMap.setOnMarkerClickListener(this);
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Battery_Saving，低功耗模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
        /**
         * 获取一次定位
         */
        //该方法默认为false，true表示只定位一次
        mLocationOption.setOnceLocation(false);

    }


    //获取周围单车位置列表
    private void initgetBikeMapList() {

        String url = Apis.Base +
                "order/getBikeMapList?locations="
                + lon + "," + lat;
        Log.d("经纬度=", lon + "," + lat + "   " + url);

        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        showShort("no");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("获取周围单车位置列表response", response);
                        GetBikeMapList g = gson.fromJson(response, GetBikeMapList.class);
                        //清除地图中的mark点
                        AMap aMap = mMapView.getMap();
                        aMap.clear();
                        if (g.getCode() == 0) {

                        } else {
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
                                }
                                // 将Marker设置为贴地显示，可以双指下拉地图查看效果
                                markerOption.setFlat(true);//设置marker平贴地图效果
                                markerOption.visible(true);
                                Marker marker = aMap.addMarker(markerOption.position(latLng));
                                marker.setObject(g.getBody().get(i));

                            }
                        }
                        showLong(g.getMsg());
                    }

                });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    //点击事件
    private void initClickListener() {


        btnUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (v != null) {
                    if (new UserService(MainActivity.this).getValidateUser().equals("1")) {
                        startActivity(UseBicycleActivity.class);
                    } else {
                        startActivity(RegisterActivity.class);
                    }
                } else {
                    startActivity(RegisterActivity.class);
                }


            }
        });

        saoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ZxingActivity.class);
            }
        });

        finish_usecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserService s = new UserService(MainActivity.this);
                if (s.getAlert().equals("0")){
                    //出弹窗吧
                    showTips();
                }else {
                    //已经勾过不再提示，直接跳
                    startActivity(LockcloseActivity.class);
                }




            }
        });

        headImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, User_Activity.class);
                it.putExtra("user", v);
                startActivity(it);

            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //重新定位并重新请求当前位置周边车辆信息
                mLocationClient.startLocation();
            }
        });
        fabQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ZxingActivity.class);
            }
        });

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


    }

    private void showTips() {
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.msg_alert, null, false);
        final Dialog dialog = new AlertDialog.Builder(MainActivity.this).setView(view).setCancelable(false).show();


        LinearLayout ll_msg = (LinearLayout) view.findViewById(R.id.ll_msg);
        ll_msg.setVisibility(View.VISIBLE);

        TextView msg_txt = (TextView) view.findViewById(R.id.msg_txt);
        msg_txt.setText("车辆归回原处才可结束用车，由用户不当操作造成的财产损失将追究法律责任。");

        TextView msg_btn_r = (TextView) view.findViewById(R.id.msg_btn_r);
        msg_btn_r.setText("确定");

        TextView msg_btn_l = (TextView) view.findViewById(R.id.msg_btn_l);
        msg_btn_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing()) dialog.dismiss();
            }
        });

        final CheckBox box = (CheckBox) view.findViewById(R.id.cb_msg);



        msg_btn_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击确定
                //判断checkbox
                if (dialog.isShowing()) dialog.dismiss();
                if (box.isChecked()){
                    new UserService(MainActivity.this).setAlert("1");
                }
                startActivity(LockcloseActivity.class);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
        initview();
        checkJumpStatus();
    }
    //跳转状态
    private void checkJumpStatus() {

        String url = Apis.Base + Apis.checkJumpStatus;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("response跳转状态", response);
                    }
                });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.my_bicycle) {
            startActivity(MyBicycleActivity.class);
        } else if (id == R.id.my_wallet) {
            startActivity(Mywallet_activity.class);
        } else if (id == R.id.my_invitation) {
            startActivity(IvfriendsActivity.class);
        } else if (id == R.id.my_fault) {
            startActivity(FaultActivity.class);
        } else if (id == R.id.my_tel) {
            startActivity(RealnameActivity.class);
        } else if (id == R.id.my_news) {
            startActivity(InformationActivity.class);
        } else if (id == R.id.my_set) {
            startActivity(Setup_Activity.class);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            L.d("onStart");
        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            L.d("onResult");
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            L.e("onError" + throwable);
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
            L.d("onCancel");
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }

    View infoWindow = null;

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    /**
     * 自定义infowinfow窗口
     */
    private TextView tv_bicyclenum_info;
    private TextView tv_distance_info;
    private TextView tv_time_info;
    private TextView tv_timerent_info;
    private TextView tv_dayrent_info;
    private TextView tv_longrent_info;
    private TextView tv_tirentbt_info;
    private TextView tv_darentbt_info;
    private TextView tv_lorentbt_info;

    public void render(Marker marker, View view) {
        //如果想修改自定义Infow中内容，请通过view找到它并修改
        tv_bicyclenum_info = (TextView) view.findViewById(R.id.tv_bicyclenum_info);
        tv_distance_info = (TextView) view.findViewById(R.id.tv_distance_info);
        tv_time_info = (TextView) view.findViewById(R.id.tv_time_info);
        tv_timerent_info = (TextView) view.findViewById(R.id.tv_timerent_info);
        tv_dayrent_info = (TextView) view.findViewById(R.id.tv_dayrent_info);
        tv_longrent_info = (TextView) view.findViewById(R.id.tv_longrent_info);
        tv_tirentbt_info = (TextView) view.findViewById(R.id.tv_tirentbt_info);
        tv_darentbt_info = (TextView) view.findViewById(R.id.tv_darentbt_info);
        tv_lorentbt_info = (TextView) view.findViewById(R.id.tv_lorentbt_info);
        final GetBikeMapList.BodyBean data = (GetBikeMapList.BodyBean) marker.getObject();
        tv_bicyclenum_info.setText("车牌号：" + data.getNumber());
        tv_distance_info.setText("距离：" + data.getDistance() + "m");
        tv_time_info.setText("在租时段" + data.getValid_time());
        tv_timerent_info.setText("时租：" + data.getLease_info().get时租() + "元");
        tv_dayrent_info.setText("日租：" + data.getLease_info().get日租() + "元");
        if (data.getColor().equals("yellow")) {

        } else {
            tv_longrent_info.setText("长租：" + data.getLease_info().get月租() + "元/月 " + data.getLease_info().get季租()
                    + "元/3个月 \n" + data.getLease_info().get半年租() + "元/半年 " + data.getLease_info().get年租() + "元/一年");
        }

        tv_lorentbt_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(LongTimeLeaseActivity.class, "biyclenum", data.getNumber());

            }
        });
        tv_tirentbt_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(ResultActivity.class, "type", "time", "bike_number", data.getNumber());
            }
        });
        tv_darentbt_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlert(data.getNumber());
            }
        });

    }

    @Override
    public View getInfoContents(Marker marker) {
        if (infoWindow == null) {
            infoWindow = LayoutInflater.from(this).inflate(
                    R.layout.custom_info_window, null);
        }
        render(marker, infoWindow);
        return infoWindow;

    }

    //mark点击事件
    @Override
    public boolean onMarkerClick(Marker marker) {
        Log.d("mark dian", "true");
        marker.showInfoWindow();
        return true;
    }

    //infowindow点击事件
    @Override
    public void onInfoWindowClick(Marker marker) {

    }


    @Override
    public void onCameraChange(CameraPosition cameraPosition) {

    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        LatLng target = cameraPosition.target;
        Log.d("onCameraChange", target.latitude + "jinjin------" + target.longitude);
        lon = target.longitude;
        lat = target.latitude;
        initgetBikeMapList();

    }


    private static final String TAG = "=====";
    List<CalendarDay> selectedDates;

    private void showAlert(final String i) {
        final String bike_number = i;
        View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.useday_calendar, null, false);
        dialog = new AlertDialog.Builder(MainActivity.this).setView(view).setCancelable(false).show();
        tv_ok = (TextView) view.findViewById(R.id.tv_ok);
        tv_dates = (TextView) view.findViewById(R.id.tv_dates);
        dialog.setCancelable(true);
        myCalendar = (MaterialCalendarView) view.findViewById(R.id.calendar_md);
        myCalendarInit();//初始化日历
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

                    startActivity(PayActivity.class, "dates", s, "bike_number", bike_number);
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
    private TextView tv_ok, tv_dates;

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
                .setMaximumDate(CalendarDay.from(today.getYear(), today.getMonth() + 2, today.getDay()))
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
                    } else {
                        myCalendar.setDateSelected(date, false);
                    }


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

                    }
                }
//                init(myCalendar.getCurrentDate().getYear(), myCalendar.getCurrentDate().get月租() + 1, list);

            }
        });
        OneDayDecorator oneDayDecorator = new OneDayDecorator();//今天
        myCalendar.addDecorators(new MySelectorDecorator(this), new HighlightWeekendsDecorator(this), oneDayDecorator);

//        signDataInit(myCalendar.getCurrentDate().getYear(), myCalendar.getCurrentDate().get月租() + 1);
    }
}