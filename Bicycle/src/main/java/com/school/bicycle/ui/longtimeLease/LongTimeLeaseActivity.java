package com.school.bicycle.ui.longtimeLease;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.school.bicycle.R;
import com.school.bicycle.entity.GetLongLeaseInfo;
import com.school.bicycle.global.Apis;
import com.school.bicycle.global.BaseToolBarActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class LongTimeLeaseActivity extends BaseToolBarActivity {


    @BindView(R.id.month1)
    TextView month1;
    @BindView(R.id.month3)
    TextView month3;
    @BindView(R.id.month6)
    TextView month6;
    @BindView(R.id.month12)
    TextView month12;
    @BindView(R.id.wx_icon)
    ImageView wxIcon;
    @BindView(R.id.cb_wx)
    CheckBox cbWx;
    @BindView(R.id.zfb_icon)
    ImageView zfbIcon;
    @BindView(R.id.cb_zfb)
    CheckBox cbZfb;
    @BindView(R.id.tv_okpay)
    TextView tvOkpay;


    private int month = 0;
    private String bike_number;
    private String pay_type;
    private String lease_type;
    private String price;
    GetLongLeaseInfo g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_alert);
        ButterKnife.bind(this);
        setToolbarText("长租");
        initlongtimeprice();
        initview();
        Intent it = getIntent();
        bike_number = it.getStringExtra("biyclenum");//车牌号
        cbZfb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                initvheck();
                cbZfb.setChecked(b);
            }
        });
        cbWx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                initvheck();
                cbWx.setChecked(b);
            }
        });

    }

    private void initvheck() {
        cbZfb.setChecked(false);
        cbWx.setChecked(false);
    }

    private void initview() {
        month1.setBackgroundResource(R.drawable.bg_gray);
        month3.setBackgroundResource(R.drawable.bg_gray);
        month6.setBackgroundResource(R.drawable.bg_gray);
        month12.setBackgroundResource(R.drawable.bg_gray);
    }

    private void initlongtimeprice() {

        String url = getResources().getString(R.string.baseurl) + "order/getLongLeaseInfo";
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("response", response);
                        g = gson.fromJson(response, GetLongLeaseInfo.class);
                        if (g.getCode() == 1) {
                            month1.setText("月租：" + g.getLonglease_info().get(0).get月租());
                            month3.setText("季租：" + g.getLonglease_info().get(1).get季租());
                            month6.setText("半年租：" + g.getLonglease_info().get(2).get半年租());
                            month12.setText("年租：" + g.getLonglease_info().get(3).get年租());
                        } else {

                        }
                    }
                });
    }

    @OnClick({R.id.month1, R.id.month3, R.id.month6, R.id.month12, R.id.wx_icon, R.id.tv_okpay, R.id.zfb_icon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.month1:
                initview();
                month1.setBackgroundResource(R.drawable.bg_org);
                month = 1;
                lease_type = "月租";
                price = g.getLonglease_info().get(0).get月租();
                break;
            case R.id.month3:
                initview();
                month3.setBackgroundResource(R.drawable.bg_org);
                month = 3;
                lease_type = "季租";
                price = g.getLonglease_info().get(1).get季租();
                break;
            case R.id.month6:
                initview();
                month6.setBackgroundResource(R.drawable.bg_org);
                month = 6;
                lease_type = "半年租";
                price = g.getLonglease_info().get(2).get半年租();
                break;
            case R.id.month12:
                initview();
                month12.setBackgroundResource(R.drawable.bg_org);
                month = 12;
                lease_type = "年租";
                price = g.getLonglease_info().get(3).get年租();
                break;
            case R.id.wx_icon:
                break;
            case R.id.tv_okpay:
                // TODO: 2017/7/26 长租提交订单
                if (month == 0) {
                    showShort("请选择长租时间");
                } else {
                    if (cbWx.isChecked() || cbZfb.isChecked()) {
                        if (cbZfb.isChecked()) {
                            pay_type = "zfb";
                        } else {
                            pay_type = "wx";
                        }
                        String url = Apis.Base +Apis.longLeaseOrder;
                        OkHttpUtils
                                .post()
                                .url(url)
                                .addParams("lease_type", lease_type)
                                .addParams("bike_number", bike_number)
                                .addParams("price", price)
                                .addParams("pay_type", pay_type)
                                .build()
                                .execute(new StringCallback() {
                                    @Override
                                    public void onError(Call call, Exception e, int id) {

                                    }

                                    @Override
                                    public void onResponse(String response, int id) {
                                        Log.d("response", response);
                                    }
                                });

                    } else {
                        showShort("请至少选择一种支付方式");
                    }
                }
                break;
            case R.id.zfb_icon:
                break;

        }
    }


}
