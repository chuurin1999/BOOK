package com.example.application;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Main_4_home extends Fragment {
    FloatingActionButton add_button2;
    public Main_4_home(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_4_home,container,false);
        add_button2 = view.findViewById(R.id.add_button2);
        add_button2.setOnClickListener(add_button_onClick);
        return view;
    }
    private final View.OnClickListener add_button_onClick = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Intent myIntent = new Intent(getActivity(), Main_4_1_1.class);
            startActivity(myIntent);//启动
        }
    };
}
