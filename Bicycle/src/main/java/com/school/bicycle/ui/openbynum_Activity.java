package com.school.bicycle.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.school.bicycle.R;
import com.school.bicycle.global.BaseToolBarActivity;
import com.school.bicycle.ui.lockopen.LockOpenActivity;
import com.school.bicycle.ui.main.MainActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.school.bicycle.ui.ZxingActivity.isOpen;

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
        String location = getIntent().getStringExtra("location");
        String status = getIntent().getStringExtra("status");
        switch (view.getId()) {
            case R.id.linear1_openbynum:
                if (!isOpen) {
                    CodeUtils.isLightEnable(true);
                    isOpen = true;
                } else {
                    CodeUtils.isLightEnable(false);
                    isOpen = false;
                }
                break;
            case R.id.linear2_openbynum:
                startActivity(openbynum_Activity.class, "location", location,"status",status);
                finish();
                break;
            case R.id.tv_bynum_now:
                String num = edBiyclenumOpen.getText().toString();
                if (!num.isEmpty()){
                    if (status.equals("0")){
                        Intent it = new Intent(openbynum_Activity.this, MainActivity.class);
                        it.putExtra("bike_number",num);
                        startActivity(it);
                        finish();
                    }else {
                        startActivity(LockOpenActivity.class, "lock_code", num, "location", location);
                        finish();
                    }

                }

                break;
        }
    }
}
