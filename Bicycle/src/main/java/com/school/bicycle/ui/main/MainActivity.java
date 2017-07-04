package com.school.bicycle.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;
import com.school.bicycle.R;
import com.school.bicycle.entity.GetBikeMapList;
import com.school.bicycle.global.BaseActivity;
import com.school.bicycle.global.L;
import com.school.bicycle.ui.ScanQRCodeActivity;
import com.school.bicycle.ui.mybicycle.MyBicycleActivity;
import com.school.bicycle.ui.register.RegisterActivity;
import com.school.bicycle.ui.search.SearchActivity;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class MainActivity extends BaseActivity implements IMainView,
        NavigationView.OnNavigationItemSelectedListener{

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


    private IMainPresenter iMainPresenter;
    private ImageView headImg;
    private TextView name, score;
    double lat ;//获取纬度
    double lon ;//获取经度
    AMapLocation aMapLocation ;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
    double latitude;
    double longitude;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;

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
                    Log.e("Amap==经度：纬度", "locationType:"+locationType+",latitude:"+latitude);
                }else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError","location Error, ErrCode:"
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
//        mMapView.onCreate(savedInstanceState);

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

        initClickListener();
        initgetBikeMapList();
        initmap();
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
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);//定位一次，且将视角移动到地图中心点。
        aMap.moveCamera(CameraUpdateFactory.zoomTo(15));//显示地图等级15级
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);

        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Battery_Saving，低功耗模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
        /**
         * 获取一次定位
         */
        //该方法默认为false，true表示只定位一次
        mLocationOption.setOnceLocation(true);
    }


    //获取周围单车位置列表
    private void initgetBikeMapList() {

        String url = getResources().getString(R.string.baseurl) +
                "order/getBikeMapList?locations="
                +"121.450397,37.486531";
        Log.d("经纬度=",lon+","+lat);

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
                        Log.d("response=", response);
                        GetBikeMapList g = gson.fromJson(response, GetBikeMapList.class);
                        showLong(g.getMsg());
                    }

                });
    }

    //点击事件
    private void initClickListener() {
        btnUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ScanQRCodeActivity.class);
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShort("111");
            }
        });
        fabQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShort("111");
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
//        getMenuInflater().inflate(R.menu.drawer, menu);
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
            new ShareAction(MainActivity.this).withText("hello")
                    .setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.SINA)
                    .setCallback(umShareListener).open();
        } else if (id == R.id.my_invitation) {
            startActivity(SearchActivity.class);
        } else if (id == R.id.my_fault) {
            startActivity(RegisterActivity.class);
        } else if (id == R.id.my_tel) {

        } else if (id == R.id.my_news) {

        } else if (id == R.id.my_set) {

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






}