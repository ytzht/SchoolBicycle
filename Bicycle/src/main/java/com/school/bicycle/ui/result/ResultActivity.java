package com.school.bicycle.ui.result;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
                    String bike_number = getIntent().getStringExtra("bike_number");
                    String url = Apis.Base + Apis.leaseBicycle;
                    String cookie = new UserService(ResultActivity.this).getCookie();
                    OkHttpUtils
                            .post()
                            .url(url)
                            .addHeader("cookie", cookie)
                            .addParams("bike_number", bike_number)
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {

                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    Log.d("response", response);
                                    BaseResult baseResult = gson.fromJson(response, BaseResult.class);
                                    if (baseResult.getCode() == 1) {
                                        //0表示不再用车中1表示用车中
                                        new UserService(ResultActivity.this).setState("1");
                                        finish();
                                    }else {
                                        showShort(baseResult.getMsg());
                                    }

                                }
                            });


                }
            });
        } else if (type.equals("yajin")) {
            teResResult.setText("押金将在2个工作日内转入到\n您的押金支付账户内");
            btResNext.setText("返回主界面");
            btResNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } else if (type.equals("tixian")) {
            teResResult.setText("提现金额将在2个工作日内转入到\n您的押金支付账户内");
            btResNext.setText("返回主界面");
            btResNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new UserService(ResultActivity.this).settixian("1");
                    finish();
                }
            });

        } else if (type.equals("date")) {
            teResResult.setText("用车结束需归还至原停车位才可技术用车。\n点击主界面扫码开锁按钮解锁车辆，结束\n用车前您可多次上锁开锁");
            btResNext.setText("确定");
            btResNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //0表示不再用车中1表示用车中
                    new UserService(ResultActivity.this).setState("1");
                    finish();
                }
            });

        } else if (type.equals("returnbiycle")) {
            teResResult.setText("还 车 成 功");
            btResNext.setText("返回主界面");
            btResNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new UserService(ResultActivity.this).setShowOneMark("0");
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
