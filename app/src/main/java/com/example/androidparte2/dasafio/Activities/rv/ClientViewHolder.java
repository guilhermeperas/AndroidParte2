package com.example.androidparte2.dasafio.Activities.rv;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.androidparte2.R;
import com.example.androidparte2.dasafio.Activities.EditClient;
import com.example.androidparte2.dasafio.Classes.Client;
import com.example.androidparte2.dasafio.db.DatabaseHelper;
import com.example.androidparte2.rv.Act2;

public class ClientViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener { // se for classes generucas este é o abstrato
    TextView nomeView;
    TextView idadeView;
    TextView idView;
    ImageView imgView;
    Context context;
    DatabaseHelper db;
    public ClientViewHolder(View itemView, Context context) {
        super(itemView);
        db = new DatabaseHelper(context.getApplicationContext());
        this.context = context;
        itemView.setOnClickListener(this);
        nomeView = (TextView) itemView.findViewById(R.id.nomeView);
        idView = (TextView) itemView.findViewById(R.id.idView);
        idadeView = (TextView) itemView.findViewById(R.id.idadeView);
        imgView = (ImageView) itemView.findViewById(R.id.imgView);
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
