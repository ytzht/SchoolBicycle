package com.school.bicycle.ui.mybicycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.school.bicycle.R;
import com.school.bicycle.global.BaseToolBarActivity;

public class MyBicycleActivity extends BaseToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bicycle);
        setToolbarText("我的车辆");
    }
}
