package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main_4_1_1 extends AppCompatActivity {
    private Button mAuthorize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4_1_1);
        mAuthorize = (Button) findViewById(R.id.authorize);
        mAuthorize.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Main_4_1_1.this , Main_4.class);
                startActivity(i);
            }
        });
    }
}
