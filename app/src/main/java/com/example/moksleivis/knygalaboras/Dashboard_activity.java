package com.example.moksleivis.knygalaboras;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by arvyd on 1/20/2018.
 */

public class Dashboard_activity extends AppCompatActivity {

    Button button;
    Button istrinti;
    EditText id;
    ListView lv;
    ArrayAdapter<Knyga> adapter;
    DatabaseHandler db = new DatabaseHandler(this);
    private ArrayAdapter<String> listAdapter ;
    private ListView mainListView ;
    List<Knyga> Books;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_activity);

        button = (Button) findViewById(R.id.prideti);
        istrinti = (Button) findViewById(R.id.istrinti);
        mainListView = (ListView) findViewById( R.id.listView1 );
        id = (EditText) findViewById(R.id.id);

         Books = db.getAllBooks();
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,Books);
        lv= (ListView) findViewById(R.id.listView1);
        //sudeda i listviewa irasus
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(getBaseContext(), singleitem_activity.class);
                intent.putExtra("item_id",  position);
                startActivity(intent);
            }

        }

    );

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                startActivity(new Intent(Dashboard_activity.this, Add_activity.class));
            }
        });




        istrinti.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
            db.deleteBooks(id.getText().toString());
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }
        });








    }



}
