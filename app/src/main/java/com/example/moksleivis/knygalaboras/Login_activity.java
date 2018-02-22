package com.example.moksleivis.knygalaboras;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Login_activity extends AppCompatActivity {
    private Button button;
    private Button Register;
    EditText mEdit;
    EditText mEdit2;
    CheckBox rememberMe;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);


// cia tuscia eilute
        //cia tuscia eilute

        mEdit   = (EditText)findViewById(R.id.login_pokemon_name);
        mEdit2   = (EditText)findViewById(R.id.editText);
        button = (Button) findViewById(R.id.buttonToast);
        Button Register = (Button)findViewById(R.id.button2);
         rememberMe = (CheckBox)findViewById(R.id.RememberMe);

        loginPreferences = getSharedPreferences("com.example.moksleivis.knygalaboras", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            mEdit.setText(loginPreferences.getString("username", ""));
            mEdit2.setText(loginPreferences.getString("password", ""));
            rememberMe.setChecked(true);
        }


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
                if (rememberMe.isChecked()) {
                    loginPrefsEditor.putBoolean("saveLogin", true);
                    loginPrefsEditor.putString("username", mEdit.getText().toString());
                    loginPrefsEditor.putString("password", mEdit2.getText().toString());
                    loginPrefsEditor.commit();
                }else {
                    loginPrefsEditor.clear();
                    loginPrefsEditor.commit();
                }



                User userForLogin = db.getUserForLogin(mEdit.getText().toString(),mEdit2.getText().toString());
                if(!Validation.isValidCredentials(mEdit.getText().toString())){
                    Toast.makeText(getApplicationContext(),
                            "Netinkamas vardas arba slaptazodis", Toast.LENGTH_LONG).show();
                }else if(!Validation.isValidCredentials(mEdit2.getText().toString())){
                    Toast.makeText(getApplicationContext(),
                            "Netinkamas vardas arba slaptazodis", Toast.LENGTH_LONG).show();
                }else if(!mEdit2.getText().toString().equals(userForLogin.getPhoneNumber())){
                    Toast.makeText(getApplicationContext(),
                            "Netinkamas vardas arba slaptazodis", Toast.LENGTH_LONG).show();

                }
               else if(userForLogin.getName() !=null) {

                    startActivity(new Intent(Login_activity.this, Dashboard_activity.class));
                }else{
                   Toast.makeText(getApplicationContext(),
                          "Toks vartotojas neegzistuoja", Toast.LENGTH_LONG).show();
               }

            }
        });

    }







}
