package com.example.pshop;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class home extends Fragment {

    private String fragmentText;

    private TextView fragmentTextView;

    public home(String fragmentText) {
        this.fragmentText=fragmentText;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_home,container,false);
        fragmentTextView= (TextView) view.findViewById(R.id.fragment_text);
        fragmentTextView.setText(fragmentText);
        return view;
    }
}