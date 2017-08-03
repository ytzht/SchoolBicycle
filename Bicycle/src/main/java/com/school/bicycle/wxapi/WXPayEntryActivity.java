package com.school.bicycle.wxapi;

import com.school.bicycle.R;
import com.school.bicycle.entity.WeiXinPayResultEvent;
import com.school.bicycle.global.BaseEvent;
import com.school.bicycle.global.BaseToolBarActivity;
import com.school.bicycle.global.L;
import com.school.bicycle.global.PayCore;
import com.school.bicycle.global.ToastManager;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.logging.Logger;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "com.Schoolbiycle.WXPayEntryActivity";

    private IWXAPI api;

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

        Log.v("wechat", "onReq() returned: " + req);
    }

    @Override
    public void onResp(BaseResp baseResp) {
        String message ;
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (0 == baseResp.errCode) {
                ToastManager.send(this, "支付成功");
                PayCore.getInstance().mWeichatState = PayCore.WeiChat_Pay_Success;
            } else if (-1 == baseResp.errCode) {
                ToastManager.send(this, "支付失败");
                PayCore.getInstance().mWeichatState = PayCore.WeiChat_Pay_Failed;
            } else if (-2 == baseResp.errCode) {
                ToastManager.send(this, "取消支付");
                PayCore.getInstance().mWeichatState = PayCore.WeiChat_Pay_Failed;
            } else {
                ToastManager.send(this, "支付失败");
                PayCore.getInstance().mWeichatState = PayCore.WeiChat_Pay_Failed;
            }
            finish();
        }
    }
}