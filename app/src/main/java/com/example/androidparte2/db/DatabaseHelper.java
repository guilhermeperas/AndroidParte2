package com.example.androidparte2.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    public DatabaseHelper(Context context,String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
