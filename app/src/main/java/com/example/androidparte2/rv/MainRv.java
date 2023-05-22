package com.example.androidparte2.rv;

import android.app.Activity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidparte2.R;

import java.util.ArrayList;
import java.util.List;


public class MainRv extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rvmain);
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        Aluno a = new Aluno("Joao","12",R.drawable.orico);
        Aluno a1 = new Aluno("Ana","55",R.drawable.orico);
        Aluno a2 = new Aluno("Pedro","14",R.drawable.orico);
        Aluno a3 = new Aluno("Arroz","13",R.drawable.orico);

        List<Aluno> list = new ArrayList<Aluno>();
        list.add(a);
        list.add(a1);
        list.add(a2);
        list.add(a3);

        AlunoAdapter adapter = new AlunoAdapter(list,this);
        rv.setAdapter(adapter);
    }

}
