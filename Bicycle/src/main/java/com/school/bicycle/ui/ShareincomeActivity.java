package com.school.bicycle.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.school.bicycle.R;
import com.school.bicycle.adapter.ShareDetails_adapter;
import com.school.bicycle.entity.QueryShareDetails;
import com.school.bicycle.global.Apis;
import com.school.bicycle.global.BaseToolBarActivity;
import com.school.bicycle.global.UserService;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class ShareincomeActivity extends BaseToolBarActivity {

    @BindView(R.id.list_share)
    ListView listShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shareincome);
        ButterKnife.bind(this);
        setToolbarText("共享收益");
        initView();
    }


    private void initView() {
        String url = Apis.Base + Apis.queryShareDetails;
        String cookie = new UserService(ShareincomeActivity.this).getCookie();
        OkHttpUtils.get()
                .url(url).addHeader("cookie", cookie)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("re共享收入", response);
                        Gson gson = new Gson();
                        QueryShareDetails queryShareDetails = gson.fromJson(response, QueryShareDetails.class);
                        if (queryShareDetails.getMsg().equals("暂无数据")) {
                            showShort("暂无数据");
                        } else {
                            ShareDetails_adapter c = new ShareDetails_adapter(getBaseContext(), queryShareDetails.getShare_details());
                            listShare.setAdapter(c);
                        }


                    }
                });
    }
}
