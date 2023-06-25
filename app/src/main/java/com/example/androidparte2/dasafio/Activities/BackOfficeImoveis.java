package com.example.androidparte2.dasafio.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidparte2.R;
import com.example.androidparte2.dasafio.Activities.rv.BackofficeImovelAdapter;
import com.example.androidparte2.dasafio.Activities.rv.ImovelAdapter;
import com.example.androidparte2.dasafio.Classes.Imovel;
import com.example.androidparte2.dasafio.db.DatabaseHelper;

import java.util.List;

public class BackOfficeImoveis extends Activity implements View.OnClickListener {
    DatabaseHelper db;
    Button btn;
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rvbmain);

        btn = (Button) findViewById(R.id.btnCreate);
        btn.setOnClickListener(this);

        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        loadAdapter();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,EditImovel.class);
        startActivity(intent);
    }
    private void loadAdapter(){
        db = new DatabaseHelper(getApplicationContext()); // oque muda deste para o imoveis e so a lista... e o onclick fazer classe para os 2
        List<Imovel> list = db.getImoveisList();

        BackofficeImovelAdapter adapter = new BackofficeImovelAdapter(list,this);
        rv.setAdapter(adapter);
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadAdapter();
    }
}
