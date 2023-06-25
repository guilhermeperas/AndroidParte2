package com.example.androidparte2.dasafio.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidparte2.R;
import com.example.androidparte2.dasafio.Classes.Client;
import com.example.androidparte2.dasafio.db.DatabaseHelper;

public class EditClient extends Activity implements View.OnClickListener {
    DatabaseHelper db;
    EditText nomeView,idadeView;
    int id;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_client);
        Intent intent = getIntent();
        intent.getStringExtra("nome");
        nomeView = findViewById(R.id.nomeView);
        idadeView = findViewById(R.id.idadeView);
        btn = (Button) findViewById(R.id.confirm_button);
        if(intent.getStringExtra("nome") != null){
            id = intent.getIntExtra("id",0);
            nomeView.setText(intent.getStringExtra("nome"));
            idadeView.setText(intent.getStringExtra("idade"));
        }else {
            btn.setText("Criar");
        }
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Client client = new Client();
        String nome = nomeView.getText().toString();
        String idade = idadeView.getText().toString();
        if(nome.matches("") && idade.matches("")){
            Toast.makeText(this, "Preencha os campos todos", Toast.LENGTH_LONG).show();
            return;
        }
        client.name = nomeView.getText().toString();
        client.age = idadeView.getText().toString();
        db = new DatabaseHelper(getApplicationContext());
        if (id != 0) {
            client.id = String.valueOf(id);
            if(db.updateClient(client) != null){
                Toast.makeText(this, "Cliente atualizado com sucesso", Toast.LENGTH_LONG).show();
            }
        }else {
            if(db.createClient(client) != null){
                Toast.makeText(this, "Cliente criado com sucesso", Toast.LENGTH_LONG).show();
            }
        }
        db.fecharDB();
        finish();
    }
}
