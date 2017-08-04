package com.school.bicycle.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.school.bicycle.R;
import com.school.bicycle.entity.BaseResult;
import com.school.bicycle.entity.User;
import com.school.bicycle.global.Apis;
import com.school.bicycle.global.BaseToolBarActivity;
import com.school.bicycle.global.UserService;
import com.school.bicycle.ui.authentication.RealnameActivity;
import com.school.bicycle.ui.eposit.DepositActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class User_Activity extends BaseToolBarActivity {

    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_phone)
    TextView userPhone;
    @BindView(R.id.user_changephone)
    TextView userChangephone;
    @BindView(R.id.linearLayout5)
    LinearLayout linearLayout5;
    @BindView(R.id.Signout)
    TextView Signout;
    @BindView(R.id.verify_status_user)
    TextView verifyStatusUser;
    @BindView(R.id.deposit_status_user)
    TextView depositStatusUser;

    User user;
    @BindView(R.id.realname_go)
    TextView realnameGo;
    @BindView(R.id.yajin_go)
    TextView yajinGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_);
        ButterKnife.bind(this);
        setToolbarText("账户设置");
        initview();
    }

    private void initview() {

        final String url = Apis.Base + Apis.getUserInfo;
        String cookie = new UserService(User_Activity.this).getCookie();
        OkHttpUtils.get()
                .url(url)
                .addHeader("cookie", cookie)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("response", response);
                        user = gson.fromJson(response, User.class);
                        if (user.getCode() == 1) {
                            userName.setText(user.getName());
                            userPhone.setText(user.getPhone());
                            if (user.getDeposit_status() == 1) {
                                depositStatusUser.setText("押金充值成功");
                            } else {
                                depositStatusUser.setText("未付押金");
                            }
                            if (user.getVerify_status() == 1) {
                                verifyStatusUser.setText("已实名认证");
                            } else {
                                verifyStatusUser.setText("未实名认证");
                            }

                        }
                    }
                });
    }

    @OnClick({R.id.user_name, R.id.user_changephone, R.id.Signout, R.id.verify_status_user, R.id.deposit_status_user, R.id.realname_go, R.id.yajin_go})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_name:
                break;
            case R.id.user_changephone:
                //更换手机号
                break;
            case R.id.Signout:
                String url = Apis.Base + Apis.userLogout;
                OkHttpUtils.get()
                        .url(url)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(String response, int id) {
                                BaseResult b = gson.fromJson(response, BaseResult.class);
                                if (b.getCode() == 1) {
                                    showShort(b.getMsg());
                                    new UserService(User_Activity.this).setValidateUser("0");
                                    new UserService(User_Activity.this).setCookie("0");
                                    finish();
                                } else {
                                    showShort(b.getMsg());
                                }
                            }
                        });
                break;
            case R.id.verify_status_user:
                break;
            case R.id.deposit_status_user:
                break;
            case R.id.realname_go:
                if (user.getVerify_status() != 1) {
                    startActivity(RealnameActivity.class);
                }
                break;
            case R.id.yajin_go:
                if (user.getDeposit_status() != 1) {
                    startActivity(DepositActivity.class);
                }
                break;
        }
    }

}
