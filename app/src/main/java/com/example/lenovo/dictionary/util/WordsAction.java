package com.example.lenovo.dictionary.util;

/**
 * Created by lenovo on 2017/12/18.
 */
 import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;
 import android.net.wifi.aware.PublishConfig;
 import android.util.Log;

import com.example.lenovo.dictionary.db.WordsSQLiteOpenHelper;
import com.example.lenovo.dictionary.model.Words;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class WordsAction {

    private static WordsAction wordsAction;

    private final String TABLE_WORDS = "Words";

    private SQLiteDatabase db;
    private MediaPlayer player = null;

    private WordsAction(Context context) {
        WordsSQLiteOpenHelper helper = new WordsSQLiteOpenHelper(context, TABLE_WORDS, null, 1);
        db = helper.getWritableDatabase();
    }


    public static WordsAction getInstance(Context context) {
        //双重效验锁，提高性能
        if (wordsAction == null) {
            synchronized (WordsAction.class) {
                if (wordsAction == null) {
                    wordsAction = new WordsAction(context);
                }
            }
        }
        return wordsAction;
    }


    public boolean saveWords(Words words) {
        //判断是否是有效对象，即有数据
        if (words.getSent().length() > 0) {
            ContentValues values = new ContentValues();
            values.put("isChinese", "" + words.getIsChinese());
            values.put("key", words.getKey());
            values.put("fy", words.getFy());
            values.put("psE", words.getPsE());
            values.put("pronE", words.getPronE());
            values.put("psA", words.getPsA());
            values.put("pronA", words.getPronA());
            values.put("posAcceptation", words.getPosAcceptation());
            values.put("sent", words.getSent());
            db.insert(TABLE_WORDS, null, values);
            values.clear();
            return true;
        }
        return false;
    }


    public Words getWordsFromSQLite(String key) {
        Words words = new Words();
        Cursor cursor = db.query(TABLE_WORDS, null, "key=?", new String[]{key}, null, null, null);
        //数据库中有
        if (cursor.getCount() > 0) {
            Log.d("测试", "数据库中有");
            if (cursor.moveToFirst()) {
                do {
                    String isChinese = cursor.getString(cursor.getColumnIndex("isChinese"));
                    if ("true".equals(isChinese)) {
                        words.setIsChinese(true);
                    } else if ("false".equals(isChinese)) {
                        words.setIsChinese(false);
                    }
                    words.setKey(cursor.getString(cursor.getColumnIndex("key")));
                    words.setFy(cursor.getString(cursor.getColumnIndex("fy")));
                    words.setPsE(cursor.getString(cursor.getColumnIndex("psE")));
                    words.setPronE(cursor.getString(cursor.getColumnIndex("pronE")));
                    words.setPsA(cursor.getString(cursor.getColumnIndex("psA")));
                    words.setPronA(cursor.getString(cursor.getColumnIndex("pronA")));
                    words.setPosAcceptation(cursor.getString(cursor.getColumnIndex("posAcceptation")));
                    words.setSent(cursor.getString(cursor.getColumnIndex("sent")));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } else {
            Log.d("测试", "数据库中没有");
            cursor.close();
        }

        return words;
    }


    public String getAddressForWords(final String key) {
        String address_p1 = "http://dict-co.iciba.com/api/dictionary.php?w=";
        String address_p2 = "";
        String address_p3 = "&key=4F9BD3DB222F3DD87AFC6379D7299941";

        if (isChinese(key)) {
            try {
                //此处非常重要！对中文的key进行重新编码，生成正确的网址
                address_p2 = "_" + URLEncoder.encode(key, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            address_p2 = key;
        }
        return address_p1 + address_p2 + address_p3;


    }


    public static boolean isChinese(String strName) {
        char[] ch = strName.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }


    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }


       public void saveWordsMP3(Words words)
       {
           String addressE = words.getPronE();
           String addressA = words.getPronA();
           if(addressE!="")
           {
               final String filePathE = words.getKey();
               HttpUtil.sentHttpRequest(addressE, new HttpCallBackListener() {
                   @Override
                   public void onFinish(InputStream inputStream) {
                       FileUtil.getInstance().writeToSD(filePathE,"E.mp3",inputStream);
                   }

                   @Override
                   public void onError() {

                   }
               });
           }
           if(addressA!="")
           {
               final String filePathA = words.getKey();
               HttpUtil.sentHttpRequest(addressE, new HttpCallBackListener() {
                   @Override
                   public void onFinish(InputStream inputStream) {
                       FileUtil.getInstance().writeToSD(filePathA,"A.mp3",inputStream);
                   }

                   @Override
                   public void onError() {

                   }
               });
           }
       }




    public void playMP3(String wordskey ,String ps,Context context)
   {
       String fileName = wordskey+"/"+ps+".mp3";
       String adrs = FileUtil.getInstance().getPathInSD(fileName);
       if(player!=null)
       {
           if(player.isPlaying())
           {
               player.stop();
           }
           player.release();
           player = null;
       }
       if(adrs !="")
       {
           player = MediaPlayer.create(context,Uri.parse(adrs));
           Log.d("测试", "播放");
           player.start();
       }else
       {
           Words words = getWordsFromSQLite(wordskey);
           saveWordsMP3(words);
       }
   }
}
