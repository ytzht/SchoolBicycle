package com.school.bicycle.global;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ytzht on 2017/07/21 下午11:57
 */

public class UserService {

    private Context context;
    private static final String USER_PREFS = "_member_";
    private static final String ACCOUNT = "_account_";
    private static final String PWD = "_pwd_";
    private static final String ValidateUser = "_ValidateUser_";
    private static final String State = "state";
    private static final String tixian = "tixian";

    public UserService(Context context) {
        this.context = context;
    }

    public void setAccount(String account) {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                USER_PREFS, Context.MODE_PRIVATE);
        memberPrefs.edit().putString(ACCOUNT, account).apply();
    }


    public String getAccount() {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                USER_PREFS, Context.MODE_PRIVATE);
        return memberPrefs.getString(ACCOUNT, "");

    }

    public void setPwd(String pwd) {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                USER_PREFS, Context.MODE_PRIVATE);
        memberPrefs.edit().putString(PWD, pwd).apply();
    }


    public String getPwd() {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                USER_PREFS, Context.MODE_PRIVATE);
        return memberPrefs.getString(PWD, "");

    }

    public void setValidateUser(String validateUser) {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                USER_PREFS, Context.MODE_PRIVATE);
        memberPrefs.edit().putString(ValidateUser, validateUser).apply();
    }


    public String getValidateUser() {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                USER_PREFS, Context.MODE_PRIVATE);
        return memberPrefs.getString(ValidateUser, "");

    }

    public void setState(String state) {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                USER_PREFS, Context.MODE_PRIVATE);
        memberPrefs.edit().putString(State, state).apply();
    }


    public String getState() {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                USER_PREFS, Context.MODE_PRIVATE);
        return memberPrefs.getString(State, "0");

    }

    public void settixian(String state) {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                USER_PREFS, Context.MODE_PRIVATE);
        memberPrefs.edit().putString(tixian, state).apply();
    }


    public String gettixian() {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                USER_PREFS, Context.MODE_PRIVATE);
        return memberPrefs.getString(tixian, "0");

    }



}
