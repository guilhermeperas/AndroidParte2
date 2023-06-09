package com.example.androidparte2.weather;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TempoHTTPClient
{
    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static String IMG_URL = "https://openweathermap.org/img/w/";
    private static String APPID_URL = "&appid=20f77187526697f2eb687d6aa61174f0";

    public String getTempoData(String location)
    {
        //HttpURLConnection -> class abstrata que contém os métodos utilizados para efetuar uma conexão HTTP. Herda atributos e métodos da class URLConnection
        HttpURLConnection con = null;
        //InputStream = A leitura de fluxo editável tipo: ficheiro, vídeo, bytes, ligação de rede, etc...
        InputStream is = null;
        try {
            con = (HttpURLConnection) new URL(BASE_URL + location + APPID_URL).openConnection();
            con.setRequestMethod("GET");
            //TODO: Não percebi este setDoInput (pesquisar)
            con.setDoInput(true);
            con.connect();
            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            //TODO: \r ???????????? qual é a diferença entre \n e \r?
            // \r - obriga a que o ponteiro de leitura depois da quebra venha para o início, em qualquer sistema(win, mac, linux)
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = br.readLine()) != null)
                buffer.append(line + "\r\n");
            is.close();
            con.disconnect();
            return buffer.toString();
        } catch (Throwable t) { t.printStackTrace(); }
        finally {
            try { is.close(); } catch (Throwable t) {}
            try { con.disconnect(); } catch (Throwable t) {}
        }
        return null;
    }

    public byte[] getImage(String code) {
        //HINT: Aqui fazemos outra ver a ligação, por isso fazer um singleton para ter apenas uma ligação
        HttpURLConnection con = null;
        InputStream is = null;
        try {
            con = (HttpURLConnection) new URL(IMG_URL + code + ".png").openConnection();
            con.setRequestMethod("GET");
            con.connect();
            is = con.getInputStream();
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while(is.read(buffer) != -1)
                baos.write(buffer);
            return baos.toByteArray();
        } catch (Throwable t) { t.printStackTrace(); }
        finally {
            try { is.close(); } catch (Throwable t) {}
            try { con.disconnect(); } catch (Throwable t) {}
        }
        return null;
    }
}