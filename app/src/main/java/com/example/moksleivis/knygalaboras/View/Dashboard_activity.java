package com.example.moksleivis.knygalaboras.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.moksleivis.knygalaboras.Service.BackgroundSoundService;
import com.example.moksleivis.knygalaboras.Model.DatabaseHandler;
import com.example.moksleivis.knygalaboras.Model.Knyga;
import com.example.moksleivis.knygalaboras.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arvyd on 1/20/2018.
 */

public class Dashboard_activity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    Button button;
    DatabaseHandler db = new DatabaseHandler(this);
    List<Knyga> Books;
    private RecyclerView recyclerView;
    private KnygaAdapter mAdapter;
    SearchView searchView = null;
    private Intent svc;
    boolean checkactivity;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkactivity = false;
        setContentView(R.layout.activity_dashboard_activity);
        button = (Button) findViewById(R.id.prideti);
         Books = db.getAllBooks();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new KnygaAdapter(Dashboard_activity.this,Books);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        svc  = new Intent(this,BackgroundSoundService.class);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                int add_item_id = -1;
                checkactivity = true;
                Intent intent1 = new Intent(getBaseContext(), Add_activity.class);
                intent1.putExtra("add_item_id",  add_item_id);
                startActivity(intent1);
                finish();
            }
        });
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
    public void onBackPressed() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
               //adds item to actionbar
                       getMenuInflater().inflate(R.menu.search, menu);
                //get search item from actionbar and get search service
                        MenuItem searchitem = menu.findItem(R.id.actionSearch);
                        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchitem);
                        searchView.setOnQueryTextListener(this);
                return true;
            }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        ArrayList<Knyga> newList = new ArrayList<>();
        List<Knyga> knygos = db.getAllBooks();
        KnygaAdapter knygaAdapter = new KnygaAdapter(Dashboard_activity.this,knygos);

        for(Knyga knyga :knygos){
            String name = knyga.getName().toLowerCase();
            if(name.contains(newText)){
                newList.add(knyga);
            }
        }
      mAdapter.setFilter(newList);
        return true;
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
