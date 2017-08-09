package com.school.bicycle.ui.pay;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.school.bicycle.R;
import com.school.bicycle.entity.DayLeaseOrder;
import com.school.bicycle.entity.PayInfo;
import com.school.bicycle.entity.PayResult;
import com.school.bicycle.entity.Pay_wallet;
import com.school.bicycle.entity.WxPayParams;
import com.school.bicycle.entity.Wxpayinfo;
import com.school.bicycle.global.Apis;
import com.school.bicycle.global.BaseToolBarActivity;
import com.school.bicycle.global.L;
import com.school.bicycle.global.PayCore;
import com.school.bicycle.global.UserService;
import com.school.bicycle.ui.Mycoupon_chooles_Activity;
import com.school.bicycle.ui.Mycoupon_choolesday_Activity;
import com.school.bicycle.ui.result.ResultActivity;
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

public class PayActivity extends BaseToolBarActivity {

    @BindView(R.id.Payment_amount)
    TextView PaymentAmount;
    @BindView(R.id.cbwechat_pay)
    CheckBox cbwechatPay;
    @BindView(R.id.cbali_pay)
    CheckBox cbaliPay;
    @BindView(R.id.cbwallet_pay)
    CheckBox cbwalletPay;
    @BindView(R.id.pay_sure)
    TextView paySure;
    String pay_type = "";
    @BindView(R.id.tv_has_day)
    TextView tvHasDay;
    @BindView(R.id.tv_cut_day)
    TextView tvCutDay;
    @BindView(R.id.youhuiquan)
    LinearLayout youhuiquan;
    @BindView(R.id.tv_need_day)
    TextView tvNeedDay;

