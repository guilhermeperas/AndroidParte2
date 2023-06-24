package com.example.androidparte2.dasafio.Activities.rv;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.example.androidparte2.R;
import com.example.androidparte2.dasafio.Classes.Client;
import com.example.androidparte2.dasafio.Classes.Imovel;
import com.example.androidparte2.rv.Act2;
import com.example.androidparte2.rv.Aluno;

import java.util.List;

/*
O Adapter irá preencher os dados no RecyclerView. No entatno o RecyclerView requer a existência de um objeto RecyclerView.ViewHolder que descreve e fornece acesso a todas as Views dentro de cada linha do item
 */
public class ImovelAdapter extends Adapter<ImovelViewHolder> {
    List<Imovel> imoveis;
    Context context;
    public ImovelAdapter(List<Imovel> lista, Context ctx){
        this.imoveis = lista;
        this.context = ctx;
    }
    @Override
    public ImovelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rvitemimovel,parent,false);
        ImovelViewHolder avh = new ImovelViewHolder(v,this.context);
        return avh;
    }
    @Override
    public void onBindViewHolder(ImovelViewHolder holder, int position) {
//        holder.imgView.setImageResource(clientes.get(position).img); TODO FIX IMAGE
        holder.descricaoView.setText(imoveis.get(position).description);
        holder.tiplogiaView.setText(imoveis.get(position).typology);
        holder.localizacaoView.setText(imoveis.get(position).location);
//        holder.saunaView.setText(imoveis.get(position).detail.hasSauna);
//        holder.commonView.setText(imoveis.get(position).detail.hasCommonArea);
    }

    @Override
    public int getItemCount() {
        return imoveis.size();
    }
}