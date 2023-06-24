package com.example.androidparte2.dasafio.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.androidparte2.R;

public class MainPageActivity extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    Button btn;
    Intent intent;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainproject);

        btn = (Button) findViewById(R.id.imoveisList);
        btn.setOnClickListener(this);
        // botao de gestao
        Spinner spinner = (Spinner) findViewById(R.id.gestaoList);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gestaolist, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }
    @Override
    public void onClick(View view) {
//        Intent intent = new Intent(this,ImoveisActivity.class);
//        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Object item = parent.getItemAtPosition(position);
        String escolha = item.toString();
        switch (escolha){
            case "Imoveis":
                intent = new Intent(this, BackOfficeImoveis.class);
                break;
            case "Clientes":
                intent = new Intent(this, BackOfficeClientes.class);
                break;
            case "":
                return;
        }
        Log.d("Escohla","Item escolhido "+escolha);
        startActivity(intent);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        return;
    }
}
