package com.school.bicycle.ui;

import android.os.Bundle;

import com.amap.api.maps.AMap;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.school.bicycle.R;
import com.school.bicycle.global.BaseActivity;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements IMainView{

    @BindView(R.id.map)
    MapView mMapView;
    AMap aMap;
    IMainPresenter iMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMapView.onCreate(savedInstanceState);


        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        iMainPresenter = new MainPresenterCompl(getBaseContext(), this);
        iMainPresenter.initLocation(aMap);
        iMainPresenter.initUISettings(aMap);

//        iMainPresenter.downloadMap(MainActivity.this, aMap);


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
