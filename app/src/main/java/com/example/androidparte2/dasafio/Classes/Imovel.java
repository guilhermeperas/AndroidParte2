package com.example.androidparte2.dasafio.Classes;

public class Imovel {
    public String id;
    public ImovelDetail detail;
    public String description;
    public String typology;
    public String location;
    public String img;

    public Imovel(){};

    public Imovel(String description,String typology,String location,String img,ImovelDetail detail){
        this.description = description;
        this.typology = typology;
        this.location = location;
        this.img = img;
        this.detail = detail;
    }
}
