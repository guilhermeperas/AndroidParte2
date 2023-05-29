package com.example.androidparte2.db;

import android.annotation.SuppressLint; // REMVER
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "bdprog20";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_ARTIGO = "artigo";
    private static final String TABLE_TAG = "tag"; // tabela com relacao de muitos para muitos
    private static final String TABLE_ARTIGO_TAB = "artigo_tag";

    // TABLE ARTIGO
    private static final String KEY_ID = "id";
    private static final String KEY_TITULO = "titulo";
    private static final String KEY_URL = "url";
    private static final String KEY_ESTADO = "estado";
    private static final String KEY_DATA_CRIACAO = "data_criacao";

    private static final String KEY_NOME = "nome";
    private static final String KEY_ARTIGO_ID = "artigo_id";
    private static final String KEY_TAG_ID = "tag_id";

    // TAG PARA O LOGCAT
    private static final String LOG = "DatabaseHelper";

    private static final String CREATE_TABLE_ARTIGO =
            "CREATE TABLE "+TABLE_ARTIGO+"("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_TITULO+" TEXT,"+KEY_URL+" TEXT,"+KEY_ESTADO+" INTEGER,"+KEY_DATA_CRIACAO+" DATETIME"+")";
    private static final String CREATE_TABLE_TAG =
            "CREATE TABLE "+TABLE_TAG+"("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_NOME+" TEXT"+")";
    private static final String CREATE_TABLE_ARTIGO_TAG =
            "CREATE TABLE "+TABLE_ARTIGO_TAB+"("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_ARTIGO_ID+" INTEGER,"+KEY_TAG_ID+" INTEGER)";

    public DatabaseHelper(Context context,String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ARTIGO);
        db.execSQL(CREATE_TABLE_TAG);
        db.execSQL(CREATE_TABLE_ARTIGO_TAG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_ARTIGO);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_TAG);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_ARTIGO_TAB);

        onCreate(db);
    }

    /**
     * ASSOCIA UM ARTIGO A UMA TAG
     */
    public long associarArtigoTag(long artigo_id,long tag_id){
        SQLiteDatabase db = this.getWritableDatabase(); // writable para escrever (update/insert) | readable para ler
        ContentValues values = new ContentValues();
        values.put(KEY_ARTIGO_ID, artigo_id);
        values.put(KEY_TAG_ID, tag_id);
        return db.insert(TABLE_ARTIGO_TAB,null,values); // retorna o id do insert
    }
    public Artigo criarArtigo(String titulo,String url,int estado,long[] tag_ids){ // inves de mandar cada atributo mandar um objeto ja criado
        SQLiteDatabase db = this.getWritableDatabase();
        Artigo artigo = new Artigo(titulo,url,estado);
        ContentValues values = new ContentValues();
        values.put(KEY_TITULO,artigo.title);
        values.put(KEY_URL,artigo.url);
        values.put(KEY_ESTADO,artigo.estado);
        values.put(KEY_DATA_CRIACAO,getDateTime());
        int artigo_id = (int) db.insert(TABLE_ARTIGO,null,values);
        artigo.id = artigo_id;
        // mexer na linha omg luta pela nota omg oh my days
        for(long tag_id : tag_ids) // usar o MEU iterator o my days
                associarArtigoTag(artigo_id,tag_id);
        return artigo;
    }
    public Tag criarTag(Tag tag){ // maneira correta o my days
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NOME,tag.nome);
        tag.id = (int) db.insert(TABLE_TAG,null,values);
        return tag;
    }
    @SuppressLint("Range")
    public Artigo obterArtigo(long artigo_id){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+ TABLE_ARTIGO + " WHERE "+KEY_ID+" = ?";
        /**
         * rawQuery -> QUERY SQL
         * SEGUNDO PARAMETRO: SAO OS VALORES PARA OS PARAMETROS DA QUERY QUE SÃO DEFINIDOS POR INTERROGAÇÃO = ?
         * return Cursor -> apontador para os resultados
         *
         * EXEMPLO SERIO OMDAYS = Cursor c = db.rawQuery("SELECT * FROM TABLE_ARTIGO WHERE KEY_ID = ?",,new String[]{Long.toString(artigo_id)}
         * EXEMPLO SEM PARAMETROS = Cursor c = db.rawQuery("SELECT * FROM TABLE_ARTIGO,null)
         */
        Cursor c = db.rawQuery(query,new String[]{Long.toString(artigo_id)});
        if(c != null) c.moveToFirst();
        Artigo artigo = new Artigo();
        artigo.id = c.getInt(c.getColumnIndex(KEY_ID));
        artigo.title = c.getString(c.getColumnIndex(KEY_TITULO));
        artigo.estado = c.getInt(c.getColumnIndex(KEY_ESTADO));
        artigo.url = c.getString(c.getColumnIndex(KEY_URL));
        artigo.data_criacao = c.getString(c.getColumnIndex(KEY_DATA_CRIACAO));
        return artigo;
    }
    private String getDateTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
    public void fecharDB(){
        SQLiteDatabase db = this.getReadableDatabase();
        if(db != null && db.isOpen())
            db.close();
    }
}
