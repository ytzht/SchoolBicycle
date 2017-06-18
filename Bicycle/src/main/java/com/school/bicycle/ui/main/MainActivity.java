package com.school.bicycle.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.school.bicycle.R;
import com.school.bicycle.global.BaseActivity;
import com.school.bicycle.ui.register.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements IMainView {

    @BindView(R.id.map)
    MapView mMapView;
    @BindView(R.id.fab_refresh)
    ImageView floatingActionButton;
    @BindView(R.id.btn_use)
    Button btnUse;
    AMap aMap;
    @BindView(R.id.iv_res_menu)
    ImageView ivResMenu;

    TextView teLmName;

    TextView teLmIsauthentication;

    TextView teLmCreditscore;

    LinearLayout lmLlUser;

    LinearLayout lmLlBicycle;

    LinearLayout lmLlWallet;

    LinearLayout lmLlInvitation;

    LinearLayout lmLlFault;

    LinearLayout lmLlTelephone;

    LinearLayout lmLlNews;

    LinearLayout lmLlSetup;

    SlidingMenu menu;

    @BindView(R.id.fab_qr)
    ImageView fabQr;
    private IMainPresenter iMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mMapView.onCreate(savedInstanceState);


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
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
//        设置默认定位按钮是否显示，非必需设置。

        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);//定位一次，且将视角移动到地图中心点。
        aMap.moveCamera(CameraUpdateFactory.zoomTo(15));
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
//        iMainPresenter.downloadMap(MainActivity.this, aMap);

        initClickListener();
        initSlidingMenu();

    }

    private void initSlidingMenu() {
        // configure the SlidingMenu
        menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        // 设置触摸屏幕的模式
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);

        // 设置滑动菜单视图的宽度
        menu.setBehindOffsetRes(R.dimen.lm_witdh);
        // 设置渐入渐出效果的值
//        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        //为侧滑菜单设置布局
        menu.setMenu(R.layout.leftmenu);
        View leftmenu = LayoutInflater.from(this).inflate(R.layout.leftmenu, null);

        teLmName = (TextView) leftmenu.findViewById(R.id.te_lm_name);
        teLmIsauthentication = (TextView) leftmenu.findViewById(R.id.te_lm_isauthentication);
        teLmCreditscore = (TextView) leftmenu.findViewById(R.id.te_lm_creditscore);
        lmLlUser = (LinearLayout) leftmenu.findViewById(R.id.lm_ll_user);
        lmLlBicycle = (LinearLayout) leftmenu.findViewById(R.id.lm_ll_bicycle);
        lmLlWallet = (LinearLayout) leftmenu.findViewById(R.id.lm_ll_wallet);
        lmLlInvitation = (LinearLayout) leftmenu.findViewById(R.id.lm_ll_invitation);
        lmLlFault = (LinearLayout) leftmenu.findViewById(R.id.lm_ll_fault);
        lmLlTelephone = (LinearLayout) leftmenu.findViewById(R.id.lm_ll_telephone);
        lmLlNews = (LinearLayout) leftmenu.findViewById(R.id.lm_ll_news);
        lmLlSetup = (LinearLayout) leftmenu.findViewById(R.id.lm_ll_setup);
        lmLlUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShort("finish");
            }
        });


    }

    private void initClickListener() {
        btnUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(RegisterActivity.class);
            }
        });
        ivResMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.showMenu();
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


}
