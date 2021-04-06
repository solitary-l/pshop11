package com.example.pshop;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class homeDBOpenHelper extends SQLiteOpenHelper {

    public homeDBOpenHelper( Context context) {
        super(context, "shop.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE home(home_id INTEGER PRIMARY KEY AUTOINCREMENT,h_id,h_photo,h_name,h_type,h_price,h_num,h_check)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
