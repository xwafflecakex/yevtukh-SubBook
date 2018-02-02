package com.example.nynic.yevtukh_subbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import java.util.List;
import java.util.Map;
import java.util.Set;


public class MainActivity extends AppCompatActivity {

    ArrayList<Subscription> subList;
    private static final String FILENAME = "subscriptions.sav";
    Context context;
    private ListView listView;
    private TextView textView;

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

//    public void deleteSub(final LinkedHashMap map){
//
//
//        //If tapped on do shit. Don't know of use yet.
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) { //the view is the row, the int is the position, the long is more  detailed.
//                //adapterView.setVisibility(View.GONE); //After a tap on  a name, the list disapears.
//                Log.i("Person Tapped: ", String.valueOf(subList.get(i)));
//                Toast  toast = Toast.makeText(getApplicationContext(), "Hello " + subList.get(i),Toast.LENGTH_LONG);
//                toast.show();
//
//                //Now if remove button pressed, we simply tap the wanted item to remove.
//
//                //Toast toast = Toast.makeText(getApplicationContext(), "Hello " + testSub.get(i),Toast.LENGTH_LONG);
//                //toast.show();
//            }
//        });
//    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFromFile(this);
        //subList = SubscriptionList.getSubList();

        context = getApplicationContext();
        Log.i("THIS IS THE CONTEXT OF MAIN: ", context.toString());


        listView = findViewById(R.id.listView);
        textView = findViewById(R.id.totalText);

        Subscription newSub = new Subscription();
        newSub.setDate("1234-12-12");
        newSub.setName("The Name");
        newSub.setCharge((float) 12.33);
        subList.add(newSub);
        setList(subList);
        Log.i("THIS IS THE LIST", "" + subList);
        //saveInFile(this);
        //SubscriptionList.addToList(newSub);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) { //the view is the row, the int is the position, the long is more  detailed.
                //adapterView.setVisibility(View.GONE); //After a tap on  a name, the list disapears.
                Log.i("Person Tapped: ", String.valueOf(SubscriptionList.getSubList().get(i)));
                Toast  toast = Toast.makeText(getApplicationContext(), "Hello " + SubscriptionList.getSubList().get(i),Toast.LENGTH_LONG);
                toast.show();
            }
        });

//        float sum= (float) 0.0;
//        for (float value : sublist.values()) {
//            sum += value;
//        }




    }

    @Override
    protected void onStart() {
        super.onStart();
        ArrayAdapter<Subscription> arrayAdapter =  new ArrayAdapter<Subscription>(this, android.R.layout.simple_list_item_1, subList);
        listView.setAdapter(arrayAdapter);


    }

    public ArrayList<Subscription> getList(){
        return SubscriptionList.getSubList();
    }

    public void setList(ArrayList<Subscription> subList) {
        this.subList = subList;
    }


    public void saveInFile(Context context) {
        try {
            FileOutputStream fos = context.getApplicationContext().openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(subList, out);
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void loadFromFile(Context context) {
        try {
            FileInputStream fis = context.getApplicationContext().openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            //Taken https://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2018-01-24

            Type listType = new TypeToken<ArrayList<Subscription>>(){}.getType();

            subList = gson.fromJson(in, listType);

            if (subList == null){
                subList = new ArrayList<>();
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block

        }

    }
}




    /*TODO
     -Gonna use ArrayList with list view. <-----------------------------------------------------------Done.
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



