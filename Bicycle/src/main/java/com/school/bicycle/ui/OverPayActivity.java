package com.school.bicycle.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.school.bicycle.R;
import com.school.bicycle.entity.FindNotPayRoute;
import com.school.bicycle.entity.PayInfo;
import com.school.bicycle.entity.PayResult;
import com.school.bicycle.entity.Pay_wallet;
import com.school.bicycle.entity.WxPayParams;
import com.school.bicycle.entity.Wxpayinfo;
import com.school.bicycle.global.Apis;
import com.school.bicycle.global.BaseActivity;
import com.school.bicycle.global.L;
import com.school.bicycle.global.PayCore;
import com.school.bicycle.global.UserService;
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

import static com.school.bicycle.R.id.tv_has;

public class OverPayActivity extends BaseActivity {


    @BindView(R.id.by_time)
    TextView byTime;
    @BindView(R.id.by_distance)
    TextView byDistance;
    @BindView(R.id.by_expend)
    TextView byExpend;
    @BindView(R.id.by_pay)
    TextView byPay;
    @BindView(tv_has)
    TextView tvHas;
    @BindView(R.id.tv_as)
    TextView tvAs;
    @BindView(R.id.tv_ye)
    TextView tvYe;
    @BindView(R.id.tv_need)
    TextView tvNeed;
    @BindView(R.id.cb_we)
    CheckBox cbWe;
    @BindView(R.id.cb_zfb)
    CheckBox cbZfb;
    @BindView(R.id.cb_wallet)
    CheckBox cbWallet;
    @BindView(R.id.confirm)
    Button confirm;
    String pay_type = "";
    String price;
    String bike_number;

    @BindView(R.id.youhuiquan)
    LinearLayout youhuiquan;
    FindNotPayRoute findNotPayRoute;
    public static int cid = 0;
    public static double cidprice = 0;
    @BindView(R.id.tb_overpay)
    Toolbar tbOverpay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_pay);
        ButterKnife.bind(this);
        tbOverpay.setTitle("时租付款");
        final ProgressDialog dialogpro = new ProgressDialog(OverPayActivity.this);

        dialogpro.setMessage("请稍候...");
        dialogpro.setCancelable(false);
        dialogpro.show();
        String url = Apis.Base + Apis.findNotPayRoute;
        String cookie = new UserService(OverPayActivity.this).getCookie();
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
                        if (dialogpro.isShowing()){
                            dialogpro.dismiss();
                        }
                        Log.d("response_overpay", response);
                        findNotPayRoute = gson.fromJson(response, FindNotPayRoute.class);
                        if (findNotPayRoute.getCode() == 1) {
                            byTime.setText(findNotPayRoute.getTime_span());
                            byDistance.setText(findNotPayRoute.getDistance());
                            byExpend.setText(findNotPayRoute.getCalories());
                            byPay.setText(findNotPayRoute.getTotal_fee() + "");
                            tvNeed.setText(findNotPayRoute.getTotal_fee() + "");
                            tvYe.setText(findNotPayRoute.getBalance());
                            price = findNotPayRoute.getTotal_fee() + "";
                            bike_number = findNotPayRoute.getBike_number();
                        } else {
                            showShort(findNotPayRoute.getMsg());
                        }
                    }
                });


    }

    String info;

    @OnClick({R.id.cb_we, R.id.cb_zfb, R.id.cb_wallet, R.id.confirm, R.id.youhuiquan})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.youhuiquan:
                if (findNotPayRoute.getCoupon() != null) {
                    if (findNotPayRoute.getCoupon().size() == 0) {
                        showShort("暂无可用的优惠券");
                    } else {
                        Intent intent = new Intent(this, Mycoupon_chooles_Activity.class);
                        intent.putExtra("findNotPayRoute", findNotPayRoute);
                        startActivity(intent);
                    }
                } else {
                    showShort("暂无可用的优惠券");
                }

                break;
            case R.id.cb_we:
                initview();
                cbWe.setChecked(true);
                pay_type = "wx";
                break;
            case R.id.cb_zfb:
                initview();
                cbZfb.setChecked(true);
                pay_type = "zfb";
                break;
            case R.id.cb_wallet:
                initview();
                cbWallet.setChecked(true);
                pay_type = "wallet";
                break;
            case R.id.confirm:
                if (pay_type=="") {
                    showShort("请选择一种支付方式");
                } else {
                    String url = Apis.Base + Apis.submitLeaseOrder;
                    String cookie = new UserService(OverPayActivity.this).getCookie();
                    Log.d("cid的值", cid + "");
                    OkHttpUtils
                            .post()
                            .url(url)
                            .addHeader("cookie", cookie)
                            .addParams("price", findNotPayRoute.getTotal_fee()+"")
                            .addParams("bike_number", bike_number)
                            .addParams("pay_type", pay_type)
                            .addParams("cid", cid + "")
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
                                                PayTask payTask = new PayTask(OverPayActivity.this);
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
                                            startActivity(ResultActivity.class, "type", "timestop");
                                            showShort(p.getMsg());
                                            finish();
                                        } else {
                                            showShort(p.getMsg());
                                        }
                                    }

                                }
                            });

                }
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
                Toast.makeText(OverPayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(ResultActivity.class, "type", "timestop");
                        finish();
                    }
                });
            } else {
                // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                Toast.makeText(OverPayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
            }
        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        if (cid != 0) {
            tvHas.setText("已选一张");
        }

        if (findNotPayRoute != null) {
            if (findNotPayRoute.getCode() == 1) {
                if (findNotPayRoute.getCoupon().size() == 0) {
                    showShort("暂无可用的优惠券");
                    tvNeed.setText(findNotPayRoute.getTotal_fee() + "");
                } else {
                    for (int i = 0; i < findNotPayRoute.getCoupon().size(); i++) {
                        if (cid != 0) {
                            if (findNotPayRoute.getCoupon().get(i).getUsercou_id() == cid) {
                                if (findNotPayRoute.getCoupon().get(i).getCou_type().equals("折扣")) {
                                    tvAs.setText("-￥" +( findNotPayRoute.getTotal_fee() -
                                            (findNotPayRoute.getTotal_fee() * findNotPayRoute.getCoupon().get(i).getCou_discount())));
                                    tvNeed.setText(findNotPayRoute.getTotal_fee() * findNotPayRoute.getCoupon().get(i).getCou_discount() + "");
                                    break;
                                } else {
                                    tvAs.setText("-￥" + findNotPayRoute.getCoupon().get(i).getCou_cut());
                                    tvNeed.setText(findNotPayRoute.getTotal_fee() - findNotPayRoute.getCoupon().get(i).getCou_cut() + "");
                                    break;
                                }
                            } else {
                                tvNeed.setText(findNotPayRoute.getTotal_fee() + "");
                            }
                        }else {
                            tvAs.setText("");
                            tvNeed.setText(findNotPayRoute.getTotal_fee() + "");
                        }
                    }

                }

            }
        }

        L.d("wchat-0", PayCore.getInstance().mWeichatState + "");
        if (PayCore.getInstance().mWeichatState == PayCore.WeiChat_Pay_Success) {
            L.d("wchat-1", PayCore.getInstance().mWeichatState + "");
            PayCore.getInstance().mWeichatState = PayCore.WeiChat_Pay_Normal;
            startActivity(ResultActivity.class, "type", "timestop");
            finish();
        }
    }

    private void initview() {
        cbWallet.setChecked(false);
        cbWe.setChecked(false);
        cbZfb.setChecked(false);
    }

}
