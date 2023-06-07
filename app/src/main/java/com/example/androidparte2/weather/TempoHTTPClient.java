package com.example.androidparte2.weather;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TempoHTTPClient {
    private static String BASE_URL =
            "http://api.openweathermap.org/data/2.5/weather?q=";
    private static String IMG_URL =
            "https://openweathermap.org/img/w/";
    private static String APPID_URL =
            "&appid=9b4e98e24bba6170583cf7b4a9cfef77";
    public String getTempoData(String location) {
        // HttpURLConnection -> classe abstrata que contém os
        // métodos utilizados
        // para efetuar uma conexão HTTP
        // Herda atributos e comportamentos
        // a classe URLConnection
        HttpURLConnection con = null ;
        // InputStream -A LEITURA DE FLUXO EDITAVEL TIPO : FICHEIRO
        //VIDEO, BYTES, LIGAÇÃO DE REDE
        InputStream is = null;
        try {
            con = (HttpURLConnection)
                    (new URL(BASE_URL + location + APPID_URL)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.connect();
            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            /* \r- obriga a que o ponteiro de leitura
            depois da quebra venha para o inicio, em qualquer
             sistema(win,mac,linux)*/
            BufferedReader br =
                    new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = br.readLine()) != null )
                buffer.append(line + "\r\n");
            is.close();
            con.disconnect();
            return buffer.toString();
        }catch(Throwable t) {
            t.printStackTrace();
        }finally{
            try { is.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }
        return null;
    }

    public byte[] getImage(String code) {
        HttpURLConnection con = null ;
        InputStream is = null;
        try {
            con = (HttpURLConnection)
                    ( new URL(IMG_URL + code + ".png")).openConnection();
            con.setRequestMethod("GET");
            con.connect();
            is = con.getInputStream();
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ( is.read(buffer) != -1)
                baos.write(buffer);
            return baos.toByteArray();
        }catch(Throwable t) {
            t.printStackTrace();
        }finally {
            try { is.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }
        return null;
    }
}
