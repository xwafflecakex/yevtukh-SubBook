package com.example.nynic.yevtukh_subbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by nynic on 2018-01-30.
 */

public class RemoveSubscription extends AppCompatActivity {


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remove_sub);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
