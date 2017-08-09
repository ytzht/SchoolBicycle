package com.school.bicycle.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.school.bicycle.R;
import com.school.bicycle.entity.BaseResult;
import com.school.bicycle.global.Apis;
import com.school.bicycle.global.BaseToolBarActivity;
import com.school.bicycle.global.UserService;
import com.school.bicycle.ui.main.MainActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class ChangePhoneNextActivity extends BaseToolBarActivity {

    @BindView(R.id.iv_reg_phone)
    ImageView ivRegPhone;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.rl_phone)
    RelativeLayout rlPhone;
    @BindView(R.id.iv_reg_code)
    ImageView ivRegCode;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.rl_code)
    RelativeLayout rlCode;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.cb_ruler)
    CheckBox cbRuler;
    @BindView(R.id.tv_user)
    TextView tvUser;
    @BindView(R.id.reg_next)
    TextView regNext;

    Gson gson = new Gson();
    TelephonyManager tm;
    String DEVICE_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        setToolbarText("验证新手机号");

        initClick();
        initHandler();
        tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        DEVICE_ID = tm.getDeviceId();


    }



    private void initClick() {
        tvUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(setup_userActivity.class);
            }
        });

        tvCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String accout = etPhone.getEditableText().toString();
                djs();
                String url = getResources().getString(R.string.baseurl) + "user/getCode?mobile=" + accout;
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
                                BaseResult baseResult = gson.fromJson(response, BaseResult.class);
                                showShort(baseResult.getMsg());
                            }

                        });
            }

        });


        regNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbRuler.isChecked() && !etCode.getText().toString().equals("")) {

                    OkHttpUtils.get()
                            .url(Apis.Base + Apis.UpdateNewPhone)
                            .addParams("code", etCode.getText().toString())
                            .build().execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {

                        }

                        @Override
                        public void onResponse(String response, int id) {

                            BaseResult result = (new Gson()).fromJson(response, BaseResult.class);
                            if (result.getCode() == 1){

                                new UserService(getBaseContext()).setCookie("0");


                                startActivity(MainActivity.class);

                            }else {
                                showShort(result.getMsg());
                            }
                        }
                    });
                }
            }
        });




    }


    private void initHandler() {
        handler = new Handler();
        runnable = new Runnable() {

            @Override
            public void run() {
                tvCode.setText(sec + "");
                sec--;
                if (sec > 0) {
                    tvCode.setEnabled(false);
                    if (handler != null && runnable != null) {
                        handler.postDelayed(runnable, 1000);
                    }
                } else {
                    tvCode.setText("获取验证码");
                    tvCode.setEnabled(true);
                }
            }
        };
    }


    private Handler handler;
    private Runnable runnable;
    private int sec = 60;

    public void djs() {
        sec = 60;
        handler.post(runnable);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            if (runnable != null) {
                handler.removeCallbacks(runnable);
                runnable = null;
            }
            handler = null;
        }
    }
}
