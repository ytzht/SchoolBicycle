package com.school.bicycle.ui.mywallet;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.school.bicycle.R;
import com.school.bicycle.entity.Wallet;
import com.school.bicycle.global.Apis;
import com.school.bicycle.global.BaseToolBarActivity;
import com.school.bicycle.ui.Details.DetailsActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/7/20.
 */

public class Mywallet_activity extends BaseToolBarActivity {


    @BindView(R.id.mywallet_balance)
    TextView mywalletBalance;
    @BindView(R.id.balance_Withdrawals)
    TextView balanceWithdrawals;
    @BindView(R.id.re_detiles_mybiycle)
    RelativeLayout reBiyclenumMybiycle;
    @BindView(R.id.income_Withdrawals)
    TextView incomeWithdrawals;
    @BindView(R.id.mywallet_income)
    TextView mywalletIncome;
    @BindView(R.id.re_Income_mybiycle)
    RelativeLayout reIncomeMybiycle;
    @BindView(R.id.mywallet_Recharge)
    TextView mywalletRecharge;
    @BindView(R.id.deposit_money)
    TextView depositMoney;
    @BindView(R.id.mywallet_refund)
    TextView mywalletRefund;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_wallet);
        setToolbarText("我的钱包");
        ButterKnife.bind(this);
        initview();
    }

    private void initview() {
        String url = Apis.Base + Apis.wallet;

        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("response", response);
                        Wallet wallet = gson.fromJson(response, Wallet.class);
                        if (wallet.getCode() == 1) {
                            mywalletBalance.setText(wallet.getBalance());
                            mywalletIncome.setText(wallet.getShare_income());
                            depositMoney.setText(wallet.getDeposit_money());
                        } else {
                            showShort("网络出现了一点小问题");
                        }
                    }
                });


    }


    @OnClick({R.id.mywallet_balance, R.id.balance_Withdrawals, R.id.re_detiles_mybiycle, R.id.income_Withdrawals, R.id.mywallet_income})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mywallet_balance:
                break;
            case R.id.balance_Withdrawals:
                break;
            case R.id.re_detiles_mybiycle:
                startActivity(DetailsActivity.class);
                break;
            case R.id.income_Withdrawals:
                break;
            case R.id.mywallet_income:
                break;
        }
    }
}
