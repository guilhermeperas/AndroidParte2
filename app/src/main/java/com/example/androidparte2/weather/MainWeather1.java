package com.example.androidparte2.weather;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.example.androidparte2.R;

import java.io.IOException;
import java.io.InputStream;

public class MainWeather1 extends Activity implements View.OnClickListener {
    EditText edittext;
    TextView textview1, textview2, textview3, textview4, textview5;
    ImageView imageview;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate((savedInstanceState));
        setContentView(R.layout.mainweather);
        edittext = (EditText) findViewById(R.id.editText1);
        textview1 = (TextView) findViewById(R.id.textView1);
        textview2 = (TextView) findViewById(R.id.textView2);
        textview3 = (TextView) findViewById(R.id.textView3);
        textview4 = (TextView) findViewById(R.id.textView4);
        textview5 = (TextView) findViewById(R.id.textView5);
        imageview = (ImageView) findViewById(R.id.imageView1);
        btn = (Button) findViewById(R.id.button1);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button1)
            verTempo(v);
    }

    public void verTempo(View v){
        JSONTarefaTempo task = new JSONTarefaTempo();
        task.execute(new String[]{edittext.getText().toString()});
    }

    private class JSONTarefaTempo extends AsyncTask<String, Void, Tempo> {
        @Override
        protected Tempo doInBackground(String... strings) {
            Tempo tempo = new Tempo();
            //Leitura Local (Assets)
            //JsonObject jsonObject = JsonObject.readFrom(loadJSONFromAsset());
            /*TempoHttpClient client = new TempoHttpClient();
            String data = client.getTempoData(strings[0]);*/

            //Chamada anônima onde não é criado um objeto porque só vou chamar esta class para usar o método
            String data = (new TempoHTTPClient()).getTempoData(strings[0]);
            JsonObject jsonObject = JsonObject.readFrom(data);

            JsonArray weatherArray = jsonObject.get("weather").asArray();
            tempo.setId(weatherArray.get(0).asObject().get("id").asInt());
            tempo.setIcon(weatherArray.get(0).asObject().get("icon").asString());
            tempo.iconData = (new TempoHTTPClient()).getImage(tempo.getIcon());
            tempo.setTemp(jsonObject.get("main").asObject().get("temp").asFloat());
            tempo.setHumidade(jsonObject.get("main").asObject().get("humidity").asFloat());
            tempo.setTemp_min(jsonObject.get("main").asObject().get("temp_min").asFloat());
            tempo.setTemp_max(jsonObject.get("main").asObject().get("temp_max").asFloat());
            return tempo;
        }

        @Override
        protected void onPostExecute(Tempo tempo) {
            super.onPostExecute(tempo);
            if(tempo.getIcon() != null && tempo.getIcon().length() > 0){
                Bitmap img = BitmapFactory.decodeByteArray(tempo.iconData, 0, tempo.iconData.length);
                imageview.setImageBitmap(img);
            }
            textview1.setText("Descrição: " + tempo.getDescription());
            textview2.setText("Temperatura: " + Math.round(tempo.getTemp() - 273.15) + "º");
            textview3.setText("Humidade: " + tempo.getHumidade() + "%");
            textview4.setText("Temperatura Min: " + Math.round(tempo.getTemp_min() - 273.15) + "º");
            textview5.setText("Temperatura Max: " + Math.round(tempo.getTemp_max() - 273.15) + "º");
        }
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("tempo.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close(); //Nunca esquecer de fechar
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }
}