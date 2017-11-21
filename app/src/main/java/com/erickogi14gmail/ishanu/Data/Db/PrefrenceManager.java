package com.erickogi14gmail.ishanu.Data.Db;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Eric on 11/21/2017.
 */

public class PrefrenceManager {
    private static final String PREF_NAME = "ishano";
    // All Shared Preferences Keys
    private static final String KEY_IS_WAITING_FOR_SMS = "IsWaitingForSms";
    private static final String KEY_MOBILE_NUMBER = "mobile_number";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_MOBILE = "mobile";
    private static final String KEY_ROUTE = "rout";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_PROFILE_SET = "profile_set";
    private static final String KEY_IS_FIRST_TIME = "isFirstTime";

    // Shared Preferences
    SharedPreferences pref;
    // Editor for Shared preferences
    SharedPreferences.Editor editor;
    // Context
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;

    public PrefrenceManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public String getMobileNumber() {
        return pref.getString(KEY_MOBILE, "0");
    }

    public void setMobileNumber(String mobileNumber) {
        editor.putString(KEY_MOBILE_NUMBER, mobileNumber);
        editor.commit();
    }

    public void createLogin(String name, String email, String mobile, String pass, String route) {
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_MOBILE, mobile);
        editor.putString(KEY_MOBILE_NUMBER, mobile);
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.putString(KEY_PASSWORD, pass);
        editor.putString(KEY_ROUTE, route);
        editor.commit();
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public boolean isFirstTime() {
        return pref.getBoolean(KEY_IS_FIRST_TIME, true);
    }

    public void setIsFirstTime(boolean isFirstTime) {
        editor.putBoolean(KEY_IS_FIRST_TIME, isFirstTime);
        editor.commit();
    }

    public void clearSession() {
        editor.clear();
        editor.commit();
    }

}
