package com.school.bicycle.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.school.bicycle.R;
import com.school.bicycle.entity.PayInfo;
import com.school.bicycle.entity.PayResult;
import com.school.bicycle.entity.Recharge;
import com.school.bicycle.entity.WxPayParams;
import com.school.bicycle.entity.Wxpayinfo;
import com.school.bicycle.global.Apis;
import com.school.bicycle.global.BaseToolBarActivity;
import com.school.bicycle.global.L;
import com.school.bicycle.global.PayCore;
import com.school.bicycle.global.UserService;
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

public class RechargeActivity extends BaseToolBarActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_alert);
        ButterKnife.bind(this);
        setToolbarText("充值");
        initlongtimeprice();
        initview();
    }

    String pro_id;
    String price = "";
    String pay_type;
    @OnClick({R.id.month1, R.id.month3, R.id.month6, R.id.month12, R.id.cb_wx, R.id.cb_zfb, R.id.tv_okpay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.month1:
                initview();
                month1.setBackgroundResource(R.drawable.bg_org);
                pro_id = recharge.getRecharge().get(0).getPro_id();
                price = recharge.getRecharge().get(0).getPrice();
                break;
            case R.id.month3:
                initview();
                month3.setBackgroundResource(R.drawable.bg_org);
                pro_id = recharge.getRecharge().get(1).getPro_id();
                price = recharge.getRecharge().get(1).getPrice();
                break;
            case R.id.month6:
                initview();
                month6.setBackgroundResource(R.drawable.bg_org);
                pro_id = recharge.getRecharge().get(2).getPro_id();
                price = recharge.getRecharge().get(2).getPrice();
                break;
            case R.id.month12:
                initview();
                month12.setBackgroundResource(R.drawable.bg_org);
                pro_id = recharge.getRecharge().get(3).getPro_id();
                price = recharge.getRecharge().get(3).getPrice();
                break;
            case R.id.cb_wx:
                initvheck();
                cbWx.setChecked(true);
                break;
            case R.id.cb_zfb:
                initvheck();
                cbZfb.setChecked(true);
                break;
            case R.id.tv_okpay:
                initpay();
                break;
        }
    }
    private void initview() {
        month1.setBackgroundResource(R.drawable.bg_gray);
        month3.setBackgroundResource(R.drawable.bg_gray);
        month6.setBackgroundResource(R.drawable.bg_gray);
        month12.setBackgroundResource(R.drawable.bg_gray);
    }
    private void initvheck() {
        cbZfb.setChecked(false);
        cbWx.setChecked(false);
    }
    Recharge recharge;
    private void initlongtimeprice() {

        String url = getResources().getString(R.string.baseurl) + "order/rechPro";
        String cookie = new UserService(RechargeActivity.this).getCookie();

        OkHttpUtils
                .get()
                .url(url)
                .addHeader("cookie",cookie)
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("response", response);
                        recharge = gson.fromJson(response, Recharge.class);
                        if (recharge.getCode() == 1) {
                            month1.setText(recharge.getRecharge().get(0).getPrice()+"元");
                            month3.setText(recharge.getRecharge().get(1).getPrice()+"元");
                            month6.setText(recharge.getRecharge().get(2).getPrice()+"元");
                            month12.setText(recharge.getRecharge().get(3).getPrice()+"元");
                        } else {

                        }
                    }
                });
    }
    String info;
    private void initpay() {
        if (price.equals("")) {
            showShort("请选择长租时间");
        } else {
            if (cbWx.isChecked() || cbZfb.isChecked()) {
                if (cbZfb.isChecked()) {
                    pay_type = "zfb";
                } else {
                    pay_type = "wx";
                }
                String url = Apis.Base + Apis.rechargeOrder;
                Log.d("充值支付", price + " " + pay_type + " " + pro_id + " "  );
                String cookie = new UserService(RechargeActivity.this).getCookie();
                OkHttpUtils
                        .post()
                        .url(url)
                        .addHeader("cookie",cookie)
                        .addParams("price", price)
                        .addParams("pay_type", pay_type)
                        .addParams("price", price)
                        .addParams("pro_id", pro_id)
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
                                    if (wxpayinfo.getCode()==1){
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
                                    }else {
                                        showShort(wxpayinfo.getMsg());
                                    }

                                } else {
                                    final PayInfo payInfo = (new Gson()).fromJson(response, PayInfo.class);

                                    if (payInfo.getCode() == 1) {
                                        info = response;
                                        new Thread() {
                                            @Override
                                            public void run() {
                                                super.run();
                                                PayTask payTask = new PayTask(RechargeActivity.this);
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
                                }}
                        });

            } else {
                showShort("请至少选择一种支付方式");
            }
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
                Toast.makeText(RechargeActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        startActivity(ResultActivity.class,"type","dateReturnBiycle");
                        finish();
                    }
                });
            } else {
                // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                Toast.makeText(RechargeActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
            }
        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        L.d("wxpay");
        if (PayCore.getInstance().mWeichatState == PayCore.WeiChat_Pay_Success) {
            L.d("wxpay1");
            PayCore.getInstance().mWeichatState = PayCore.WeiChat_Pay_Normal;
            finish();
        }
    }
}
