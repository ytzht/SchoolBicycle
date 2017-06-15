package com.school.bicycle.ui.register;

/**
 * Created by ytzht on 2017/06/15 下午11:02
 */

public interface IRegisterPresenter {
    void verificationPhone(String phone);

    void verificationCode(String code);

    void getCode(String phone);
}
