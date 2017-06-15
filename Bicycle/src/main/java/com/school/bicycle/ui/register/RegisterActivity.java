package com.school.bicycle.ui.register;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.school.bicycle.R;
import com.school.bicycle.global.BaseToolBarActivity;

import butterknife.BindView;

public class RegisterActivity extends BaseToolBarActivity implements IRegisterView{

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.reg_next)
    TextView regNext;
    private IRegisterPresenter iRegisterPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setToolbarText("注册");

        iRegisterPresenter = new RegisterPresenter(getBaseContext(), this);
        initClick();

    }

    private void initClick() {

        tvCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iRegisterPresenter.verificationPhone(etPhone.getText().toString());
            }
        });
        regNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iRegisterPresenter.verificationCode(etCode.getText().toString());
            }
        });
    }

    @Override
    public void isPhone(boolean b) {
        if (!b){
            showShort("请输入正确的手机号码");
        } else {
            iRegisterPresenter.getCode(etPhone.getText().toString());
        }
    }

    @Override
    public void goNext() {
        showShort("下一步");
    }

    @Override
    public void sendCode(String s) {
        showShort(s);
    }
}
