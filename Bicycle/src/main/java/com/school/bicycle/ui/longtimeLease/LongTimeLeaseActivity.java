package com.school.bicycle.ui.longtimeLease;


import android.os.Bundle;
import com.school.bicycle.R;
import com.school.bicycle.global.BaseToolBarActivity;

public class LongTimeLeaseActivity extends BaseToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_time_lease);
        setToolbarText("长租");
    }
}
