package com.example.androidparte2.dasafio.Activities.rv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.example.androidparte2.R;
import com.example.androidparte2.dasafio.Classes.VolleySingleton;
import com.example.androidparte2.dasafio.Classes.Imovel;

import java.util.List;

/*
O Adapter irá preencher os dados no RecyclerView. No entatno o RecyclerView requer a existência de um objeto RecyclerView.ViewHolder que descreve e fornece acesso a todas as Views dentro de cada linha do item
 */
public class BackofficeImovelAdapter extends Adapter<BackofficeImovelViewHolder> {
    List<Imovel> imoveis;
    Context context;
    public BackofficeImovelAdapter(List<Imovel> lista, Context ctx){
        this.imoveis = lista;
        this.context = ctx;
    }
    @Override
    public BackofficeImovelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rvitembimovel,parent,false);
        BackofficeImovelViewHolder avh = new BackofficeImovelViewHolder(v,this.context,this);
        return avh;
    }
    @Override
    public void onBindViewHolder(BackofficeImovelViewHolder holder, int position) {
        holder.imgView.setImageUrl(imoveis.get(position).img, VolleySingleton.getInstance(context).getImageLoader());
        holder.descricaoView.setText(imoveis.get(position).description);
        holder.tiplogiaView.setText(imoveis.get(position).typology);
        holder.localizacaoView.setText(imoveis.get(position).location);
        holder.saunaView.setText(imoveis.get(position).detail.hasSauna);
        holder.commonView.setText(imoveis.get(position).detail.hasCommonArea);
        holder.idView.setText(imoveis.get(position).id);
    }

    @Override
    public int getItemCount() {
        return imoveis.size();
    }
    public void removeAt(int position) {
        imoveis.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, imoveis.size());
    }
}