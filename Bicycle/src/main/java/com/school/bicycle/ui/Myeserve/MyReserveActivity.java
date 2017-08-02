package com.school.bicycle.ui.Myeserve;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.school.bicycle.R;
import com.school.bicycle.adapter.Myreserve_adapter;
import com.school.bicycle.entity.BaseResult;
import com.school.bicycle.entity.MyAppoint;
import com.school.bicycle.global.Apis;
import com.school.bicycle.global.BaseToolBarActivity;
import com.school.bicycle.global.UserService;
import com.school.bicycle.ui.authentication.RealnameActivity;
import com.school.bicycle.ui.main.MainActivity;
import com.school.bicycle.ui.usebicycle.UseBicycleActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class MyReserveActivity extends BaseToolBarActivity implements Myreserve_adapter.Callback {

    @BindView(R.id.myreserve_list)
    ListView myreserveList;

    MyAppoint myAppoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reserve);
        ButterKnife.bind(this);
        setToolbarText("我的预约");
        initview();
    }

    private void initview() {
        String url = Apis.Base + Apis.getMyAppoint;
        String cookie = new UserService(MyReserveActivity.this).getCookie();

        OkHttpUtils.get()
                .url(url).addHeader("cookie",cookie)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("response我的预约", response);
                        myAppoint = gson.fromJson(response, MyAppoint.class);
                        if (myAppoint.getCode() == 1) {
                            Myreserve_adapter myreserve_adapter = new Myreserve_adapter(MyReserveActivity.this, myAppoint.getMy_appoint(), MyReserveActivity.this);
                            myreserveList.setAdapter(myreserve_adapter);
                        } else {
                            showShort(myAppoint.getMsg());
                        }
                    }
                });
    }

    @Override
    public void ondetialClick(View v) {
        Intent it = new Intent(MyReserveActivity.this, MainActivity.class);
        it.putExtra("bike_number",String.valueOf(myAppoint.getMy_appoint().get((Integer) v.getTag()).getNumber()));
        startActivity(it);

    }

    @Override
    public void oncancleClick(final View view) {
        Log.d("AlertDialog","AlertDialog");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("据用车时间少于24h，\n取消将扣除10%手续费，是否取消");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String url = Apis.Base + Apis.cancelMyAppoint;
                OkHttpUtils
                        .post()
                        .url(url)
                        .addParams("aid", String.valueOf(myAppoint.getMy_appoint().get((Integer) view.getTag()).getAid()))
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(String response, int id) {
                                BaseResult baseResult = gson.fromJson(response,BaseResult.class);
                                if (baseResult.getCode()==1){
                                    showShort(baseResult.getMsg());
                                    initview();
                                    myreserveList.refreshDrawableState();
                                }else {
                                    showShort("取消失败");
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


    }
}
