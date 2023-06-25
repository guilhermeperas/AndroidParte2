package com.example.androidparte2.dasafio.Activities.rv;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.androidparte2.R;
import com.example.androidparte2.dasafio.Activities.EditImovel;
import com.example.androidparte2.dasafio.db.DatabaseHelper;

public class BackofficeImovelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener { // se for classes generucas este é o abstrato
    TextView descricaoView,tiplogiaView,commonView,localizacaoView,idView,saunaView;
    NetworkImageView imgView;
    Context context;
    DatabaseHelper db;
    Button delBtn,editBtn;
    BackofficeImovelAdapter adapter;
    public BackofficeImovelViewHolder(View itemView, Context context, BackofficeImovelAdapter imovelAdapter) {
        super(itemView);
        db = new DatabaseHelper(context.getApplicationContext());
        this.context = context;
        itemView.setOnClickListener(this);
        adapter = imovelAdapter;
        idView = (TextView) itemView.findViewById(R.id.idView);
        descricaoView = (TextView) itemView.findViewById(R.id.descricaoView);
        tiplogiaView = (TextView) itemView.findViewById(R.id.tipologiaView);
        commonView = (TextView) itemView.findViewById(R.id.commonView);
        saunaView = (TextView) itemView.findViewById(R.id.saunaView);
        localizacaoView = (TextView) itemView.findViewById(R.id.localizacaoView);
        imgView = (NetworkImageView) itemView.findViewById(R.id.imgView);

        delBtn = (Button) itemView.findViewById(R.id.delBtn);
        editBtn = (Button) itemView.findViewById(R.id.editBtn);

        editBtn.setOnClickListener(this);
        delBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        db = new DatabaseHelper(context.getApplicationContext());
        if(view.getId() == editBtn.getId()) {
            Intent intent = new Intent(context, EditImovel.class);
            int id = Integer.parseInt(idView.getText().toString());
            intent.putExtra("id",id);
            context.startActivity(intent);
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(R.string.confirmDelete);
            builder.setTitle(R.string.confirmacao);
            builder.setCancelable(false);
            builder.setPositiveButton(R.string.ok, (DialogInterface.OnClickListener) (dialog, which) -> {
                if(db.apagarImovel(Integer.parseInt(idView.getText().toString()))) {
                    adapter.removeAt(this.getLayoutPosition());
                    Toast.makeText(context, "Imovel apagado", Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(context, "Erro ao apagar IMOVEL", Toast.LENGTH_LONG).show();
            });
            builder.setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) (dialog, which) -> {
                dialog.cancel();
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

}
