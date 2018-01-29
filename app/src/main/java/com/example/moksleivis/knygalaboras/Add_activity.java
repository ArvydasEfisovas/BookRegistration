package com.example.moksleivis.knygalaboras;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

/**
 * Created by arvyd on 1/21/2018.
 */

public class Add_activity extends AppCompatActivity {



    EditText name;
    EditText releaseyear;
    EditText Author;
    EditText Pages;
    CheckBox check;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button button;
    private Spinner spin;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_activity);



        name   = (EditText)findViewById(R.id.name);
        releaseyear   = (EditText)findViewById(R.id.release_year);
        Author   = (EditText)findViewById(R.id.author);
        Pages   = (EditText)findViewById(R.id.pages);

        button = (Button) findViewById(R.id.prideti1);
        radioGroup = (RadioGroup) findViewById(R.id.radio);

        check = (CheckBox)findViewById(R.id.check1);
        spin = (Spinner)findViewById(R.id.spinner1) ;





        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);
                DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                // String name, String user, String release_year, String author, String genre, String rarity, int pages,
                //  String cover)
                db.addBook(new Knyga(name.getText().toString(), releaseyear.getText().toString(), Author.getText().toString(),
                        check.getText().toString(), "Common", Integer.parseInt(Pages.getText().toString()), radioButton.getText().toString()));
               startActivity(new Intent(Add_activity.this, Dashboard_activity.class));

            }
        });







    }



}
