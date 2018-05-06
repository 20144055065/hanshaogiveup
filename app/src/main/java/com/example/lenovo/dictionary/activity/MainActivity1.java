package com.example.lenovo.dictionary.activity;

/**
 * Created by lenovo on 2018/2/15.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.dictionary.R;
import com.example.lenovo.dictionary.model.Words;
import com.example.lenovo.dictionary.util.HttpCallBackListener;
import com.example.lenovo.dictionary.util.HttpUtil;
import com.example.lenovo.dictionary.util.ParseXML;
import com.example.lenovo.dictionary.util.WordsAction;
import com.example.lenovo.dictionary.util.WordsHandler;
public class MainActivity1 extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1_main);
        Button button1 =(Button) findViewById(R.id.button1);
        Button button3 =(Button) findViewById(R.id.button3);
        Button button2 =(Button) findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity1.this,MainActivity.class);
                startActivityForResult(intent,1);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity1.this,MainActivity2.class);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity1.this,MainActivity3.class);
                startActivity(intent);
            }
        });
    }
}
