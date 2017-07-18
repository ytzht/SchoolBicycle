package com.school.bicycle.ui.longtimeLease;


import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.school.bicycle.R;
import com.school.bicycle.entity.GetLongLeaseInfo;
import com.school.bicycle.global.BaseToolBarActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_alert);
        ButterKnife.bind(this);
        setToolbarText("长租");
        initlongtimeprice();
    }

    private void initlongtimeprice() {

        String url = getResources().getString(R.string.baseurl) + "order/getLongLeaseInfo";
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
                        GetLongLeaseInfo g = gson.fromJson(response,GetLongLeaseInfo.class);
                        if (g.getCode()==1){
                            month1.setText("月租："+g.getLonglease_info().get(0).get月租());
                            month3.setText("季租："+g.getLonglease_info().get(1).get季租());
                            month6.setText("半年租："+g.getLonglease_info().get(2).get半年租());
                            month12.setText("年租："+g.getLonglease_info().get(3).get年租());
                        }else {

                        }
                    }
                });
    }

    @OnClick({R.id.month1, R.id.month3, R.id.month6, R.id.month12, R.id.wx_icon, R.id.cb_wx, R.id.zfb_icon})
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

        }
    }
}
