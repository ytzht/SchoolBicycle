package com.school.bicycle.ui.longtimeLease;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
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
import com.school.bicycle.entity.Wxpayinfo;
import com.school.bicycle.global.Apis;
import com.school.bicycle.global.BaseToolBarActivity;
import com.school.bicycle.global.L;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.mayubao.pay_library.AliPayReq2;
import io.github.mayubao.pay_library.PayAPI;
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
                                            final IWXAPI msgApi = WXAPIFactory.createWXAPI(getBaseContext(), null);
                                            msgApi.registerApp("wx71fd0a9986ec271f");
                                            PayReq request = new PayReq();
                                            request.appId = "wx71fd0a9986ec271f";
                                            request.partnerId = "1486493792";
                                            request.prepayId = "wx20170727154142ef2d5632d00326279373";
                                            request.packageValue = "Sign=WXPay";
                                            request.nonceStr = "Q88yplU21l5Bi6Es";
                                            request.timeStamp = "1501141302";
                                            request.sign = "D6BBCB9A4634CBF8A5B732C54D57AC0E";
                                            msgApi.sendReq(request);
                                        } else {
                                            PayInfo payInfo = (new Gson()).fromJson(response, PayInfo.class);
                                            info = response;
                                            AliPay(payInfo);
                                        }


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

    private void AliPay(PayInfo info) {
        //1.创建支付宝支付订单的信息
        String rawAliOrderInfo = new AliPayReq2.AliOrderInfo()
                .setPartner("2088721345232205") //商户PID || 签约合作者身份ID
                .setSeller("viplecheng@163.com")  // 商户收款账号 || 签约卖家支付宝账号
                .setOutTradeNo(info.getOrder_no()) //设置唯一订单号
                .setSubject("充值订单支付" + info.getOrder_no()) //设置订单标题
                .setBody("充值订单支付") //设置订单内容
                .setPrice(price) //设置订单价格
                .setCallbackUrl("http://106.14.192.87/xyxapi/pay/alipay/order/notify") //设置回调链接
                .createOrderInfo(); //创建支付宝支付订单信息


        //2.签名  支付宝支付订单的信息 ===>>>  商户私钥签名之后的订单信息
        //TODO 这里需要从服务器获取用商户私钥签名之后的订单信息
        String signAliOrderInfo = info.getPay_info();

        //3.发送支付宝支付请求
        AliPayReq2 aliPayReq = new AliPayReq2.Builder()
                .with(LongTimeLeaseActivity.this)//Activity实例
                .setRawAliPayOrderInfo(rawAliOrderInfo)//支付宝支付订单信息
                .setSignedAliPayOrderInfo(signAliOrderInfo) //设置 商户私钥RSA加密后的支付宝支付订单信息
                .create()//
                .setOnAliPayListener(null);//
        PayAPI.getInstance().sendPayRequest(aliPayReq);

        //关于支付宝支付的回调
        aliPayReq.setOnAliPayListener(new AliPayReq2.OnAliPayListener() {
            @Override
            public void onPaySuccess(String resultInfo) {
                L.d("onPaySuccess " + resultInfo);
            }

            @Override
            public void onPayFailure(String resultInfo) {
                L.d("resultInfo " + resultInfo);
            }

            @Override
            public void onPayConfirmimg(String resultInfo) {
                L.d("resultInfo " + resultInfo);
            }

            @Override
            public void onPayCheck(String status) {
                L.d("status " + status);
            }
        });
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

}
