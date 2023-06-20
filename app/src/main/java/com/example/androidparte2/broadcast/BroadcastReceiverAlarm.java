package com.example.androidparte2.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BroadcastReceiverAlarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("Sergio Script", "BroadcastReceiverAlarm");
        Toast.makeText(context, "Alarme Ativado", Toast.LENGTH_SHORT).show();
    }
}