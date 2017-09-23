package com.school.bicycle.global;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ytzht on 2017/07/21 下午11:57
 */

public class UserService {

    private Context context;
    private static final String USER_PREFS = "_member_";
    private static final String DeviceToken = "DeviceToken";
    private static final String ValidateUser = "_ValidateUser_";
    private static final String State = "state";
    private static final String tixian = "tixian";
    private static final String alert = "alert";
    private static final String showalert = "showalert";
    private static final String Cookie = "Cookie";
    private static final String BikeNumber = "BikeNumber";
    private static final String COOKIE_PREFS = "CookiePrefsFile";
    private static final String ShowOneMark = "ShowOneMark";
    private static final String LatLon   = "LatLon";
    private static final String usebiycle   = "usebiycle";
    private static final String iscall   = "iscall";
    private static final String over   = "over";
    private static final String isgetbiycle   = "isgetbiycle";
    private static final String xinyongfen   = "xinyongfen";



    public UserService(Context context) {
        this.context = context;
    }

    // 1为租车状态 0为未租车状态
    public void setxinyongfen(String latlon) {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                xinyongfen, Context.MODE_PRIVATE);
        memberPrefs.edit().putString(xinyongfen, latlon).apply();
    }


    public String getxinyongfen() {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                xinyongfen, Context.MODE_PRIVATE);
        return memberPrefs.getString(xinyongfen, "0");
    }


        // 1为租车状态 0为未租车状态
    public void setisgetbiycle(String latlon) {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                isgetbiycle, Context.MODE_PRIVATE);
        memberPrefs.edit().putString(isgetbiycle, latlon).apply();
    }


    public String getisgetbiycle() {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                isgetbiycle, Context.MODE_PRIVATE);
        return memberPrefs.getString(isgetbiycle, "0");

    }
    //1为确定 0为否
    public void setover(String latlon) {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                over, Context.MODE_PRIVATE);
        memberPrefs.edit().putString(over, latlon).apply();
    }


    public String getover() {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                over, Context.MODE_PRIVATE);
        return memberPrefs.getString(over, "0");

    }
    //1为开启 0为关闭
    public void setiscall(String latlon) {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                usebiycle, Context.MODE_PRIVATE);
        memberPrefs.edit().putString(iscall, latlon).apply();
    }


    public String getiscall() {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                usebiycle, Context.MODE_PRIVATE);
        return memberPrefs.getString(iscall, "0");

    }

    //1为显示一个 0为显示所有
    public void setusebiycle(String latlon) {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                usebiycle, Context.MODE_PRIVATE);
        memberPrefs.edit().putString(usebiycle, latlon).apply();
    }


    public String getusebiycle() {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                usebiycle, Context.MODE_PRIVATE);
        return memberPrefs.getString(usebiycle, "0");

    }


    //1为显示一个 0为显示所有
    public void setLatLon(String latlon) {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                LatLon, Context.MODE_PRIVATE);
        memberPrefs.edit().putString(LatLon, latlon).apply();
    }


    public String getLatLon() {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                LatLon, Context.MODE_PRIVATE);
        return memberPrefs.getString(LatLon, "0");

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
    public void setDeviceToken(String validateUser) {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                ValidateUser, Context.MODE_PRIVATE);
        memberPrefs.edit().putString(DeviceToken, validateUser).apply();
    }


    public String getDeviceToken() {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                ValidateUser, Context.MODE_PRIVATE);
        return memberPrefs.getString(DeviceToken, "0");

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
    public void setshowAlert(String s) {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                showalert, Context.MODE_PRIVATE);
        memberPrefs.edit().putString(showalert, s).apply();
    }

    public String getshowAlert() {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                showalert, Context.MODE_PRIVATE);
        return memberPrefs.getString(showalert, "0");

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
    public void setBikeNumber(String value) {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                USER_PREFS, Context.MODE_PRIVATE);
        memberPrefs.edit().putString(BikeNumber,value).apply();
    }

    public String getBikeNumber() {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                USER_PREFS, Context.MODE_PRIVATE);
        return memberPrefs.getString(BikeNumber, "0");

    }
    public void setBikeNumberTime(String number, long time) {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                USER_PREFS, Context.MODE_PRIVATE);
        memberPrefs.edit().putLong(number,time).apply();
    }

    public long getBikeNumberTime(String number) {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                USER_PREFS, Context.MODE_PRIVATE);
        return memberPrefs.getLong(number, 0);

    }



}
