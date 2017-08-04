package com.school.bicycle.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.school.bicycle.R;
import com.school.bicycle.entity.BikeAlarmList;
import com.school.bicycle.global.Apis;
import com.school.bicycle.global.UserService;
import com.school.bicycle.ui.MyRoute.MyRoute_Activity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class BikeAlarmListActivity extends AppCompatActivity {

    @BindView(R.id.back_call)
    ImageView backCall;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.callpolice)
    ImageView callpolice;
    @BindView(R.id.base_toolbar)
    Toolbar baseToolbar;
    @BindView(R.id.list_callpolice)
    ListView listCallpolice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_alarm_list);
        ButterKnife.bind(this);
        initview();
    }

    private void initview() {
        String iscall = new UserService(BikeAlarmListActivity.this).getiscall();
        if (iscall.equals("0")){
            callpolice.setImageDrawable(getResources().getDrawable(R.drawable.callclose));
        }else {
            callpolice.setImageDrawable(getResources().getDrawable(R.drawable.calloppen));
        }

        String url = Apis.Base + Apis.bikeAlarmList;
        String cookie = new UserService(BikeAlarmListActivity.this).getCookie();

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
                        Log.d("报警啊！",response);
                        BikeAlarmList b  = new Gson().fromJson(response,BikeAlarmList.class);
                        if (b.getCode()==0){
                            Toast.makeText(getBaseContext(),"暂无车辆报警信息",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    @OnClick({R.id.back_call, R.id.callpolice})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_call:
                finish();
                break;
            case R.id.callpolice:
                String iscall = new UserService(BikeAlarmListActivity.this).getiscall();
                if (iscall.equals("0")){
                    new UserService(BikeAlarmListActivity.this).setiscall("1");
                    callpolice.setImageResource(R.drawable.calloppen);
//                    callpolice.setImageDrawable(getResources().getDrawable(R.drawable.calloppen));
                }else {
                    new UserService(BikeAlarmListActivity.this).setiscall("0");
                    callpolice.setImageResource(R.drawable.callclose);
//                    callpolice.setImageDrawable(getResources().getDrawable(R.drawable.callclose));
                }
                break;
        }
    }
}
