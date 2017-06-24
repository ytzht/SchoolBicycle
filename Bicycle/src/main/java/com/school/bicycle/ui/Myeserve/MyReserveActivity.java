package com.school.bicycle.ui.Myeserve;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.school.bicycle.R;
import com.school.bicycle.global.BaseToolBarActivity;

public class MyReserveActivity extends BaseToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reserve);
        setToolbarText("我的预约");
    }
}
