package com.example.androidparte2.db;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.androidparte2.R;

import java.util.List;

public class ArtigoAdapter extends RecyclerView.Adapter<ArtigoAdapter.ArtigoViewHolder> {
    List<Artigo> artigos;
    ArtigoAdapter(List<Artigo> list){
        this.artigos = list;
    }
    public ArtigoAdapter.ArtigoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_rvitemdb,parent,false);
        ArtigoViewHolder artigo_vh = new ArtigoViewHolder(v, parent.getContext());
        return artigo_vh;
    }

    @Override
    public void onBindViewHolder(ArtigoAdapter.ArtigoViewHolder holder, int position) {
        holder.id.setText(String.valueOf(artigos.get(position).id));
        holder.title.setText(artigos.get(position).title);
        holder.url.setText(artigos.get(position).url);
    }
    @Override
    public int getItemCount() {
        return artigos.size();
    }

    public class ArtigoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Context ctx;
        TextView title;
        TextView id;
        TextView url;
        public ArtigoViewHolder(View itemView,Context ctx) {
            super(itemView);
            this.ctx = ctx;
            itemView.setOnClickListener(this);
            title = (TextView) itemView.findViewById(R.id.tituloView);
            id = (TextView) itemView.findViewById(R.id.idView);
            url = (TextView) itemView.findViewById(R.id.urlView);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ctx,DetalheArtigo.class);
            intent.putExtra("id",id.getText());
            ctx.startActivity(intent);
        }
    }
}
