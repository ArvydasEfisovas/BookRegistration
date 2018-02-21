package com.example.moksleivis.knygalaboras;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
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

import java.util.ArrayList;
import java.util.Collections;
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


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_activity);
        button = (Button) findViewById(R.id.prideti);
         Books = db.getAllBooks();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new KnygaAdapter(Dashboard_activity.this,Books);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                int add_item_id = -1;
                Intent intent1 = new Intent(getBaseContext(), Add_activity.class);
                intent1.putExtra("add_item_id",  add_item_id);
                startActivity(intent1);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
               //adds item to actionbar
                       getMenuInflater().inflate(R.menu.search, menu);
                //get search item from actionbar and get search service
                        MenuItem searchitem = menu.findItem(R.id.actionSearch);
                        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchitem);
                        searchView.setOnQueryTextListener(this);

              /*  SearchManager searchManger = (SearchManager) Dashboard_activity.this.getSystemService(Context.SEARCH_SERVICE);
                if (searchitem != null) {
                        searchView = (SearchView) searchitem.getActionView();
                   }
                if (searchView != null) {
                        searchView.setSearchableInfo(searchManger.getSearchableInfo(Dashboard_activity.this.getComponentName()));
                        searchView.setIconified(false);
                    }*/
                return true;
            }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
               return super.onOptionsItemSelected(item);
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


   /* @Override
   //every time when you press search button an actvity is recreated which in turn
           //calls this function
            protected void onNewIntent(Intent intent) {
                //get search query and create object of class AsyncFetch
                        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
                        String query = intent.getStringExtra(SearchManager.QUERY);
                        if (searchView != null) {
                                searchView.clearFocus();
                            }
                        new AsyncFetch(query).execute();
                    }
            }

    class AsyncFetch extends AsyncTask<String, String, String> {
        ProgressDialog progressDialog = new ProgressDialog(Dashboard_activity.this);
        String searchQuery;
        List<Knyga> knygos = Collections.emptyList();

                public AsyncFetch(String searchQuery) {
                        this.searchQuery = searchQuery;
                    }

                @Override
        protected void onPreExecute() {
                        super.onPreExecute();
                        progressDialog.setMessage("Prašome palaukti");
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                    }

                @Override
        protected String doInBackground(String... params) {

                                DatabaseHandler db = new DatabaseHandler(Dashboard_activity.this);


                                        knygos = db.getAllBooks();
                        if (knygos.isEmpty()) {
                                return "no rows";
                           } else {
                                return "rows";
                            }
                    }

                @Override
        protected void onPostExecute(String result) {
                        progressDialog.dismiss();
                        if (result.equals("no rows")) {
                                Toast.makeText(Dashboard_activity.this, "Pagal paiešką nerasta duomenų", Toast.LENGTH_LONG).show();
                            } else {
                                //setup and hand over list pokemonai to recyclerview
                                        RecyclerView rvPokemonai = (RecyclerView) findViewById(R.id.recycler_view);
                                KnygaAdapter knygaAdapter = new KnygaAdapter(Dashboard_activity.this, knygos);
                               rvPokemonai.setAdapter(knygaAdapter);
                                rvPokemonai.setLayoutManager(new LinearLayoutManager(Dashboard_activity.this));
                              }
                }
    }
*/



}
