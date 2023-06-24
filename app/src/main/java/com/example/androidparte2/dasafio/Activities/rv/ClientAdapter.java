package com.example.androidparte2.dasafio.Activities.rv;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.example.androidparte2.R;
import com.example.androidparte2.dasafio.Classes.Client;
import com.example.androidparte2.rv.Act2;
import com.example.androidparte2.rv.Aluno;

import java.util.List;

/*
O Adapter irá preencher os dados no RecyclerView. No entatno o RecyclerView requer a existência de um objeto RecyclerView.ViewHolder que descreve e fornece acesso a todas as Views dentro de cada linha do item
 */
public class ClientAdapter extends Adapter<ClientViewHolder> {
    List<Client> clientes;
    Context context;
    public ClientAdapter(List<Client> lista, Context ctx){
        this.clientes = lista;
        this.context = ctx;
    }
    @Override
    public ClientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rvitemclient,parent,false);
        ClientViewHolder avh = new ClientViewHolder(v,this.context);
        return avh;
    }
    @Override
    public void onBindViewHolder(ClientViewHolder holder, int position) {
        holder.nomeView.setText(clientes.get(position).name);
        holder.idadeView.setText(clientes.get(position).age);
//        holder.imgView.setImageResource(clientes.get(position).img); TODO FIX IMAGE
    }

    @Override
    public int getItemCount() {
        return clientes.size();
    }
}