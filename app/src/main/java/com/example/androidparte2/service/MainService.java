package com.example.androidparte2.service;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidparte2.R;
import com.example.androidparte2.broadcast.BroadcastReceiver2;
import com.example.androidparte2.rv.Aluno;
import com.example.androidparte2.rv.AlunoAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainService extends Activity implements View.OnClickListener {
    TextView txt;
    Button btn;

    private BroadcastReceiver broadService = new BroadcastReceiverService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_service);

        txt = (TextView) findViewById(R.id.status);
        btn = (Button) findViewById(R.id.btnDownload);

        IntentFilter it = new IntentFilter();
        it.addAction("com.example.androidparte2.service"); // nome unico, pode ser oque quisemos
        it.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(broadService,it);

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this,ServiceDownload.class);

        intent.putExtra(ServiceDownload.FILENAME,"index.html");
        intent.putExtra(ServiceDownload.URL,"https://epcc.pt/");

        startActivity(intent);

        txt.setText("Service started");
    }
}
