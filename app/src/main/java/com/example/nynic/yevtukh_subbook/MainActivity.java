package com.example.nynic.yevtukh_subbook;

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
        final HashMap<String, Float> testSub = new HashMap<>();
        testSub.put("Diana", (float) 24.09);
        testSub.put("Tyga", (float) 243.45);
        testSub.put("Rich Homie Quan", (float) 24);
        testSub.put("Donna", (float) 33.0);
        testSub.put("Bartholomew", (float) 33.0);
        testSub.put("Eden", (float) 33.0);
        testSub.put("Dina", (float) 33.0);
        testSub.put("Tga", (float) 33.0);
        testSub.put("Rch Homie Quan", (float) 33.0);
        testSub.put("Donna", (float) 33.0);
        testSub.put("Bartolomew", (float) 33.0);
        testSub.put("Een", (float) 24);
        float sum= (float) 0.0;
        for (float value : testSub.values()) {
            sum += value;
        }

        textView.append(" " + Float.toString(sum));


        List<HashMap<String, String>> listItems = new ArrayList<>();
        SimpleAdapter adapter = new SimpleAdapter(this, listItems, R.layout.list_item,
                new String[]{"First Line", "Second Line"},
                new int[]{R.id.nameText, R.id.price});

        Iterator iterator = testSub.entrySet().iterator();
        while (iterator.hasNext()) {
            HashMap<String, String> resultsMap = new HashMap<>();
            Map.Entry pair = (Map.Entry)iterator.next(); //just want the key value pair per iteration
            resultsMap.put("First Line", pair.getKey().toString());
            resultsMap.put("Second Line", " $" + pair.getValue().toString());
            listItems.add(resultsMap);
        }
        listView.setAdapter(adapter);

        //If tapped on do shit. Don't know of use yet.
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) { //the view is the row, the int is the position, the long is more  detailed.
//                //adapterView.setVisibility(View.GONE); //After a tap on  a name, the list disapears.
//                Log.i("Person Tapped: ",String.valueOf(l));
//                //Toast toast = Toast.makeText(getApplicationContext(), "Hello " + testSub.get(i),Toast.LENGTH_LONG);
//                //toast.show();
//            }
//        });

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
     -Do encapsulate stuff
     -Finalize.
     -Do video demo
     -Submit.
     */



