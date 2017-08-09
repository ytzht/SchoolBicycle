package com.school.bicycle.ui;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.school.bicycle.R;
import com.school.bicycle.global.BaseToolBarActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class setup_yonghuActivity extends BaseToolBarActivity {

    @BindView(R.id.about_desponit)
    WebView aboutDesponit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_desposit);
        ButterKnife.bind(this);
        setToolbarText("用户指南");
        WebSettings webSettings = aboutDesponit.getSettings();
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);

        aboutDesponit.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;// 返回false
            }
        });
        //加载需要显示的网页
        aboutDesponit.loadUrl("http://xiaoyixinggo.com/admin/supplier/account/userguide.html");
        //设置Web视图
    }
}
