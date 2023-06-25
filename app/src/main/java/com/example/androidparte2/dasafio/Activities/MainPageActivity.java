package com.example.androidparte2.dasafio.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.androidparte2.R;

public class MainPageActivity extends Activity implements View.OnClickListener {
    Button btn,btnGestaoClientes,btnGestaoImoveis;
    Intent intent;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainproject);

        btn = (Button) findViewById(R.id.imoveisList);
        btnGestaoClientes = (Button) findViewById(R.id.gestaoClientes);
        btnGestaoImoveis = (Button) findViewById(R.id.gestaoImoveis);
        btn.setOnClickListener(this);
        btnGestaoClientes.setOnClickListener(this);
        btnGestaoImoveis.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        if(view.getId() == btn.getId()){
            intent = new Intent(this,ImoveisActivity.class);
        }else if (view.getId() == btnGestaoClientes.getId()){
            intent = new Intent(this, BackOfficeClientes.class);

        } else if (view.getId() == btnGestaoImoveis.getId()) {
            intent = new Intent(this, BackOfficeImoveis.class);
        }
        startActivity(intent);

    }
}
