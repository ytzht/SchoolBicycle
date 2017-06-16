package com.school.bicycle.ui.authentication;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.school.bicycle.R;
import com.school.bicycle.global.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RealnameActivity extends BaseActivity {

    @BindView(R.id.rn_name)
    EditText rnName;
    @BindView(R.id.rn_idnumber)
    EditText rnIdnumber;
    @BindView(R.id.rn_idstudent)
    EditText rnIdstudent;
    @BindView(R.id.rn_photo)
    ImageView rnPhoto;
    @BindView(R.id.rn_back)
    ImageView rnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_realname);
        ButterKnife.bind(this);
    }
}