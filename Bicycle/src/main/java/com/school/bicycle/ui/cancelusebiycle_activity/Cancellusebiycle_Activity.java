package com.school.bicycle.ui.cancelusebiycle_activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.school.bicycle.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Cancellusebiycle_Activity extends AppCompatActivity {

    @BindView(R.id.cub_back)
    TextView cubBack;
    @BindView(R.id.cub_cancel)
    TextView cubCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cancelthebiycle_layout);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.cub_back, R.id.cub_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cub_back:
                break;
            case R.id.cub_cancel:
                break;
        }
    }
}
