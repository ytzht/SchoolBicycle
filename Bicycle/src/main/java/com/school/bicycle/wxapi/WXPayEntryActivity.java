package com.school.bicycle.wxapi;

import com.school.bicycle.R;
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
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.util.logging.Logger;


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

        Log.v("wechat","onReq() returned: " + req);
    }

    @Override
    public void onResp(BaseResp resp) {

        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            wxreslut = resp.errCode;
            switch (resp.errCode) {
                case 0:
                    L.d("wchat0",resp.errCode+"");
                    ToastManager.send(this, "支付成功");
                    PayCore.getInstance().mWeichatState = PayCore.WeiChat_Pay_Success;
                    break;
                case -2:
                    L.d("wchat-2",resp.errCode+"");
                    ToastManager.send(this, "取消支付");
                    PayCore.getInstance().mWeichatState = PayCore.WeiChat_Pay_Cancle;
                    break;
                default:
                    L.d("wchatdefault",resp.errCode+"");
                    ToastManager.send(this, "支付失败");
                    PayCore.getInstance().mWeichatState = PayCore.WeiChat_Pay_Failed;
                    break;
            }


        }
    }
}