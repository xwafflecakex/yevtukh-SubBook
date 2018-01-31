package com.example.nynic.yevtukh_subbook;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.annotation.Nullable;
import android.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by nynic on 2018-01-30.
 */

public class AddSubscription extends AppCompatActivity {
    EditText editTextName;
    EditText editTextDate;
    EditText editTextCharge;
    EditText editTextComment;
    Button saveBtn;
    boolean name;
    boolean date;
    boolean charge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_subscription);
        // Really wanted to get the back button working, thanks to this https://stackoverflow.com/questions/14545139/android-back-button-in-the-title-bar.
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = false;
        date = false;
        charge = false;

        // The Text Stuff.
        editTextName = findViewById(R.id.editTextName);
        editTextName.addTextChangedListener(nameTextWatcher);

        editTextDate = findViewById(R.id.editTextDate);
        editTextDate.addTextChangedListener(dateTextWatcher);

        editTextCharge = findViewById(R.id.editTextCharge);
        editTextCharge.addTextChangedListener(chargeTextWatcher);

        editTextComment = findViewById(R.id.editTextComment);

        saveBtn = findViewById(R.id.buttonSave);
        Toast.makeText(this, "Please enter a Subscription name, date started and charge.",Toast.LENGTH_LONG).show();
        saveBtn.setEnabled(false);


        //Testing the fields.
        Log.i("Name",editTextName.getText().toString());
        Log.i("date",editTextDate.getText().toString());
        //Restrict negative numbers. Done.
        Log.i("Charge",editTextCharge.getText().toString());
        Log.i("Comment",editTextComment.getText().toString());

        //Subscription subscription= new Subscription(editTextName.getText().toString(), editTextDate.getText().toString().);

    }
    private TextWatcher chargeTextWatcher = new TextWatcher() {
        //can't get it working the way i want so left it as a hint type error, do the actually check later.
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String workingCharge = s.toString();
            boolean isValid = true;

            if (workingCharge.length() <= 0) {
                isValid = false;
            }

            if (!isValid) {
                editTextCharge.setError("Enter a Monthly Charge.");
                saveBtn.setEnabled(false);

            } else {
                editTextCharge.setError(null);
                charge = true;
                if (name && date){
                    saveBtn.setEnabled(true);
                }
            }
        }
        @Override
        public void afterTextChanged(Editable editable) {}
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
    };

    private TextWatcher nameTextWatcher = new TextWatcher() {
        //can't get it working the way i want so left it as a hint type error, do the actually check later.
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String workingName = s.toString();
            boolean isValid = true;

            if (workingName.length() <= 0) {
                isValid = false;
            }

            if (!isValid) {
                editTextName.setError("Enter a name of Subscription.");
                saveBtn.setEnabled(false);

            } else {
                editTextName.setError(null);
                name = true;
                if (charge && date){
                    saveBtn.setEnabled(true);
                }
            }
        }
        @Override
        public void afterTextChanged(Editable editable) {}
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
    };

    private TextWatcher dateTextWatcher = new TextWatcher() {
        //can't get it working the way i want so left it as a hint type error, do the actually check later.
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String working = s.toString();
            boolean isValid = true;
            //Year
            if (working.length()== 4 && before == 0) {
                Log.i("working", working);
                int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                if (Integer.parseInt(working) < 3 || Integer.parseInt(working)>12) {
                    isValid = false;
                } else if (Integer.parseInt(working) > currentYear){
                    isValid = false;
                }
            }
            //Month
            else if (working.length()== 4 && before ==0) {
                String enteredYear = working.substring(3);
                Log.i("entered Year", enteredYear);
            } else if (working.length()!= 10) {
                isValid = false;
            }

            if (!isValid) {
                editTextDate.setError("Enter a valid date: YYYY-MM-DD");
                saveBtn.setEnabled(false);

            } else {
                editTextDate.setError(null);
                date = true;
                if (name && charge){
                    saveBtn.setEnabled(true);
                }
            }
        }
        @Override
        public void afterTextChanged(Editable editable) {}
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
    };





    public void saveSub(View view){


        new AlertDialog.Builder(this)
                .setIcon((android.R.drawable.ic_dialog_alert))
                .setTitle("Hey!")
                .setMessage("Are you sure you want to add this Subscription?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Yes.
                        Toast.makeText(AddSubscription.this, "Saved!", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("No", null)
                .show();
        Log.i("Save Button","Button was pressed");
        //making and alert to save.When save is pressed.



        /*TODO
        -Need to make a sub.
        -Need to store sub in hash map for array list.
        -need to save sub  with Gson.after button press.
         */
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


