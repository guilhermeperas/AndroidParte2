package com.example.androidparte2.weather;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.example.androidparte2.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainWeather1 extends Activity implements View.OnClickListener {
    EditText editText;
    ImageView imageView;
    TextView textview1,textview2,textview3,textview4,textview5;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainweather);
        editText = (EditText) findViewById(R.id.editText1);
        imageView = (ImageView) findViewById(R.id.imageView1);
        textview1 = (TextView) findViewById(R.id.textView1);
        textview2 = (TextView) findViewById(R.id.textView2);
        textview3 = (TextView) findViewById(R.id.textView3);
        textview4 = (TextView) findViewById(R.id.textView4);
        textview5 = (TextView) findViewById(R.id.textView5);
        button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button1)
            verTempo(view);
    }
    private void verTempo(View view){
        JSONTarefaTempo task = new JSONTarefaTempo();
        task.execute();
    }

    private class JSONTarefaTempo extends AsyncTask<String,Void,Tempo>{
        @Override
        protected Tempo doInBackground(String... strings) {
            Tempo tempo = new Tempo();
            // lê o json
            JsonObject jsonObject = JsonObject.readFrom(loadJSONFromAsset());
            JsonArray weatherArray = jsonObject.get("weather").asArray();
            tempo.setId(weatherArray.get(0).asObject().get("id").asInt());
            tempo.setDescription(weatherArray.get(0).asObject().get("description").asString());
            tempo.setIcon(weatherArray.get(0).asObject().get("icon").asString());
            // tempo.iconData =
            tempo.setTemp(jsonObject.get("main").asObject().get("temp").asFloat());
            tempo.setHumidade(jsonObject.get("main").asObject().get("humidity").asFloat());
            tempo.setTemp_min(jsonObject.get("main").asObject().get("temp_min").asFloat());
            tempo.setTemp_max(jsonObject.get("main").asObject().get("temp_max").asFloat());

            return tempo;
        }
        @Override
        protected void onPostExecute(Tempo tempo) {
            super.onPostExecute(tempo);
            if (tempo.getIcon() != null && tempo.getIcon().length() > 0){

            }
            textview1.setText("Descrição: "+tempo.getDescription());
            textview2.setText("Temperatura: "+Math.round((tempo.getTemp() -273.15)) + "º");
            textview3.setText("Temperatura min: "+Math.round((tempo.getTemp_min() -273.15)) + "º");
            textview4.setText("Temperatura max: "+Math.round((tempo.getTemp_max() -273.15)) + "º");
            textview5.setText("Humidade: "+tempo.getHumidade()+"%");
        }
    }
    public String loadJSONFromAsset(){
        String json= null;
        try{
            InputStream is = getAssets().open("tempo.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer,"UTF-8");
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return json;
    }
}
