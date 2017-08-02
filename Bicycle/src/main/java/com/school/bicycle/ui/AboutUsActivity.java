package com.school.bicycle.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.school.bicycle.R;
import com.school.bicycle.global.BaseToolBarActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutUsActivity extends BaseToolBarActivity {

    @BindView(R.id.aboutus_phone)
    TextView aboutusPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ButterKnife.bind(this);
        setToolbarText("关于我们");
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
