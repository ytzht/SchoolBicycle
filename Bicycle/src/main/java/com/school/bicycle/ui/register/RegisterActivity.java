package com.school.bicycle.ui.register;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import com.google.gson.Gson;
import com.school.bicycle.R;
import com.school.bicycle.entity.BaseResult;
import com.school.bicycle.global.BaseToolBarActivity;
import com.school.bicycle.utils.Forms;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import okhttp3.Call;

public class RegisterActivity extends BaseToolBarActivity implements IRegisterView{

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.reg_next)
    TextView regNext;
    private IRegisterPresenter iRegisterPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setToolbarText("注册");

        iRegisterPresenter = new RegisterPresenter(getBaseContext(), this);
        initClick();

        initHandler();
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
                    String url = getResources().getString(R.string.baseurl)+"user/getCode?mobile="+accout;
                    OkHttpUtils.get()
                            .url(url)
                            .build()
                            .execute(new StringCallback()
                            {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    showShort("no");
                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    Log.d("response=",response);
                                    Gson gson = new Gson();
                                    BaseResult baseResult = gson.fromJson(response,BaseResult.class);
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
            }
        });
    }

    @Override
    public void isPhone(boolean b) {
        if (!b){
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
