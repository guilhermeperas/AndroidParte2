package com.example.androidparte2.broadcast;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidparte2.R;

public class MainBroadcast extends Activity implements View.OnClickListener {
    private BroadcastReceiver2 mybroadcast = new BroadcastReceiver2();
    Button btn,btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainbroadcast);
        /*
        Não existe registo no manifesto a sua declaração é via api
         */
        btn = (Button) findViewById(R.id.btn);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

        IntentFilter filter = new IntentFilter();
        filter.addAction("BROADCAST_RECEIVER_API");
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(mybroadcast,filter);
    }
    @Override
    public void onClick(View view) {
        Button bt = (Button) view;
        Intent intent = new Intent(bt.getText().toString());
        sendBroadcast(intent);
    }
    /*
    é invocado antes da app ser lançada, ou metodo onPause se chamado(alguma app fica em 1º plano)
     */

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction("BROADCAST_RECEIVER_API");
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(mybroadcast,filter);
        Toast.makeText(this, "Voltei ", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mybroadcast);
        Log.i("onStop", "dead");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try{
            unregisterReceiver(mybroadcast);
        }catch (Exception e){
            Log.i("onDestroy","ja nao está registada");
        }
    }
}
