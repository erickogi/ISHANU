package com.erickogi14gmail.ishanu.Data.Db;

import android.content.Context;
import android.content.SharedPreferences;

import com.erickogi14gmail.ishanu.Data.Models.MyAccountModel;

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

    private static final String KEY_SALES = "sales";
    private static final String KEY_RETURNS = "returns";

    private static final String KEY_TOTAL_SALES = "total_sales";
    private static final String KEY_TOTAL_RETURNS = "total_returns";

    private static final String KEY_CAR = "car";

    private static final String KEY_CUSTOMER_NAME = "customer";


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

    public String getCustomer() {

        return pref.getString(KEY_CUSTOMER_NAME, "Customers Name");
    }

    public void setCustomersName(String name) {

        editor.putString(KEY_CUSTOMER_NAME, name);
        editor.commit();

    }

    public void clearCustomerName() {

        editor.putString(KEY_CUSTOMER_NAME, "null");
        editor.remove(KEY_CUSTOMER_NAME);

        editor.commit();
    }

    public String[] getLoginParams() {

        String params[] = new String[3];
        params[0] = pref.getString(KEY_PASSWORD, "null");

        params[1] = pref.getString(KEY_MOBILE, "null");
        params[2] = pref.getString(KEY_EMAIL, "null");


        return params;
    }

    public void createLogin(MyAccountModel myAccountModel) {

        editor.putString(KEY_NAME, myAccountModel.getName());
        editor.putString(KEY_EMAIL, myAccountModel.getEmail());

        editor.putString(KEY_MOBILE, myAccountModel.getMobilr());
        editor.putString(KEY_MOBILE_NUMBER, myAccountModel.getMobilr());

        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.putString(KEY_PASSWORD, myAccountModel.getPassword());

        editor.putString(KEY_ROUTE, myAccountModel.getRoute());
        editor.putString(KEY_CAR, myAccountModel.getCar());
        editor.commit();
    }

    public void storeSales(String sales, String total_sales) {
        editor.putString(KEY_SALES, sales);
        editor.putString(KEY_TOTAL_SALES, total_sales);
        editor.commit();
    }

    public void storeReturns(String returns, String total_retrns) {
        editor.putString(KEY_RETURNS, returns);
        editor.putString(KEY_TOTAL_RETURNS, total_retrns);
        editor.commit();
    }

    public String[] getSales() {
        String[] sales = new String[2];

        sales[0] = pref.getString(KEY_SALES, "null");
        sales[1] = pref.getString(KEY_TOTAL_SALES, "null");

        return sales;
    }

    public String[] getReturns() {
        String[] returns = new String[2];

        returns[0] = pref.getString(KEY_RETURNS, "null");
        returns[1] = pref.getString(KEY_TOTAL_RETURNS, "null");

        return returns;
    }

    public void clearSalesData() {

        editor.putString(KEY_SALES, "null");
        editor.putString(KEY_TOTAL_SALES, "null");

        editor.remove(KEY_SALES);
        editor.remove(KEY_TOTAL_SALES);

        editor.remove(KEY_TOTAL_RETURNS);
        editor.remove(KEY_RETURNS);
        editor.commit();
    }

    public void clearReturnsData() {
        editor.putString(KEY_RETURNS, "null");
        editor.putString(KEY_TOTAL_RETURNS, "null");

        editor.remove(KEY_SALES);
        editor.remove(KEY_TOTAL_SALES);
        editor.remove(KEY_TOTAL_RETURNS);
        editor.remove(KEY_RETURNS);
        editor.commit();
    }


    public MyAccountModel getAccount() {

        MyAccountModel myAccountModel = new MyAccountModel();

        myAccountModel.setName(pref.getString(KEY_NAME, "My Name"));

        myAccountModel.setEmail(pref.getString(KEY_EMAIL, "myemail@gmail.com"));

        myAccountModel.setMobilr(pref.getString(KEY_MOBILE, "0712345678"));

        myAccountModel.setCar(pref.getString(KEY_CAR, "KBJ 123"));

        myAccountModel.setPassword(pref.getString(KEY_PASSWORD, "mypassword"));

        myAccountModel.setRoute(pref.getString(KEY_ROUTE, "myroute"));

        return myAccountModel;
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

    public void setIsLoggedIn(boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        editor.commit();
    }

    public void clearSession() {
        editor.clear();
        editor.commit();
    }

}
