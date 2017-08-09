package com.school.bicycle.ui.setup;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.school.bicycle.R;
import com.school.bicycle.global.BaseToolBarActivity;
import com.school.bicycle.ui.AboutUsActivity;
import com.school.bicycle.ui.setup_banbenhaoActivity;
import com.school.bicycle.ui.setup_chongzhiActivity;
import com.school.bicycle.ui.setup_despositActivity;
import com.school.bicycle.ui.setup_userActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Setup_Activity extends BaseToolBarActivity {


    @BindView(R.id.setup_banbenhao)
    RelativeLayout setupBanbenhao;
    @BindView(R.id.setup_address)
    RelativeLayout setupAddress;
    @BindView(R.id.setup_UserProtocol)
    RelativeLayout setupUserProtocol;
    @BindView(R.id.setup_RechargeProtocol)
    RelativeLayout setupRechargeProtocol;
    @BindView(R.id.setup_desposit)
    RelativeLayout setupDesposit;
    @BindView(R.id.setup_aboutus)
    RelativeLayout setupAboutus;
    @BindView(R.id.setup_userLogout)
    RelativeLayout setupUserLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        ButterKnife.bind(this);
        setToolbarText("设置");
    }


    @OnClick({R.id.setup_banbenhao, R.id.setup_address, R.id.setup_UserProtocol, R.id.setup_RechargeProtocol, R.id.setup_desposit, R.id.setup_aboutus, R.id.setup_userLogout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setup_banbenhao:
                startActivity(setup_banbenhaoActivity.class);
                break;
            case R.id.setup_address:
                break;
            case R.id.setup_UserProtocol:
                startActivity(setup_userActivity.class);
                break;
            case R.id.setup_RechargeProtocol:
                startActivity(setup_chongzhiActivity.class);
                break;
            case R.id.setup_desposit:
                startActivity(setup_despositActivity.class);
                break;
            case R.id.setup_aboutus:
                startActivity(AboutUsActivity.class);
                break;
            case R.id.setup_userLogout:
                break;
        }
    }
}
