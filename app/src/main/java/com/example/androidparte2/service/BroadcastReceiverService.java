package com.example.androidparte2.service;

import static android.app.Activity.RESULT_OK;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


public class BroadcastReceiverService extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if(bundle != null) {
            String string = bundle.getString(ServiceDownload.FILEPATH);
            int resultCode = bundle.getInt(ServiceDownload.RESULT);
            if (resultCode == RESULT_OK)
                Toast.makeText(context, "Download complete. Download URI: " + string, Toast.LENGTH_LONG).show();
        }
    }
}
