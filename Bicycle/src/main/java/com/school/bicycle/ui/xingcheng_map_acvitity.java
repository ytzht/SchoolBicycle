package com.school.bicycle.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.school.bicycle.R;
import com.school.bicycle.entity.GetMyRoute;
import com.school.bicycle.global.BaseToolBarActivity;
import com.school.bicycle.ui.main.IMainPresenter;
import com.school.bicycle.ui.main.IMainView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/21.
 */

public class xingcheng_map_acvitity extends BaseToolBarActivity implements IMainView {


    AMap aMap;
    @BindView(R.id.map_map)
    MapView mMapView;
    @BindView(R.id.map_bike_number)
    TextView mapBikeNumber;
    @BindView(R.id.map_distance)
    TextView mapDistance;
    @BindView(R.id.map_total_fee)
    TextView mapTotalFee;
    @BindView(R.id.view_divider)
    View viewDivider;
    @BindView(R.id.map_calories)
    TextView mapCalories;
    @BindView(R.id.map_carbon_saved)
    TextView mapCarbonSaved;
    @BindView(R.id.map_start_time)
    TextView mapStartTime;
    @BindView(R.id.ll_detail)
    LinearLayout llDetail;
    @BindView(R.id.useing_biycle_lay)
    RelativeLayout useingBiycleLay;
    @BindView(R.id.riqitype)
    TextView riqitype;

    private IMainPresenter iMainPresenter;
    Intent it;
    private CameraUpdate cameraUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);
        setToolbarText("我的行程");
        mMapView.onCreate(savedInstanceState);// 此方法必须重写
        initmap();

    }


    //初始化设置地图
    private void initmap() {
        if (aMap == null) {
            aMap = mMapView.getMap();
            UiSettings uiSettings = aMap.getUiSettings();
            // 通过UISettings.setZoomControlsEnabled(boolean)来设置缩放按钮是否能显示
            uiSettings.setZoomControlsEnabled(false);
        }

//        iMainPresenter = new MainPresenterCompl(getBaseContext(), this);
//        iMainPresenter.initLocation(aMap);
//        iMainPresenter.initUISettings(aMap);
//        MyLocationStyle myLocationStyle;
//        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类
//        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE); //定位一次，且将视角移动到地图中心点。
//        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
//        aMap.getUiSettings().setMyLocationButtonEnabled(false);//设置默认定位按钮是否显示，非必需设置。
//        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.moveCamera(CameraUpdateFactory.zoomTo(16));//显示地图等级15级
        initline();

    }

    private void initline() {
        it = getIntent();
        GetMyRoute getMyRoute = (GetMyRoute) it.getSerializableExtra("getMyRoute");
        String position = it.getStringExtra("position");
        int i = Integer.parseInt(position);
        if (getMyRoute.getBody().get(i).getLines().size() == 0) {

        } else {
            cameraUpdate = CameraUpdateFactory
                    .newCameraPosition(new CameraPosition(new LatLng(
                            getMyRoute.getBody().get(i).getLines().get(0).getLat(), getMyRoute.getBody().get(i).getLines().get(0).getLog()), 18, 0, 30));
            aMap.moveCamera(cameraUpdate);
            mapBikeNumber.setText("车 牌 号" + getMyRoute.getBody().get(i).getBike_number());
            mapDistance.setText("骑行距离：" + getMyRoute.getBody().get(i).getDistance() + "M");
            mapTotalFee.setText("骑行消费：" + getMyRoute.getBody().get(i).getTotal_fee() + "元");
            mapCalories.setText(getMyRoute.getBody().get(i).getCalories() + "卡");
            mapCarbonSaved.setText(getMyRoute.getBody().get(i).getCarbon_saved() + "g");

            if (getMyRoute.getBody().get(i).getRoute_type()==1){
                riqitype.setText("骑行日期");
                String str = getMyRoute.getBody().get(i).getCreate_time().substring(2, 10);
                mapStartTime.setText(str);
            }else if (getMyRoute.getBody().get(i).getRoute_type()==2){
                riqitype.setText("骑行时间");
                mapStartTime.setText(getMyRoute.getBody().get(i).getTime_span()+"分钟");
            }else if (getMyRoute.getBody().get(i).getRoute_type()==0){
                riqitype.setText("骑行日期");
                String str = getMyRoute.getBody().get(i).getCreate_time().substring(2, 10);
                mapStartTime.setText(str);
            }


            Log.d("getMyRoute", getMyRoute.getBody().get(i).toString());
            List<LatLng> latLngs = new ArrayList<LatLng>();
            for (int a = 0; a < getMyRoute.getBody().get(i).getLines().size(); a++) {
                latLngs.add(new LatLng(getMyRoute.getBody().get(i).getLines().get(a).getLat()
                        , getMyRoute.getBody().get(i).getLines().get(a).getLog()));
            }

            Polyline polyline = aMap.addPolyline(new PolylineOptions().
                    addAll(latLngs).width(10).color(getResources().getColor(R.color.org)));
        }
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
