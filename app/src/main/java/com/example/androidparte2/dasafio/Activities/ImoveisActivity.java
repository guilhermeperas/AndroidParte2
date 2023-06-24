package com.example.androidparte2.dasafio.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidparte2.R;
import com.example.androidparte2.dasafio.Activities.rv.ClientAdapter;
import com.example.androidparte2.dasafio.Activities.rv.ImovelAdapter;
import com.example.androidparte2.dasafio.Classes.Client;
import com.example.androidparte2.dasafio.Classes.Imovel;
import com.example.androidparte2.dasafio.db.DatabaseHelper;

import java.util.List;

public class ImoveisActivity extends Activity implements View.OnClickListener {
    DatabaseHelper db;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rvmain);

        btn = (Button) findViewById(R.id.btnCreate);
        btn.setOnClickListener(this);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        db = new DatabaseHelper(getApplicationContext()); // oque muda deste para o imoveis e so a lista... e o onclick fazer classe para os 2
        List<Imovel> list = db.getImoveisList();
        Log.d("Lista imoveis", "Lista "+list);
        ImovelAdapter adapter = new ImovelAdapter(list,this);
        rv.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {

    }
}
