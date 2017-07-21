package com.school.bicycle.ui.result;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.school.bicycle.R;
import com.school.bicycle.global.BaseToolBarActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResultActivity extends BaseToolBarActivity {


    @BindView(R.id.iv_res_result)
    ImageView ivResResult;
    @BindView(R.id.te_res_result)
    TextView teResResult;
    @BindView(R.id.bt_res_next)
    Button btResNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        setToolbarText("结果");
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_res_next)
    public void onViewClicked() {

    }
}
