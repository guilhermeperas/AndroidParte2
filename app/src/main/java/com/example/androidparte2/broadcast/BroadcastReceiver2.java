package com.example.androidparte2.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BroadcastReceiver2 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("Sergio Script","BroadCastReceiver2");
        Toast.makeText(context, "boda", Toast.LENGTH_LONG).show();
    }
}
