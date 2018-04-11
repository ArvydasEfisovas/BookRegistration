package com.example.moksleivis.knygalaboras.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.app.AlertDialog;

import com.example.moksleivis.knygalaboras.Service.BackgroundSoundService;
import com.example.moksleivis.knygalaboras.Model.DatabaseHandler;
import com.example.moksleivis.knygalaboras.Model.Knyga;
import com.example.moksleivis.knygalaboras.R;
import com.example.moksleivis.knygalaboras.Controller.Validation;

import java.util.List;

/**
 * Created by arvyd on 1/21/2018.
 */

public class Add_activity extends AppCompatActivity {
    Intent svc;
    boolean checkactivity;
    String Text;
    EditText name;
    EditText releaseyear;
    EditText Author;
    EditText Pages;
    CheckBox check1;
    CheckBox check2;
    CheckBox check3;
    CheckBox check4;
    String checkString = "";
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private Button addButton;
    private Button updateButton;
    private Button deleteButton;
    private Spinner spin;
    int check1B = 0;
    int check2B = 0;
    int check3B = 0;
    int check4B = 0;
    int check1B1 = 0;
    int check2B1 = 0;
    int check3B1 = 0;
    int check4B1 = 0;
    int add;
    int update;
    int update2;
    int delete;
    List<Knyga> Books;
    DatabaseHandler db;
     int selectedId ;
    int selectedId1 ;
    int selectedId2 ;
    Knyga knygaSubmitted;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkactivity = false;
        svc  = new Intent(this,BackgroundSoundService.class);

        setContentView(R.layout.activity_add_activity);
         db = new DatabaseHandler(getApplicationContext());
        name   = (EditText)findViewById(R.id.name);
        releaseyear   = (EditText)findViewById(R.id.release_year);
        Author   = (EditText)findViewById(R.id.author);
        Pages   = (EditText)findViewById(R.id.pages);
        addButton = (Button) findViewById(R.id.prideti1);
        updateButton = (Button) findViewById(R.id.update);
        deleteButton = (Button) findViewById(R.id.delete);
        radioGroup = (RadioGroup) findViewById(R.id.radio);

