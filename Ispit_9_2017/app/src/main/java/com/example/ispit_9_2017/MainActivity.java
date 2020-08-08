package com.example.ispit_9_2017;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    ConnectivityManager connManager;
    static NetworkInfo networkInfo;
    static Context appContext;

    private String sharedPrefFile = "com.example.ispit_9_2017";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connManager=(ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        networkInfo=connManager.getActiveNetworkInfo();
        appContext=getApplicationContext();
        doTask();
        /* proverka
        SharedPreferences sharedPreferences=getSharedPreferences(sharedPrefFile,MODE_PRIVATE);
        Log.d("ZANETA", sharedPreferences.getString("response", ""));*/
    }

    public static void doTask() {
        if (connectivity())
            new DoAsyncTask(appContext).execute();
    }

    private static boolean connectivity() {
        return networkInfo!=null && networkInfo.isConnected();
    }
}