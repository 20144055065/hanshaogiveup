package com.example.lenovo.dictionary.example;

/**
 * Created by lenovo on 2017/12/18.
 */
 import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBaseHelper extends SQLiteOpenHelper{
    public Context mContext=null;
    public String tableName=null;
    public static int VERSION=1;

    public SQLiteDatabase dbR=null;
    public SQLiteDatabase dbW=null;
    public DataBaseHelper(Context context, String name, CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
        mContext=context;
        tableName=name;
        dbR=this.getReadableDatabase();
        dbW=this.getWritableDatabase();
    }

    public DataBaseHelper(Context context, String name, CursorFactory factory){
        this(context,name,factory,VERSION);
        mContext=context;
        tableName=name;
    }

    public DataBaseHelper(Context context, String name){
        this(context,name,null);
        mContext=context;
        tableName=name;
    };

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("create table glossary(word text,interpret text," +
                "right int,wrong int,grasp int,learned int)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub

    }

  public boolean insertWordInfoToDataBase(String word,String interpret,boolean overWrite){

        Cursor cursor=null;

        cursor=    dbR.query(tableName, new String[]{"word"}, "word=?", new String[]{word},null, null, "word");
        if(cursor.moveToNext()){
            if(overWrite){
                ContentValues values=new ContentValues();
                values.put("interpret", interpret);
                values.put("right", 0);
                values.put("wrong",0);
                values.put("grasp",0);
                values.put("learned", 0);

                dbW.update(tableName, values, "word=?", new String[]{word});
                cursor.close();
                return true;
            }else{
                cursor.close();
                return false;
            }
        }else{

            ContentValues values=new ContentValues();
            values.put("word", word);
            values.put("interpret", interpret);
            values.put("right", 0);
            values.put("wrong",0);
            values.put("grasp",0);
            values.put("learned", 0);
            dbW.insert(tableName, null, values);
            cursor.close();
            return true;
        }




    }
}
