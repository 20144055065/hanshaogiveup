package com.example.lenovo.dictionary.model;

/**
 * Created by lenovo on 2017/12/18.
 */
 public class Vocabulary {

    public static final int MASTERY_LEVEL_1 = 1;

    public static final int MASTERY_LEVEL_2 = 2;

    public static final int MASTERY_LEVEL_3 = 3;

    public static final int MASTERY_LEVEL_4 = 4;

    private String wordsKey;

    private String translation;

    private int masteryLevel;

    private int right;

    private int wrong;

    public Vocabulary() {
        wordsKey = "";
        translation = "";
        masteryLevel = MASTERY_LEVEL_1;
        right = 0;
        wrong = 0;
    }

    public Vocabulary(String wordsKey, String translation) {
        this.wordsKey = wordsKey;
        this.translation = translation;
        masteryLevel = MASTERY_LEVEL_1;
        right = 0;
        wrong = 0;
    }

    public String getWordsKey() {
        return wordsKey;
    }

    public void setWordsKey(String wordsKey) {
        this.wordsKey = wordsKey;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public int getMasteryLevel() {
        return masteryLevel;
    }

    public int getRight() {
        return right;
    }

    public int getWrong() {
        return wrong;
    }

    public void setMasteryLevel(int masteryLevel) {
        this.masteryLevel = masteryLevel;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public void setWrong(int wrong) {
        this.wrong = wrong;
    }
}
