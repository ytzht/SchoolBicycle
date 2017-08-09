package com.school.bicycle.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.school.bicycle.R;
import com.school.bicycle.global.BaseToolBarActivity;

public class setup_banbenhaoActivity extends BaseToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_banbenhao);
        setToolbarText("版本信息");
    }
}
