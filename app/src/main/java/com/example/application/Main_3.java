package com.example.application;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class Main_3 extends AppCompatActivity {
    static final String DATABASE_NAME = "BookLibrary.db";// 資料庫名稱
    static final String TABLE_NAME = "library";        // 資料表名稱
    private static final String COLUMN_DATE = "日期";
    MyDBHelper dbHelper; //宣告資料庫幫助器變數
    Button query1_button,query_button,OneDate,StartDate,EndDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        Bundle bundle = this.getIntent().getExtras();
        query1_button=findViewById(R.id.query1_button);
        query1_button.setOnClickListener(query1_button_onClick);
        query_button=findViewById(R.id.query_button);
        query_button.setOnClickListener(query_button_onClick);
        OneDate=findViewById(R.id.OneDate_button);
        StartDate=findViewById(R.id.start_button);
        EndDate=findViewById(R.id.end_button);
    }
    private final View.OnClickListener query1_button_onClick = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            MyDBHelper myDB = new MyDBHelper(Main_3.this);
            String OneDay = OneDate.getText().toString();
            Intent myIntent = new Intent(Main_3.this, Main_3_1_2_4a_3.class);
            Bundle myBundle = new Bundle();
            myBundle.putString("OneDay",OneDay);
            Log.d("OneDay",OneDay);
            myIntent.putExtras(myBundle);
            startActivity(myIntent);
        }
    };
    private final View.OnClickListener query_button_onClick = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            MyDBHelper myDB = new MyDBHelper(Main_3.this);
            String startDate = StartDate.getText().toString();
            String endtDate = EndDate.getText().toString();
            Intent myIntent = new Intent(Main_3.this, Main_3a.class);
            Bundle myBundle = new Bundle();
            myBundle.putString("startDate",startDate);
            myBundle.putString("endtDate",endtDate);
            Log.d("startDate",startDate);
            myIntent.putExtras(myBundle);
            startActivity(myIntent);
        }
    };
    public void btnChoseOneDate(View v)
    {
        DialogFragment newFragment = new DatePickerFragment();
        Bundle bData = new Bundle();
        bData.putInt("view", v.getId());
        Button button = (Button) v;
        bData.putString("date", button.getText().toString());
        newFragment.setArguments(bData);
        newFragment.show(getSupportFragmentManager(), "日期挑選器");
    }
    public void btnChoseStartDate(View v)
    {
        DialogFragment newFragment = new DatePickerFragment();
        Bundle bData = new Bundle();
        bData.putInt("view", v.getId());
        Button button = (Button) v;
        bData.putString("date", button.getText().toString());
        newFragment.setArguments(bData);
        newFragment.show(getSupportFragmentManager(), "日期挑選器");
    }

    public void btnChoseEndDate(View v)
    {
        DialogFragment newFragment = new DatePickerFragment();
        Bundle bData = new Bundle();
        bData.putInt("view", v.getId());
        Button button = (Button) v;
        bData.putString("date", button.getText().toString());
        newFragment.setArguments(bData);
        newFragment.show(getSupportFragmentManager(), "日期挑選器");
    }
}
