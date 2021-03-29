package com.example.pshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class center extends AppCompatActivity implements View.OnClickListener{
    private static final int MOOD_PRIVATE = 0;
    private Button Back;
    private Button change;
    private TextView username;
    private TextView usermail;
    private MyDatabaseHelper mDBOpenHelper;
    private String c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center);
        Button back = (Button) findViewById(R.id.back);
        Button change = (Button) findViewById(R.id.change);

        TextView username = (TextView)findViewById(R.id.name);
        TextView email = (TextView)findViewById(R.id.mail);
        mDBOpenHelper = new MyDatabaseHelper(this);
        SQLiteDatabase db = mDBOpenHelper.getWritableDatabase();
        //获取MainActivity中LayoutInflater （上下文参数）
        SharedPreferences sp = getSharedPreferences ("data",MOOD_PRIVATE);
        String data = sp.getString("name","");					//获取用户名
        username.setText("用户名："+data);
        Cursor cursor = db.query("user", null, null, null, null, null, "name DESC");
        if (cursor.moveToFirst()) {
            do {
                String user2 = cursor.getString(cursor.getColumnIndex("name"));
                String mail=cursor.getString(cursor.getColumnIndex("mail"));
                if (data.equals(user2)) {
                    email.setText("邮箱："+mail);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        usermail = (TextView)findViewById(R.id.mail);
        mDBOpenHelper = new MyDatabaseHelper(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.change:
                Intent intent1 = new Intent(center.this, userchange.class);
                startActivity(intent1);
                break;
            case R.id.back:
                Intent intent = new Intent(center.this, Main2Activity.class);
                intent.putExtra(c, 1);
                startActivity(intent);
                break;

        }

    }

}
