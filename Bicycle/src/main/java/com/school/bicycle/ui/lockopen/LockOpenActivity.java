package com.school.bicycle.ui.lockopen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.school.bicycle.R;
import com.school.bicycle.global.BaseToolBarActivity;

public class LockOpenActivity extends BaseToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_open);
        setToolbarText("开锁");
    }
}
