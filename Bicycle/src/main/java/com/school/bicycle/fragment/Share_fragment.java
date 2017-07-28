package com.school.bicycle.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.school.bicycle.R;
import com.school.bicycle.adapter.Consumption_adapter;
import com.school.bicycle.adapter.ShareDetails_adapter;
import com.school.bicycle.entity.Consumption;
import com.school.bicycle.entity.QueryShareDetails;
import com.school.bicycle.global.Apis;
import com.school.bicycle.global.BaseFragment;
import com.school.bicycle.global.UserService;
import com.school.bicycle.ui.authentication.RealnameActivity;
import com.umeng.qq.tencent.BaseApi;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/7/19.
 */

public class Share_fragment extends BaseFragment {
    private  ListView consumption_listview;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        consumption_listview = (ListView) view.findViewById(R.id.consumption_listview);
        String url = Apis.Base + Apis.queryShareDetails;
        String cookie = new UserService(getActivity()).getCookie();

        OkHttpUtils.get()
                .url(url) .addHeader("cookie",cookie)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("re",response);
                        Gson gson  = new Gson();
                        QueryShareDetails queryShareDetails = gson.fromJson(response,QueryShareDetails.class);
                        ShareDetails_adapter c =
                                new ShareDetails_adapter(getActivity(),queryShareDetails.getShare_details());
                        consumption_listview.setAdapter(c);

                    }
                });
    }



}
