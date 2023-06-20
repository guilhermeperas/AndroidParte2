package com.example.androidparte2.service;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;


/**
 Classes de Started service:

 Service - base de todos os serviços. Um serviço definido pela classe executado pela mainthread e não cria a sua propria thread.
    Desta forma torna-se necessário para serviços demorados criar uma nova thread dentro do serviço atraves dla classe handler ou de classe AsyncTask
    Este lança uma thread principal.

 IntentService - é a subclasse de service que usa um worker thread para lidar com todos os pedidos do arranque do serviço.
     Implementa o método onHandlerIntent() a cada pedido
     Este lança uma thread secundária

 Um serviço tem de estar vinculado ao manifesto enquanto um broadcast não precisa
 */
public class ServiceDownload extends IntentService {
    public static final String URL = "urlpath";
    public static final String FILENAME = "filename";
    public static final String FILEPATH = "filepath";
    public static final String RESULT = "result";
    public static final String NOTIFICATION = "com.example.androidparte2.service";
    private int result = Activity.RESULT_CANCELED;
    public ServiceDownload() {
        super("ServiceDownload");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String urlPath = intent.getStringExtra(URL);
        String fileName = intent.getStringExtra(FILENAME);

        // MEMÓRIA INTERNA
        File output = new File(this.getFilesDir(),fileName);
        // CARTÃO DE MEMORIA
        // File output = new File(this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),fileName);
        if(output.exists())
            output.delete();
        InputStream stream = null;
        FileOutputStream fos = null;
        try{
            URL url = new URL(urlPath);
            stream = url.openConnection().getInputStream();
            InputStreamReader reader = new InputStreamReader(stream);
            fos = new FileOutputStream(output.getPath());
            int next = -1;
            while ((next = reader.read()) != -1)
                fos.write(next);
            result = Activity.RESULT_OK;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(stream != null)
                try{ stream.close(); } catch (IOException e){ e.printStackTrace(); }
            if(fos != null){
                try{ fos.close(); } catch (IOException e){ e.printStackTrace(); }
            }
        }
        publishResults(output.getAbsolutePath(),result);
    }

    private void publishResults(String absolutePath, int result) {
        Intent intent = new Intent(NOTIFICATION);
        intent.putExtra(FILEPATH,absolutePath);
        intent.putExtra(RESULT,result);

        sendBroadcast(intent);
    }
}
