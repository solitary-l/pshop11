package com.example.pshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class userchange extends AppCompatActivity implements View.OnClickListener {
    private static final int MOOD_PRIVATE = 0;
    private Button Back;
    private Button change;
    private EditText username;
    private EditText usermail;
    private MyDatabaseHelper mDBOpenHelper;
    private String c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userchange);
        mDBOpenHelper = new MyDatabaseHelper(this);
        TextView username = (TextView)findViewById(R.id.name);
        EditText email=(EditText) findViewById(R.id.mail);
        Button back = (Button) findViewById(R.id.back);
        Button change = (Button) findViewById(R.id.change);
        mDBOpenHelper = new MyDatabaseHelper(this);
        SQLiteDatabase db = mDBOpenHelper.getWritableDatabase();
        SharedPreferences sp = getSharedPreferences ("data",MOOD_PRIVATE);
        String data = sp.getString("name","");					//获取用户名
        username.setText(data);
        Cursor cursor = db.query("user", null, null, null, null, null, "name DESC");
        if (cursor.moveToFirst()) {
            do {
                String user2 = cursor.getString(cursor.getColumnIndex("name"));
                String mail=cursor.getString(cursor.getColumnIndex("mail"));
                if (data.equals(user2)) {
                    email.setText(mail);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();

    }
    @Override
    public void onClick(View v) {
        mDBOpenHelper = new MyDatabaseHelper(this);
        SQLiteDatabase db = mDBOpenHelper.getWritableDatabase();
        switch (v.getId()) {
            // 跳转到注册界面
            case R.id.change:
                String changename=username.getText().toString();
                ContentValues values2 = new ContentValues();
                values2.put("name", changename);
                db.update("user", values2, "name = ?", new String[]{changename});
                Intent intent1 = new Intent(userchange.this, center.class);
                startActivity(intent1);
                break;
            case R.id.back:
                Intent intent = new Intent(userchange.this, center.class);
                startActivity(intent);
                break;

        }
    }
}
