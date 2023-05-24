package com.example.androidparte2.rv;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.example.androidparte2.R;

import java.util.List;

/*
O Adapter irá preencher os dados no RecyclerView. No entatno o RecyclerView requer a existência de um objeto RecyclerView.ViewHolder que descreve e fornece acesso a todas as Views dentro de cada linha do item
 */
public class AlunoAdapter extends Adapter<AlunoAdapter.AlunoViewHolder> {
    List<Aluno> alunos;
    Context context;
    AlunoAdapter(List<Aluno> lista, Context ctx){
        this.alunos = lista;
        this.context = ctx;
    }
    @Override
    public AlunoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { // nao se pega o context pelo parent
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rvitemaluno,parent,false); // podiamos usar o nosso contexto mas neste caso queremos o layout logo usamos o parent
        AlunoViewHolder avh = new AlunoViewHolder(v,this.context);
        return avh;
    }
    //  define os atributos de exibição com base nos dados
    @Override
    public void onBindViewHolder(AlunoViewHolder holder, int position) {
        holder.nomeView.setText(alunos.get(position).nome);
        holder.idadeView.setText(alunos.get(position).idade);
        holder.imgView.setImageResource(alunos.get(position).fotoId);
    }

    @Override
    public int getItemCount() {
        return alunos.size();
    }

    public static class AlunoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener { // se for classes generucas este é o abstrato
        TextView nomeView;
        TextView idadeView;
        ImageView imgView;
        Context context;
        public AlunoViewHolder(View itemView, Context context) {
            super(itemView);
            this.context = context;
            itemView.setOnClickListener(this);
            nomeView = (TextView) itemView.findViewById(R.id.nomeView);
            idadeView = (TextView) itemView.findViewById(R.id.idadeView);
            imgView = (ImageView) itemView.findViewById(R.id.imgView);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(this.context,Act2.class);
            intent.putExtra("nome",nomeView.getText().toString());
            intent.putExtra("int",20);
            this.context.startActivity(intent);

        }
    }
}