package com.example.moksleivis.knygalaboras;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import junit.framework.Test;

import java.util.List;

/**
 * Created by arvyd on 1/21/2018.
 */

public class Add_activity extends AppCompatActivity {


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
    private Button addButton;
    private Button updateButton;
    private Button deleteButton;
    private Spinner spin;
    int check1B = 0;
    int check2B = 0;
    int check3B = 0;
    int check4B = 0;
    int add;
    int update;
    int delete;
    List<Knyga> Books;
    DatabaseHandler db;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        delete = getIntent().getIntExtra("item_id",-1);
        Books = db.getAllBooks();



        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                 Text = String.valueOf(spin.getSelectedItem());
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

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
            }
            if(Books.get(update).getCheck2() == 1){
                check2.setChecked(true);
                check2B = 1;
            }
            if(Books.get(update).getCheck3() == 1){
                check3.setChecked(true);
                check3B = 1;
            }
            if(Books.get(update).getCheck4() == 1){
                check4.setChecked(true);
                check4B = 1;
            }

        }else if(add== -1){
            updateButton.setEnabled(false);
            deleteButton.setEnabled(false);
            addButton.setBackgroundResource(R.drawable.enabledbutton);
            updateButton.setBackgroundResource(R.drawable.disabledbutton);
            deleteButton.setBackgroundResource(R.drawable.disabledbutton);
        }

        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                int selectedId = radioGroup.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);
                if(knyga_validate("add")){
                    radioButton = (RadioButton) findViewById(selectedId);
                    db.addBook(new Knyga(name.getText().toString(), releaseyear.getText().toString(), Author.getText().toString(),
                            checkString, Text, Integer.parseInt(Pages.getText().toString()), radioButton.getText().toString(),check1B,check2B,check3B,check4B));

                    Intent intent2 = new Intent(Add_activity.this, Dashboard_activity.class);
                    startActivity(intent2);
                }
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                int selectedId = radioGroup.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);
                if(knyga_validate("update")){
                    radioButton = (RadioButton) findViewById(selectedId);
                         db.updateBook(new Knyga(update, name.getText().toString(), releaseyear.getText().toString(), Author.getText().toString(),
                            checkString, Text, Integer.parseInt(Pages.getText().toString()), radioButton.getText().toString(),check1B,check2B,check3B,check4B));
                    Intent intent3 = new Intent(Add_activity.this, Dashboard_activity.class);
                    startActivity(intent3);
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                db.deleteBooks(String.valueOf(delete));
                    Intent intent3 = new Intent(Add_activity.this, Dashboard_activity.class);
                    startActivity(intent3);
                }
        });
    }
    private boolean knyga_validate(String action) {
        checkString = "";
        switch(action) {

            case "add":
                if(!Validation.isValidClientNameForAdd(name.getText().toString())){
                    Toast.makeText(getApplicationContext(),
                            "Netinkamas vardas", Toast.LENGTH_LONG).show();
                    return false;
                }else if(!Validation.isValidAuthor(Author.getText().toString())){
                    Toast.makeText(getApplicationContext(),
                            "Netinkamas Autorio vardas", Toast.LENGTH_LONG).show();

                     return false;
                }else if(!Validation.isValidPages(Pages.getText().toString())){
                    Toast.makeText(getApplicationContext(),
                            "Netinkamas puslapiu kiekis", Toast.LENGTH_LONG).show();
                    return false;
                }else if(!Validation.isValidYear(releaseyear.getText().toString())){
                    Toast.makeText(getApplicationContext(),
                            "Netinkamas metu formatas", Toast.LENGTH_LONG).show();
                    return false;
                }
                else if(!(check1.isChecked()||check2.isChecked()||check3.isChecked()||check4.isChecked()))
                {
                    Toast.makeText(getApplicationContext(),
                            "Nepasirinktas žanras", Toast.LENGTH_LONG).show();
                     return false;
                }else { if (check1.isChecked()){
                    checkString = checkString  +  " " + check1.getText().toString();
                   check1B = 1;
                }if(check2.isChecked()){
                    checkString  = checkString + ", " + check2.getText().toString();
                    check2B = 1;
                }if (check3.isChecked()){
                    checkString  = checkString + ", " +  check3.getText().toString();
                    check3B = 1;
                }if (check4.isChecked()){
                    checkString  = checkString + ", "+  check4.getText().toString();
                    check4B = 1;
                }

                    return true;
                }

            case "update":
                if(!Validation.isValidClientNameForAdd(name.getText().toString())){
                    Toast.makeText(getApplicationContext(),
                            "Netinkamas vardas", Toast.LENGTH_LONG).show();
                    return false;
                }else if(!Validation.isValidAuthor(Author.getText().toString())){
                    Toast.makeText(getApplicationContext(),
                            "Netinkamas Autorio vardas", Toast.LENGTH_LONG).show();

                    return false;
                }else if(!Validation.isValidPages(Pages.getText().toString())){
                    Toast.makeText(getApplicationContext(),
                            "Netinkamas puslapiu kiekis", Toast.LENGTH_LONG).show();
                    return false;
                }else if(!Validation.isValidYear(releaseyear.getText().toString())){
                    Toast.makeText(getApplicationContext(),
                            "Netinkamas metu formatas", Toast.LENGTH_LONG).show();
                    return false;
                }
                else if(!(check1.isChecked()||check2.isChecked()||check3.isChecked()||check4.isChecked()))
                {
                    Toast.makeText(getApplicationContext(),
                            "Nepasirinktas žanras", Toast.LENGTH_LONG).show();
                    return false;
                }else { if (check1.isChecked()){
                    checkString = checkString + " " + check1.getText().toString();
                    check1B = 1;
                }if(check2.isChecked()){
                    checkString  = checkString + ", " + check2.getText().toString();
                    check2B = 1;
                }if (check3.isChecked()){
                    checkString  = checkString + ", " + check3.getText().toString();
                    check3B = 1;
                }if (check4.isChecked()){
                    checkString  = checkString + ", " +  check4.getText().toString();
                    check4B = 1;
                }

                    return true;
                }
        }
        return true;
    }


}
