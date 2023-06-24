package com.example.androidparte2.dasafio.Activities.rv;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.androidparte2.R;
import com.example.androidparte2.dasafio.Activities.EditClient;
import com.example.androidparte2.dasafio.Classes.Client;
import com.example.androidparte2.dasafio.db.DatabaseHelper;
import com.example.androidparte2.rv.Act2;

public class ClientViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener { // se for classes generucas este é o abstrato
    TextView nomeView;
    TextView idadeView;
    TextView idView;
    NetworkImageView imgView;
    Context context;
    public ClientViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
        itemView.setOnClickListener(this);
        nomeView = (TextView) itemView.findViewById(R.id.nomeView);
        idView = (TextView) itemView.findViewById(R.id.idView);
        idadeView = (TextView) itemView.findViewById(R.id.idadeView);
        imgView = (NetworkImageView) itemView.findViewById(R.id.imgView);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(context,EditClient.class);
        intent.putExtra("id",Integer.parseInt(idView.getText().toString()));
        context.startActivity(intent);
    }
}
