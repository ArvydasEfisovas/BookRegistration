package com.example.moksleivis.knygalaboras;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import static android.R.attr.button;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Created by moksleivis on 2018-01-19.
 */


    public class Register_activity extends AppCompatActivity {

    private Button Register;
    EditText mEdit;
    EditText mEdit2;
    EditText mEdit3;
    EditText mEdit4;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register_activity);


            mEdit   = (EditText)findViewById(R.id.register_pokemon_name);
            mEdit2   = (EditText)findViewById(R.id.register_pokemon_password);
            mEdit3   = (EditText)findViewById(R.id.register_pokemon_repeat_password);
            mEdit4   = (EditText)findViewById(R.id.register_pokemon_email);



            Register = (Button)findViewById(R.id.Register);
            Register.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {

                    if(!Validation.isValidCredentials(mEdit.getText().toString())){
                        Toast.makeText(getApplicationContext(),
                                "Netinkamas username arba password", Toast.LENGTH_LONG).show();
                    }else if(!Validation.isValidCredentials(mEdit2.getText().toString())){
                        Toast.makeText(getApplicationContext(),
                                "Netinkamas username arba password", Toast.LENGTH_LONG).show();
                    }else if(!mEdit2.getText().toString().equals(mEdit3.getText().toString())){
                        Toast.makeText(getApplicationContext(),
                                "Nesutampa slaptažodžiai", Toast.LENGTH_LONG).show();
                    }else if(!Validation.isValidEmail(mEdit4.getText().toString())){
                        Toast.makeText(getApplicationContext(),
                                "Netinkamai ivestas email", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(),
                              "Sekmingai prisiregistravot" + "\n"+ "Username :" + mEdit.getText().toString() + "\n" + "Password :" + mEdit2.getText().toString()+
                                "\n" + "Password :" + mEdit4.getText().toString(), Toast.LENGTH_LONG).show();

                        Contact userTocheck = new Contact();
                        DatabaseHandler db = new DatabaseHandler(getApplicationContext());

                        db.addContact(new Contact(mEdit.getText().toString(),mEdit2.getText().toString(),mEdit4.getText().toString()));
                        startActivity(new Intent(Register_activity.this, Login_activity.class));
                    }



                }
            });


        }
    public void addUser()
    {
        String sql = "INSERT INTO `users`"
                + "(`username`, `password`,'userlevel',  `email`)"
                + " VALUES (?, ?, ?, ?)";
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://192.168.0.105/db", "root", "");
            PreparedStatement add = myConn.prepareStatement(sql);
            add.setString(1,mEdit.getText().toString());
            add.setString(2,mEdit2.getText().toString());
            add.setInt(3,1);
            add.setString(4,mEdit4.getText().toString());


            Toast.makeText(getApplicationContext(),
                    "suveike duombaze", Toast.LENGTH_LONG).show();

            add.execute();
            add.close();
        }catch(Exception exc){
            exc.printStackTrace();
            Toast.makeText(getApplicationContext(),
                    "nesuveike duombaze", Toast.LENGTH_LONG).show();
        }
    }


    }

