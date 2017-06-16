package com.school.bicycle.ui.eposit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.school.bicycle.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DepositActivity extends AppCompatActivity {

    @BindView(R.id.rdb_wchatpay)
    RadioButton rdbWchatpay;
    @BindView(R.id.rdb_alipay)
    RadioButton rdbAlipay;
    @BindView(R.id.dps_paynow)
    Button dpsPaynow;
    @BindView(R.id.dps_back)
    ImageView dpsBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__deposit);
        ButterKnife.bind(this);
    }
}
