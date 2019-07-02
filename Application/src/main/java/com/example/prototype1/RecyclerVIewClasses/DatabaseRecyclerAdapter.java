package com.example.prototype1.RecyclerVIewClasses;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.prototype1.R;

import java.util.ArrayList;

public class DatabaseRecyclerAdapter extends RecyclerView.Adapter<DatabaseRecyclerAdapter.DatabaseViewHolder> {

   ArrayList<ModelClass> objModelClassArrayList;

    public DatabaseRecyclerAdapter(ArrayList<ModelClass> objModelClassArrayList) {
        this.objModelClassArrayList = objModelClassArrayList;
    }

    @NonNull
    @Override
    public DatabaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View singleRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_row,viewGroup,false);
        return new DatabaseViewHolder(singleRow);

    }

    @Override
    public void onBindViewHolder(@NonNull DatabaseViewHolder databaseViewHolder, int i) {


        ModelClass objModelClass = objModelClassArrayList.get(i);
        databaseViewHolder.id.setText(Integer.toString(objModelClass.getID()));
        databaseViewHolder.amount.setText(String.valueOf(objModelClass.getAmount()));
        databaseViewHolder.date.setText(objModelClass.getDate());
        databaseViewHolder.payment.setText(objModelClass.getPayment_method());
        databaseViewHolder.category.setText(objModelClass.getCategory());
        databaseViewHolder.desc.setText(objModelClass.getDescription());
    }

    @Override
    public int getItemCount() {
        return objModelClassArrayList.size();
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
