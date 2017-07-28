package com.school.bicycle.ui.lockopen;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.school.bicycle.R;
import com.school.bicycle.entity.BaseResult;
import com.school.bicycle.global.Apis;
import com.school.bicycle.global.BaseToolBarActivity;
import com.school.bicycle.global.UserService;
import com.school.bicycle.ui.authentication.RealnameActivity;
import com.school.bicycle.ui.result.ResultActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

public class LockOpenActivity extends BaseToolBarActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_open);
        setToolbarText("开锁");
        String lock_code = getIntent().getStringExtra("lock_code");
        String location = getIntent().getStringExtra("location");
        String url = Apis.Base + Apis.unlocking;
        Log.d("lock_code", lock_code);
        Log.d("locationstring", location);
        String cookie = new UserService(LockOpenActivity.this).getCookie();

        OkHttpUtils
                .post()
                .url(url)
                .addHeader("cookie", cookie)
                .addParams("lock_code", lock_code)
                .addParams("location", location)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("response", response);
                        BaseResult baseResult = gson.fromJson(response, BaseResult.class);
                        if (baseResult.getCode()==1){
                            finish();
                        }else {
                            showShort(baseResult.getMsg());
                        }

                    }
                });
    }


}
