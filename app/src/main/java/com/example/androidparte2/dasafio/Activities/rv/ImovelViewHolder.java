package com.example.androidparte2.dasafio.Activities.rv;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.androidparte2.R;
import com.example.androidparte2.dasafio.db.DatabaseHelper;

public class ImovelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener { // se for classes generucas este é o abstrato
    TextView descricaoView,tiplogiaView,commonView,localizacaoView,idView,saunaView;
    NetworkImageView imgView;
    Context context;
    DatabaseHelper db;
    Button delBtn,editBtn;
    ImovelAdapter adapter;
    public ImovelViewHolder(View itemView, Context context, ImovelAdapter imovelAdapter) {
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
        int id = Integer.parseInt(idView.getText().toString());
        if(view.getId() == delBtn.getId())
            adapter.removeAt(this.getLayoutPosition());
        Toast.makeText(context, "ID "+idView.getText().toString(), Toast.LENGTH_SHORT).show();
    }

}
