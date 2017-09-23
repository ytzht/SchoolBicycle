package com.school.bicycle.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

import com.school.bicycle.R;
import com.school.bicycle.global.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZhiDaoActivity extends Activity {

    @BindView(R.id.ima)
    ImageView ima;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏 第一种方法
        setContentView(R.layout.activity_zhi_dao);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.ima)
    public void onViewClicked() {
        if (i == 0) {
            i++;
            ima.setImageResource(R.drawable.zhidao2);
        } else if (i == 1) {
            i++;
            ima.setImageResource(R.drawable.zhidao3);
        } else if (i == 2) {
            finish();
        }
    }
}
