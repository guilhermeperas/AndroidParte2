package com.example.androidparte2.weather;

public class Tempo {
    private int id;
    private String description;
    private String icon;
    private float temp;
    private float humidade;
    private float temp_min;
    private float temp_max;
    public byte[] iconData; // guarda a imagem em binario

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        // mudar a lingua to texto uso o switch
        /**
         * switch(id){
         *  case 200:setDescription("blablabla chuva forte") ; break;
         * }
         */
    }
    private Float kelvinToCelcius(float temp){
        return null;
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
