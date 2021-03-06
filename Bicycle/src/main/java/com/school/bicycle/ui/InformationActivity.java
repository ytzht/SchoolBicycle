package com.school.bicycle.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.school.bicycle.R;
import com.school.bicycle.adapter.GetMyMessage_adapter;
import com.school.bicycle.entity.GetMyMessage;
import com.school.bicycle.global.Apis;
import com.school.bicycle.global.BaseToolBarActivity;
import com.school.bicycle.global.UserService;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class InformationActivity extends BaseToolBarActivity {

    @BindView(R.id.myinformation_listview)
    ListView myinformationListview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        ButterKnife.bind(this);
        setToolbarText("我的消息");
        initview();
    }
    GetMyMessage g;
    private void initview() {

        String url = Apis.Base + Apis.getMyMessage + "?pageNumber=1";
        String cookie = new UserService(InformationActivity.this).getCookie();

        OkHttpUtils.get()
                .url(url) .addHeader("cookie",cookie)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("response",response);
                        g = gson.fromJson(response, GetMyMessage.class);
                        GetMyMessage_adapter getMyMessage_adapter = new GetMyMessage_adapter(InformationActivity.this,
                                g.getMessage());
                        myinformationListview.setAdapter(getMyMessage_adapter);
                    }
                });

        myinformationListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(MyMsgDetailActivity.class, "msg", g.getMessage().get(position).getContent());
            }
        });
    }
}
