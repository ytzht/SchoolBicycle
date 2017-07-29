package com.school.bicycle.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.school.bicycle.R;
import com.school.bicycle.global.BaseToolBarActivity;
import com.school.bicycle.ui.lockopen.LockOpenActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class openbynum_Activity extends BaseToolBarActivity {

    @BindView(R.id.ed_biyclenum_open)
    EditText edBiyclenumOpen;
    @BindView(R.id.linear1_openbynum)
    LinearLayout linear1Openbynum;
    @BindView(R.id.linear2_openbynum)
    LinearLayout linear2Openbynum;
    @BindView(R.id.tv_bynum_now)
    TextView tvBynumNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openbynum_);
        ButterKnife.bind(this);
        setToolbarText("号码开锁");
        String location = getIntent().getStringExtra("location");
    }

    @OnClick({R.id.linear1_openbynum, R.id.linear2_openbynum, R.id.tv_bynum_now})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.linear1_openbynum:
                break;
            case R.id.linear2_openbynum:
                finish();
                break;
            case R.id.tv_bynum_now:
                String location = getIntent().getStringExtra("location");
                String num = edBiyclenumOpen.getText().toString();
                if (!num.isEmpty()){
                    startActivity(LockOpenActivity.class, "lock_code", num, "location", location);
                    finish();
                }

                break;
        }
    }
}
