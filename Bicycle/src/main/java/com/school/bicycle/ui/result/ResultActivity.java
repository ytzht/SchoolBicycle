package com.school.bicycle.ui.result;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.school.bicycle.R;
import com.school.bicycle.global.BaseToolBarActivity;
import com.school.bicycle.global.UserService;
import com.school.bicycle.ui.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResultActivity extends BaseToolBarActivity {


    @BindView(R.id.iv_res_result)
    ImageView ivResResult;
    @BindView(R.id.te_res_result)
    TextView teResResult;
    @BindView(R.id.bt_res_next)
    Button btResNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        setToolbarText("结果");
        ButterKnife.bind(this);
        initview();
    }

    private void initview() {
        Intent it = getIntent();
        String type = it.getStringExtra("type");
        if (type.endsWith("time")) {
            teResResult.setText("点击确定后预定该车，进入用车界\n面十分钟后或扫码" +
                    "开锁后开始计费\n,\n还车请归还到原车位，否则系统将\n无法结束计费");
            btResNext.setText("确定");
            btResNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //0表示不再用车中1表示用车中
                    new UserService(ResultActivity.this).setState("1");
                    finish();
                }
            });
        } else {
            // TODO: 2017/7/24 用于设置各个界面跳转到当前界面 该界面的显示
        }
    }

    @OnClick(R.id.bt_res_next)
    public void onViewClicked() {

    }
}
