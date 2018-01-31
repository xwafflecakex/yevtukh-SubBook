package com.example.nynic.yevtukh_subbook;

import android.app.Activity;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.annotation.Nullable;
import android.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

/**
 * Created by nynic on 2018-01-30.
 */

public class AddSubscription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_subscription);
        // Really wanted to get the back button working, thanks to this https://stackoverflow.com/questions/14545139/android-back-button-in-the-title-bar.
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // The Text Stuff.
        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextDate = findViewById(R.id.editTextDate);
        EditText editTextCharge = findViewById(R.id.editTextCharge);
        EditText editTextComment = findViewById(R.id.editTextComment);







    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}


