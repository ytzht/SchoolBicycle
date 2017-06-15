package com.school.bicycle.ui.register;

import android.content.Context;

/**
 * Created by ytzht on 2017/06/15 下午11:02
 */

public class RegisterPresenter implements IRegisterPresenter {

    IRegisterView iRegisterView;
    Context context;

    public RegisterPresenter(Context context, IRegisterView iRegisterView){
        this.context = context;
        this.iRegisterView = iRegisterView;
    }


    @Override
    public void verificationPhone(String phone) {
        iRegisterView.isPhone(false);
    }

    @Override
    public void verificationCode(String code) {
        iRegisterView.goNext();
    }

    @Override
    public void getCode(String phone) {
        iRegisterView.sendCode("已发送短信验证码");
    }
}
