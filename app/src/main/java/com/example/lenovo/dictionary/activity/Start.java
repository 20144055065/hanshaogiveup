package com.example.lenovo.dictionary.activity;

/**
 * Created by lenovo on 2018/2/10.
 */
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import com.example.lenovo.dictionary.R;
public class Start extends Activity {
    private ImageView ivStart;
    private SimpleDateFormat sdf;
    private String date;
    //http://cdn.iciba.com/web/news/longweibo/imag/2015-05-03.jpg
    private static final String url = "http://cdn.iciba.com/web/news/longweibo/imag/";

    private Handler handle = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Bitmap bmp=(Bitmap)msg.obj;
                    ivStart.setImageBitmap(bmp);
                    break;
            }
        };
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_layout);

        ivStart = (ImageView) findViewById(R.id.ivStart);
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        date = sdf.format(new java.util.Date());
        //System.out.println(date);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bmp = getURLimage(url + date + ".jpg");
                Message msg = new Message();
                msg.what = 0;
                msg.obj = bmp;
                handle.sendMessage(msg);
            }
        }).start();
        Handler handler = new Handler();
        handler.postDelayed(new splashhandler(), 10000);
    }

    class splashhandler implements Runnable{

        public void run() {
            startActivity(new Intent(getApplication(),MainActivity1.class));
            Start.this.finish();
        }
    }

    //下载网络图片
    public Bitmap getURLimage(String url) {
        Bitmap bmp = null;
        try {
            URL myurl = new URL(url);
            // 获得连接
            HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
            conn.setConnectTimeout(6000);//设置超时
            conn.setDoInput(true);
            conn.setUseCaches(false);//不缓存
            conn.connect();
            InputStream is = conn.getInputStream();//获得图片的数据流
            bmp = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bmp;
    }
}

