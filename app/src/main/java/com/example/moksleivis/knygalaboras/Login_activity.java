package com.example.moksleivis.knygalaboras;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login_activity extends AppCompatActivity {
    private Button button;
    private Button Register;
    EditText mEdit;
    EditText mEdit2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);





        mEdit   = (EditText)findViewById(R.id.login_pokemon_name);
        mEdit2   = (EditText)findViewById(R.id.editText);
        button = (Button) findViewById(R.id.buttonToast);
        Button Register = (Button)findViewById(R.id.button2);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_activity.this, Register_activity.class));
            }
        });




        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
             DatabaseHandler db =  new DatabaseHandler(getApplicationContext());

                Contact contactForLogin = db.getContactForLogin(mEdit.getText().toString(),mEdit2.getText().toString());
                if(!Validation.isValidCredentials(mEdit.getText().toString())){
                    Toast.makeText(getApplicationContext(),
                            "Netinkamas vardas arba slaptazodis", Toast.LENGTH_LONG).show();
                }else if(!Validation.isValidCredentials(mEdit2.getText().toString())){
                    Toast.makeText(getApplicationContext(),
                            "Netinkamas vardas arba slaptazodis", Toast.LENGTH_LONG).show();
                }else if(!mEdit2.getText().toString().equals(contactForLogin.getPhoneNumber())){
                    Toast.makeText(getApplicationContext(),
                            "Netinkamas vardas arba slaptazodis", Toast.LENGTH_LONG).show();

                }
               else if(contactForLogin.getName() !=null) {

                   startActivity(new Intent(Login_activity.this, Dashboard_activity.class));
                }else{
                   Toast.makeText(getApplicationContext(),
                           "Toks vartotojas neegzistuoja", Toast.LENGTH_LONG).show();
               }

            }
        });

    }







}
