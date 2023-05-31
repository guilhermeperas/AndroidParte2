package com.example.androidparte2.rv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidparte2.R;

public class Act2 extends Activity {
    TextView txt;
    TextView idadetxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainact2);
        txt = (TextView) findViewById(R.id.txtNome);
        idadetxt = (TextView) findViewById(R.id.idadeView);

        Intent intent = getIntent();
        String nome = intent.getStringExtra("nome");
        int idade = intent.getIntExtra("int",12);

        txt.setText(nome);
        Toast.makeText(this, "idade "+idade, Toast.LENGTH_LONG).show();
    }
}
