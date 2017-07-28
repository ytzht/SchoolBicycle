package com.school.bicycle.ui.Withdrawals;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.school.bicycle.R;
import com.school.bicycle.entity.BaseResult;
import com.school.bicycle.global.Apis;
import com.school.bicycle.global.BaseToolBarActivity;
import com.school.bicycle.global.UserService;
import com.school.bicycle.ui.authentication.RealnameActivity;
import com.school.bicycle.ui.result.ResultActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class WithdrawalsActivity extends BaseToolBarActivity {

    @BindView(R.id.edit_Withdrawals)
    EditText editWithdrawals;
    @BindView(R.id.getShare_income_Withdrawals)
    TextView getShareIncomeWithdrawals;
    @BindView(R.id.ok_Withdrawals)
    TextView okWithdrawals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawals);
        ButterKnife.bind(this);
        setToolbarText("提现");
        initview();
    }

    private void initview() {
        Intent it = getIntent();
        String getShare_income = it.getStringExtra("getShare_income");
        getShareIncomeWithdrawals.setText("本次可提现金额" + getShare_income + "元");
    }

    @OnClick(R.id.ok_Withdrawals)
    public void onViewClicked() {
        if (TextUtils.isEmpty(editWithdrawals.getText())) {
            showShort("请输入提现金额");
        } else {
            String url = Apis.Base + Apis.withdraw;
            String cookie = new UserService(WithdrawalsActivity.this).getCookie();

            OkHttpUtils.post()
                    .url(url) .addHeader("cookie",cookie)
                    .addParams("withdraw_money", editWithdrawals.getText().toString())
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {

                        }

                        @Override
                        public void onResponse(String response, int id) {
                            BaseResult b = gson.fromJson(response,BaseResult.class);
                            if (b.getCode()==1){
                                startActivity(ResultActivity.class,"type","tixian");
                                finish();
                            }else {
                                showShort(b.getMsg());
                            }
                        }
                    });
        }

    }
}
