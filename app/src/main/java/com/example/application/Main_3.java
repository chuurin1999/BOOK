package com.example.application;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class Main_3 extends AppCompatActivity {
    private int mYear, mMonth, mDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
//        final TextView dateText = (TextView)findViewById(R.id.textView3);
//        Button dateButton = (Button)findViewById(R.id.button9);
//        dateButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                final Calendar c = Calendar.getInstance();
//                mYear = c.get(Calendar.YEAR);
//                mMonth = c.get(Calendar.MONTH);
//                mDay = c.get(Calendar.DAY_OF_MONTH);
//                new DatePickerDialog(Main_3.this, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int day) {
//                        String format = "您設定的日期為:"+ setDateFormat(year,month,day);
//                        dateText.setText(format);
//                    }
//                }, mYear,mMonth, mDay).show();
//            }
//        });
    }
//    private String setDateFormat(int year,int monthOfYear,int dayOfMonth) {
//        return String.valueOf(year) + "-"
//                + String.valueOf(monthOfYear + 1) + "-"
//                + String.valueOf(dayOfMonth);
//    }
    public void showDatePickerDialog(View v)
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
