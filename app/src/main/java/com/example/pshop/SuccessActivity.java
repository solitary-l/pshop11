package com.example.pshop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pshop.Main2Activity;

public class SuccessActivity extends AppCompatActivity {

    private TextView back_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        back_home = findViewById(R.id.back_home);

        back_home.setOnClickListener(v -> {
            startActivity(new Intent(this, Main2Activity.class));
            overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            finish();
        });
    }
}
