package com.example.androidparte2.dasafio.Classes;

public class ImovelDetail {
    public String id;
    public String hasSauna;
    public String hasCommonArea;

    public ImovelDetail(){}
    public ImovelDetail(String sauna,String common){
        this.hasSauna = sauna;
        this.hasCommonArea = common;
    }
}
