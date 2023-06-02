package com.example.androidparte2.db;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.androidparte2.R;

public class DetalheArtigo extends Activity {
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teste);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        db = new DatabaseHelper(getApplicationContext());

        Artigo artigo = db.obterArtigo(Integer.parseInt(id));
        TextView idView = (TextView) findViewById(R.id.idText);
        TextView title = (TextView) findViewById(R.id.tituloView);
        TextView url = (TextView) findViewById(R.id.urlView);

        idView.setText(String.valueOf(artigo.id));
        title.setText(artigo.title);
        url.setText(artigo.url);

        db.fecharDB();
    }
}
