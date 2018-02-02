package com.example.nynic.yevtukh_subbook;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.content.Context;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by nynic on 2018-01-30.
 */

public class AddSubscription extends AppCompatActivity {

    Context context;
    EditText editTextName;
    EditText editTextDate;
    EditText editTextCharge;
    EditText editTextComment;
    Button saveBtn;
    boolean name;
    boolean date;
    boolean charge;
    MainActivity activity;
    ArrayList<Subscription> subList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = getApplicationContext();
        Log.i("CONTEXT",context.toString());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_subscription);
        // Really wanted to get the back button working, thanks to this https://stackoverflow.com/questions/14545139/android-back-button-in-the-title-bar.
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SubscriptionList subscriptionList = new SubscriptionList();
        ArrayList<Subscription> list =  subscriptionList.getSubList();

        Toast.makeText(this, "These are the current entries: "+ list, Toast.LENGTH_SHORT).show();


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
        //Toast.makeText(this, "Please enter a Subscription name, date started and charge.",Toast.LENGTH_LONG).show();
        saveBtn.setEnabled(false);


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
            if (working.length() <= 0) {
                isValid = false;
            }
            else if (working.length()!= 10) {
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
        public void afterTextChanged(Editable editable) {



        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
    };





    public void saveSub(View view){




        Subscription newSub = new Subscription();


        //Log.i("List contents", SubscriptionList.getSubList().toString());

        Matcher m = Pattern.compile("[0-9]{4}-[0-1][0-9]-[0-3][0-9]\\b").matcher(editTextDate.getText().toString());
        if (!m.matches()){
            saveBtn.setEnabled(false);
            new AlertDialog.Builder(this)
                    .setIcon((android.R.drawable.ic_dialog_alert))
                    .setTitle("Hey!")
                    .setMessage("Please enter a date in the format of YYYY-MM-DD and a real month and day.")
                    .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //Yes.
                            saveBtn.setEnabled(true);
                        }
                    })
                    .show();
        }else{
                saveBtn.setEnabled(true);
                newSub.setDate(editTextDate.getText().toString());
                //There is my hash map  to work with.
                newSub.setName(editTextName.getText().toString());
                //the date check verification.


                try { //Basically a null catcher.
                    newSub.setCharge(Float.parseFloat(editTextCharge.getText().toString()));
                }catch (Exception e){
                    Log.i("Exception", e.toString());
                }
                newSub.setComment(editTextComment.getText().toString());

                Log.i("This is the new sub",newSub.printSub());
                //activity.addSub(newSub);
                activity.saveInFile(this);


            editTextName.getText().clear();
            editTextDate.getText().clear();
            editTextComment.getText().clear();
            editTextCharge.getText().clear();

            Toast.makeText(this, "You can now add More Subscriptions.", Toast.LENGTH_LONG).show();
            //now gson save stuff.



        }






        //Saving the hashMap









//        new AlertDialog.Builder(this)
//                .setIcon((android.R.drawable.ic_dialog_alert))
//                .setTitle("Hey!")
//                .setMessage("Are you sure you want to add this Subscription?")
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        //Yes.
//                        Toast.makeText(AddSubscription.this, "Saved!", Toast.LENGTH_LONG).show();
//                    }
//                })
//                .setNegativeButton("No", null)
//                .show();
        //making and alert to save.When save is pressed.




        /*TODO
        -Need to make date string correct.
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


