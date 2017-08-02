package com.school.bicycle.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.school.bicycle.R;
import com.school.bicycle.global.BaseActivity;
import com.school.bicycle.ui.main.MainActivity;

public class AppLauncherActivity extends BaseActivity {

    private static final String TAG = "Launch=====";
    private String first = "";
    private long time = 1000;

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {

        super.onResume();

        if (first.equals("no")) {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    superFinish();
                }
            }, time);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_app_launcher);

        PackageManager packageManager = getPackageManager();

        try {
            PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
            String version = packInfo.versionName;


        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash);
        animation.setFillAfter(true);

        SharedPreferences preferences = getSharedPreferences("first_open", Context.MODE_PRIVATE);
        first = preferences.getString("first", "yes");
        Log.d(TAG, "onCreate: " + first);
        if (first.equals("yes")) {//第一次打开应用

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    superFinish();
                }
            }, time);
        }

    }

    public void superFinish() {
        startActivity(MainActivity.class);
        super.finish();
    }


    @Override
    public void finish() {
    }

}
