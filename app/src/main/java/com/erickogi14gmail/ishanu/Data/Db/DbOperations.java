package com.erickogi14gmail.ishanu.Data.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.erickogi14gmail.ishanu.Data.Models.ProductModel;
import com.erickogi14gmail.ishanu.Data.Models.RecordModel;

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


    public LinkedList<RecordModel> getAllRecords(String date) {
        //Open connection to read only
        SQLiteDatabase db = dbHandler.getReadableDatabase();


        LinkedList<RecordModel> data = new LinkedList<>();
        String QUERY = null;


        if (date.equals("")) {
            QUERY = "SELECT * FROM  ishano_records_data WHERE   date LIKE '%" + date + "%'";
        } else {
            QUERY = "SELECT * FROM  ishano_records_data WHERE   date = '" + date + "'";
        }


        Cursor cursor = db.rawQuery(QUERY, null);

        if (!cursor.isLast()) {

            while (cursor.moveToNext()) {
                RecordModel pojo = new RecordModel();
                pojo.setRecord_id(cursor.getInt(0));
                pojo.setProducts(cursor.getString(1));
                pojo.setProduct_total(cursor.getString(2));
                pojo.setRetutns(cursor.getString(3));
                pojo.setReturns_total(cursor.getString(4));

                pojo.setPaid(cursor.getString(5));
                pojo.setBalance(cursor.getString(6));
                pojo.setDate(cursor.getString(7));

                pojo.setCustomer_name(cursor.getString(8));

                pojo.setCash(cursor.getString(9));
                pojo.setMpesa(cursor.getString(10));
                pojo.setCheque(cursor.getString(11));

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

    public boolean insertRecord(RecordModel data) {
        boolean success = false;

        SQLiteDatabase db = dbHandler.getWritableDatabase();
        ContentValues values = new ContentValues();

        // values.put("item_id", data.getItem_id());
        //values.put("product_id", String.valueOf(data.getProduct_id()));


        values.put("products", data.getProducts());
        values.put("product_total", data.getProduct_total());
        values.put("retutns", data.getRetutns());
        values.put("returns_total", data.getReturns_total());
        values.put("paid", data.getPaid());

        values.put("balance", data.getBalance());
        values.put("date", data.getDate());
        values.put("customer", data.getCustomer_name());

        values.put("cash", data.getCash());
        values.put("mpesa", data.getMpesa());
        values.put("cheque", data.getCheque());


        if (db.insert("ishano_records_data", null, values) >= 1) {
            success = true;
        }
        db.close();


        return success;


    }

}
