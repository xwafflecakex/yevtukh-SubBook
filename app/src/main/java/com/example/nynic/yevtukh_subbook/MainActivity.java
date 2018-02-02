package com.example.nynic.yevtukh_subbook;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {

    private ArrayList<Subscription> subList;
    private static final String FILENAME = "subscriptions.sav";
    private static final String EXPENSE = "TOTAL MONTHLY EXPENSE:";
    private ListView listView;
    private TextView textView;
    private EditText editTextName;
    private EditText editTextDate;
    private EditText editTextCharge;
    private EditText editTextComment;
    private Button saveBtn;
    private Button editBtn;
    boolean name;
    boolean date;
    boolean charge;
    private Subscription newSub;
    boolean add;
    boolean remove;
    boolean edit;
    private AlertDialog.Builder alert;
    private ArrayAdapter<Subscription> arrayAdapter;
    int positionIndex;




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
        switch (item.getItemId()){
            case R.id.add_sub:
                if (!add){
                    Toast.makeText(this, "ADD SElECTED",Toast.LENGTH_LONG).show();
                    add = true;
                    setVisibilityMain(0);
                    setVisibilityAdd(true);
                    editTextName.getText().clear();
                    editTextDate.getText().clear();
                    editTextComment.getText().clear();
                    editTextCharge.getText().clear();
                    editBtn.setVisibility(View.GONE);
                    editBtn.setEnabled(false);
                    invalidateOptionsMenu();
                }else {
                    add = false;
                    setVisibilityMain(1);
                    setVisibilityAdd(false);
                }
                return true;


            case  R.id.remove_sub:
                if (!remove){
                    remove = true;
                    Log.i("Menu item selected", "remove_sub");
                    Toast.makeText(getApplicationContext(), "Pick a Subscription to remove. Hold to delete. ",Toast.LENGTH_LONG).show();
                    listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                             new AlertDialog.Builder(MainActivity.this)
                                    .setIcon((android.R.drawable.ic_dialog_alert))
                                    .setTitle("Hey!")
                                    .setMessage("Are you sure you want to REMOVE this Subscription?")
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            //Yes.
                                            Toast.makeText(getApplicationContext(), "Removed", Toast.LENGTH_LONG).show();
                                            Log.i("REMOVEING", ""+subList.get(position));
                                            subList.remove(position);
                                            updateExpense();
                                            saveInFile(getApplicationContext());
                                            arrayAdapter.notifyDataSetChanged();

                                        }
                                    })
                                    .setNegativeButton("No", null)
                                    .show();
                            return false;
                        }
                    });
                }else {
                    remove  = false;

                }return true;

            case R.id.edit_sub:

                if (!edit){
                    Log.i("Menu item selected", "edit_sub");
                    edit=true;
                    Toast.makeText(getApplicationContext(), "Pick a Subscription to edit.",Toast.LENGTH_LONG).show();
                    listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                            setVisibilityAdd(true);
                            saveBtn.setVisibility(View.GONE);
                            editBtn.setVisibility(View.VISIBLE);
                            editBtn.setEnabled(true);
                            setVisibilityMain(0);
                            positionIndex = position;


                            editTextName.setText(subList.get(positionIndex).getName());
                            editTextDate.setText(subList.get(positionIndex).getDate());
                            editTextComment.setText(subList.get(positionIndex).getComment());
                            try { //Basically a null catcher.
                                editTextCharge.setText(String.format("%.2f",subList.get(positionIndex).getCharge()));
                            }catch (Exception e){
                                Log.i("Exception", e.toString());
                            }


                            return false;
                        }
                    });

                }else {
                    edit = false;
                    setVisibilityMain(1);
                    setVisibilityAdd(false);
                    editBtn.setVisibility(View.GONE);
                }

                //adapterView.setVisibility(View.GONE); //After a tap on  a name, the list disapears.

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }




    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFromFile(this);



        listView = findViewById(R.id.listView);
        textView = findViewById(R.id.totalText);
        name = false;
        date = false;
        charge = false;
        add = false;
        edit = false;
        remove  = false;



        // The Text Stuff.
        editTextName = findViewById(R.id.editTextName);
        editTextName.addTextChangedListener(nameTextWatcher);

        editTextDate = findViewById(R.id.editTextDate);
        editTextDate.addTextChangedListener(dateTextWatcher);

        editTextCharge = findViewById(R.id.editTextCharge);
        editTextCharge.addTextChangedListener(chargeTextWatcher);

        editTextComment = findViewById(R.id.editTextComment);

        saveBtn = findViewById(R.id.buttonSave);
        editBtn = findViewById(R.id.buttonEdit);
        editBtn.setEnabled(false);
        editBtn.setVisibility(View.GONE);
        saveBtn.setEnabled(false);
        setVisibilityAdd(false);
        updateExpense();


        saveInFile(this);
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
        public void afterTextChanged(Editable editable) {}
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
    };


    public void editSub(View view){

        ///Need to change the save stuff to edit like the text already in the fields then it just saves not adds.

        final Subscription edit = subList.get(positionIndex);
        Log.i("THE ITEM TO BE EDITED", "" + edit);




        Matcher m = Pattern.compile("[2-3][0-1][0-2][0-9]-[0-1][0-9]-[0-3][0-9]\\b").matcher(editTextDate.getText().toString());
        if (!m.matches()){
            editBtn.setEnabled(false);
            new AlertDialog.Builder(this)
                    .setIcon((android.R.drawable.ic_dialog_alert))
                    .setTitle("Hey!")
                    .setMessage("Please enter a date in the format of YYYY-MM-DD and a real month and day.")
                    .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //Yes.
                            editBtn.setEnabled(true);
                        }
                    })
                    .show();
        }else{
            editBtn.setEnabled(true);


            edit.setName(editTextName.getText().toString());
            edit.setDate(editTextDate.getText().toString());
            edit.setComment(editTextComment.getText().toString());
            try { //Basically a null catcher.
                edit.setCharge(Float.parseFloat(editTextCharge.getText().toString()));
            }catch (Exception e){
                Log.i("Exception", e.toString());
            }
            alert = new AlertDialog.Builder(this);
            alert.setIcon((android.R.drawable.ic_dialog_alert));
            alert.setTitle("Hey!");
            alert.setMessage("Are you sure you want to Update this Subscription?");
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //Yes.
                    Toast.makeText(getApplicationContext(), "Updated!", Toast.LENGTH_LONG).show();
                    subList.set(positionIndex,edit);

                    updateExpense();
                    arrayAdapter.notifyDataSetChanged();
                    setVisibilityAdd(false);
                    setVisibilityMain(1);
                    saveInFile(getApplicationContext());
                    editTextName.getText().clear();
                    editTextDate.getText().clear();
                    editTextComment.getText().clear();
                    editTextCharge.getText().clear();
                    editBtn.setEnabled(false);
                    editBtn.setVisibility(View.GONE);
                    positionIndex = 0;
                }
            });
            alert.setNegativeButton("No", null);
            AlertDialog dialog = alert.create();
            dialog.show();

        }

    }


    public void saveSub(View view){

        newSub = new Subscription();
        Matcher m = Pattern.compile("[2-3][0-1][0-2][0-9]-[0-1][0-9]-[0-3][0-9]\\b").matcher(editTextDate.getText().toString());
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
            newSub.setName(editTextName.getText().toString());
            //the date check verification.


            try { //Basically a null catcher.
                newSub.setCharge(Float.parseFloat(editTextCharge.getText().toString()));
            }catch (Exception e){
                Log.i("Exception", e.toString());
            }
            alert = new AlertDialog.Builder(this);
                alert.setIcon((android.R.drawable.ic_dialog_alert));
                alert.setTitle("Hey!");
                alert.setMessage("Are you sure you want to add this Subscription?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Yes.
                        Toast.makeText(getApplicationContext(), "Saved!", Toast.LENGTH_LONG).show();
                        newSub.setComment(editTextComment.getText().toString());
                        subList.add(newSub);

                        updateExpense();
                        editTextName.getText().clear();
                        editTextDate.getText().clear();
                        editTextComment.getText().clear();
                        editTextCharge.getText().clear();
                        setVisibilityAdd(false);
                        setVisibilityMain(1);
                        saveInFile(getApplicationContext());
                    }
                });
                alert.setNegativeButton("No", null);
                AlertDialog dialog = alert.create();
                dialog.show();







        }

    }

    public void updateExpense(){
        float sum= (float) 0.0;
        for (Subscription subscription : this.subList) {
            sum += subscription.getCharge();
        }
        this.textView.setText(String.format("%s $%s", EXPENSE, sum));
    }


    @Override
    protected void onStart() {
        super.onStart();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, this.subList);
        listView.setAdapter(arrayAdapter);
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

    public void setVisibilityAdd(boolean visible){
        if (!visible){
            editTextCharge.setVisibility(View.GONE);
            editTextComment.setVisibility(View.GONE);
            editTextDate.setVisibility(View.GONE);
            editTextName.setVisibility(View.GONE);
            saveBtn.setVisibility(View.GONE);
        }else {
            editTextCharge.setVisibility(View.VISIBLE);
            editTextComment.setVisibility(View.VISIBLE);
            editTextDate.setVisibility(View.VISIBLE);
            editTextName.setVisibility(View.VISIBLE);
            saveBtn.setVisibility(View.VISIBLE);
        }

    }

    public void setVisibilityMain(int visible){
        listView.setAlpha(visible);
        textView.setAlpha(visible);
    }
    protected void onDestroy(){
        super.onDestroy();
        Log.i("In Destroy method","The app is closing");
    }
}





    /*TODO
     -Gonna use ArrayList with list view. <-----------------------------------------------------------Done.
     -For the plus make two options, add or edit drop-downs. // made a separate edit button<---------Done.
     -Need to make activities for add_sub for input of details then storing them.<-----------------------------------------------Done.
     -Make activities for remove_sub removing details gonna use name and charge as a type of id.<-----------------------------------------------Done.
     -Add back buttons for both activities. <-----------------------------------------------Abandon.
     -Add class subscription that makes sub objects. <-----------------------------------------------Done.
     -Link sub objects to the ArrayList.<-----------------------------------------------Done.
     -Calc total, and display<-----------------------------------------------Done.
     -Enforce input rules.<-----------------------------------------------Done.
     -Do encapsulate stuff <_-----------------------------------------------DONe.
     -Finalize. <------------------------------------------------------------Done.
     -Do video demo
     -Submit.
     */



