package com.example.nynic.yevtukh_subbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    @Override
    public boolean onCreateOptionsMenu(Menu subMenu) {
        //linking menu.xml

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.sub_menu,subMenu);


        return super.onCreateOptionsMenu(subMenu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_sub:
                Log.i("Menu item selected", "add_sub");
                return true;
            case  R.id.remove_sub:
                Log.i("Menu item selected", "remove_sub");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }




    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        TextView textView = findViewById(R.id.totalText);
        //Testing array list for Subs
        HashMap<String, String> nameAddresses = new HashMap<>();
        nameAddresses.put("Diana", "3214 Broadway Avenue");
        nameAddresses.put("Tyga", "343 Rack City Drive");
        nameAddresses.put("Rich Homie Quan", "111 Everything Gold Way");
        nameAddresses.put("Donna", "789 Escort St");
        nameAddresses.put("Bartholomew", "332 Dunkin St");
        nameAddresses.put("Eden", "421 Angelic Blvd");
        nameAddresses.put("Dina", "3214 Broadway Avenue");
        nameAddresses.put("Tga", "343 Rack City Drive");
        nameAddresses.put("Rch Homie Quan", "111 Eveything Gold Way");
        nameAddresses.put("Donna", "789 Ecort St");
        nameAddresses.put("Bartolomew", "332 Dukin St");
        nameAddresses.put("Een", "421 Angelic Blvd");

        List<HashMap<String, String>> listItems = new ArrayList<>();
        SimpleAdapter adapter = new SimpleAdapter(this, listItems, R.layout.list_item,
                new String[]{"First Line", "Second Line"},
                new int[]{R.id.nameText, R.id.price});


        Iterator iterator = nameAddresses.entrySet().iterator();
        while (iterator.hasNext()) {
            HashMap<String, String> resultsMap = new HashMap<>();
            Map.Entry pair = (Map.Entry)iterator.next(); //just want the key value pair per iteration
            resultsMap.put("First Line", pair.getKey().toString());
            resultsMap.put("Second Line", pair.getValue().toString());
            listItems.add(resultsMap);
        }

        listView.setAdapter(adapter);

    }
}




    /*TODO
     -Gonna use Hash map with list view. <-----------------------------------------------------------Done.
     -For the plus make two options, add or edit drop-downs.
     -Need to make activities for add_sub for input of details then storing them.
     -Make activities for remove_sub removing details gonna use name and charge as a type of id.
     -Add back buttons for both activities.
     -Add class subscription that makes sub objects. <-----------------------------------------------Done.
     -Link sub objects to the ArrayList.
     -Calc total, and display
     -Enforce input rules.
     -Do encap stuff
     -Finalize.
     -Do video demo
     -Submit.
     */



