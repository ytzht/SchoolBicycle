package com.school.bicycle.ui.MyRoute;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.school.bicycle.R;
import com.school.bicycle.adapter.GetMyRoute_adapter;
import com.school.bicycle.entity.GetMyRoute;
import com.school.bicycle.global.Apis;
import com.school.bicycle.global.BaseToolBarActivity;
import com.school.bicycle.global.UserService;
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
    GetMyRoute getMyRouteadd;
    @BindView(R.id.myroute_te)
    TextView myrouteTe;
    int pageNumber = 1;
    ProgressDialog dialogproa ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_route);
        ButterKnife.bind(this);
        setToolbarText("我的行程");
        initview();
    }

    private void initview() {
        dialogproa = new ProgressDialog(MyRoute_Activity.this);
        dialogproa.setMessage("请稍候...");
        dialogproa.setCancelable(false);
        dialogproa.show();
        String url = Apis.Base + Apis.getMyRoute;
        String cookie = new UserService(MyRoute_Activity.this).getCookie();

        OkHttpUtils.get()
                .url(url).addHeader("cookie", cookie)
                .addParams("pageNumber", pageNumber + "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                        Log.d(TAG, "onResponse我的行程: " + response);
                        getMyRoute = gson.fromJson(response, GetMyRoute.class);
                        if (getMyRoute.getBody().size() == 0) {
                            if (dialogproa.isShowing()) {
                                dialogproa.dismiss();
                            }
                            myrouteTe.setVisibility(View.VISIBLE);
                            getMyRouteList.setVisibility(View.GONE);
                        } else {
                            if (getMyRoute.getBody().size() < 20) {
                                getMyRouteList.setVisibility(View.VISIBLE);
                                myrouteTe.setVisibility(View.GONE);
                                GetMyRoute_adapter getMyRouteAdapter = new GetMyRoute_adapter(MyRoute_Activity.this, getMyRoute.getBody());
                                getMyRouteList.setAdapter(getMyRouteAdapter);
                            } else {
                                pageNumber++;
                                addmyroute();
                            }
                        }
                    }
                });

        getMyRouteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(MyRoute_Activity.this, xingcheng_map_acvitity.class);
                it.putExtra("getMyRoute", getMyRoute);
                it.putExtra("position", position + "");
                startActivity(it);
            }
        });


    }


    private void addmyroute() {
        String url = Apis.Base + Apis.getMyRoute;
        String cookie = new UserService(MyRoute_Activity.this).getCookie();

        OkHttpUtils.get()
                .url(url).addHeader("cookie", cookie)
                .addParams("pageNumber", pageNumber + "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                        Log.d(TAG, "onResponse我的行程: " + response);
                        getMyRouteadd = gson.fromJson(response, GetMyRoute.class);
                        if (getMyRouteadd.getBody().size() == 0) {
                            if (dialogproa.isShowing()) {
                                dialogproa.dismiss();
                            }
                            myrouteTe.setVisibility(View.VISIBLE);
                            getMyRouteList.setVisibility(View.GONE);
                        } else {

                            if (getMyRouteadd.getBody().size() < 20) {
                                if (dialogproa.isShowing()) {
                                    dialogproa.dismiss();
                                }
                                for (int i = 0; i < getMyRouteadd.getBody().size(); i++) {
                                    getMyRoute.getBody().add(getMyRouteadd.getBody().get(i));
                                }
                                getMyRouteList.setVisibility(View.VISIBLE);
                                myrouteTe.setVisibility(View.GONE);
                                GetMyRoute_adapter getMyRouteAdapter = new GetMyRoute_adapter(MyRoute_Activity.this, getMyRoute.getBody());
                                getMyRouteList.setAdapter(getMyRouteAdapter);
                            } else {
                                for (int i = 0; i < getMyRouteadd.getBody().size(); i++) {
                                    getMyRoute.getBody().add(getMyRouteadd.getBody().get(i));
                                }
                                pageNumber++;
                                addmyroute();
                            }

                        }


                    }
                });
    }
}
