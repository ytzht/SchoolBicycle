package com.school.bicycle.wxapi;

import com.school.bicycle.R;
import com.school.bicycle.global.BaseToolBarActivity;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import android.content.Intent;
import android.os.Bundle;

import org.greenrobot.eventbus.EventBus;


public class WXPayEntryActivity extends BaseToolBarActivity implements IWXAPIEventHandler {

    private static final String TAG = "com.Schoolbiycle.WXPayEntryActivity";

    private IWXAPI api;
    public static  int wxreslut;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);
        api = WXAPIFactory.createWXAPI(this, "wx71fd0a9986ec271f");
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {

        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            wxreslut = resp.errCode;
            switch (resp.errCode) {
                case 0:
                    showShort("支付成功");

                    break;
                case -2:
                    showShort("取消支付");

                    break;
                default:
                    showShort("支付失败");

                    break;
            }
        }
    }
}