package com.example.application;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Main_2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment_2);

        NavigationUI.setupWithNavController(bottomNavigationView,navController);


    }
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
