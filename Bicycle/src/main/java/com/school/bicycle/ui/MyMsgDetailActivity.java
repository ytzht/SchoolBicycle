package com.school.bicycle.ui;

import android.os.Bundle;
import android.webkit.WebView;

import com.school.bicycle.R;
import com.school.bicycle.global.BaseToolBarActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyMsgDetailActivity extends BaseToolBarActivity {

    @BindView(R.id.msg_detail)
    WebView msgDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_msg_detail);
        ButterKnife.bind(this);

        setToolbarText("我的消息");
        msgDetail.getSettings().setDefaultTextEncodingName("UTF-8");//设置默认为utf-8
        msgDetail.loadData(getIntent().getStringExtra("msg"), "text/html; charset=UTF-8", null);
    }
}
