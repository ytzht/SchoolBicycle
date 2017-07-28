package com.school.bicycle.ui.MyRoute;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.school.bicycle.R;
import com.school.bicycle.adapter.GetMyRoute_adapter;
import com.school.bicycle.entity.GetMyRoute;
import com.school.bicycle.global.Apis;
import com.school.bicycle.global.BaseToolBarActivity;
import com.school.bicycle.global.UserService;
import com.school.bicycle.ui.authentication.RealnameActivity;
import com.school.bicycle.ui.xingcheng_map_acvitity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class MyRoute_Activity extends BaseToolBarActivity {


    private static final String TAG = "MyRouteAct=====";
    @BindView(R.id.getMyRoute_list)
    ListView getMyRouteList;

    GetMyRoute getMyRoute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_route);
        ButterKnife.bind(this);
        setToolbarText("我的行程");
        initview();
    }

    private void initview() {

        String url = Apis.Base + Apis.getMyRoute;
        String cookie = new UserService(MyRoute_Activity.this).getCookie();

        OkHttpUtils.get()
                .url(url) .addHeader("cookie",cookie)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d(TAG, "onResponse: "+response);
                        getMyRoute = gson.fromJson(response, GetMyRoute.class);
                        GetMyRoute_adapter getMyRouteAdapter = new GetMyRoute_adapter(MyRoute_Activity.this, getMyRoute.getBody());
                        getMyRouteList.setAdapter(getMyRouteAdapter);
                    }
                });

        getMyRouteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(MyRoute_Activity.this, xingcheng_map_acvitity.class);
                it.putExtra("getMyRoute",  getMyRoute);
                it.putExtra("position",  position+"");
                startActivity(it);
            }
        });


    }
}
