package com.example.moksleivis.knygalaboras;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by arvyd on 2/14/2018.
 */

public class KnygaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    public List<Knyga>Knygos = Collections.emptyList();


    public KnygaAdapter(Context context,List<Knyga> KnygaList) {
        this.Knygos = KnygaList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
               View view = LayoutInflater.from(context).inflate(R.layout.row,parent,false);
        MyHolder holder = new MyHolder(view);
                return holder;
            }



    @Override
    public void  onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyHolder holder1 = (MyHolder) holder;
        Knyga knyga = Knygos.get(position);
        holder1.name.setText(knyga.getName());
        holder1.genre.setText("Genre : "+ knyga.getGenre());
        holder1.release_year.setText("Release Date : "+ knyga.getRelease_year());
        holder1.author.setText("Author : " + knyga.getAuthor());
        holder1.pages.setText("Pages : " + knyga.getPages());

    }

    @Override
    public int getItemCount() {
        return Knygos.size();
    }
    public void setFilter (List<Knyga> newList){
        Knygos = new ArrayList<>();
        Knygos.addAll(newList);
        notifyDataSetChanged();

    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView name, release_year, author,genre,pages;
               public MyHolder(View itemView){
                       super(itemView);

                   name = (TextView) itemView.findViewById(R.id.title);
                   release_year = (TextView) itemView.findViewById(R.id.year);
                   author = (TextView) itemView.findViewById(R.id.author);
                   genre = (TextView) itemView.findViewById(R.id.genre);
                   pages = (TextView) itemView.findViewById(R.id.pages);
                      itemView.setOnClickListener(this);
                   }

               @Override
        public void onClick(View v) {
                   Toast.makeText(context,"veikia0",Toast.LENGTH_LONG).show();
                   int itemPosition = getAdapterPosition();
                            // TODO: siųsti pasirinkto pokemono objektą vietoj id
                   Intent intent = new Intent(context, Add_activity.class);
                   intent.putExtra("item_id_position",  itemPosition);
                   intent.putExtra("item_id",  Knygos.get(itemPosition).getId());
                   context.startActivity(intent);
                    }

    }







}
