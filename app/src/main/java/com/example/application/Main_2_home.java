package com.example.application;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class Main_2_home extends Fragment {
    EditText money_input, caption_input, note_input;
    Button add_button,clear_button,date_input;
    //<-----------------------
    private String[] type = new String[] {"收入", "支出"};
    private String[] tea = new String[]{"薪資收入","獎金收入","投資收入","兼職收入","零用金"};
    private String[][] type2 = new String[][]{{"薪資收入","獎金收入","投資收入","兼職收入","零用金"},{"飲食支出","交通支出","娛樂支出","醫療支出","生活支出","衣著支出","教育支出"}};
    private Spinner sp;//第一個下拉選單
    private Spinner sp2;//第二個下拉選單
    private Context context;
    ArrayAdapter<String> adapter ;
    ArrayAdapter<String> adapter2;
    //--------------------->
    public Main_2_home() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_2_home, container, false);
        money_input = view.findViewById(R.id.money_input);
        caption_input = view.findViewById(R.id.caption_input);
        note_input = view.findViewById(R.id.note_input);
        date_input = view.findViewById(R.id.date_input);
        clear_button=view.findViewById(R.id.clear_button);
        add_button = view.findViewById(R.id.add_button);
        clear_button.setOnClickListener(clear_button_onClick);
        add_button.setOnClickListener(add_button_onClick);
        //從這-----------------------------------------------------------------------------------------------
        context = getContext();
        //程式剛啟始時載入第一個下拉選單
        adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, type);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp = (Spinner) view.findViewById(R.id.type);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(selectListener);
        //因為下拉選單第一個為地址，所以先載入地址群組進第二個下拉選單
        adapter2 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, tea);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2 = (Spinner) view.findViewById(R.id.type2);
        sp2.setAdapter(adapter2);
        //-----------------------------------------------------------------------------------------------------到這

        return view;
    }
    private final View.OnClickListener add_button_onClick = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            MyDBHelper myDB = new MyDBHelper(getActivity());
            myDB.addBook(
                    date_input.getText().toString().trim(),
                    Integer.valueOf(money_input.getText().toString().trim()),
                    caption_input.getText().toString().trim(),
                    sp.getSelectedItem().toString().trim(),
                    sp2.getSelectedItem().toString().trim(),
                    note_input.getText().toString().trim());
        }
    };
    private final View.OnClickListener clear_button_onClick = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            clearAll();
        }
    };
    private void clearAll() {//清空目前所選以及所有editText
        date_input.setText("");
        money_input.setText("");
        caption_input.setText("");
        sp.setSelection(0);
        sp2.setSelection(0);
        note_input.setText("");
    }
    //第一個下拉類別的監看
    private AdapterView.OnItemSelectedListener selectListener = new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
            //讀取第一個下拉選單是選擇第幾個
            int pos = sp.getSelectedItemPosition();
            //重新產生新的Adapter，用的是二維陣列type2[pos]
            adapter2 = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, type2[pos]);
            //載入第二個下拉選單Spinner
            sp2.setAdapter(adapter2);
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
}