package com.example.androidparte2.db;

public class Artigo {
    int id;
    String title;
    String url;
    int estado; // 0 nao lido | 1 -> lido
    String data_criacao;
    public Artigo(){
    }
    Artigo(String title,String url,int estado){
        this.title = title;
        this.url = url;
        this.estado = estado;
    }
}
