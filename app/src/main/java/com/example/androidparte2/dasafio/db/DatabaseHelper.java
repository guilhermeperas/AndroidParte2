package com.example.androidparte2.dasafio.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.androidparte2.dasafio.Classes.Client;
import com.example.androidparte2.dasafio.Classes.Imovel;
import com.example.androidparte2.dasafio.Classes.ImovelDetail;
import com.example.androidparte2.dasafio.Classes.User;
import com.example.androidparte2.db.Artigo;
import com.example.androidparte2.db.Tag;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "desafio";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USERS = "users";
    private static final String TABLE_CLIENTS = "clientes";
    private static final String TABLE_IMOVEIS = "imoveis";
    private static final String TABLE_DETAILSIMOVEIS = "detailsimoveis";

    // TABLE users
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    // table CLIENTS
    private static final String KEY_NAME = "name";
    private static final String KEY_AGE = "age";
    private static final String KEY_IMG = "img";
    // table imoveis
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_TYPOLOGY = "typology";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_DETAILID = "detail_id";
    // table caracteristicas lista
    private static final String KEY_SAUNA = "has_sauna";
    private static final String KEY_COMMON_AREA = "has_common_area";


    // TAG PARA O LOGCAT
    private static final String LOG = "DatabaseHelper";

    private static final String CREATE_TABLE_USERS =
            "CREATE TABLE "+TABLE_USERS+"("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_USERNAME+" TEXT,"+KEY_PASSWORD+" TEXT)";
    private static final String CREATE_TABLE_CLIENTS =
            "CREATE TABLE "+TABLE_CLIENTS+"("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_NAME+" TEXT,"+KEY_AGE+" INTEGER,"+KEY_IMG+" TEXT)";
    private static final String CREATE_TABLE_IMOVEIS =
            "CREATE TABLE "+TABLE_IMOVEIS+"("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_DESCRIPTION+" TEXT,"+KEY_TYPOLOGY+" TEXT,"+KEY_LOCATION+" TEXT,"+KEY_DETAILID+" INTEGER)";
    private static final String CREATE_TABLE_DETAILSIMOVEIS =
            "CREATE TABLE "+TABLE_DETAILSIMOVEIS+"("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_SAUNA+" TEXT,"+KEY_COMMON_AREA+" TEXT)";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_CLIENTS);
        db.execSQL(CREATE_TABLE_DETAILSIMOVEIS);
        db.execSQL(CREATE_TABLE_IMOVEIS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_CLIENTS);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_DETAILSIMOVEIS);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_IMOVEIS);

        onCreate(db);
    }
    public Imovel createImovel(Imovel imovel){
        SQLiteDatabase db = this.getWritableDatabase();
        // crio o detalhe primeiro
        imovel.detail = createDetail(imovel.detail);
        ContentValues values = new ContentValues();
        values.put(KEY_DESCRIPTION,imovel.description);
        values.put(KEY_TYPOLOGY,imovel.typology);
        values.put(KEY_LOCATION,imovel.location);
        values.put(KEY_IMG,imovel.img);
        values.put(KEY_DETAILID,imovel.detail.id);
        int imovel_id = (int) db.insert(TABLE_IMOVEIS,null,values);
        imovel.id = imovel_id;

        return imovel;
    }
    public ImovelDetail createDetail(ImovelDetail detail){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SAUNA,detail.hasSauna);
        values.put(KEY_COMMON_AREA,detail.hasCommonArea);
        int detail_id = (int) db.insert(TABLE_DETAILSIMOVEIS,null,values);
        detail.id = detail_id;
        return detail;
    }
    public Client createClient(Client client){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,client.name);
        values.put(KEY_AGE,client.age);
        values.put(KEY_IMG,client.img);
        client.id = (int) db.insert(TABLE_CLIENTS,null,values);
        return client;
    }
    public User createUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME,user.username);
        values.put(KEY_PASSWORD,user.password);
        user.id = (int) db.insert(TABLE_USERS,null,values);
        return user;
    }
    /*
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
    //Obter todos os artigos
    @SuppressLint("Range")
    public List<Artigo> obterArtigos(String nomeTag)
    {
        List<Artigo> artigos = new ArrayList<Artigo>();
        String query;
        if(nomeTag == "")
            query = "SELECT * FROM " + TABLE_ARTIGO;
        else
            query = "SELECT * FROM " + TABLE_ARTIGO + " ta, "
                    + TABLE_TAG + " tt, " + TABLE_ARTIGO_TAB
                    + " tat WHERE tt." + KEY_NOME
                    + " = '" + nomeTag + "'" + " AND tt." + KEY_ID
                    + " = " + "tat." + KEY_TAG_ID;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);
        if(c.moveToFirst())
        {
            do
            {
                Artigo artigo = new Artigo();
                artigo.id = c.getInt(c.getColumnIndex(KEY_ID));
                artigo.title = c.getString((c.getColumnIndex(KEY_TITULO)));
                artigo.url = c.getString((c.getColumnIndex(KEY_URL)));
                artigo.estado = c.getInt((c.getColumnIndex(KEY_ESTADO)));
                artigo.data_criacao = c.getString((c.getColumnIndex(KEY_DATA_CRIACAO)));
                artigos.add(artigo);
            }
            while(c.moveToNext());

        }
        return artigos;
    }
    private String getDateTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
    */
    public void fecharDB(){
        SQLiteDatabase db = this.getReadableDatabase();
        if(db != null && db.isOpen())
            db.close();
    }
}
