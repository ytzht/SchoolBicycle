package com.school.bicycle.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.school.bicycle.R;
import com.school.bicycle.adapter.Mycoupon_adapter;
import com.school.bicycle.entity.BaseResult;
import com.school.bicycle.entity.Mycoupon;
import com.school.bicycle.global.Apis;
import com.school.bicycle.global.BaseActivity;
import com.school.bicycle.global.L;
import com.school.bicycle.global.UserService;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class Mycoupon_Activity extends BaseActivity {

    @BindView(R.id.my_coupon_back)
    ImageView myCouponBack;
    @BindView(R.id.my_coupon_make)
    TextView myCouponMake;
    @BindView(R.id.mycoupon_ed)
    EditText mycouponEd;
    @BindView(R.id.mycoupon_ok)
    TextView mycouponOk;
    @BindView(R.id.mycoupon_list)
    ListView mycouponList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycoupon_);
        ButterKnife.bind(this);
        initview();
    }

    private void initview() {

        String url = Apis.Base + Apis.myCoupon;
        String cookie = new UserService(Mycoupon_Activity.this).getCookie();
        OkHttpUtils.post()
                .url(url)
                .addHeader("cookie", cookie)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("我的卡券",response);
                        Mycoupon mycoupon = gson.fromJson(response,Mycoupon.class);
                        if (mycoupon.getCode()==1){
                            Mycoupon_adapter m = new Mycoupon_adapter(getBaseContext(),mycoupon.getBody());
                            mycouponList.setAdapter(m);
                        }else {
                            showShort("暂无优惠券！");
                        }

                    }
                });
    }

    @OnClick({R.id.my_coupon_back, R.id.my_coupon_make, R.id.mycoupon_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_coupon_back:
                finish();
                break;
            case R.id.my_coupon_make:
                break;
            case R.id.mycoupon_ok:
                if (mycouponEd.getText().equals("")){
                    showShort("请输入兑换码");
                }else {
                    String code = mycouponEd.getText().toString();
                    String url = Apis.Base + Apis.cashCoupon;
                    String cookie = new UserService(Mycoupon_Activity.this).getCookie();
                    OkHttpUtils.post()
                            .url(url)
                            .addHeader("cookie", cookie)
                            .addParams("code",code)
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {

                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    Log.d("兑换码",response);
                                    BaseResult baseResult = gson.fromJson(response,BaseResult.class);
                                    if (baseResult.getCode()==1) {
                                        showShort(baseResult.getMsg());
                                        initview();
                                    }else {
                                        showShort(baseResult.getMsg());
                                    }
                                }
                            });
                }

                break;
        }
    }
}
