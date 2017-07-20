package com.school.bicycle.ui.register;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.school.bicycle.R;
import com.school.bicycle.entity.BaseResult;
import com.school.bicycle.entity.Login;
import com.school.bicycle.global.BaseToolBarActivity;
import com.school.bicycle.utils.Forms;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class RegisterActivity extends BaseToolBarActivity implements IRegisterView {

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.reg_next)
    TextView regNext;
    @BindView(R.id.cb_ruler)
    CheckBox cbRuler;
    private IRegisterPresenter iRegisterPresenter;
    Gson gson = new Gson();
    TelephonyManager tm;
    String DEVICE_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        setToolbarText("注册");


        iRegisterPresenter = new RegisterPresenter(getBaseContext(), this);
        initClick();

        initHandler();

        tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        DEVICE_ID = tm.getDeviceId();
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


    private void initClick() {

        tvCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String accout = etPhone.getEditableText().toString();
                if (Forms.disValid(accout, Forms.PHONENUM)) {
                    etPhone.requestFocus();
                    showShort("请输入正确的手机号");
                } else {
                    iRegisterPresenter.verificationPhone(etPhone.getText().toString());
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
                                    showLong(baseResult.getMsg());
                                }

                            });
                }

            }
        });
        regNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iRegisterPresenter.verificationCode(etCode.getText().toString());
                String url = getResources().getString(R.string.baseurl) + "user/register";

                if (cbRuler.isChecked()){
                    OkHttpUtils
                            .post()
                            .url(url)
                            .addParams("device_id", DEVICE_ID)
                            .addParams("code", etCode.getText().toString())
                            .addParams("reg_from", "android")
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    showLong("你的网络貌似不太好？");
                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    Login login = gson.fromJson(response, Login.class);

                                }
                            });
                }else {

                }

            }
        });
    }

    @Override
    public void isPhone(boolean b) {
        if (!b) {
            showShort("请输入正确的手机号码");
        } else {
            iRegisterPresenter.getCode(etPhone.getText().toString());
        }
    }

    @Override
    public void goNext() {
        showShort("下一步");
    }

    @Override
    public void sendCode(String s) {
        showShort(s);
    }
}
