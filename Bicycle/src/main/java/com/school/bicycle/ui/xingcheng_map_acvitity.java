package com.school.bicycle.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.school.bicycle.R;
import com.school.bicycle.entity.GetMyRoute;
import com.school.bicycle.global.BaseToolBarActivity;
import com.school.bicycle.ui.main.IMainPresenter;
import com.school.bicycle.ui.main.IMainView;
import com.school.bicycle.ui.main.MainPresenterCompl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/21.
 */

public class xingcheng_map_acvitity extends BaseToolBarActivity implements IMainView{

    @BindView(R.id.map)
    MapView mMapView;
    @BindView(R.id.imageView16)
    ImageView imageView16;
    @BindView(R.id.fab_refresh)
    ImageView fabRefresh;
    @BindView(R.id.btn_use)
    Button btnUse;
    @BindView(R.id.fab_qr)
    ImageView fabQr;
    @BindView(R.id.iv_pull)
    ImageView ivPull;
    @BindView(R.id.tv_use)
    TextView tvUse;
    @BindView(R.id.use_no)
    TextView useNo;
    @BindView(R.id.view_divider)
    View viewDivider;
    @BindView(R.id.ll_detail)
    LinearLayout llDetail;
    @BindView(R.id.useing_biycle_lay)
    RelativeLayout useingBiycleLay;

    AMap aMap;
    private IMainPresenter iMainPresenter;
    Intent it;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mMapView.onCreate(savedInstanceState);// 此方法必须重写
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
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE); //定位一次，且将视角移动到地图中心点。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(false);//设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.moveCamera(CameraUpdateFactory.zoomTo(16));//显示地图等级15级
        initline();

    }

    private void initline() {
        it = getIntent();
        GetMyRoute getMyRoute = (GetMyRoute) it.getSerializableExtra("getMyRoute");
        String position = it.getStringExtra("position");
        int i = Integer.parseInt(position);
        List<LatLng> latLngs = new ArrayList<LatLng>();
        for (int a = 0;a<getMyRoute.getBody().get(i).getLines().size();a++){
            latLngs.add(new LatLng(getMyRoute.getBody().get(i).getLines().get(a).getLat()
                    ,getMyRoute.getBody().get(i).getLines().get(a).getLog()));
        }

        Polyline polyline =aMap.addPolyline(new PolylineOptions().
                addAll(latLngs).width(10).color(Color.argb(255, 1, 1, 1)));
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

}
