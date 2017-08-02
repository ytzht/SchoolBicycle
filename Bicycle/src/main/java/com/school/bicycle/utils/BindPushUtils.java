package com.school.bicycle.utils;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.school.bicycle.entity.BaseResult;
import com.school.bicycle.entity.User;
import com.school.bicycle.global.Apis;
import com.school.bicycle.global.L;
import com.school.bicycle.global.UserService;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by zht on 2017/08/02 13:37
 */

public class BindPushUtils {

    public static void bind(final Context context) {

        final String url = Apis.Base + Apis.getUserInfo;
        String cookie = new UserService(context).getCookie();
        OkHttpUtils.get()
                .url(url)
                .addHeader("cookie", cookie)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("response", response);
                        User user = (new Gson()).fromJson(response, User.class);
                        if (user.getCode() == 1) {

                            bindId(context, user.getPhone());


                        }
                    }
                });


    }

    private static void bindId(Context context, String phone) {

        final String deviceToken = new UserService(context).getDeviceToken();
        OkHttpUtils
                .post()
                .url(Apis.Base + Apis.DiviceToken)
                .addHeader("divice_token", deviceToken)
                .addParams("phone", phone)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        BaseResult result = (new Gson()).fromJson(response, BaseResult.class);
                        if (result.getCode() == 1){
                            L.d("umengPush 友盟推送绑定成功，id："+ deviceToken);
                        }else {
                            L.e("umengPush 友盟推送绑定失败，msg："+ result.getMsg());
                        }

                    }
                });
    }
}
