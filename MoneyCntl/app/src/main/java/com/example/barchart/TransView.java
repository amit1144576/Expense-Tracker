package com.example.barchart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class TransView extends AppCompatActivity {

    private DatabaseViewModel databaseViewModel;

    private FloatingActionButton addNewTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /*
        addNewTransaction = findViewById(R.id.add_transaction);
        addNewTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityNewTrans();
            }
        });
        */


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_trans_view);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final TransactionsAdapter adapter = new TransactionsAdapter();

        databaseViewModel = ViewModelProviders.of(this).get(DatabaseViewModel.class);
        databaseViewModel.getAllDatabase().observe(this, new Observer<List<Transactions>>() {
            @Override
            public void onChanged(List<Transactions> transactions) {
                adapter.setTransactions(transactions);
            }
        });
    }

    /*
    public void openActivityNewTrans(){

        Intent intent = new Intent(this, AddNewTransaction.class);
        startActivity(intent);
    }
    */

}
