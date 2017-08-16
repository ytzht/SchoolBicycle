package com.school.bicycle.ui;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import com.school.bicycle.R;
import com.school.bicycle.global.BaseToolBarActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class setup_banbenhaoActivity extends BaseToolBarActivity {

    @BindView(R.id.banbennum)
    TextView banbennum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_banbenhao);
        ButterKnife.bind(this);
        setToolbarText("版本信息");
        try {
            final PackageInfo packInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            banbennum.setText(packInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
