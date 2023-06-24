package com.example.androidparte2.dasafio.Activities.rv;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.androidparte2.R;
import com.example.androidparte2.dasafio.Activities.EditClient;
import com.example.androidparte2.dasafio.Classes.Client;
import com.example.androidparte2.dasafio.db.DatabaseHelper;
import com.example.androidparte2.rv.Act2;

public class ImovelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener { // se for classes generucas este é o abstrato
    TextView descricaoView;
    TextView tiplogiaView;
    TextView commonView;
    TextView saunaView;
    TextView localizacaoView;
    TextView idView;
    ImageView imgView;
    Context context;
    DatabaseHelper db;
    public ImovelViewHolder(View itemView, Context context) {
        super(itemView);
        db = new DatabaseHelper(context.getApplicationContext());
        this.context = context;
        itemView.setOnClickListener(this);

        idView = (TextView) itemView.findViewById(R.id.idView);
        descricaoView = (TextView) itemView.findViewById(R.id.descricaoView);
        tiplogiaView = (TextView) itemView.findViewById(R.id.tipologiaView);
        commonView = (TextView) itemView.findViewById(R.id.commonView);
        saunaView = (TextView) itemView.findViewById(R.id.saunaView);
        localizacaoView = (TextView) itemView.findViewById(R.id.localizacaoView);
        imgView = (ImageView) itemView.findViewById(R.id.imgView);

        Button btnEdit = (Button) itemView.findViewById(R.id.editBtn);
        Button btnDel = (Button) itemView.findViewById(R.id.deleteBtn);

        btnEdit.setVisibility(View.GONE);
        btnDel.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.editBtn:
                Intent intent = new Intent(context, EditClient.class);
                intent.putExtra("id",Integer.parseInt(idView.getText().toString()));
                context.startActivity(intent);
                break;
            case R.id.deleteBtn:
                // mandar um aviso a perguntar sim ou nao
                if(db.apagarClient(Integer.parseInt(idView.getText().toString()))){
                    Toast.makeText(context, "Cliente apagado com sucesso", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(context, "Erro ao apagar cliente", Toast.LENGTH_SHORT).show();
                break;
        }
        // verificar que botao ele clico

//        Intent intent = new Intent(this.context, Act2.class);
//        intent.putExtra("id",idView.getText().toString());
//        this.context.startActivity(intent);

    }
}
