package com.school.bicycle.ui.lockclose;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.school.bicycle.R;
import com.school.bicycle.global.BaseToolBarActivity;

public class LockcloseActivity extends BaseToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lockclose);
        setToolbarText("锁车");
    }
}
