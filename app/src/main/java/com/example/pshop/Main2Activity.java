package com.example.pshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{

    LinearLayout homeLinear;
    LinearLayout CarLinear;
    LinearLayout userLinear;

    home fragmentHome;
    car fragmentCar;
    people fragmentUser;

    private FragmentManager mfragmentManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        homeLinear= (LinearLayout) findViewById(R.id.linear_home);
        CarLinear= (LinearLayout) findViewById(R.id.linear_Car);
        userLinear= (LinearLayout) findViewById(R.id.linear_User);
        homeLinear.setOnClickListener(this);
        CarLinear.setOnClickListener(this);
        userLinear.setOnClickListener(this);
        mfragmentManger = getSupportFragmentManager();
        homeLinear.performClick();
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction fragmentTransaction = mfragmentManger.beginTransaction();//只能是局部变量，不能为全局变量，否则不能重复commit
        //FragmentTransaction只能使用一次
        hideAllFragment(fragmentTransaction);
        switch (view.getId()){
            case R.id.linear_home:
                setAllFalse();
                homeLinear.setSelected(true);
                if (fragmentHome==null){
                    fragmentHome=new home("Home");
                    fragmentTransaction.add(R.id.fragment_frame,fragmentHome);
                }else{
                    fragmentTransaction.show(fragmentHome);
                }
                break;
            case R.id.linear_Car:
                setAllFalse();
                CarLinear.setSelected(true);
                if(fragmentCar==null){
                    fragmentCar=new car("ShoppingCar");
                    fragmentTransaction.add(R.id.fragment_frame,fragmentCar);
                }else {
                    fragmentTransaction.show(fragmentCar);
                }
                break;
            case R.id.linear_User:
                setAllFalse();
                userLinear.setSelected(true);
                if(fragmentUser==null){
                    fragmentUser=new people("User");
                    fragmentTransaction.add(R.id.fragment_frame,fragmentUser);
                }else {
                    fragmentTransaction.show(fragmentUser);
                }
                break;
        }
        fragmentTransaction.commit();//记得必须要commit,否则没有效果

    }

    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if(fragmentHome!=null){
            fragmentTransaction.hide(fragmentHome);
        }
        if(fragmentCar!=null){
            fragmentTransaction.hide(fragmentCar);
        }
        if(fragmentUser!=null){
            fragmentTransaction.hide(fragmentUser);
        }
    }

    private void setAllFalse() {
        homeLinear.setSelected(false);
        CarLinear.setSelected(false);
        userLinear.setSelected(false);
    }
}