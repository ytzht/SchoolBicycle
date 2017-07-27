package com.school.bicycle.ui.lockclose;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.school.bicycle.R;
import com.school.bicycle.entity.BaseResult;
import com.school.bicycle.global.Apis;
import com.school.bicycle.global.BaseToolBarActivity;
import com.school.bicycle.global.UserService;
import com.school.bicycle.ui.main.MainActivity;
import com.school.bicycle.ui.result.ResultActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class LockcloseActivity extends BaseToolBarActivity {

    @BindView(R.id.lock_refresh)
    ImageView lockRefresh;
    @BindView(R.id.lock_no)
    TextView lockNo;
    @BindView(R.id.lock_kefu)
    ImageView lockKefu;
    @BindView(R.id.lock_ok)
    TextView lockOk;

    private LocationManager locationManager;
    private String locationProvider;
    Location location;
    TelephonyManager tm;
    String DEVICE_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lockclose);
        ButterKnife.bind(this);
        setToolbarText("锁车");
        new UserService(LockcloseActivity.this).setState("0");


    }
    //获取当前位置并请求停车
    private void initlocation() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //获取所有可用的位置提供器
        List<String> providers = locationManager.getProviders(true);
        if(providers.contains(LocationManager.GPS_PROVIDER)){
            //如果是GPS
            locationProvider = LocationManager.GPS_PROVIDER;
        }else if(providers.contains(LocationManager.NETWORK_PROVIDER)){
            //如果是Network
            locationProvider = LocationManager.NETWORK_PROVIDER;
        }else{
            Toast.makeText(this, "没有可用的位置提供器", Toast.LENGTH_SHORT).show();
            return ;
        }
        //获取Location
        location = locationManager.getLastKnownLocation(locationProvider);
        if(location!=null){
            //不为空,显示地理位置经纬度
            String url = Apis.Base + Apis.overUseBike;
            String locationstring = location.getLongitude()+","+location.getLatitude();

            tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            DEVICE_ID = tm.getDeviceId();
            OkHttpUtils
                    .post()
                    .url(url)
                    .addParams("location", locationstring)
                    .addParams("imei", DEVICE_ID)
                    .addParams("diu", DEVICE_ID)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {

                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Log.d("response",response);
                            BaseResult baseResult = gson.fromJson(response,BaseResult.class);
                            if (baseResult.getCode()==1){
                                startActivity(ResultActivity.class,"type","returnbiycle");
                                finish();
                            }else {
                                showShort(baseResult.getMsg());
                            }

                        }
                    });

        }
        //监视地理位置变化
        locationManager.requestLocationUpdates(locationProvider, 3000, 1, locationListener);
    }

    @OnClick({R.id.lock_refresh, R.id.lock_no, R.id.lock_kefu, R.id.lock_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lock_refresh:
                break;
            case R.id.lock_no:
                break;
            case R.id.lock_kefu:
                break;
            case R.id.lock_ok:
                initlocation();
                break;
        }
    }
    /**
     * LocationListern监听器
     * 参数：地理位置提供器、监听位置变化的时间间隔、位置变化的距离间隔、LocationListener监听器
     */

    LocationListener locationListener =  new LocationListener() {

        @Override
        public void onStatusChanged(String provider, int status, Bundle arg2) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }

        @Override
        public void onLocationChanged(Location locationa) {
            location = locationa;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(locationManager!=null){
            //移除监听器
            locationManager.removeUpdates(locationListener);
        }
    }


}
