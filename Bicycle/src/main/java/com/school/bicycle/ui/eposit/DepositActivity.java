package com.school.bicycle.ui.eposit;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.school.bicycle.R;
import com.school.bicycle.entity.GetDeposit;
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
import com.school.bicycle.ui.OverPayActivity;
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

public class DepositActivity extends BaseToolBarActivity implements IDepositView {


    @BindView(R.id.deposit_num)
    TextView depositNum;
    @BindView(R.id.rdb_wchatpay)
    RadioButton rdbWchatpay;
    @BindView(R.id.rdb_alipay)
    RadioButton rdbAlipay;
    @BindView(R.id.dps_paynow)
    Button dpsPaynow;

    String deposit_money;
    String pay_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__deposit);
        ButterKnife.bind(this);
        setToolbarText("押金充值");
        initgetmoney();
    }

    private void initgetmoney() {

        //获取押金金额
        String url = getResources().getString(R.string.baseurl) + "user/getDeposit";
        String cookie = new UserService(DepositActivity.this).getCookie();
        OkHttpUtils
                .get()
                .url(url).addHeader("cookie", cookie)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        L.d(response);
                        GetDeposit g = gson.fromJson(response, GetDeposit.class);
                        if (g.getCode() == 1) {
                            depositNum.setText(g.getDeposit());
                            deposit_money = g.getDeposit();
                        } else {
                            showShort(g.getMsg());
                        }
                    }

                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (PayCore.getInstance().mWeichatState == PayCore.WeiChat_Pay_Success) {
            PayCore.getInstance().mWeichatState = PayCore.WeiChat_Pay_Normal;
            startActivity(ResultActivity.class, "type", "yajin");
            finish();
        }
    }

    @OnClick({R.id.rdb_wchatpay, R.id.rdb_alipay, R.id.dps_paynow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rdb_wchatpay:
                initview();
                rdbWchatpay.setChecked(true);
                pay_type = "wx";
                break;
            case R.id.rdb_alipay:
                initview();
                rdbAlipay.setChecked(true);
                pay_type = "zfb";
                break;
            case R.id.dps_paynow:
                if (pay_type.equals("")){
                    showShort("请选择一种支付方式");
                }else {
                    initpay();
                }


                break;
        }
    }

    String info;

    private void initpay() {

        String url = Apis.Base + Apis.submit;
        String cookie = new UserService(DepositActivity.this).getCookie();
        OkHttpUtils
                .post()
                .url(url)
                .addHeader("cookie", cookie)
                .addParams("deposit_money", deposit_money)
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
                                    PayTask payTask = new PayTask(DepositActivity.this);
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
                                showShort(p.getMsg());
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
                Toast.makeText(DepositActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(ResultActivity.class, "type", "yajin");
                        finish();
                    }
                });
            } else {
                // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                Toast.makeText(DepositActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private void initview() {
        rdbAlipay.setChecked(false);
        rdbWchatpay.setChecked(false);
    }
}
