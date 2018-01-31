package com.example.nynic.yevtukh_subbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by nynic on 2018-01-30.
 */

public class RemoveSubscription extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remove_sub);
        listView = findViewById(R.id.listView);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


//        List<HashMap<String, String>> listItems = new ArrayList<>();
//        SimpleAdapter adapter = new SimpleAdapter(this, listItems, R.layout.list_item,
//                new String[]{"First Line", "Second Line"},
//                new int[]{R.id.nameText, R.id.price});
//
//        Iterator iterator = MainActivity.testSub.entrySet().iterator();
//        while (iterator.hasNext()) {
//            HashMap<String, String> resultsMap = new HashMap<>();
//            Map.Entry pair = (Map.Entry)iterator.next(); //just want the key value pair per iteration
//            resultsMap.put("First Line", pair.getKey().toString());
//            resultsMap.put("Second Line", " $" + pair.getValue().toString());
//            listItems.add(resultsMap);
//        }
//
//
//        listView.setAdapter(adapter);
//
   }
}
