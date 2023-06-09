package com.example.androidparte2.volley;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.eclipsesource.json.JsonArray;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActVolley extends ListActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    private static final String TAG = "JsonPessoa";
    List<Pessoa> pessoas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String url = "https://www.dropbox.com/s/mpd5v96we9por6p/pessoas_.json?dl=1";
        RequestQueue queue = Volley.newRequestQueue(this); // needs singleton
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, // tipo de pedido
                url, // caminho do pedido
                null, // envia post para servidor
                this, // Resposnse. listener
                this // Response.ErrorListeneer
        );
        queue.add(jsonObjectRequest);
    }

    @Override
    public void onResponse(JSONObject response) {
        // verificar a resposta caso esteja vazia
        pessoas = new ArrayList<Pessoa>();
        try{
            JSONObject jsonPessoas = response.getJSONObject("pessoas"); // PEGA O OBJETO COM O ARRAY PESSOA LA DENTRO
            JSONArray jsonPessoa = jsonPessoas.getJSONArray("pessoa"); // ARRAY COM OBJETOS DE CADA PESSOA LA DENTRO
            // omg usar o MEU iterator
            for(int i = 0;i< jsonPessoa.length();i++){
                JSONObject jsonPessoaItem = jsonPessoa.getJSONObject(i);
                String nome = jsonPessoaItem.getString("nome");
                String url = jsonPessoaItem.getString("url_foto");
                pessoas.add(new Pessoa(nome,url));
            }
            // se fosse recycler view metia as coisinhas aqui
            setListAdapter(new PessoaAdapter(this,pessoas));
            registerForContextMenu(getListView());
        }catch (Exception e){
            e.printStackTrace();
            Log.d(TAG , e.getMessage().toString());
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        // ao clicar no item da lista
        Toast.makeText(this,pessoas.get(position).nome,Toast.LENGTH_LONG).show();
        // se fosse para mostrar o detalhe do item criava o intent aqui...
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "Erro -> " + error.getMessage().toString(), Toast.LENGTH_LONG).show();

    }
}
