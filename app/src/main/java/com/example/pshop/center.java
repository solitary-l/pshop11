package com.example.pshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class center extends AppCompatActivity implements View.OnClickListener{
    private Button Back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center);
        Button back = (Button) findViewById(R.id.back);
    }
    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, Main2Activity.class));
        finish();
    }

}
