package com.example.application;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

public class MyDBHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "BookLibrary.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "library";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_DATE = "日期";
    private static final String COLUMN_MONEY = "金額";
    private static final String COLUMN_CAPTION = "摘要";
    private static final String COLUMN_SPINNER1 = "狀態";
    private static final String COLUMN_SPINNER2 = "詳細狀態";
    private static final String COLUMN_NOTE = "備註";

    public MyDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +" (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DATE + " DATE, " +
                COLUMN_MONEY + " INTEGER, " +
                COLUMN_CAPTION + " TEXT, " +
                COLUMN_SPINNER1 + " TEXT, " +
                COLUMN_SPINNER2 + " TEXT, " +
                COLUMN_NOTE + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    void addBook(String date,int money,String caption,String spinner1,String spinner2,String note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_MONEY, money);
        cv.put(COLUMN_CAPTION, caption);
        cv.put(COLUMN_SPINNER1, spinner1);
        cv.put(COLUMN_SPINNER2, spinner2);
        cv.put(COLUMN_NOTE, note);
        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    void updateData(String row_id,String date,String money,String caption,String spinner1,String spinner2,String note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_MONEY, money);
        cv.put(COLUMN_CAPTION, caption);
        cv.put(COLUMN_SPINNER1, spinner1);
        cv.put(COLUMN_SPINNER2, spinner2);
        cv.put(COLUMN_NOTE, note);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }


    Cursor queryData(){
//        Bundle bundle = getIntent().getExtras();
//        String startDate = bundle.getString("startDate");
//        String endDate = bundle.getString("endtDate");
//        String query = "SELECT * FROM " + TABLE_NAME+" WHERE " + COLUMN_DATE+ " between "+startDate+" AND "+endDate;
        String query = "SELECT * FROM " + TABLE_NAME+" WHERE " + COLUMN_DATE+ " between '2020-4-11' AND '2020-4-30'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


}
