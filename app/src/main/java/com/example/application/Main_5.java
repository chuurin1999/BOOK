package com.example.application;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Main_5 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView3);

        NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment_5);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.main_5_home,R.id.main_5_dash).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        NavigationUI.setupWithNavController(bottomNavigationView,navController);
    }
}
