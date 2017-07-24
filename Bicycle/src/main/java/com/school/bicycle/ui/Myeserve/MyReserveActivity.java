package com.school.bicycle.ui.Myeserve;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.school.bicycle.R;
import com.school.bicycle.adapter.Myreserve_adapter;
import com.school.bicycle.entity.MyAppoint;
import com.school.bicycle.global.Apis;
import com.school.bicycle.global.BaseToolBarActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class MyReserveActivity extends BaseToolBarActivity {

    @BindView(R.id.myreserve_list)
    ListView myreserveList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reserve);
        ButterKnife.bind(this);
        setToolbarText("我的预约");
        initview();
    }

    private void initview() {
        String url = Apis.Base +
                Apis.getMyAppoint;

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
                        MyAppoint myAppoint = gson.fromJson(response, MyAppoint.class);
                        if (myAppoint.getCode() == 1) {
                            Myreserve_adapter myreserve_adapter = new Myreserve_adapter(MyReserveActivity.this,myAppoint.getMy_appoint());
                            myreserveList.setAdapter(myreserve_adapter);
                        } else {
                            showShort(myAppoint.getMsg());
                        }

                    }
                });


    }
}
