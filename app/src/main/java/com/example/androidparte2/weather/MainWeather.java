package com.example.androidparte2.weather;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainWeather extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // AsyncTask<String,Void,String>
        new GetMethodTempo().execute("https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&appid=20f77187526697f2eb687d6aa61174f0");
    }
    public class GetMethodTempo extends AsyncTask<String,Void,String> {
        String server_response;
        @Override
        protected String doInBackground(String... strings) {
            URL url;
            HttpURLConnection urlConnection = null;
            try{
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("Accept","application/json;");
                urlConnection.setRequestProperty("Content-Type","application/json; charset=UTF-8");

                int responseCode = urlConnection.getResponseCode();
                if(responseCode == HttpURLConnection.HTTP_OK){
                    server_response = readStream(urlConnection.getInputStream()); // le a resposta da comunicacao
                }
            }
            catch (MalformedURLException e){
                e.printStackTrace();
            }
            catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }
        private String readStream(InputStream in){
            BufferedReader reader = null;
            StringBuffer response = new StringBuffer();
            try{
                reader = new BufferedReader(new InputStreamReader(in));
                String line = "";
                while((line = reader.readLine()) != null)
                    response.append(line);
            }
            catch (IOException e){
                e.printStackTrace();
            }
            return response.toString();
        }
    }

}
