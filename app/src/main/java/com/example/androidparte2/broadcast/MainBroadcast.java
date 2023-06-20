package com.example.androidparte2.broadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidparte2.R;


public class MainBroadcast extends Activity {
    //Registo via API
    private BroadcastReceiver mybroadcast2 = new BroadcastReceiver2();
    private BroadcastReceiver mybroadcast3 = new BroadcastReceiver3();
    private BroadcastReceiver mybroadcastAlarm = new BroadcastReceiverAlarm();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainbroadcast);
        //Não existe registo no manifesto, a sua declaração é via API
        IntentFilter it = new IntentFilter();
        it.addAction("BROADCAST_RECEIVER_API");
        it.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(mybroadcast2, it);

        IntentFilter it2 = new IntentFilter();
        it2.addAction("BROADCAST_RECEIVER_START_ACTIVITY");
        it2.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(mybroadcast3, it2);

        IntentFilter it3 = new IntentFilter();
        it3.addAction("ALARM_EXECUTE");
        it3.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(mybroadcastAlarm, it3);
    }

    //Invocado antes da app ser lançada, ou o método onPause se chamado (alguma app fica em 1º plano)
    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter it = new IntentFilter();
        it.addAction("BROADCAST_RECEIVER_API");
        it.addCategory(Intent.CATEGORY_DEFAULT);

        registerReceiver(mybroadcast2, it);
        Toast.makeText(this, "Voltei", Toast.LENGTH_LONG).show();
    }

    //Isto precisa de estar presente senão a ligação ao broadcast nunca desaparece
    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mybroadcast2);
        //Não dá para fazer um Toast porque o Toast já não vai ter ligação com o contexto no onStop
        Log.i("onStop", "Já não está registada");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(mybroadcast2);
        } catch (Exception e) { Log.i("onDestroy", "Já não está registada"); }
    }

    public void invocar(View view) {
        if (view.getId() == R.id.btnAlarm) {
            Intent it = new Intent(MainBroadcast.this, AlarmActivity.class);
            it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        } else {
            Button btn = (Button) view;
            Intent intent = new Intent(btn.getText().toString());
            sendBroadcast(intent);
        }
    }
}