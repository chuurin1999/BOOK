package com.example.application;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class bookDB extends Application {
    public void onCreate() {
        super.onCreate();
        // 啟動Stetho
        Stetho.initializeWithDefaults(this);

    }
}
