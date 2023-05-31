package com.example.androidparte2.db;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidparte2.R;

public class DetalheArtigo extends Activity {
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teste);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);
        if(id == 0)
            return;
        db = new DatabaseHelper(getApplicationContext());
        Artigo artigo = db.obterArtigo(id);
        TextView idView = (TextView) findViewById(R.id.idView);
        TextView title = (TextView) findViewById(R.id.tituloView);
        TextView url = (TextView) findViewById(R.id.urlView);

        idView.setText(String.valueOf(artigo.id));
        title.setText(artigo.title);
        url.setText(artigo.url);

    }
}
