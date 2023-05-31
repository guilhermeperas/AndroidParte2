package com.example.androidparte2.db;


import android.app.Activity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidparte2.R;

import java.util.List;

public class MainDB extends Activity
{
    DatabaseHelper db;
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maindb);
        rv = (RecyclerView) findViewById(R.id.rvbd);
        rv.setLayoutManager(new LinearLayoutManager(this));
        db = new DatabaseHelper(getApplicationContext());
        Tag t_php = db.criarTag("PHP");
        Tag t_java = db.criarTag("Java");
        Tag t_c = db.criarTag("C");

        Artigo l_php = db.criarArtigo("PHP for beginners", "https://www.php.net/", 1, new long[]{t_php.id});
        Artigo l_java = db.criarArtigo("Java for beginners", "", 1, new long[]{t_java.id});
        Artigo l_php_java = db.criarArtigo("PHP & Java for beginners", "", 1, new long[]{t_php.id, t_java.id});

        List<Artigo> listaArtigos = db.obterArtigos("");
        List<Artigo> listaArtigosPhp = db.obterArtigos(t_php.nome);

        ArtigoAdapter adapter = new ArtigoAdapter(listaArtigos);
        rv.setAdapter(adapter);

        db.fecharDB();
    }
}