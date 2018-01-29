package com.example.moksleivis.knygalaboras;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by arvyd on 1/20/2018.
 */

public class Dashboard_activity extends AppCompatActivity {

    Button button;
    private ArrayAdapter<String> listAdapter ;
    private ListView mainListView ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_activity);

        button = (Button) findViewById(R.id.prideti);
        mainListView = (ListView) findViewById( R.id.listView1 );

        DatabaseHandler db = new DatabaseHandler(this);
        List<Knyga> Books = db.getAllBooks();
        ArrayAdapter<Knyga> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,Books);
        ListView lv= (ListView) findViewById(R.id.listView1);
        //sudeda i listviewa irasus
        lv.setAdapter(adapter);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                startActivity(new Intent(Dashboard_activity.this, Add_activity.class));
            }
        });










    }



}
