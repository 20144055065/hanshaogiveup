package com.example.lenovo.dictionary.activity;

/**
 * Created by lenovo on 2018/2/15.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.lenovo.dictionary.R;

public class MainActivity2 extends  Activity  {
    private static SearchView searchView;
    private static WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_main);
        searchView= (SearchView) findViewById(R.id.sv);
        webView= (WebView) findViewById(R.id.lv);
        searchView.setSubmitButtonEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String strURI = (query);
                strURI = strURI.trim();
                //如果查询内容为空提示
                if (strURI.length() == 0) {
                    Toast.makeText(getApplicationContext(), "查询内容不能为空!", Toast.LENGTH_LONG)
                            .show();
                }
                //否则则以参数的形式从http://dict.youdao.com/m取得数据，加载到WebView里.
                else {
                    String strURL = "http://www.iciba.com/"
                            + strURI;
                    webView.loadUrl(strURL);
                }
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

}
