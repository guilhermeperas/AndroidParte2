package com.example.androidparte2.dasafio.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.example.androidparte2.dasafio.Activities.LoginActivity;
import com.example.androidparte2.dasafio.Classes.Client;
import com.example.androidparte2.dasafio.Classes.Imovel;
import com.example.androidparte2.dasafio.Classes.ImovelDetail;
import com.example.androidparte2.dasafio.Classes.User;
import com.example.androidparte2.dasafio.db.DatabaseHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MyService extends IntentService {
    public static final String URL = "urlpath";
    public static final String NOTIFICATION  = "com.example.androidparte2.dasafio.service";
    private static final String TAG = "INIT SERVICE";
    private int result = LoginActivity.RESULT_CANCELED;
    DatabaseHelper db;

    public MyService() {
        super("MyService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String urlPath = intent.getStringExtra(URL);
        HttpURLConnection con = null;
        InputStream is = null;
        try {
            con = (HttpURLConnection) new URL(urlPath).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.connect();
            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = br.readLine()) != null)
                buffer.append(line + "\r\n");
            is.close();
            con.disconnect();
            String jsonString = buffer.toString();
            processJson(jsonString);
        } catch (IOException e) {
            Log.e(TAG, "Erro a buscar o  JSON: " + e.getMessage());
        }
        publishResults();
    }
    private void publishResults() {
        Intent intent = new Intent(NOTIFICATION);
        sendBroadcast(intent);
    }
    private void processJson(String jsonString) {
        db = new DatabaseHelper(getApplicationContext());
        Log.d("processjson","tou aqui");
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray usersArray = jsonObject.getJSONArray("users");
            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject userObject = usersArray.getJSONObject(i);
                String user = userObject.getString("user");
                String password = userObject.getString("password");
                Log.d(TAG, "User created => " + db.createUser(new User(user), password));
            }

            JSONObject clientesObject = jsonObject.getJSONObject("clientes");
            JSONArray clienteArray = clientesObject.getJSONArray("cliente");
            for (int i = 0; i < clienteArray.length(); i++) {
                JSONObject clienteObject = clienteArray.getJSONObject(i);
                String nome = clienteObject.getString("nome");
                String idade = clienteObject.getString("idade");
                String urlFoto = clienteObject.getString("url_foto");
                Log.d("Cliente insert", "Cliente => " + db.createClient(new Client(nome, idade, urlFoto)));
            }
            db.createUser(new User("teste"), "teste");
            JSONArray imoveisArray = jsonObject.getJSONObject("imoveis").getJSONArray("imovel");

            for (int i = 0; i < imoveisArray.length(); i++) {
                JSONObject imovelObject = imoveisArray.getJSONObject(i);

                String descricao = imovelObject.getString("descricao");
                String tipologia = imovelObject.getString("tipologia");
                String localizacao = imovelObject.getString("localizacao");
                String urlFoto = imovelObject.getString("url_foto");
                Log.d("INICO IMG", "IMAGEM = "+urlFoto);
                JSONArray caracteristicasArray = imovelObject.optJSONArray("lista_caracteristicas");
                ImovelDetail caracteristica = new ImovelDetail();
                if (caracteristicasArray != null && caracteristicasArray.length() > 0) {

                    JSONObject caracteristicaObject = caracteristicasArray.getJSONObject(0);
                    caracteristica.hasSauna = caracteristicaObject.optString("sauna");

                    JSONObject commonArea = caracteristicasArray.getJSONObject(1);
                    caracteristica.hasCommonArea = commonArea.optString("areacomum");
                }
                Log.d("Data-imovel", "Desc: "+descricao+" Tip: "+tipologia+" Local: "+localizacao+ " Img: " +urlFoto+" Carateristicas: sauna :"+caracteristica.hasSauna+" common:"+caracteristica.hasCommonArea);
                Log.d("Imovel insert", "Imovel => " + db.createImovel(new Imovel(descricao, tipologia, localizacao, urlFoto, caracteristica)));
            }
            // Broadcast the completion of the service
        } catch (JSONException e) {
            Log.e(TAG, "Error parsing JSON: " + e.getMessage());
        }
    }
}