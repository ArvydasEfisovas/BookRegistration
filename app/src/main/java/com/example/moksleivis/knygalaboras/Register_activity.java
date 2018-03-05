package com.example.moksleivis.knygalaboras;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
            mEdit   = (EditText)findViewById(R.id.register_name);
            mEdit2   = (EditText)findViewById(R.id.register_password);
            mEdit3   = (EditText)findViewById(R.id.register_repeat_password);
            mEdit4   = (EditText)findViewById(R.id.register_email);
            Register = (Button)findViewById(R.id.Register);
            Register.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    if(!Validation.isValidCredentials(mEdit.getText().toString())){
                        Toast.makeText(getApplicationContext(),
                                "Invalid Name Or Password", Toast.LENGTH_LONG).show();
                    }else if(!Validation.isValidCredentials(mEdit2.getText().toString())){
                        Toast.makeText(getApplicationContext(),
                                "Invalid Name Or Password", Toast.LENGTH_LONG).show();
                    }else if(!mEdit2.getText().toString().equals(mEdit3.getText().toString())){
                        Toast.makeText(getApplicationContext(),
                                "Password doesn't match", Toast.LENGTH_LONG).show();
                    }else if(!Validation.isValidEmail(mEdit4.getText().toString())){
                        Toast.makeText(getApplicationContext(),
                                "Invalid email", Toast.LENGTH_LONG).show();
                    }else{
                        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                        db.addUsers(new User(mEdit.getText().toString(),mEdit2.getText().toString(),mEdit4.getText().toString()));
                        startActivity(new Intent(Register_activity.this, Login_activity.class));
                        finish();
                    }
                }
            });
        }
    }

