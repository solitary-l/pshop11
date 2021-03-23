package com.example.pshop;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class people extends Fragment{

    private String fragmentText;
    private TextView fragmentTextView;

    public people(String fragmentText) {
        this.fragmentText = fragmentText;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_people, container, false);
        fragmentTextView = (TextView) view.findViewById(R.id.fragment_text);
        Button people = (Button) view.findViewById(R.id.center);
        people.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), center.class));
            }
        });
        return view;
    }
}