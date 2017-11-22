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
