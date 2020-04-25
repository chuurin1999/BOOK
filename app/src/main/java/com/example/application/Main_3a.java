package com.example.application;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.NumberFormat;
import java.util.ArrayList;

public class Main_3a extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageView empty_imageview;
    TextView no_data,date_view,day_view;
    MyDBHelper myDB;
    ArrayList<String> book_id, book_date, book_money, book_caption,book_spinner1,book_spinner2, book_note;
    CustomAdapter customAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3a);
        date_view=(TextView)findViewById(R.id.date_view);
        recyclerView = findViewById(R.id.recyclerView);
        day_view = findViewById(R.id.day_view);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);
        myDB = new MyDBHelper(Main_3a.this);
        book_id = new ArrayList<>();
        book_date = new ArrayList<>();
        book_money = new ArrayList<>();
        book_caption = new ArrayList<>();
        book_spinner1 =new ArrayList<>();
        book_spinner2 = new ArrayList<>();
        book_note = new ArrayList<>();
        storeDataInArrays();
        querySettlement();
        customAdapter = new CustomAdapter(Main_3a.this,this, book_id,book_date,book_money,book_caption,book_spinner1,book_spinner2,book_note);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Main_3a.this));
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = this.queryData();
        if(cursor.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                book_id.add(cursor.getString(0));
                book_date.add(cursor.getString(1));
                book_money.add(cursor.getString(2));
                book_caption.add(cursor.getString(3));
                book_spinner1.add(cursor.getString(4));
                book_spinner2.add(cursor.getString(5));
                book_note.add(cursor.getString(6));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }
    Cursor queryData(){
        Intent myIntent=getIntent();
        Bundle bundle = myIntent.getExtras();
        String startDate = bundle.getString("startDate");
        String endDate = bundle.getString("endtDate");
        String date="\uD83D\uDDD3"+" "+startDate+"~"+endDate;
        date_view.setText(date);
        String query = "SELECT * FROM library WHERE 日期 between '"+startDate+"' AND '"+endDate+"'";
        Log.d("startDate",query);
        SQLiteDatabase db = myDB.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    //    本期結算
    void querySettlement() {
        int num=0;
        String string="";
        Intent myIntent=getIntent();
        Bundle bundle = myIntent.getExtras();
        String startDate = bundle.getString("startDate");
        String endDate = bundle.getString("endtDate");
        String income = "SELECT * FROM library WHERE (日期 between '"+startDate+"' AND '"+endDate+"')AND (狀態 = '收入' )";
        String expense ="SELECT * FROM library WHERE (日期 between '"+startDate+"' AND '"+endDate+"')AND (狀態 = '支出' )";
        Log.d("query1",income);
        SQLiteDatabase db = myDB.getReadableDatabase();
        Cursor cursor = null;
        Cursor cursor1 = null;
        if (db != null) {
            cursor = db.rawQuery(income, null);
            cursor1 = db.rawQuery(expense, null);
        }
        if (cursor.getCount() == 0) {
            day_view.setText("本期結算:0");
        } else {
            while (cursor.moveToNext()) {
                num+=(cursor.getInt(2));
            }
        }
        if (cursor1.getCount() == 0) {
            day_view.setText("本期結算:0");
        } else {
            while (cursor1.moveToNext()) {
                num-=(cursor1.getInt(2));
            }
        }
        string+="本期結算 "+num;
        Log.d("string",string);
        day_view.setText(string);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all Data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDBHelper myDB = new MyDBHelper(Main_3a.this);
                myDB.deleteAllData();
                //Refresh Activity
                Intent intent = new Intent(Main_3a.this, Main_3a.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.create().show();
    }
}