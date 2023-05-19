package com.example.androidparte2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Main extends Activity implements View.OnClickListener {
    TextView txt;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        txt = (TextView) findViewById(R.id.txt);
        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(Main.this, "BODA", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,Main2.class); // (origem,destino)
        intent.putExtra("nome","sergio");
        startActivity(intent);
    }
}
