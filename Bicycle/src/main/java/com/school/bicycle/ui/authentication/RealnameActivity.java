package com.school.bicycle.ui.authentication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.school.bicycle.R;
import com.school.bicycle.entity.Login;
import com.school.bicycle.global.BaseToolBarActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class RealnameActivity extends BaseToolBarActivity {

    @BindView(R.id.rn_name)
    EditText rnName;
    @BindView(R.id.rn_idnumber)
    EditText rnIdnumber;
    @BindView(R.id.rn_idstudent)
    EditText rnIdstudent;
    @BindView(R.id.rn_photo)
    ImageView rnPhoto;
    @BindView(R.id.rn_back)
    ImageView rnBack;
    @BindView(R.id.shangchuan)
    Button shangchuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_realname);
        ButterKnife.bind(this);
        setToolbarText("实名认证");
        initonclink();
    }

    private void initonclink() {
        shangchuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = getResources().getString(R.string.baseurl) + "user/profile";
                OkHttpUtils
                        .post()
                        .url(url)
                        .addParams("name", "")
                        .addParams("id_number", "")
                        .addParams("campus_card_number", "android")
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.d("response=", response);
                                Log.d("response=", "");
                                Login login = gson.fromJson(response, Login.class);
                            }
                        });
            }
        });
    }
}
