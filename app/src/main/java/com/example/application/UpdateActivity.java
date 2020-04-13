package com.example.application;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class UpdateActivity extends AppCompatActivity {
    EditText money_input, caption_input, note_input;
    Button update_button, delete_button,date_input;
    String id, date, money, caption,spinner1,spinner2,note;
    private String[] type = new String[] {"收入", "支出"};
    private String[] tea = new String[]{"薪資收入","獎金收入","投資收入","兼職收入","零用金"};
    private String[][] type2 = new String[][]{{"薪資收入","獎金收入","投資收入","兼職收入","零用金"},{"飲食支出","交通支出","娛樂支出","醫療支出","生活支出","衣著支出","教育支出"}};
    private Spinner spinner1_input;//第一個下拉選單
    private Spinner spinner2_input;//第二個下拉選單
    private Context context;
    ArrayAdapter<String> adapter ;
    ArrayAdapter<String> adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        date_input = findViewById(R.id.date_input2);
        money_input = findViewById(R.id.money_input2);
        caption_input = findViewById(R.id.caption_input2);
        //程式剛啟始時載入第一個下拉選單
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, type);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1_input = findViewById(R.id.type);
        spinner1_input.setAdapter(adapter);
        spinner1_input.setOnItemSelectedListener(selectListener);
        //因為下拉選單第一個為地址，所以先載入地址群組進第二個下拉選單
        adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, tea);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2_input =findViewById(R.id.type2);
        spinner2_input.setAdapter(adapter2);
        note_input = findViewById(R.id.note_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);
        context =this;
        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle("編輯"+spinner1+"紀錄");
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDBHelper myDB = new MyDBHelper(UpdateActivity.this);
                date = date_input.getText().toString().trim();
                money = money_input.getText().toString().trim();
                caption = caption_input.getText().toString().trim();
                spinner1 =spinner1_input.getSelectedItem().toString().trim();
                spinner2 = spinner2_input.getSelectedItem().toString().trim();
                note = note_input.getText().toString().trim();
                myDB.updateData(id, date, money,caption,spinner1,spinner2,note);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id")    && getIntent().hasExtra("date")  &&
           getIntent().hasExtra("money") && getIntent().hasExtra("caption") &&
           getIntent().hasExtra("spinner1") &&  getIntent().hasExtra("spinner2") &&
           getIntent().hasExtra("note")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            date = getIntent().getStringExtra("date");
            money = getIntent().getStringExtra("money");
            caption = getIntent().getStringExtra("caption");
            spinner1 = getIntent().getStringExtra("spinner1");
            spinner2 = getIntent().getStringExtra("spinner2");
            note = getIntent().getStringExtra("note");
            //Setting Intent Data
            date_input.setText(date);
            money_input.setText(money);
            caption_input.setText(caption);
            note_input.setText(note);
            spinner1_input.setAdapter(adapter);
            spinner1_input.setOnItemSelectedListener(selectListener);
            spinner2_input.setAdapter(adapter2);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + date + " ?");
        builder.setMessage("Are you sure you want to delete " + caption + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDBHelper myDB = new MyDBHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
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
    private AdapterView.OnItemSelectedListener selectListener = new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
            //讀取第一個下拉選單是選擇第幾個
            int pos = spinner1_input.getSelectedItemPosition();
            //重新產生新的Adapter，用的是二維陣列type2[pos]
            adapter2 = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, type2[pos]);
            //載入第二個下拉選單Spinner
            spinner2_input.setAdapter(adapter2);
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
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
