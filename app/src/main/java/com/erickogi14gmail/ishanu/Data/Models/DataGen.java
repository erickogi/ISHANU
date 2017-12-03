package com.erickogi14gmail.ishanu.Data.Models;

import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

/**
 * Created by Eric on 11/21/2017.
 */

public class DataGen {
    private static LinkedList<ProductModel> productModels;
    private static LinkedList<CustomerModel> customerModels;
    private static LinkedList<PaymentMethods> paymentMethods;


    public static LinkedList<PaymentMethods> genData(boolean debug) {
        paymentMethods = new LinkedList<>();

        PaymentMethods paymentMethods1 = new PaymentMethods();
        paymentMethods1.setId(1);
        paymentMethods1.setName("Mpesa");

        PaymentMethods paymentMethods2 = new PaymentMethods();
        paymentMethods2.setId(2);
        paymentMethods2.setName("Equitel");


        PaymentMethods paymentMethods3 = new PaymentMethods();
        paymentMethods3.setId(3);
        paymentMethods3.setName("Cash");


        PaymentMethods paymentMethods4 = new PaymentMethods();
        paymentMethods4.setId(4);
        paymentMethods4.setName("Airtel Money");


        PaymentMethods paymentMethods5 = new PaymentMethods();
        paymentMethods5.setId(5);
        paymentMethods5.setName("Cheque");

        paymentMethods.add(paymentMethods1);
        paymentMethods.add(paymentMethods2);
        paymentMethods.add(paymentMethods3);
        paymentMethods.add(paymentMethods4);
        paymentMethods.add(paymentMethods5);


        return paymentMethods;
    }

    public static LinkedList<CustomerModel> genData() {
        customerModels = new LinkedList<>();

//        <item>Peter Mark Shop</item>
//        <item>Ngengi Joseph</item>
//        <item>Muasia Peter Shop</item>
//
//        <item>Zack Odoyo Shop</item>
//
//

        CustomerModel customerModel1 = new CustomerModel();
        customerModel1.setCusomer_id(1);
        customerModel1.setCustomer_location("Customer Location");
        customerModel1.setCustomer_name("Mark's Shop");

        CustomerModel customerModel2 = new CustomerModel();
        customerModel2.setCustomer_name("Ngeng Joseph");
        customerModel2.setCusomer_id(2);
        customerModel2.setCustomer_location("Customer Location");

        CustomerModel customerModel3 = new CustomerModel();
        customerModel3.setCustomer_name("Muasia Peter Shop");
        customerModel3.setCusomer_id(3);
        customerModel3.setCustomer_location("Muasia Location");


        CustomerModel customerModel4 = new CustomerModel();
        customerModel4.setCustomer_name("Zack Odoyo");
        customerModel4.setCusomer_id(4);
        customerModel4.setCustomer_location("Customer Location");

        customerModels.add(customerModel1);
        customerModels.add(customerModel2);
        customerModels.add(customerModel3);
        customerModels.add(customerModel4);


        return customerModels;
    }
    public static LinkedList<ProductModel> genData(Activity activity, String fileName) {
        productModels = new LinkedList<>();

        productModels = jsonToProduct(activity, fileName);


        return productModels;

    }


    private static String loadJSONFromAsset(Activity activity, String fileName) {
        String json = null;
        try {
            InputStream is = activity.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private static LinkedList<ProductModel> jsonToProduct(Activity activity, String fileName) {
        LinkedList<ProductModel> productModels = new LinkedList<>();

        try {
            // JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = new JSONArray(loadJSONFromAsset(activity, fileName));


            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject obj = m_jArry.getJSONObject(i);
                ProductModel productModel = new ProductModel();
                productModel.setProduct_name(obj.getString("FIELD6"));
                productModel.setProduct_id(obj.getString("FIELD7"));
                productModel.setProduct_load_quantity(obj.getDouble("FIELD9"));
                productModel.setProduct_sale_quantity(0.0);
                productModel.setProduct_price(obj.getDouble("FIELD10"));


                productModels.add(productModel);


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return productModels;
    }
}