        check1 = (CheckBox)findViewById(R.id.check1);
        check2 = (CheckBox)findViewById(R.id.check2);
        check3 = (CheckBox)findViewById(R.id.check3);
        check4 = (CheckBox)findViewById(R.id.check4);
        spin = (Spinner)findViewById(R.id.spinner1) ;
        add = getIntent().getIntExtra("add_item_id",-1);
        update = getIntent().getIntExtra("item_id_position",-1);
        update2 = getIntent().getIntExtra("item_id",-1);
        Toast.makeText(this, String.valueOf(update2), Toast.LENGTH_SHORT).show();
        delete = getIntent().getIntExtra("item_id_delete",-1);
        Books = db.getAllBooks();
        Pages.setText("0");
        setActivityFields();
        // find the radiobutton by returned id
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                 Text = String.valueOf(spin.getSelectedItem());
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if(knyga_validate()){
                    selectedId2 = radioGroup.getCheckedRadioButtonId();
                    radioButton2 = (RadioButton) findViewById(selectedId2);
                    db.addBook(new Knyga(name.getText().toString(), releaseyear.getText().toString(), Author.getText().toString(),
                            checkString, Text, Integer.parseInt(Pages.getText().toString()), radioButton2.getText().toString(),check1B1,check2B1,check3B1,check4B1));
                    checkactivity = true;
                    Intent intent2 = new Intent(Add_activity.this, Dashboard_activity.class);
                    startActivity(intent2);
                    finish();
                }
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);
                if(knyga_validate()){
                    radioButton = (RadioButton) findViewById(selectedId);
                         db.updateBook(new Knyga(update2, name.getText().toString(), releaseyear.getText().toString(), Author.getText().toString(),
                            checkString, Text, Integer.parseInt(Pages.getText().toString()), radioButton.getText().toString(),check1B,check2B,check3B,check4B));
                    checkactivity = true;
                    Intent intent3 = new Intent(Add_activity.this, Dashboard_activity.class);
                    startActivity(intent3);
                    finish();
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                db.deleteBooks(String.valueOf(delete));
                checkactivity = true;
                Intent intent3 = new Intent(Add_activity.this, Dashboard_activity.class);
                    startActivity(intent3);
                    finish();
                }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                checkIfChangesMade();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(!checkactivity) {
            stopService(svc);
        }
    }
    @Override
    protected void onUserLeaveHint()
    {
        Log.d("onUserLeaveHint","Home button pressed");
        super.onUserLeaveHint();
        onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startService(svc);
    }

    @Override
    public void onBackPressed() {
    }

    private void setActivityFields(){
        if(update >-1){
            addButton.setEnabled(false);
            addButton.setBackgroundResource(R.drawable.disabledbutton);
            updateButton.setBackgroundResource(R.drawable.enabledbutton);
            deleteButton.setBackgroundResource(R.drawable.enabledbutton);
            name.setText(Books.get(update).getName().toString());
            releaseyear.setText(Books.get(update).getRelease_year().toString());
            Author.setText(Books.get(update).getAuthor().toString());
            Pages.setText(String.valueOf(Books.get(update).getPages()));
            if(Books.get(update).getCover().equals("Hard")){
                ((RadioButton)radioGroup.getChildAt(0)).setChecked(true);
            }else if(Books.get(update).getCover().equals("Soft")){
                ((RadioButton)radioGroup.getChildAt(1)).setChecked(true);
            }

            if(Books.get(update).getCheck1() == 1){

                check1.setChecked(true);
                check1B = 1;
            }else{
                check1.setChecked(false);
                check1B = 0;
            }
            if(Books.get(update).getCheck2() == 1){

                check2.setChecked(true);
                check2B = 1;
            }else{
                check2.setChecked(false);
                check2B = 0;
            }
            if(Books.get(update).getCheck3() == 1){

                check3.setChecked(true);
                check3B = 1;
            }else{
                check3.setChecked(false);
                check3B = 0;
            }
            if(Books.get(update).getCheck4() == 1){

                check4.setChecked(true);
                check4B = 1;
            }else{
                check4.setChecked(false);
                check4B = 0;
            }

        }else if(add== -1){
            knygaSubmitted = new Knyga();
            knygaSubmitted.setPages(0);
            updateButton.setEnabled(false);
            deleteButton.setEnabled(false);
            addButton.setBackgroundResource(R.drawable.enabledbutton);
            updateButton.setBackgroundResource(R.drawable.disabledbutton);
            deleteButton.setBackgroundResource(R.drawable.disabledbutton);
        }
    }
    private void checkIfChangesMade(){
        if(update > -1) {
            if(check1.isChecked()){
                check1.setChecked(true);
                check1B = 1;
            }else{
                check1B = 0;
            }
            if(check2.isChecked()){
                check2.setChecked(true);
                check2B = 1;
            }else{
                check2B = 0;
            }
            if(check3.isChecked()){
                check3.setChecked(true);
                check3B = 1;
            }else{
                check3B = 0;
            }
            if(check4.isChecked()){
                check4.setChecked(true);
                check4B = 1;
            }else{
                check4B = 0;
            }
            selectedId = radioGroup.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(selectedId);
            if (!Books.get(update).getName().equals(name.getText().toString())) {
                createDialog();
            } else if (!Books.get(update).getRelease_year().equals(releaseyear.getText().toString())) {
                createDialog();
            } else if (!Books.get(update).getAuthor().equals(Author.getText().toString())) {
                createDialog();
            } else if (Books.get(update).getCheck1() != check1B) {
                createDialog();
            } else if (Books.get(update).getCheck2() != check2B) {
                createDialog();
            } else if (Books.get(update).getCheck3() != check3B) {
                createDialog();
            } else if (Books.get(update).getCheck4() != check4B) {
                createDialog();
            } else if (!Books.get(update).getRarity().equals(Text.toString())) {
                createDialog();
            } else if (!Books.get(update).getCover().equals(radioButton.getText().toString())) {
                createDialog();
            } else if (Books.get(update).getPages() != Integer.parseInt(Pages.getText().toString())) {
                createDialog();
            } else {
                checkactivity = true;
                Intent intent1 = new Intent(getBaseContext(), Dashboard_activity.class);
                startActivity(intent1);
                Add_activity.this.finish();
            }
        }else{
            if(check1.isChecked()){

                check1B1 = 1;
            }else{
                check1B1 = 0;
            }
            if(check2.isChecked()){

                check2B1 = 1;
            }else{
                check2B1 = 0;
            }
            if(check3.isChecked()){

                check3B1 = 1;
            }else{
                check3B1 = 0;
            }
            if(check4.isChecked()){

                check4B1 = 1;
            }else{
                check4B1 = 0;
            }
            Knyga knygaDefault = new Knyga("","","","Documentary","Common",0,"Hard",1,0,0,0);
            selectedId1 = radioGroup.getCheckedRadioButtonId();
            radioButton1 = (RadioButton) findViewById(selectedId1);

            knygaSubmitted = new Knyga(name.getText().toString(),
                    releaseyear.getText().toString(),
                    Author.getText().toString(),
                    checkString,
                    Text,
                    Integer.parseInt(Pages.getText().toString()),
                    radioButton1.getText().toString(),
                    check1B1,check2B1,check3B1,check4B1);
            if (!knygaDefault.getName().equals(name.getText().toString())){
                createDialog();
            }
            else if(!knygaDefault.getRelease_year().equals(releaseyear.getText().toString())){
                createDialog();
            }
            else if(!knygaDefault.getAuthor().equals(Author.getText().toString())){
                createDialog();
            }
            else if(knygaDefault.getCheck1() !=knygaSubmitted.getCheck1()){
                createDialog();
            }
            else if(knygaDefault.getCheck2() != knygaSubmitted.getCheck2()){
                createDialog();
            }
            else if(knygaDefault.getCheck3() != knygaSubmitted.getCheck3()){
                createDialog();
            }
            else if(knygaDefault.getCheck4() != knygaSubmitted.getCheck4()){
                createDialog();
            }
            else if(!knygaDefault.getRarity().equals(knygaSubmitted.getRarity())){
                createDialog();
            }
            else if(!knygaDefault.getCover().equals(knygaSubmitted.getCover().toString())){
                createDialog();
            }
            else if(knygaDefault.getPages() != knygaSubmitted.getPages())
            {
                createDialog();
            }
            else{
                checkactivity = true;
                Intent intent1 = new Intent(getBaseContext(), Dashboard_activity.class);
                startActivity(intent1);
                Add_activity.this.finish();
            }
        }
    }

    public void createDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                Add_activity.this);
        // set title
        alertDialogBuilder.setTitle("Unsaved changes.");

        // set dialog message
        alertDialogBuilder
                .setMessage("Click yes to save")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, close
                        // current activity
                        dialog.cancel();
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        Intent intent1 = new Intent(getBaseContext(), Dashboard_activity.class);
                        startActivity(intent1);
                        Add_activity.this.finish();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }
    private boolean knyga_validate() {
        checkString = "";
                if(!Validation.isValidBookNameForAdd(name.getText().toString())){
                    Toast.makeText(getApplicationContext(),
                            "Improper Book Name", Toast.LENGTH_LONG).show();
                    return false;
                }else if(!Validation.isValidAuthor(Author.getText().toString())){
                    Toast.makeText(getApplicationContext(),
                            "Improper Author's Name", Toast.LENGTH_LONG).show();

                     return false;
                }else if(!Validation.isValidPages(Pages.getText().toString())){
                    Toast.makeText(getApplicationContext(),
                            "Invalid pages amount (1-9999)", Toast.LENGTH_LONG).show();
                    return false;
                }else if(!Validation.isValidYear(releaseyear.getText().toString())){
                    Toast.makeText(getApplicationContext(),
                            "Invalid Date", Toast.LENGTH_LONG).show();
                    return false;
                }
                else if(!(check1.isChecked()||check2.isChecked()||check3.isChecked()||check4.isChecked()))
                {
                    Toast.makeText(getApplicationContext(),
                            "Genre not selected", Toast.LENGTH_LONG).show();
                     return false;
                }else { if (check1.isChecked()) {
                    checkString = checkString + " " + check1.getText().toString();
                    check1B = 1;
                    check1B1 = 1;
                }else{
                        check1.setChecked(false);
                        check1B = 0;
                    }
                if(check2.isChecked()) {
                    checkString = checkString + ", " + check2.getText().toString();
                    check2B = 1;
                    check2B1 = 1;
                } else{
                        check2.setChecked(false);
                        check2B = 0;
                    }
                }if (check3.isChecked()) {
                    checkString = checkString + ", " + check3.getText().toString();
                    check3B = 1;
                    check3B1 = 1;
                }else{
                        check3.setChecked(false);
                        check3B = 0;
                    }
                if (check4.isChecked()){
                    checkString  = checkString + ", "+  check4.getText().toString();
                    check4B = 1;
                    check4B1 = 1;
                }else{
                    check4.setChecked(false);
                    check4B = 0;
                }
                    return true;
                }
    }

