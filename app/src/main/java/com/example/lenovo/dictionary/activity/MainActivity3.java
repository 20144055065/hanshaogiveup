package com.example.lenovo.dictionary.activity;

import android.app.Activity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.lenovo.dictionary.R;
import com.example.lenovo.dictionary.db.WordsSQLiteOpenHelper;
import com.example.lenovo.dictionary.model.Words;

/**
 * Created by lenovo on 2018/2/19.
 */

public class MainActivity3 extends Activity {
     private String[] data ={"haughty  adj.傲慢的，骄傲的；目中无人的；自大的；","keyhole  n.锁眼，钥匙孔；","kneel  vi.跪，下跪；" ,
             "owe  vt.欠....债；感激；应归功于；怀有情感；","luminous  adj.发光的；明亮的；清楚的；辉赫；","procession  n.队伍，行列；一列，一排；列队行进；"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3_main);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity3.this,android.R.layout.simple_list_item_1,data);
        TextView tv=(TextView) findViewById(R.id.textView);
        ListView lv= (ListView) findViewById(R.id.listView);
        lv.setAdapter(adapter);
    }
}
