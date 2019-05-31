package com.example.barchart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import java.util.List;

public class TransView extends AppCompatActivity {


    // recycler view as follows:

    private TransactionsViewModel transactionsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans_view);

        RecyclerView recyclerView = findViewById(R.id.trans_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final TransactionsAdapter adapter = new TransactionsAdapter();
        recyclerView.setAdapter(adapter);

        transactionsViewModel = ViewModelProviders.of(this).get(TransactionsViewModel.class);
        transactionsViewModel.getAllTransOrderByAmount().observe(this, new Observer<List<Transactions>>() {
            @Override
            public void onChanged(List<Transactions> transactions) {

                adapter.setTransactions(transactions);
            }
        });
    }


    // menu option relevant code
    // Toast.makers should be replaced with relevant code to open new pages

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.set_currency:
                Toast.makeText(this, "currency clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.change_password:
                Toast.makeText(this, "change_password clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.categorical_charts:
                Toast.makeText(this, "categorical_charts clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.income_expense:
                Toast.makeText(this, "Income graph clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.set_exit:
                Toast.makeText(this, "Exit clicked", Toast.LENGTH_SHORT).show();
                return true;
             default:
                 return super.onOptionsItemSelected(item);
        }

    }
}
