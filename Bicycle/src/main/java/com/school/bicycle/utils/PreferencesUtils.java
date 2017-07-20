package com.school.bicycle.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Jero on 2017/3/30 0030.
 */

public class PreferencesUtils {
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    public static final String VAL_Null = "null";

    public PreferencesUtils(Context context) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        mEditor = mSharedPreferences.edit();
    }

    public PreferencesUtils(Context context, String fileName) {
        mSharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }


    public String getUser() {
        return mSharedPreferences.getString("User", "");
    }

    public void setUser(String user) {
        mEditor.putString("User", user).commit();
    }


}
