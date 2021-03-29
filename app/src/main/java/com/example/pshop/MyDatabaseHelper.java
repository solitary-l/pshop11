package com.example.pshop;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

class MyDatabaseHelper extends SQLiteOpenHelper{

    private SQLiteDatabase db;

    public MyDatabaseHelper(Context context) {
        super(context, "db_test", null, 1);
        db = getReadableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS user(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "mail TEXT, "+
                "password TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }

    public void add(String name,String mail,String password){
        db.execSQL("INSERT INTO user (name,mail,password) VALUES(?,?,?)",new Object[]{name,mail,password});
    }
    public void delete(String name,String password){
        db.execSQL("DELETE FROM user WHERE name = AND password ="+name+password);
    }
    public void updata1(String password){
        db.execSQL("UPDATE user SET password = ?",new Object[]{password});
    }
    public void updata2(String name){
        db.execSQL("UPDATE user SET name = ?",new Object[]{name});
    }
    public void updata3(String mail){
        db.execSQL("UPDATE user SET mail = ?",new Object[]{mail});
    }

    public ArrayList<User> getAllData(){

        ArrayList<User> list = new ArrayList<User>();
        Cursor cursor = db.query("user",null,null,null,null,null,"name DESC");
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String mail = cursor.getString(cursor.getColumnIndex("mail"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            list.add(new User(name,null,password));
        }
        return list;
    }
}




