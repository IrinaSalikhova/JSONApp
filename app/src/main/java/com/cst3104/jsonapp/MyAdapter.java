package com.cst3104.jsonapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter< MyViewHolder > {
    ArrayList<Restaurant> restaurants ;
    Context mycontext;


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        mycontext = parent.getContext();
        View thisRow = LayoutInflater.from(mycontext).inflate(R.layout.message_layout, parent, false);

        return new MyViewHolder( thisRow );
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder thisRow, int position) {
        Restaurant actualRest = restaurants.get(position);
        thisRow.nameRest.setText(actualRest.getName());
        thisRow.descRest.setText(actualRest.getDescription());

        int id = mycontext.getResources().getIdentifier(actualRest.getImage(), "drawable", mycontext.getPackageName());
        thisRow.restImage.setImageResource(id);

    }


    @Override
    public int getItemCount() {
        return restaurants.size();
    }
}
