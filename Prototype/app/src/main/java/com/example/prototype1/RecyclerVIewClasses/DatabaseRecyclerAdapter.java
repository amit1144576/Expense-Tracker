package com.example.prototype1.RecyclerVIewClasses;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.prototype1.R;

import java.util.ArrayList;

public class DatabaseRecyclerAdapter extends RecyclerView.Adapter<DatabaseRecyclerAdapter.DatabaseViewHolder> {

   ArrayList<ModelClass> ar


    @NonNull
    @Override
    public DatabaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DatabaseViewHolder databaseViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class DatabaseViewHolder extends  RecyclerView.ViewHolder{

        TextView amount,date,category,id,payment,desc;



        public DatabaseViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.rv_id);
            amount = itemView.findViewById(R.id.rv_amount);
            date = itemView.findViewById(R.id.rv_date);
            category = itemView.findViewById(R.id.rv_category);
            payment = itemView.findViewById(R.id.rv_payment);
            desc = itemView.findViewById(R.id.rv_desc);

        }
    }


}