    DayLeaseOrder dayLeaseOrder;
    public  static  int cid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);
        setToolbarText("付款");
        initview();
    }

    private void initview() {
        String url = Apis.Base + Apis.dayLeaseOrder;
        String cookie = new UserService(PayActivity.this).getCookie();

        OkHttpUtils
                .post()
                .url(url).addHeader("cookie", cookie)
                .addParams("dates", getIntent().getStringExtra("dates"))
                .addParams("bike_number", getIntent().getStringExtra("bike_number"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("response", response);
                        dayLeaseOrder = gson.fromJson(response, DayLeaseOrder.class);
                        if (dayLeaseOrder.getCode() == 1) {
                            PaymentAmount.setText(dayLeaseOrder.getPrice()+"");
                        } else {
                            showShort(dayLeaseOrder.getMsg());
//                            finish();
                        }

                    }
                });
    }


    @OnClick({R.id.cbwechat_pay, R.id.cbali_pay, R.id.cbwallet_pay, R.id.pay_sure,R.id.youhuiquan})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.youhuiquan:
                if (dayLeaseOrder.getCoupon()!=null){
                    if (dayLeaseOrder.getCoupon().size()==0){
                        showShort("暂无可用的优惠券");
                    }else {
                        Intent intent = new Intent(this, Mycoupon_choolesday_Activity.class);
                        intent.putExtra("dayLeaseOrder", dayLeaseOrder);
                        startActivity(intent);
                    }
                }else {
                    showShort("暂无可用的优惠券");
                }


                break;
            case R.id.cbwechat_pay:
                initcheckbox();
                cbwechatPay.setChecked(true);
                pay_type = "wx";
                break;
            case R.id.cbali_pay:
                initcheckbox();
                cbaliPay.setChecked(true);
                pay_type = "zfb";
                break;
            case R.id.cbwallet_pay:
                initcheckbox();
                cbwalletPay.setChecked(true);
                pay_type = "wallet";
                break;
            case R.id.pay_sure:
                if (pay_type=="") {
                    showShort("请选择一种支付方式");
                } else {
                    initpay();
                }
                break;
        }
    }

    String info;

    @Override
    protected void onResume() {
        super.onResume();

        if (cid!=0){
            tvHasDay.setText("已选一张");
        }

        if (dayLeaseOrder!=null){
            if (dayLeaseOrder.getCode() == 1) {
                if (dayLeaseOrder.getCoupon().size()==0){
                    showShort("暂无可用的优惠券");
                }else {
                    for (int i = 0;i<dayLeaseOrder.getCoupon().size();i++){
                        if (dayLeaseOrder.getCoupon().get(i).getUsercou_id() ==cid){
                            if (dayLeaseOrder.getCoupon().get(i).getCou_type().equals("折扣")){
                                tvCutDay.setText("-￥"+(dayLeaseOrder.getPrice()*dayLeaseOrder.getCoupon().get(i).getCou_discount()));
                                tvNeedDay.setText(dayLeaseOrder.getPrice()-
                                        (dayLeaseOrder.getPrice()*dayLeaseOrder.getCoupon().get(i).getCou_discount())+"");
                            }else {
                                tvCutDay.setText("-￥"+dayLeaseOrder.getCoupon().get(i).getCou_cut());
                                tvNeedDay.setText(dayLeaseOrder.getPrice()-dayLeaseOrder.getCoupon().get(i).getCou_cut()+"");
                            }
                        }
                    }
                }

            }
        }
        L.d("wchat-0", PayCore.getInstance().mWeichatState + "");
        if (PayCore.getInstance().mWeichatState == PayCore.WeiChat_Pay_Success) {
            L.d("wchat-1", PayCore.getInstance().mWeichatState + "");
            PayCore.getInstance().mWeichatState = PayCore.WeiChat_Pay_Normal;
            startActivity(ResultActivity.class, "type", "date");
            finish();
        }
    }


    private void initpay() {
        String url = Apis.Base + Apis.submitDayLeaseMoney;
        String cookie = new UserService(PayActivity.this).getCookie();
        OkHttpUtils
                .post()
                .url(url)
                .addHeader("cookie", cookie)
                .addParams("price", PaymentAmount.getText().toString())
                .addParams("pay_type", pay_type)
                .addParams("cid", cid+"")
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
                            WxPayParams wxPayParams = gson.fromJson(wxpayinfo.getPay_info(), WxPayParams.class);
                            final IWXAPI msgApi = WXAPIFactory.createWXAPI(getBaseContext(), null);
                            msgApi.registerApp(wxPayParams.appid);
                            PayReq request = new PayReq();
                            request.appId = wxPayParams.appid;
                            request.partnerId = wxPayParams.partnerid;
                            request.prepayId = wxPayParams.prepayid;
                            request.packageValue = "Sign=WXPay";
                            request.nonceStr = wxPayParams.noncestr;
                            request.timeStamp = wxPayParams.timestamp;
                            request.sign = wxPayParams.sign;
                            msgApi.sendReq(request);

                        } else if (pay_type.equals("zfb")) {
                            final PayInfo payInfo = (new Gson()).fromJson(response, PayInfo.class);
                            info = response;
                            new Thread() {
                                @Override
                                public void run() {
                                    super.run();
                                    PayTask payTask = new PayTask(PayActivity.this);
                                    Map<String, String> result = payTask.payV2(payInfo.getPay_info(), true);
                                    Message message = mHandler.obtainMessage();
                                    message.what = 200;
                                    message.obj = result;
                                    mHandler.sendMessage(message);
                                }
                            }.start();

                        } else {
                            Pay_wallet p = gson.fromJson(response, Pay_wallet.class);
                            if (p.getCode() == 1) {
                                startActivity(ResultActivity.class, "type", "date");
                                finish();
                            } else {
                                showShort(p.getMsg());
                            }
                        }
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
                Toast.makeText(PayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(ResultActivity.class, "type", "date");
                        finish();
                    }
                });
            } else {
                // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                Toast.makeText(PayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private void initcheckbox() {
        cbaliPay.setChecked(false);
        cbwalletPay.setChecked(false);
        cbwechatPay.setChecked(false);
    }



}
