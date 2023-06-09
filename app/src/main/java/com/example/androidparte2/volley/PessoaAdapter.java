package com.example.androidparte2.volley;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.androidparte2.R;

import java.util.List;

public class PessoaAdapter extends ArrayAdapter<Pessoa> {
    static final int LAYOUT = R.layout.item_lista;
    public PessoaAdapter(Context context, List<Pessoa> objs) {
        super(context, LAYOUT, objs);

    }

    @Override
    public View getView(int position,View convertView, ViewGroup parent) {
        Context ctx = parent.getContext(); // contexto onde vamos trabalhar
        if(convertView == null)
            convertView = LayoutInflater.from(ctx).inflate(LAYOUT,null);
        NetworkImageView img = (NetworkImageView) convertView.findViewById(R.id.img);
        TextView txt = (TextView) convertView.findViewById(R.id.txtName);
        Pessoa pessoa = getItem(position);
        txt.setText(pessoa.nome);
        img.setImageUrl(pessoa.url,VolleySingleton.getInstance(getContext()).getImageLoader()); // obriganos a usar uma classe singleton
        return convertView;
    }
}
