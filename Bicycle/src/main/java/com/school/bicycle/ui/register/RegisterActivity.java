package com.school.bicycle.ui.register;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.school.bicycle.R;
import com.school.bicycle.app.MyApplication;
import com.school.bicycle.entity.BaseResult;
import com.school.bicycle.entity.Login;
import com.school.bicycle.entity.Register;
import com.school.bicycle.global.Apis;
import com.school.bicycle.global.BaseActivity;
import com.school.bicycle.global.BaseToolBarActivity;
import com.school.bicycle.global.UserService;
import com.school.bicycle.ui.authentication.RealnameActivity;
import com.school.bicycle.ui.eposit.DepositActivity;
import com.school.bicycle.ui.main.MainActivity;
import com.school.bicycle.ui.pay.PayActivity;
import com.school.bicycle.utils.Forms;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.Response;

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
        setToolbarText("登录");
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
                                    showShort(baseResult.getMsg());
                                }

                            });
                }

            }
        });
        regNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iRegisterPresenter.verificationCode(etCode.getText().toString());
                String url = Apis.Base + "user/register";
                final String[] sid = new String[1];

                if (cbRuler.isChecked()) {

                    OkHttpUtils
                            .post()
                            .url(url)
                            .addParams("device_id", DEVICE_ID)
                            .addParams("code", etCode.getText().toString())
                            .addParams("reg_from", "android")
                            .build()
                            .execute(new StringCallback() {
                                String s;
                                @Override
                                public String parseNetworkResponse(Response response, int id) throws IOException {
                                    Headers headers = response.headers();
                                    Log.d("info_headers", "header " + headers);
                                    List<String> cookies = headers.values("Set-Cookie");
                                    String session = cookies.get(0);
                                    Log.d("info_cookies", "onResponse-size: " + cookies);
                                    s = session.substring(0, session.indexOf(";"));
                                    Log.d("info_s", "session is  :" + s);
                                    new UserService(RegisterActivity.this).setCookie(s);
                                    return super.parseNetworkResponse(response, id);
                                }

                                @Override
                                public void onError(Call call, Exception e, int id) {

                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    Log.d("登录",response+" s");
                                    new UserService(RegisterActivity.this).setCookie(s);
                                    Register register = gson.fromJson(response,Register.class);
                                    if (register.getCode()==1){
                                        if (register.getVerify_status()!=1){
                                            startActivity(RealnameActivity.class);
                                            finish();
                                        }else {
                                            showShort("登录成功");
                                            finish();
                                        }
                                    }
                                }
                            });
//                    OkHttpUtils
//                            .post()
//                            .url(url)
//                            .addParams("device_id", DEVICE_ID)
//                            .addParams("code", etCode.getText().toString())
//                            .addParams("reg_from", "android")
//                            .build()
//                            .execute(new Callback() {
//
//                                @Override
//                                public Object parseNetworkResponse(Response response, int id) throws Exception {
//                                    Headers headers = response.headers();
//                                    Log.d("info_headers", "header " + headers);
//                                    List<String> cookies = headers.values("Set-Cookie");
//                                    String session = cookies.get(0);
//                                    Log.d("info_cookies", "onResponse-size: " + cookies);
//                                    s = session.substring(0, session.indexOf(";"));
//                                    Log.d("info_s", "session is  :" + s);
//                                    new UserService(RegisterActivity.this).setCookie(s);
//                                    return null;
//                                }
//
//                                @Override
//                                public void onError(Call call, Exception e, int id) {
//                                    Log.d("response", e.toString());
//                                }
//
//                                @Override
//                                public void onResponse(Object response, int id) {
//                                    new UserService(RegisterActivity.this).setCookie(s);
//                                    BaseResult baseResult = (BaseResult)response;
//                                    if (baseResult.getCode()==1){
//
//                                    }
//                                    response.toString();
//                                    startActivity(MainActivity.class,"bike_number","");
//                                    finish();
//                                }
//
//                            });
                } else {

                }

            }
        });
    }

    @Override
    public void isPhone(boolean b) {
        if (b) {
            showShort("请输入正确的手机号码");
        } else {
            iRegisterPresenter.getCode(etPhone.getText().toString());
        }
    }

    @Override
    public void goNext() {

    }

    @Override
    public void sendCode(String s) {
        showShort(s);
    }
}
