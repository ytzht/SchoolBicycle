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
import com.school.bicycle.entity.FindNotPayRoute;
import com.school.bicycle.global.Apis;
import com.school.bicycle.global.BaseToolBarActivity;
import com.school.bicycle.global.UserService;
import com.school.bicycle.ui.OverPayActivity;
import com.school.bicycle.ui.main.MainActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Date;

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
            teResResult.setText("点击确定后预定该车，进入用车界面十分钟后或扫码" +
                    "开锁后开始计时,还车请归还到原车位，否则系统将无法结束计时。");
            btResNext.setText("确定");
            btResNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String bike_number = getIntent().getStringExtra("bike_number");
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
                                    Log.d("response=====", response);
                                    BaseResult baseResult = gson.fromJson(response, BaseResult.class);
                                    if (baseResult.getCode() == 1) {
                                        //0表示不再用车中1表示用车中
                                        Date date = new Date();
                                        long time = date.getHours() * 3600 * 1000 + date.getMinutes() * 60000 + date.getSeconds() * 1000;
                                        new UserService(getBaseContext()).setBikeNumberTime(bike_number, time);
                                        startActivity(MainActivity.class,"bike_number", bike_number);
                                        finish();
                                    }else {
                                        showShort(baseResult.getMsg());
                                    }

                                }
                            });
                }
            });
        } else if (type.equals("yajin")) {
            teResResult.setText("押金充值成功");
            btResNext.setText("返回主界面");
            btResNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } else if (type.equals("tixian")) {
            teResResult.setText("押金将在0-3个工作日内转入到您的押金支付账户内");
            btResNext.setText("返回主界面");
            btResNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new UserService(ResultActivity.this).settixian("1");
                    finish();
                }
            });

        } else if (type.equals("date")) {
            teResResult.setText("用车结束需归还至原停车位才可继续用车。点击主界面扫码开锁按钮解锁车辆，结束用车前您可多次上锁开锁。");
            btResNext.setText("确定");
            new UserService(ResultActivity.this).setShowOneMark("0");
            btResNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //0表示不再用车中1表示用车中
                    finish();
                }
            });

        } else if (type.equals("dateReturnBiycle")) {
            teResResult.setText("还 车 成 功");
            btResNext.setText("返回主界面");
            btResNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new UserService(ResultActivity.this).setShowOneMark("0");
                    finish();
                }
            });
        } else if (type.equals("longtime")){
            teResResult.setText("支 付 成 功");
            btResNext.setText("返回主界面");
            btResNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new UserService(ResultActivity.this).setShowOneMark("0");
                    finish();
                }
            });
        }else if (type.equals("timestop")){
            teResResult.setText("还 车 成 功");
            btResNext.setText("返回主界面");

            btResNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = Apis.Base + Apis.findNotPayRoute;
                    String cookie = new UserService(ResultActivity.this).getCookie();
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
                                   BaseResult baseResult = gson.fromJson(response,BaseResult.class);
                                    if (baseResult.getCode()==0){
                                        finish();
                                    }else {
                                        new UserService(ResultActivity.this).settixian("1");
                                        startActivity(OverPayActivity.class);
                                        finish();
                                    }

                                }
                            });

                }
            });

        }else if (type.equals("tixianxiaoe")){
            teResResult.setText("提现金额将在0-3个工作日内转入您的押金充值账户！");
            btResNext.setText("返回主界面");
            btResNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new UserService(ResultActivity.this).settixian("1");
                    finish();
                }
            });
        }else if (type.equals("timereturnbiycle")){
            teResResult.setText("支付成功");
            btResNext.setText("返回主界面");
            btResNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }else if (type.equals("datestop")){
            teResResult.setText("还车成功");
            btResNext.setText("返回主界面");
            btResNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    @OnClick(R.id.bt_res_next)
    public void onViewClicked() {
        String type = getIntent().getStringExtra("type");
        if (type.equals("timestop")){
            finish();
        }

    }
}
