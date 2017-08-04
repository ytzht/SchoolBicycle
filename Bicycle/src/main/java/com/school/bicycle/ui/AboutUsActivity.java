package com.school.bicycle.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.school.bicycle.R;
import com.school.bicycle.entity.BaseResult;
import com.school.bicycle.global.Apis;
import com.school.bicycle.global.BaseToolBarActivity;
import com.school.bicycle.global.UserService;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class AboutUsActivity extends BaseToolBarActivity {

    @BindView(R.id.aboutus_phone)
    TextView aboutusPhone;
    @BindView(R.id.about_us)
    TextView aboutUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ButterKnife.bind(this);
        setToolbarText("关于我们");
        initview();

    }

    private void initview() {
        String url = Apis.Base + Apis.aboutUs;
        String cookie = new UserService(AboutUsActivity.this).getCookie();
        OkHttpUtils
                .get()
                .url(url)
                .addHeader("cookie", cookie)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        BaseResult b = gson.fromJson(response, BaseResult.class);
                        if (b.getCode()==1){
                            aboutUs.setText(b.getMsg());
                        }
                    }
                });
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

    @OnClick(R.id.aboutus_phone)
    public void onViewClicked() {
        call("0535-2105657");
    }
}
