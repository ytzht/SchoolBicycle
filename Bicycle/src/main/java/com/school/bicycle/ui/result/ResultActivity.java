package com.school.bicycle.ui.result;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.school.bicycle.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultActivity extends AppCompatActivity {

    @BindView(R.id.iv_res_back)
    ImageView ivResBack;
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
        ButterKnife.bind(this);
    }
}
