package com.example.androidparte2.dasafio.Activities;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidparte2.R;
import com.example.androidparte2.dasafio.Classes.User;
import com.example.androidparte2.dasafio.db.DatabaseHelper;

public class LoginActivity extends Activity implements View.OnClickListener {
    EditText usernameInput,passwordInput;
    DatabaseHelper db;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameInput = (EditText) findViewById(R.id.username);
        passwordInput = (EditText) findViewById(R.id.password);
        btn = (Button) findViewById(R.id.loginBtn);
        btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        // verificar o login depois fazer o intent
        db = new DatabaseHelper(getApplicationContext());
        ContentValues values = new ContentValues();
        values.put("username",usernameInput.getText().toString());
        values.put("password",passwordInput.getText().toString()); // encriptar

        User user = db.checkLogin(values);
        Log.d("LOGIN","User "+user);
        if(user == null){
            Toast.makeText(this, "Dados invalidos", Toast.LENGTH_LONG).show();
             return;
        }
        // ver coisas de sessao idk
        db.fecharDB();
        Intent intent = new Intent(this,MainPageActivity.class);
        startActivity(intent);
    }
}
