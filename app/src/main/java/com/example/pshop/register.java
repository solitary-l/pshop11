package com.example.pshop;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class register extends AppCompatActivity implements View.OnClickListener {

    private ImageView Back;
    private MyDatabaseHelper mDBOpenHelper;
    private Button Register;
    private EditText mEtRegisteractivityUsername;
    private EditText email;
    private EditText password1;
    private EditText repeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();

        mDBOpenHelper = new MyDatabaseHelper(this);


    }

    private void initView(){
        Register = findViewById(R.id.register);
        mEtRegisteractivityUsername = findViewById(R.id.username);
        email=findViewById(R.id.mail);
        password1 = findViewById(R.id.password);
        repeat = findViewById(R.id.repeat);
        Back = findViewById(R.id.back);

        Register.setOnClickListener(this);
        Back.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back: //返回登录页面
                Intent intent1 = new Intent(this, MainActivity.class);
                startActivity(intent1);
                finish();
                break;

            case R.id.register:    //注册按钮
                //获取用户输入的用户名、密码、确认密码
                String username = mEtRegisteractivityUsername.getText().toString().trim();
                String mail = email.getText().toString().trim();
                String password = password1.getText().toString().trim();
                String repassword= repeat.getText().toString().trim();
                SQLiteDatabase db = mDBOpenHelper.getWritableDatabase();
                int i=0;

                Cursor cursor = db.query("user", null, null, null, null, null, "name DESC");
                if (cursor.moveToFirst()) {
                    do {
                        String user2 = cursor.getString(cursor.getColumnIndex("name"));
                        Log.d("register", "username is " + user2);
                        if (username.equals(user2)) {
                            Toast.makeText(register.this, "账号已存在", Toast.LENGTH_SHORT).show();
                            i=1;
                        }
                    } while (cursor.moveToNext());
                }
                cursor.close();
                if(i!=1) {
                    if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
                        if (password.equals(repassword)) {
                            if (password.length() > 5 && password.length() < 19) {
                                //将用户名和密码加入到数据库中
                                mDBOpenHelper.add(username,mail,password);
                                Intent intent2 = new Intent(this, MainActivity.class);
                                startActivity(intent2);
                                finish();
                                Toast.makeText(this, "验证通过，注册成功", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(this, "密码长度不正确", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(this, "二次密码输入不一致，请重新输入", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "未完善信息，注册失败", Toast.LENGTH_SHORT).show();
                    }
                }
                    break;
        }
    }
}

