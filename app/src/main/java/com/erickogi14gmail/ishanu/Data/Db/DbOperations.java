package com.erickogi14gmail.ishanu.Data.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.erickogi14gmail.ishanu.Data.Models.ProductModel;

import java.util.LinkedList;

/**
 * Created by Eric on 11/21/2017.
 */

public class DbOperations {
    private Context context;
    private DbClass dbHandler;
    private boolean successful = false;
    private double returnValueDouble = 0.0;

    public DbOperations(Context context) {
        dbHandler = new DbClass(context);
        this.context = context;
    }


    public LinkedList<ProductModel> getAllItems(String search, int type) {
        //Open connection to read only
        SQLiteDatabase db = dbHandler.getReadableDatabase();


        LinkedList<ProductModel> data = new LinkedList<>();
        String QUERY = null;


        QUERY = "SELECT * FROM  ishano_loading_data WHERE  product_name LIKE '%" + search + "%' AND product_type= '" + type + "'";



        Cursor cursor = db.rawQuery(QUERY, null);

        if (!cursor.isLast()) {

            while (cursor.moveToNext()) {
                ProductModel pojo = new ProductModel();
                pojo.setProduct_id(cursor.getString(0));
                pojo.setProduct_name(cursor.getString(1));
                pojo.setProduct_load_quantity(cursor.getDouble(2));
                pojo.setProduct_sale_quantity(cursor.getDouble(3));
                pojo.setProduct_price(cursor.getDouble(4));


                data.add(pojo);

            }
        }
        db.close();
        // looping through all rows and adding to list

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return data;


    }

    public boolean insertItem(ProductModel data, int type) {
        boolean success = false;

        SQLiteDatabase db = dbHandler.getWritableDatabase();
        ContentValues values = new ContentValues();

        // values.put("item_id", data.getItem_id());
        //values.put("product_id", String.valueOf(data.getProduct_id()));


        values.put("product_name", data.getProduct_name());
        values.put("product_load_quantity", String.valueOf(data.getProduct_load_quantity()));
        values.put("product_sale_quantity", String.valueOf(data.getProduct_sale_quantity()));
        values.put("product_price", String.valueOf(data.getProduct_price()));
        values.put("product_type", String.valueOf(type));


        if (db.insert("ishano_loading_data", null, values) >= 1) {
            success = true;
        }
        db.close();


        return success;


    }

}
