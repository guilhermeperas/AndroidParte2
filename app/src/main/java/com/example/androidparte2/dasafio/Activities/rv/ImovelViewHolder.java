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

public class ImovelViewHolder extends RecyclerView.ViewHolder {
    TextView descricaoView,tiplogiaView,commonView,localizacaoView,idView,saunaView;
    NetworkImageView imgView;
    Context context;
    public ImovelViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
        idView = (TextView) itemView.findViewById(R.id.idView);
        descricaoView = (TextView) itemView.findViewById(R.id.descricaoView);
        tiplogiaView = (TextView) itemView.findViewById(R.id.tipologiaView);
        commonView = (TextView) itemView.findViewById(R.id.commonView);
        saunaView = (TextView) itemView.findViewById(R.id.saunaView);
        localizacaoView = (TextView) itemView.findViewById(R.id.localizacaoView);
        imgView = (NetworkImageView) itemView.findViewById(R.id.imgView);

    }
}
