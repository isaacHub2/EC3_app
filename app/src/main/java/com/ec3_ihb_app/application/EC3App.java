package com.ec3_ihb_app.application;

import android.app.Application;
import android.content.Context;

import androidx.appcompat.app.AppCompatDelegate;

public class EC3App extends Application {
    private static EC3App instance;
    private static Context appContext;

    public static EC3App getInstance(){
        return instance;
    }

    public static Context getAppContext(){
        return appContext;
    }

    public void setAppContext(Context mAppContext){
        this.appContext = mAppContext;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        instance = this;

        this.setAppContext(getApplicationContext());
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
}
