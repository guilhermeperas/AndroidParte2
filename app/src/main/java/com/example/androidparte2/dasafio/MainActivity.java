package com.example.androidparte2.dasafio;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import com.example.androidparte2.dasafio.Activities.BroadcastReceiverService;
import com.example.androidparte2.dasafio.db.DatabaseHelper;
import com.example.androidparte2.dasafio.service.MyService;


public class MainActivity extends Activity {
    DatabaseHelper db;
    private BroadcastReceiver broadService = new BroadcastReceiverService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.d("MainAtividade", "Entrou aqui");
        IntentFilter it = new IntentFilter();
        it.addAction("com.example.androidparte2.dasafio.service");
        it.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(broadService, it);

        String jsonUrl = "https://www.dropbox.com/s/brj3s1aenj07icm/desafio.json?dl=1";
        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("urlpath", jsonUrl);
        startService(intent);

    }
}
