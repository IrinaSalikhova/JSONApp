package com.cst3104.jsonapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder{
    TextView nameRest;
    TextView descRest;
    ImageView restImage;

    //View will be a ConstraintLayout
    public MyViewHolder(View thisRow) {
        super(thisRow);

         nameRest = thisRow.findViewById(R.id.nameText);
         descRest = thisRow.findViewById(R.id.descriptionText);
         restImage = thisRow.findViewById(R.id.imageView);

        thisRow.setOnClickListener( click -> {
            int position = getAdapterPosition();//which row was clicked.
        });
    }
}