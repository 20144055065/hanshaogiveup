package com.example.lenovo.dictionary.util;

/**
 * Created by lenovo on 2017/12/18.
 */
 import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lenovo.dictionary.db.VocabularySQLiteHelper;
import com.example.lenovo.dictionary.model.Vocabulary;
import com.example.lenovo.dictionary.model.Words;

import java.util.ArrayList;
import java.util.List;


public class VocabularyAction {

    private final String TABLE_VOCABULARY = "Vocabulary";

    private static VocabularyAction vocabularyAction;

    private SQLiteDatabase db;


    private VocabularyAction(Context context) {
        VocabularySQLiteHelper vocabularySQLiteHelper = new VocabularySQLiteHelper(context, TABLE_VOCABULARY, null, 1);
        db = vocabularySQLiteHelper.getWritableDatabase();
    }


    public static VocabularyAction getInstance(Context context) {
        if (vocabularyAction == null) {
            synchronized (VocabularyAction.class) {
                if (vocabularyAction == null) {
                    vocabularyAction = new VocabularyAction(context);
                }
            }
        }
        return vocabularyAction;
    }


    public void addToVocabulary(Words words) {
        Vocabulary vocabulary = new Vocabulary(words.getKey(), words.getPosAcceptation());
        ContentValues values = new ContentValues();
        values.put("wordsKey", vocabulary.getWordsKey());
        values.put("translation", vocabulary.getTranslation());
        values.put("masteryLevel", vocabulary.getMasteryLevel());
        values.put("right", vocabulary.getRight());
        values.put("wrong", vocabulary.getWrong());
        db.insert(TABLE_VOCABULARY, null, values);
        values.clear();
    }


    public boolean isExistInVocabulary(String wordsKey) {
        Cursor cursor = db.query(TABLE_VOCABULARY, null, "wordsKey = ?", new String[]{wordsKey}, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }


    public void deleteFormVocabulary(String wordsKey) {
        if (isExistInVocabulary(wordsKey)) {
            db.delete(TABLE_VOCABULARY, "wordsKey = ?", new String[]{wordsKey});
        }
    }


    public List<Vocabulary> getVocabularyList() {
        List<Vocabulary> vocabularyList = new ArrayList<Vocabulary>();
        Cursor cursor = db.query(TABLE_VOCABULARY, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Vocabulary vocabulary = new Vocabulary();
                vocabulary.setWordsKey(cursor.getString(cursor.getColumnIndex("wordsKey")));
                vocabulary.setTranslation(cursor.getString(cursor.getColumnIndex("translation")));
                vocabulary.setMasteryLevel(cursor.getInt(cursor.getColumnIndex("masteryLevel")));
                vocabulary.setRight(cursor.getInt(cursor.getColumnIndex("right")));
                vocabulary.setWrong(cursor.getInt(cursor.getColumnIndex("wrong")));
                vocabularyList.add(vocabulary);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return vocabularyList;
    }


    public void finishSelect(Vocabulary vocabulary, boolean isRight) {
        int masteryLevel = vocabulary.getMasteryLevel();
        int right = vocabulary.getRight();
//        int worng = vocabulary.getWrong();//留用于影响掌握等级的答错次数修改
        if (isRight) {
            right += 1;
            if (right >= 3) {
                //练习答对3次，提升掌握等级
                //if条件语句省略括号
                if (masteryLevel == Vocabulary.MASTERY_LEVEL_1)
                    masteryLevel = Vocabulary.MASTERY_LEVEL_2;
                else if (masteryLevel == Vocabulary.MASTERY_LEVEL_2)
                    masteryLevel = Vocabulary.MASTERY_LEVEL_3;
                else
                    masteryLevel = Vocabulary.MASTERY_LEVEL_4;
                right = 0;
            }
        } else {
            //答错直接降低掌握等级
            if (masteryLevel == Vocabulary.MASTERY_LEVEL_4)
                masteryLevel = Vocabulary.MASTERY_LEVEL_3;
            else if (masteryLevel == Vocabulary.MASTERY_LEVEL_3)
                masteryLevel = Vocabulary.MASTERY_LEVEL_2;
            else
                masteryLevel = Vocabulary.MASTERY_LEVEL_1;
        }
        ContentValues values = new ContentValues();
        values.put("masteryLevel", masteryLevel);
        values.put("right", right);
        db.update(TABLE_VOCABULARY, values, "wordsKey = ?", new String[]{vocabulary.getWordsKey()});
        values.clear();
    }
}
