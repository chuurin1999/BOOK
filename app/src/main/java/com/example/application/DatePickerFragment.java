package com.example.application;

import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;


import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    int vid;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        super.onCreateDialog(savedInstanceState);

        //欲轉換的日期字串
        Bundle bData = getArguments();
        // 記錄下傳進來的是哪個 button 的 id
        vid = bData.getInt("view");
        String str = bData.getString("date");
        final Calendar c = Calendar.getInstance();
        Date date;
        if(!str.equals(""))
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //進行轉換
            ParsePosition pos = new ParsePosition(0);
            date = sdf.parse(str, pos);
            c.setTime(date);
        }
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // 建立 DatePickerDialog instance 並回傳
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day)
    {
        Button button = (Button) getActivity().findViewById(vid);
        // 注意 月的起始值是 0，所以要加 1
        button.setText(""+year+"-"+(month+1)+"-"+day);
    }

}

