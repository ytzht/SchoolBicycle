package com.school.bicycle.ui.eposit;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.school.bicycle.R;
import com.school.bicycle.entity.GetDeposit;
import com.school.bicycle.global.BaseToolBarActivity;
import com.school.bicycle.global.UserService;
import com.school.bicycle.ui.authentication.RealnameActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class DepositActivity extends BaseToolBarActivity implements IDepositView {

    @BindView(R.id.rdb_wchatpay)
    RadioButton rdbWchatpay;
    @BindView(R.id.rdb_alipay)
    RadioButton rdbAlipay;
    @BindView(R.id.dps_paynow)
    Button dpsPaynow;
    @BindView(R.id.deposit_num)
    TextView depositNum;


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
                .url(url)  .addHeader("cookie",cookie)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        GetDeposit g = gson.fromJson(response, GetDeposit.class);
                        if (g.getCode() == 1) {
                            depositNum.setText(g.getDeposit());
                        }else {
                            showShort(g.getMsg());
                        }
                    }

                });
    }
}
