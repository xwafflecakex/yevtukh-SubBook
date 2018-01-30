package com.example.nynic.yevtukh_subbook;

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
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

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

        //Toolbar toolbarTop = (Toolbar) findViewById(R.id.toolbarTop);
        ListView listView = findViewById(R.id.listView);
        TextView textView = findViewById(R.id.textView);
        //setSupportActionBar(toolbarTop);
        //Testing array list for Subs
        final ArrayList<String> robPeople  = new ArrayList<String>();
        robPeople.add("Rob");
        robPeople.add("Kristen");
        robPeople.add("Tommy");
        robPeople.add("Ralphie");
        robPeople.add("Ralphie");
        robPeople.add("Ralphie");
        robPeople.add("Ralphie");
        robPeople.add("Ralphie");
        robPeople.add("Tommy");
        robPeople.add("Tommy");
        robPeople.add("Tommy");
        robPeople.add("Tommy");
        robPeople.add("Tommy");
        robPeople.add("Tommy");
        robPeople.add("Tommy");
        robPeople.add("Tommy");



        //Using an array adapter to change to a list format.
        ArrayAdapter<String> arrayAdapter =  new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, robPeople);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) { //the view is the row, the int is the position, the long is more  detailed.
                //adapterView.setVisibility(View.GONE); //After a tap on  a name, the list disappears.
                Log.i("Person Tapped: ", robPeople.get(i));
                Toast  toast = Toast.makeText(getApplicationContext(), "Hello " + robPeople.get(i),Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }




//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.add_sub:
//                // User chose the "add" item, show the app settings UI...
//                Toast.makeText(this, "Add_Sub clicked", Toast.LENGTH_LONG).show();
//                return true;
//
//            case R.id.remove_sub:
//                // User chose the "remove" action, mark the current item
//                Toast.makeText(this, "Remove_Sub clicked", Toast.LENGTH_LONG).show();
//
//                return true;
//
//            default:
//                // If we got here, the user's action was not recognized.
//                // Invoke the superclass to handle it.
//                return super.onOptionsItemSelected(item);
//
//        }
//    }

}
