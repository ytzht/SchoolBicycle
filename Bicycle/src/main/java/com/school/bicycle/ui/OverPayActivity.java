package com.school.bicycle.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.school.bicycle.R;
import com.school.bicycle.global.Apis;
import com.school.bicycle.global.BaseToolBarActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class OverPayActivity extends BaseToolBarActivity {

    @BindView(R.id.by_time)
    TextView byTime;
    @BindView(R.id.by_distance)
    TextView byDistance;
    @BindView(R.id.by_expend)
    TextView byExpend;
    @BindView(R.id.by_pay)
    TextView byPay;
    @BindView(R.id.tv_has)
    TextView tvHas;
    @BindView(R.id.tv_as)
    TextView tvAs;
    @BindView(R.id.tv_ye)
    TextView tvYe;
    @BindView(R.id.tv_need)
    TextView tvNeed;
    @BindView(R.id.cb_we)
    CheckBox cbWe;
    @BindView(R.id.cb_zfb)
    CheckBox cbZfb;
    @BindView(R.id.confirm)
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_pay);
        ButterKnife.bind(this);
        setToolbarText("付款");



    }



    @OnClick({R.id.cb_we, R.id.cb_zfb, R.id.confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cb_we:
                break;
            case R.id.cb_zfb:
                break;
            case R.id.confirm:
                break;
        }
    }
}
