package com.erickogi14gmail.ishanu.Data.Db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Eric on 11/21/2017.
 */

public class DbClass extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ishano.db";

    String createTableItems = "CREATE TABLE `ishano_loading_data` (" +
            "  `product_id` INTEGER PRIMARY KEY AUTOINCREMENT  ," +
            "  `product_name` varchar NOT NULL," +
            "  `product_load_quantity` varchar NOT NULL," +
            "  `product_sale_quantity` varchar NOT NULL," +
            "  `product_price` varchar , " +
            "  `product_type` varchar "


            + ")";

    public DbClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTableItems);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + createTableItems);

    }
}
