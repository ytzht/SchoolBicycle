package com.school.bicycle.ui.longtimeLease;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.school.bicycle.R;
import com.school.bicycle.entity.GetLongLeaseInfo;
import com.school.bicycle.entity.PayInfo;
import com.school.bicycle.entity.PayResult;
import com.school.bicycle.entity.WxPayParams;
import com.school.bicycle.entity.Wxpayinfo;
import com.school.bicycle.global.Apis;
import com.school.bicycle.global.BaseToolBarActivity;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Map;

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

    String info;

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
                if (month == 0) {
                    showShort("请选择长租时间");
                } else {
                    if (cbWx.isChecked() || cbZfb.isChecked()) {
                        if (cbZfb.isChecked()) {
                            pay_type = "zfb";
                        } else {
                            pay_type = "wx";
                        }
                        String url = Apis.Base + Apis.longLeaseOrder;
                        Log.d("=====", lease_type + " " + bike_number + " " + price + " " + pay_type);
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

                                        if (pay_type.equals("wx")) {
                                            Wxpayinfo wxpayinfo = gson.fromJson(response, Wxpayinfo.class);
                                            WxPayParams wxPayParams =gson.fromJson(wxpayinfo.getPay_info(),WxPayParams.class);
                                            final IWXAPI msgApi = WXAPIFactory.createWXAPI(getBaseContext(), null);
                                            msgApi.registerApp(wxPayParams.appid);
                                            PayReq request = new PayReq();
                                            request.appId =wxPayParams.appid;
                                            request.partnerId = wxPayParams.partnerid;
                                            request.prepayId = wxPayParams.prepayid;
                                            request.packageValue = "Sign=WXPay";
                                            request.nonceStr = wxPayParams.noncestr;
                                            request.timeStamp = wxPayParams.timestamp;
                                            request.sign = wxPayParams.sign;
                                            msgApi.sendReq(request);
                                        } else {
                                        final PayInfo payInfo = (new Gson()).fromJson(response, PayInfo.class);

                                        if (payInfo.getCode() == 1) {
                                            info = response;
                                            new Thread() {
                                                @Override
                                                public void run() {
                                                    super.run();
                                                    PayTask payTask = new PayTask(LongTimeLeaseActivity.this);
                                                    Map<String, String> result = payTask.payV2(payInfo.getPay_info(), true);
                                                    Message message = mHandler.obtainMessage();
                                                    message.what = 200;
                                                    message.obj = result;
                                                    mHandler.sendMessage(message);
                                                }
                                            }.start();
                                        }else {
                                            showShort(payInfo.getMsg());
                                        }
                                        info = response;
                                        new Thread() {
                                            @Override
                                            public void run() {
                                                super.run();
                                                PayTask payTask = new PayTask(LongTimeLeaseActivity.this);
                                                Map<String, String> result = payTask.payV2(payInfo.getPay_info(), true);
                                                Message message = mHandler.obtainMessage();
                                                message.what = 200;
                                                message.obj = result;
                                                mHandler.sendMessage(message);
                                            }
                                        }.start();

                                    }}
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

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            PayResult payResult = new PayResult((Map<String, String>) msg.obj);
            /**
             对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
             */
            String resultInfo = payResult.getResult();// 同步返回需要验证的信息
            String resultStatus = payResult.getResultStatus();
            // 判断resultStatus 为9000则代表支付成功
            if (TextUtils.equals(resultStatus, "9000")) {
                // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                Toast.makeText(LongTimeLeaseActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
            } else {
                // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                Toast.makeText(LongTimeLeaseActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
            }
        }
    };








    Dialog dialog;
    private void msgAlert(){
        View view = LayoutInflater.from(this).inflate(R.layout.msg_alert, null, false);
        dialog = new AlertDialog.Builder(this).setView(view).setCancelable(false).show();

        TextView msg_txt = (TextView) view.findViewById(R.id.msg_txt);
        TextView msg_btn_l = (TextView) view.findViewById(R.id.msg_btn_l);
        TextView msg_btn_r = (TextView) view.findViewById(R.id.msg_btn_r);

        msg_btn_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        msg_btn_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
