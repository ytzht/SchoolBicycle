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
    private static final String alert = "alert";
    private static final String Cookie = "Cookie";
    private static final String COOKIE_PREFS = "CookiePrefsFile";

    private static final String ShowOneMark = "ShowOneMark";

    public UserService(Context context) {
        this.context = context;
    }


    //1为显示一个 0为显示所有
    public void setShowOneMark(String ShowOneMark1) {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                ShowOneMark, Context.MODE_PRIVATE);
        memberPrefs.edit().putString(ShowOneMark, ShowOneMark1).apply();
    }


    public String getShowOneMark() {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                ShowOneMark, Context.MODE_PRIVATE);
        return memberPrefs.getString(ShowOneMark, "0");

    }


    public void setValidateUser(String validateUser) {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                ValidateUser, Context.MODE_PRIVATE);
        memberPrefs.edit().putString(ValidateUser, validateUser).apply();
    }


    public String getValidateUser() {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                ValidateUser, Context.MODE_PRIVATE);
        return memberPrefs.getString(ValidateUser, "0");

    }

    public void setState(String state) {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                State, Context.MODE_PRIVATE);
        memberPrefs.edit().putString(State, state).apply();
    }


    public String getState() {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                State, Context.MODE_PRIVATE);
        return memberPrefs.getString(State, "0");

    }

    public void settixian(String state) {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                tixian, Context.MODE_PRIVATE);
        memberPrefs.edit().putString(tixian, state).apply();
    }


    public String gettixian() {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                tixian, Context.MODE_PRIVATE);
        return memberPrefs.getString(tixian, "0");

    }


    public void setAlert(String s) {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                USER_PREFS, Context.MODE_PRIVATE);
        memberPrefs.edit().putString(alert, s).apply();
    }

    public String getAlert() {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                USER_PREFS, Context.MODE_PRIVATE);
        return memberPrefs.getString(alert, "0");

    }

    public void setCookie(String value) {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                COOKIE_PREFS, Context.MODE_PRIVATE);
        memberPrefs.edit().putString(Cookie,value).apply();
    }

    public String getCookie() {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                COOKIE_PREFS, Context.MODE_PRIVATE);
        return memberPrefs.getString(Cookie, "0");

    }

}
