package com.school.bicycle.ui.register;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.school.bicycle.R;
import com.school.bicycle.global.BaseToolBarActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends BaseToolBarActivity {

    @BindView(R.id.ed_reg_phonenum)
    EditText edRegPhonenum;
    @BindView(R.id.ed_reg_codenum)
    EditText edRegCodenum;
    @BindView(R.id.te_reg_getcode)
    TextView teRegGetcode;
    @BindView(R.id.ra_reg_agree)
    RadioButton raRegAgree;
    @BindView(R.id.te_reg_Useragreement)
    TextView teRegUseragreement;
    @BindView(R.id.dps_paynow)
    Button dpsPaynow;
    @BindView(R.id.rn_back)
    ImageView rnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }
}
