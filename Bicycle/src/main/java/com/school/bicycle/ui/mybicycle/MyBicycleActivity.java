package com.school.bicycle.ui.mybicycle;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import com.school.bicycle.R;
import com.school.bicycle.global.BaseToolBarActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyBicycleActivity extends BaseToolBarActivity {

    @BindView(R.id.re_biyclenum_mybiycle)
    RelativeLayout reBiyclenumMybiycle;
    @BindView(R.id.re_Income_mybiycle)
    RelativeLayout reIncomeMybiycle;
    @BindView(R.id.re_myTrip_mybiycle)
    RelativeLayout reMyTripMybiycle;
    @BindView(R.id.re_mystate_mybiycle)
    RelativeLayout reMystateMybiycle;
    @BindView(R.id.re_myReservation_mybiycle)
    RelativeLayout reMyReservationMybiycle;
    @BindView(R.id.re_mypolice_mybiycle)
    RelativeLayout reMypoliceMybiycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bicycle);
        ButterKnife.bind(this);
        setToolbarText("我的车辆");
    }

    @OnClick({R.id.re_biyclenum_mybiycle, R.id.re_Income_mybiycle, R.id.re_myTrip_mybiycle, R.id.re_mystate_mybiycle, R.id.re_myReservation_mybiycle, R.id.re_mypolice_mybiycle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.re_biyclenum_mybiycle:
                break;
            case R.id.re_Income_mybiycle:
                break;
            case R.id.re_myTrip_mybiycle:
                break;
            case R.id.re_mystate_mybiycle:
                break;
            case R.id.re_myReservation_mybiycle:
                break;
            case R.id.re_mypolice_mybiycle:
                break;
        }
    }
}
