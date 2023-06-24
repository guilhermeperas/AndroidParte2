package com.example.androidparte2.dasafio.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.example.androidparte2.R;
import com.example.androidparte2.dasafio.Classes.Client;
import com.example.androidparte2.dasafio.db.DatabaseHelper;

public class EditClient extends Activity implements View.OnClickListener {
    DatabaseHelper db;
    EditText nomeView,idadeView;
    Button btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_client);
        btn = (Button) findViewById(R.id.confirm_button);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
