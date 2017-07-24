package com.school.bicycle.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.school.bicycle.R;
import com.school.bicycle.entity.BaseResult;
import com.school.bicycle.entity.ValidateUser;
import com.school.bicycle.global.Apis;
import com.school.bicycle.global.BaseToolBarActivity;
import com.school.bicycle.global.UserService;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_);
        ButterKnife.bind(this);
        setToolbarText("账户设置");
        initview();
    }

    private void initview() {
        Intent it = getIntent();
        ValidateUser v = (ValidateUser) it.getSerializableExtra("user");
        userName.setText(v.getBody().getUser_id());
        userPhone.setText(v.getBody().getPhone());
        Signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = Apis.Base +Apis.userLogout ;
                OkHttpUtils.get()
                        .url(url)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(String response, int id) {
                                BaseResult b = gson.fromJson(response,BaseResult.class);
                                if (b.getCode()==1){
                                    showShort(b.getMsg());
                                    UserService userService = new UserService(User_Activity.this);
                                    userService.setValidateUser("");
                                    finish();
                                }else {
                                    showShort(b.getMsg());
                                }
                            }
                        });
            }
        });
        userChangephone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @OnClick({R.id.user_name, R.id.user_changephone, R.id.Signout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_name:
                break;
            case R.id.user_changephone:
                break;
            case R.id.Signout:
                break;
        }
    }
}