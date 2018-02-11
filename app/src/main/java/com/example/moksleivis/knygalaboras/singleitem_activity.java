package com.example.moksleivis.knygalaboras;

import android.content.Intent;
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

import java.util.List;

/**
 * Created by arvyd on 1/21/2018.
 */

public class singleitem_activity extends AppCompatActivity {


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
    private Button button;
    private Spinner spin;
    List<Knyga> Books;
    DatabaseHandler db = new DatabaseHandler(this);
    int check1B = 0;
    int check2B = 0;
    int check3B = 0;
    int check4B = 0;
    int s;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleitem);



        name   = (EditText)findViewById(R.id.name);
        releaseyear   = (EditText)findViewById(R.id.release_year);
        Author   = (EditText)findViewById(R.id.author);
        Pages   = (EditText)findViewById(R.id.pages);

        button = (Button) findViewById(R.id.update);
        radioGroup = (RadioGroup) findViewById(R.id.radio);

        check1 = (CheckBox)findViewById(R.id.check1);
        check2 = (CheckBox)findViewById(R.id.check2);
        check3 = (CheckBox)findViewById(R.id.check3);
        check4 = (CheckBox)findViewById(R.id.check4);
        spin = (Spinner)findViewById(R.id.spinner1) ;
        Books = db.getAllBooks();


        spin.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
        s = getIntent().getIntExtra("item_id",0);


        Toast.makeText(getApplicationContext(),
                String.valueOf(s), Toast.LENGTH_LONG).show();
        name.setText(Books.get(s).getName().toString());
        releaseyear.setText(Books.get(s).getRelease_year().toString());
        Author.setText(Books.get(s).getAuthor().toString());
        Pages.setText(String.valueOf(Books.get(s).getPages()));
        if(Books.get(s).getCover().equals("Hard")){
            ((RadioButton)radioGroup.getChildAt(0)).setChecked(true);
        }else if(Books.get(s).getCover().equals("Soft")){
            ((RadioButton)radioGroup.getChildAt(1)).setChecked(true);
        }

        if(Books.get(s).getCheck1() == 1){
            check1.setChecked(true);
             check1B = 1;
        }
        if(Books.get(s).getCheck2() == 1){
            check2.setChecked(true);
            check2B = 1;
        }
        if(Books.get(s).getCheck3() == 1){
            check3.setChecked(true);
            check3B = 1;
        }
        if(Books.get(s).getCheck4() == 1){
            check4.setChecked(true);
            check4B = 1;
        }


        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                // Object item = parent.getItemAtPosition(pos);
                Text = String.valueOf(spin.getSelectedItem());
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                int selectedId = radioGroup.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);
                DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                // String name, String user, String release_year, String author, String genre, String rarity, int pages,
                //  String cover)
                if(knyga_validate("update")){
                    radioButton = (RadioButton) findViewById(selectedId);
                    db.updateBook(new Knyga(s, name.getText().toString(), releaseyear.getText().toString(), Author.getText().toString(),
                            checkString, Text, Integer.parseInt(Pages.getText().toString()), radioButton.getText().toString(),check1B,check2B,check3B,check4B));
                    startActivity(new Intent(singleitem_activity.this, Dashboard_activity.class));
                }




            }
        });
    }
    private boolean knyga_validate(String action) {
        checkString = "";
        switch(action) {

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
                    checkString = checkString + check1.getText().toString();
                }if(check2.isChecked()){
                    checkString  = checkString + check2.getText().toString();
                }if (check3.isChecked()){
                    checkString  = checkString +  check3.getText().toString();
                }if (check4.isChecked()){
                    checkString  = checkString +  check4.getText().toString();
                }
                    return true;
                }


        }
        return true;
    }


}
