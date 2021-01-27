package com.example.restaurantapk;

import android.app.Application;

import com.backendless.Backendless;

public class ApplicationClass extends Application
{

    public static final String APPLICATION_ID = "FB52ED6D-85F7-DB3F-FFAE-8F6000B61F00";
    public static final String API_KEY = "09A5215D-947F-4E2A-A8BF-3B19AAFE5F2D";
    public static final String SERVER_URL = "http://api.backendless.com";


    @Override
    public void onCreate() {
        super.onCreate();

        Backendless.setUrl(SERVER_URL);
        Backendless.initApp(getApplicationContext(),
                APPLICATION_ID,
                API_KEY);

    }

}
