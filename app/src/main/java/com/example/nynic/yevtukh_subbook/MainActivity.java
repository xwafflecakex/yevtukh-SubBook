package com.example.nynic.yevtukh_subbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    SubscriptionList list = new SubscriptionList();
    ListView listView;
    TextView textView;

    //Menu inflater, worker.
    @Override
    public boolean onCreateOptionsMenu(Menu subMenu) {
        //linking menu.xml

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.sub_menu,subMenu);

        return super.onCreateOptionsMenu(subMenu);
    }

    //// Menu stuff.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent;
        switch (item.getItemId()){
            case R.id.add_sub:
                Log.i("Menu item selected", "add_sub");

                myIntent = new Intent(MainActivity.this, AddSubscription.class);
                startActivityForResult( myIntent,0);
                return true;
            case  R.id.remove_sub:
                Log.i("Menu item selected", "remove_sub");




                deleteSub(list.getSubList());


                //myIntent = new Intent(MainActivity.this, RemoveSubscription.class);
                //startActivityForResult( myIntent,0);
                return true;
            case  R.id.edit_sub:
                Log.i("Menu item selected", "edit_sub");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }




    }

    public void deleteSub(final LinkedHashMap map){


        //If tapped on do shit. Don't know of use yet.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) { //the view is the row, the int is the position, the long is more  detailed.
                //adapterView.setVisibility(View.GONE); //After a tap on  a name, the list disapears.
                Set mapSet = map.entrySet();
                Map.Entry<String, Float> picked = (Map.Entry<String, Float>) mapSet.toArray()[i];

                /// Sweet it worked thanks to..https://gist.github.com/tejainece/d32cba84b747c0b2e7df. Just need the above 2 lines.
                Log.i("Person Tapped: ",  picked.getKey());

                //Now if remove button pressed, we simply tap the wanted item to remove.

                //Toast toast = Toast.makeText(getApplicationContext(), "Hello " + testSub.get(i),Toast.LENGTH_LONG);
                //toast.show();
            }
        });
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        textView = findViewById(R.id.totalText);
        //Testing array list for Subs

        // So the Name is the Key and the Charge and Date Started are the values.
        // So far cannot think of a way to have same name Sub.
        LinkedHashMap<String, Float> sublist = list.getSubList();
        sublist.put("Diana", (float) 24.09);
        sublist.put("Tyga", (float) 243.45);
        sublist.put("Rich Homie Quan", (float) 24);
        sublist.put("Donna", (float) 33.0);
        sublist.put("Bartholomew", (float) 33.0);
        sublist.put("Eden", (float) 33.0);
        sublist.put("Dina", (float) 33.0);
        sublist.put("Tga", (float) 33.0);
        sublist.put("Rch Homie Quan", (float) 33.0);
        sublist.put("Donna", (float) 33.0);
        sublist.put("Bartolomew", (float) 33.0);
        sublist.put("Een", (float) 24);
        list.setSubList(sublist);



        float sum= (float) 0.0;
        for (float value : sublist.values()) {
            sum += value;
        }

        textView.append(" " + Float.toString(sum));

        List<HashMap<String, String>> listItems = new ArrayList<>();
        SimpleAdapter adapter = new SimpleAdapter(this, listItems, R.layout.list_item,
                new String[]{"First Line", "Second Line"},
                new int[]{R.id.nameText, R.id.price});

        Iterator iterator = sublist.entrySet().iterator();
        while (iterator.hasNext()) {
            HashMap<String, String> resultsMap = new HashMap<>();
            Map.Entry pair = (Map.Entry)iterator.next(); //just want the key value pair per iteration
            resultsMap.put("First Line", pair.getKey().toString());
            resultsMap.put("Second Line", " $" + pair.getValue().toString());
            listItems.add(resultsMap);
        }


        listView.setAdapter(adapter);


    }
}


    /*TODO
     -Gonna use Hash map with list view. <-----------------------------------------------------------Done.
     -For the plus make two options, add or edit drop-downs. // made a separate edit button<---------Done.
     -Need to make activities for add_sub for input of details then storing them.
     -Make activities for remove_sub removing details gonna use name and charge as a type of id.
     -Add back buttons for both activities.
     -Add class subscription that makes sub objects. <-----------------------------------------------Done.
     -Link sub objects to the ArrayList.
     -Calc total, and display
     -Enforce input rules.
     -Do encapsulate stuff
     -Finalize.
     -Do video demo
     -Submit.
     */



