package com.school.bicycle.ui.pay;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.school.bicycle.R;
import com.school.bicycle.global.BaseToolBarActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);
        setToolbarText("付款");
    }

    @OnClick({ R.id.cbwechat_pay, R.id.cbali_pay, R.id.cbwallet_pay, R.id.pay_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cbwechat_pay:
                break;
            case R.id.cbali_pay:
                break;
            case R.id.cbwallet_pay:
                break;
            case R.id.pay_sure:
                finish();
                break;
        }
    }


}
