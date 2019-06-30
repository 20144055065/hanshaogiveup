package com.example.lenovo.dictionary.db;

/**
 * Created by lenovo on 2017/12/18.
 */
 import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class VocabularySQLiteHelper extends SQLiteOpenHelper {
    /**
     * 建表语句
     */
    private final String CREATE_VOCABULARY =
            "create table Vocabulary ("+
                    "id Integer primary key autoincrement,"+
                    "wordsKey text," +
                    "translation text,"+
                    "masteryLevel Integer,"+
                    "wrong Integer)";

    public VocabularySQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_VOCABULARY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}


