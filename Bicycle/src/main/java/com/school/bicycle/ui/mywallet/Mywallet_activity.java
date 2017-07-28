package com.school.bicycle.ui.mywallet;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.school.bicycle.R;
import com.school.bicycle.entity.BaseResult;
import com.school.bicycle.entity.Wallet;
import com.school.bicycle.global.Apis;
import com.school.bicycle.global.BaseToolBarActivity;
import com.school.bicycle.global.UserService;
import com.school.bicycle.ui.Details.DetailsActivity;
import com.school.bicycle.ui.Withdrawals.WithdrawalsActivity;
import com.school.bicycle.ui.authentication.RealnameActivity;
import com.school.bicycle.ui.result.ResultActivity;
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

    Wallet wallet;

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
        String cookie = new UserService(Mywallet_activity.this).getCookie();


        OkHttpUtils.get()
                .url(url) .addHeader("cookie",cookie)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("response", response);
                        wallet = gson.fromJson(response, Wallet.class);
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

    @Override
    protected void onResume() {
        super.onResume();
        String tixian = new UserService(Mywallet_activity.this).gettixian();
        if(tixian.equals("1")){
            new UserService(Mywallet_activity.this).settixian("0");
            finish();
        }
    }

    @OnClick({R.id.mywallet_balance, R.id.balance_Withdrawals, R.id.mywallet_refund, R.id.income_Withdrawals, R.id.mywallet_Recharge})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mywallet_balance:
                break;
            case R.id.balance_Withdrawals:
                startActivity(DetailsActivity.class);
                break;
            case R.id.mywallet_refund:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("提示");
                builder.setMessage("是否退还押金");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO: 2017/7/25 押金退款
                        String url = Apis.Base + Apis.depositRefund;
                        OkHttpUtils
                                .get()
                                .url(url)
                                .build()
                                .execute(new StringCallback() {
                                    @Override
                                    public void onError(Call call, Exception e, int id) {

                                    }

                                    @Override
                                    public void onResponse(String response, int id) {
                                        BaseResult baseResult = gson.fromJson(response, BaseResult.class);
                                        if (baseResult.getCode() == 1) {
                                            startActivity(ResultActivity.class, "type", "yajin");
                                            finish();
                                        } else {
                                            showShort(baseResult.getMsg());
                                        }
                                    }
                                });
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                break;
            case R.id.income_Withdrawals:
                startActivity(WithdrawalsActivity.class, "getShare_income", wallet.getShare_income());
                break;
            case R.id.mywallet_Recharge:

                break;
        }
    }
}
