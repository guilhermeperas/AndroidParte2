package com.example.androidparte2.weather;

public class Tempo
{
    private int id;
    private String description;
    private String icon;
    private float temp;
    private float humidade;
    private float temp_min;
    private float temp_max;
    public byte[] iconData;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        switch (id)
        {
            case 200: setDescription("trovoada com chuva leve"); break;
            case 201: setDescription("trovoada com chuva"); break;
            //...
            case 300: setDescription("garoa fraca"); break;
            case 301: setDescription("garoa"); break;
            case 302: setDescription("garoa intensa"); break;
            case 310: setDescription("chuva leve"); break;
            //...
            case 601: setDescription("neve"); break;
            case 602: setDescription("neve pesada"); break;
            case 611: setDescription("chuva com neve"); break;
            case 621: setDescription("banho de neve"); break;
            default: setDescription("descrição indisponível"); break;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getHumidade() {
        return humidade;
    }

    public void setHumidade(float humidade) {
        this.humidade = humidade;
    }

    public float getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(float temp_min) {
        this.temp_min = temp_min;
    }

    public float getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(float temp_max) {
        this.temp_max = temp_max;
    }
}