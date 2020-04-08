package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main_1 extends AppCompatActivity {
    private Button button1,button2,button3,button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        button1 = (Button) findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent是一种运行时绑定（run-time binding）机制，它能在程序运行过程中连接两个不同的组件。在存放资源代码的文件夹下下，
                Intent i = new Intent(Main_1.this , Main_2.class);
                //启动
                startActivity(i);
            }
        });

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent是一种运行时绑定（run-time binding）机制，它能在程序运行过程中连接两个不同的组件。在存放资源代码的文件夹下下，
                Intent i = new Intent(Main_1.this , Main_4.class);
                //启动
                startActivity(i);
            }
        });

        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent是一种运行时绑定（run-time binding）机制，它能在程序运行过程中连接两个不同的组件。在存放资源代码的文件夹下下，
                Intent i = new Intent(Main_1.this , Main_3.class);
                //启动
                startActivity(i);
            }
        });

        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent是一种运行时绑定（run-time binding）机制，它能在程序运行过程中连接两个不同的组件。在存放资源代码的文件夹下下，
                Intent i = new Intent(Main_1.this , Main_5.class);
                //启动
                startActivity(i);
            }
        });
    }
}
