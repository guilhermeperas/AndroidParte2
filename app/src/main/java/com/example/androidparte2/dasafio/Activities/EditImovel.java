package com.example.androidparte2.dasafio.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.androidparte2.R;
import com.example.androidparte2.dasafio.Classes.Imovel;
import com.example.androidparte2.dasafio.Classes.ImovelDetail;
import com.example.androidparte2.dasafio.db.DatabaseHelper;

public class EditImovel extends Activity implements View.OnClickListener {
    EditText descriptionView,typologyView,localizacaoView;
    CheckBox saunaView,commonAreaView;
    Button btn;
    DatabaseHelper db;
    Imovel imovel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_imovel);
        descriptionView = (EditText) findViewById(R.id.edittext_description);
        typologyView = (EditText) findViewById(R.id.edittext_typology);
        localizacaoView = (EditText) findViewById(R.id.edittext_location);
        saunaView = (CheckBox) findViewById(R.id.checkbox_sauna);
        commonAreaView = (CheckBox) findViewById(R.id.checkbox_common_area);
        btn = (Button) findViewById(R.id.button_save);
        imovel = new Imovel();
        db = new DatabaseHelper(getApplicationContext());

        Intent intent = getIntent();
        int id  = intent.getIntExtra("id",0);
        if(id != 0){
            imovel = db.getImovel(id);
            descriptionView.setText(imovel.description);
            typologyView.setText(imovel.typology);
            localizacaoView.setText(imovel.location);
            Log.d("Sauna","tem sauna "+imovel.detail.hasSauna);
            Log.d("common","tem hasCommonArea "+imovel.detail.hasCommonArea);
            saunaView.setChecked(changeValueToBoolean(imovel.detail.hasSauna));
            commonAreaView.setChecked(changeValueToBoolean(imovel.detail.hasCommonArea));
        }else
            btn.setText("Criar");
        btn.setOnClickListener(this);
    }
    private boolean changeValueToBoolean(String value) {
        return "sim".equals(value);
    }
    private String changeBooleanToValue(boolean value) {
        return value ? "sim" : "não";
    }

    @Override
    public void onClick(View v) {
        String descricao = descriptionView.getText().toString();
        String tipologia = typologyView.getText().toString();
        String location = localizacaoView.getText().toString();
        boolean sauna = saunaView.isChecked();
        boolean common = commonAreaView.isChecked();
        if(descricao.matches("") && tipologia.matches("") && location.matches("")){
            Toast.makeText(this, "Preencha os campos obrigatorios", Toast.LENGTH_LONG).show();
            return;
        }
        imovel.description = descricao;
        imovel.typology = tipologia;
        imovel.location = location;
        imovel.detail = new ImovelDetail(changeBooleanToValue(sauna),changeBooleanToValue(common));
        if (imovel.id != null) {
            Imovel updatedImovel = db.updateImovel(imovel);
            if (updatedImovel != null) {
                Toast.makeText(this, "Imovel atualizado com sucesso", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Falha ao atualizar o imóvel", Toast.LENGTH_LONG).show();
            }
        } else {
            if (db.createImovel(imovel) != null) {
                Toast.makeText(this, "Imovel criado com sucesso", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Falha ao criar o imóvel", Toast.LENGTH_LONG).show();
            }
        }
        // update ao recycler view...
        finish();
    }
}
