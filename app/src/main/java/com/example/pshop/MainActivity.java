package com.example.pshop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int MOOD_PRIVATE =0 ;
    private MyDatabaseHelper mDBOpenHelper;
    private TextView mTvLoginactivityRegister;
    private EditText mEtLoginactivityUsername;
    private EditText mEtLoginactivityPassword;
    private Button mBtLoginactivityLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        mDBOpenHelper = new MyDatabaseHelper(this);
    }

    private void initView() {
        // 初始化控件
        mBtLoginactivityLogin = findViewById(R.id.login);
        mTvLoginactivityRegister = findViewById(R.id.register);
        mEtLoginactivityUsername = findViewById(R.id.username);
        mEtLoginactivityPassword = findViewById(R.id.password);

        // 设置点击事件监听器
        mBtLoginactivityLogin.setOnClickListener(this);
        mTvLoginactivityRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // 跳转到注册界面
            case R.id.register:
                startActivity(new Intent(this, register.class));
                finish();
                break;
            case R.id.login:
                String name = mEtLoginactivityUsername.getText().toString().trim();
                String password = mEtLoginactivityPassword.getText().toString().trim();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {
                    ArrayList<User> data = mDBOpenHelper.getAllData();
                    boolean match = false;
                    for (int i = 0; i < data.size(); i++) {
                        User user = data.get(i);
                        if (name.equals(user.getName()) && password.equals(user.getPassword())) {
                            match = true;
                            //获取sp对象， 参数名"data"表示文件名,MOOD_PRIVATE表示文件操作模式
                            SharedPreferences sp = getSharedPreferences("data",MOOD_PRIVATE);
                            SharedPreferences .Editor editor = sp.edit(); 		//获取编辑器
                            editor.putString("name",name);						//存入String类型数据
                            editor.commit();									//提交修改
                            break;
                        } else {
                            match = false;
                        }
                    }
                    if (match) {
                        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, Main2Activity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("name", name);
                        startActivity(intent);
                        finish();//销毁此Activity
                    } else {
                        Toast.makeText(this, "用户名或密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "请输入你的用户名或密码", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
}
