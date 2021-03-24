package com.example.pshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class center extends AppCompatActivity implements View.OnClickListener{
    private Button Back;
    private TextView username;
    private TextView usermail;
    private MyDatabaseHelper mDBOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center);
        Button back = (Button) findViewById(R.id.back);
        TextView username = (TextView)findViewById(R.id.username);
        username.setText("lihaomoou");
        usermail = (TextView)findViewById(R.id.usermail);
        mDBOpenHelper = new MyDatabaseHelper(this);
    }
    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, Main2Activity.class));
        finish();
    }

}
