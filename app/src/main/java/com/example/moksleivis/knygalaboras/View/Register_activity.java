package com.example.moksleivis.knygalaboras.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moksleivis.knygalaboras.Service.BackgroundSoundService;
import com.example.moksleivis.knygalaboras.Model.DatabaseHandler;
import com.example.moksleivis.knygalaboras.Model.User;
import com.example.moksleivis.knygalaboras.R;
import com.example.moksleivis.knygalaboras.Controller.Validation;

/**
 * Created by moksleivis on 2018-01-19.
 */
    public class Register_activity extends AppCompatActivity {

    private Button Register;
    EditText mEdit;
    EditText mEdit2;
    EditText mEdit3;
    EditText mEdit4;
    boolean checkactivity;
    Intent svc;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            checkactivity = false;
            svc  = new Intent(this,BackgroundSoundService.class);
            setContentView(R.layout.activity_register_activity);
            mEdit   = (EditText)findViewById(R.id.register_name);
            mEdit2   = (EditText)findViewById(R.id.register_password);
            mEdit3   = (EditText)findViewById(R.id.register_repeat_password);
            mEdit4   = (EditText)findViewById(R.id.register_email);
            Register = (Button)findViewById(R.id.Register);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
                        checkactivity = true;
                        startActivity(new Intent(Register_activity.this, Login_activity.class));
                        finish();
                    }
                }
            });
        }
    @Override
    public void onBackPressed() {

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                checkactivity = true;
                Intent intent2 = new Intent(getBaseContext(), Login_activity.class);
                startActivity(intent2);
                finish();
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
}

