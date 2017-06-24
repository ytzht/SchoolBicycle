package com.school.bicycle.ui.Withdrawals;

import android.os.Bundle;

import com.school.bicycle.R;
import com.school.bicycle.global.BaseToolBarActivity;

public class WithdrawalsActivity extends BaseToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawals);
        setToolbarText("提现");
    }
}
