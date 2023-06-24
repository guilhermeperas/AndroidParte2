package com.example.androidparte2.dasafio.Activities;

import static android.app.Activity.RESULT_OK;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.androidparte2.dasafio.Activities.LoginActivity;


public class BroadcastReceiverService extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Receiver","Recebi");
        Intent intencao = new Intent(context, LoginActivity.class);
        context.startActivity(intencao);
    }
}
