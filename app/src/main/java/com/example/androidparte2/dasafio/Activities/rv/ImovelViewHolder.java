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

public class ImovelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener { // se for classes generucas este é o abstrato
    TextView descricaoView,tiplogiaView,commonView,localizacaoView,idView,saunaView;
    NetworkImageView imgView;
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
        imgView = (NetworkImageView) itemView.findViewById(R.id.imgView);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(context, "ID "+idView.getText().toString(), Toast.LENGTH_SHORT).show();
    }
}
