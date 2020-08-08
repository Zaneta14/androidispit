package com.example.ispit_9_2017;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import static com.example.ispit_9_2017.MainActivity.doTask;

public class ConnectivityChangeBR extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //registriran e vo manifestot, a intentot ne go naogja kako sistemski
        String action=intent.getAction();
        if (action.equals(Intent.CONNECTIVITY_CHANGE))
            MainActivity.doTask();
    }
}
