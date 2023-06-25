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
import com.example.androidparte2.dasafio.Activities.EditClient;
import com.example.androidparte2.dasafio.Classes.Client;
import com.example.androidparte2.dasafio.db.DatabaseHelper;

import java.util.List;

public class ClientViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener { // se for classes generucas este é o abstrato
    TextView nomeView,idadeView,idView;
    NetworkImageView imgView;
    Context context;
    Button btnEdit,btnDel;
    Intent intent;

    ClientAdapter adapter;
    DatabaseHelper db;

    public ClientViewHolder(View itemView, Context context, ClientAdapter adapt) {
        super(itemView);
        this.context = context;

        adapter = adapt;

        nomeView = (TextView) itemView.findViewById(R.id.nomeView);
        idView = (TextView) itemView.findViewById(R.id.idView);
        idadeView = (TextView) itemView.findViewById(R.id.idadeView);
        imgView = (NetworkImageView) itemView.findViewById(R.id.imgView);
        btnEdit = (Button) itemView.findViewById(R.id.editBtn);
        btnDel = (Button) itemView.findViewById(R.id.delBtn);

        btnDel.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        db = new DatabaseHelper(context.getApplicationContext());
        if(view.getId() == btnEdit.getId()) {
            intent = new Intent(context, EditClient.class);
            intent.putExtra("nome", nomeView.getText().toString());
            intent.putExtra("id", Integer.parseInt(idView.getText().toString()));
            intent.putExtra("idade", idadeView.getText().toString());
            context.startActivity(intent);
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(R.string.confirmDelete);
            builder.setTitle(R.string.confirmacao);
            builder.setCancelable(false);
            builder.setPositiveButton(R.string.ok, (DialogInterface.OnClickListener) (dialog, which) -> {
                if(db.apagarClient(Integer.parseInt(idView.getText().toString()))) {
                    adapter.removeAt(this.getLayoutPosition());
                    return;
                }
                Toast.makeText(context, "Erro ao apagar cliente", Toast.LENGTH_LONG).show();
            });
            builder.setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) (dialog, which) -> {
                dialog.cancel();
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }
}
