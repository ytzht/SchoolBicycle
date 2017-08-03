package com.school.bicycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.school.bicycle.app.MyApplication;

/**
 * Created by Administrator on 2017/8/3.
 */

public class HooliganActivity extends Activity {

    private static HooliganActivity instance;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        Window window = getWindow();
        window.setGravity(Gravity.LEFT|Gravity.TOP);
        WindowManager.LayoutParams params = window.getAttributes();
        params.x = 0;
        params.y = 0;
        params.height = 1;
        params.width = 1;
        window.setAttributes(params);
    }

    //开启保活界面
    public  static  void  startHooligan(){
        Intent intent = new Intent(MyApplication.aContext,HooliganActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        MyApplication.aContext.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        instance = null;
    }

    public static void killHooligan(){
        if (instance!=null){
            instance.finish();
        }
    }
}
