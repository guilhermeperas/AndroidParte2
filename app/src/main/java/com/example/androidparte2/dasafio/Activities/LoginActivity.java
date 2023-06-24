package com.example.androidparte2.dasafio.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.androidparte2.R;

public class LoginActivity extends Activity implements View.OnClickListener {
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn = (Button) findViewById(R.id.loginBtn);
        btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        // verificar o login depois fazer o intent

        //
        Intent intent = new Intent(this,MainPageActivity.class);
        startActivity(intent);
    }
}
