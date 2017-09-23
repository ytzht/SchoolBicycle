package com.school.bicycle.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.school.bicycle.R;
import com.school.bicycle.global.BaseActivity;
import com.school.bicycle.ui.main.MainActivity;

public class AppLauncherActivity extends BaseActivity {

    private static final String TAG = "Launch=====";
    private String first = "";
    SharedPreferences sp;
    private long time = 3000;

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {

        super.onResume();

//        if (first.equals("no")) {
//            new Handler().postDelayed(new Runnable() {
//
//                @Override
//                public void run() {
//                    superFinish();
//                }
//            }, time);
//        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_app_launcher);

        PackageManager packageManager = getPackageManager();
        sp = getSharedPreferences("locationDis", MODE_PRIVATE);

        try {
            PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
            String version = packInfo.versionName;
            SharedPreferences preferences = getSharedPreferences("first_open", MODE_PRIVATE);
            first = preferences.getString("first", "");
            Log.d(TAG, "onCreate: " + first);
            if (!first.equals(version)) {//第一次打开应用

                //引导页
                ViewPager viewpager = (ViewPager) findViewById(R.id.viewpager);
//            CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
                viewpager.setAdapter(new SamplePagerAdapter());
//            indicator.setViewPager(viewpager);

                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("first", version);
                editor.apply();
            } else {
                goMain();
            }

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
                    superFinish(1);
                }
            }, time);
        }

    }

    private void goMain() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                superFinish(0);
            }
        }, time);
    }

    public void superFinish(int i) {
        if(i==0){
            startActivity(MainActivity.class,"first","0");
            super.finish();
        }else {
            startActivity(MainActivity.class,"first","1");
            super.finish();
        }

    }


    @Override
    public void finish() {
    }


    private class SamplePagerAdapter extends PagerAdapter {
        private int mSize;

        SamplePagerAdapter() {
            mSize = 3;
        }

        @Override
        public int getCount() {
            return mSize;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            view.removeView((View) object);
        }


        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            ImageView imageView = new ImageView(view.getContext());
            if (position == 0) {
                imageView.setImageResource(R.drawable.launcher_01);
            } else if (position == 1) {
                imageView.setImageResource(R.drawable.launcher_02);
            } else {
                imageView.setImageResource(R.drawable.launcher_03);
            }

            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            if (position == 2) {
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        superFinish(1);
                    }
                });
            }
            view.addView(imageView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams
                    .MATCH_PARENT);
            return imageView;
        }

    }
}
