package com.example.androidparte2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2 extends Activity implements View.OnClickListener {
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);

        TextView txt = findViewById(R.id.txt);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);

        Intent intent = getIntent();
        String str = intent.getStringExtra("nome");

        txt.setText(str);
    }

    @Override
    public void onClick(View view) {
        /* MANEIRA ERRADA
        Intent intent = new Intent(this,Main.class);
        startActivity(intent);
        */
        // SE QUISERMOS VOLTAR A MESMA ACTIVITY USAMOS O FINISH
        // Usamos o método finish que destroi obj, caso volte ao contexto que o chamou logo nao e preciso a intent
        finish();
    }
}
