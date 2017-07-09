package com.school.bicycle.ui.longtimeLease;


import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.school.bicycle.R;
import com.school.bicycle.global.BaseToolBarActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R.id.wallet_icon)
    ImageView walletIcon;
    @BindView(R.id.cb_wallet)
    CheckBox cbWallet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_alert);
        ButterKnife.bind(this);
        setToolbarText("长租");
    }

    @OnClick({ R.id.month1, R.id.month3, R.id.month6, R.id.month12, R.id.wx_icon, R.id.cb_wx, R.id.zfb_icon, R.id.wallet_icon, R.id.cb_wallet})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.month1:
                break;
            case R.id.month3:
                break;
            case R.id.month6:
                break;
            case R.id.month12:
                break;
            case R.id.wx_icon:
                break;
            case R.id.cb_wx:
                break;
            case R.id.zfb_icon:
                break;
            case R.id.wallet_icon:
                break;
            case R.id.cb_wallet:
                break;
        }
    }
}
