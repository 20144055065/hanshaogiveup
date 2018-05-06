package com.example.lenovo.dictionary.util;

/**
 * Created by lenovo on 2017/12/18.
 */
 import java.io.BufferedReader;
import java.io.InputStream;


public interface HttpCallBackListener {

    void onFinish(InputStream inputStream);

    void onError();
}