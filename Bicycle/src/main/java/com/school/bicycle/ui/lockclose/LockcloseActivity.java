package com.school.bicycle.ui.lockclose;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
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
import com.school.bicycle.ui.OverPayActivity;
import com.school.bicycle.ui.authentication.RealnameActivity;
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

    @OnClick({R.id.lock_refresh, R.id.lock_no, R.id.lock_kefu, R.id.lock_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lock_refresh:
                showShort("正在重新定位锁的位置和状态");
                break;
            case R.id.lock_no:
                finish();
                break;
            case R.id.lock_kefu:
                call("0535-2105657");
                break;
            case R.id.lock_ok:
                String url = Apis.Base + Apis.overUseBike;
                String bike_number = getIntent().getStringExtra("bike_number");
                String location = getIntent().getStringExtra("location");
                tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                DEVICE_ID = tm.getDeviceId();
                String cookie = new UserService(LockcloseActivity.this).getCookie();

                OkHttpUtils
                        .post()
                        .url(url)
                        .addHeader("cookie", cookie)
                        .addParams("location", location)
                        .addParams("imei", DEVICE_ID)
                        .addParams("diu", DEVICE_ID)
                        .addParams("bike_number", bike_number)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.d("response", response);
                                BaseResult baseResult = gson.fromJson(response, BaseResult.class);
                                if (baseResult.getCode() == 1) {
                                    int status = Integer.parseInt(getIntent().getStringExtra("status"));
                                    Log.d("status",status+"");
                                    if (status == 1) {
                                        new UserService(LockcloseActivity.this).setShowOneMark("0");
                                        startActivity(ResultActivity.class, "type", "date");
                                        finish();
                                        //日租停车
                                    } else if (status == 2) {
                                        startActivity(ResultActivity.class, "type", "timestop");
                                        new UserService(LockcloseActivity.this).setState("0");
                                        finish();
                                        //时租停车
                                    } else if (status == 4) {
                                        finish();
                                    }
                                } else {
                                    showShort(baseResult.getMsg());
                                }


                            }
                        });
                break;
        }
    }

    /**
     * LocationListern监听器
     * 参数：地理位置提供器、监听位置变化的时间间隔、位置变化的距离间隔、LocationListener监听器
     */

    LocationListener locationListener = new LocationListener() {

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
        if (locationManager != null) {
            //移除监听器
            locationManager.removeUpdates(locationListener);
        }
    }


}
