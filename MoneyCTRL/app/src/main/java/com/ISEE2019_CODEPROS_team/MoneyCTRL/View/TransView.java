package com.ISEE2019_CODEPROS_team.MoneyCTRL.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.ISEE2019_CODEPROS_team.MoneyCTRL.Model.Transactions;
import com.ISEE2019_CODEPROS_team.MoneyCTRL.R;
import com.ISEE2019_CODEPROS_team.MoneyCTRL.ViewModel.TransactionsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class TransView extends AppCompatActivity {

    public static final int ADD_TRANS_REQ = 1;

    // recycler view as follows:

    private TransactionsViewModel transactionsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans_view);


        RecyclerView recyclerView = findViewById(R.id.trans_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final TransactionsAdapter adapter = new TransactionsAdapter();
        recyclerView.setAdapter(adapter);

        transactionsViewModel = ViewModelProviders.of(this).get(TransactionsViewModel.class);
        transactionsViewModel.getAllTransOrderByID().observe(this, new Observer<List<Transactions>>() {
            @Override
            public void onChanged(@Nullable List<Transactions> transactions) {

                adapter.setTransactions(transactions);
            }
        });


        // floating button to start new activity of adding new transaction
        FloatingActionButton buttonAddTrans = findViewById(R.id.button_add_trans);
        buttonAddTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TransView.this, AddNewTrans.class);
                startActivityForResult(intent, ADD_TRANS_REQ);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_TRANS_REQ && resultCode == RESULT_OK) {
            String amount = data.getStringExtra(AddNewTrans.EXTRA_AMOUNT);
            String description = data.getStringExtra(AddNewTrans.EXTRA_DESCRIPTION);
            int t_date = data.getIntExtra(AddNewTrans.EXTRA_DATE,1);

            // Transactions trans = new Transactions(amount, description, priority);
            //transactionsViewModel.insert(trans);

            Toast.makeText(this, "Transaction saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Transaction not saved", Toast.LENGTH_SHORT).show();
        }
    }



    // menu option relevant code
    // Toast.makers should be replaced with relevant code to open new pages

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_menu, menu);
        return true;
    }


    // navigation to menu items
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

    // filter transactions on transactions view list based on date
    public void filterDuration(View view) {
        RadioGroup radioGroup = findViewById(R.id.group_trans_list_duration);
        switch (radioGroup.getCheckedRadioButtonId()){
            case R.id.view_30days:
                Toast.makeText(this,"last 30 days displayed",Toast.LENGTH_SHORT).show();
                break;

            case R.id.view_90days:
                Toast.makeText(this,"last 90 days displayed",Toast.LENGTH_SHORT).show();
                break;

            case R.id.view_180days:
                Toast.makeText(this,"last 180 days displayed",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    // sort transactions on transactions view list
    public void sortTransactions(View view) {
        RadioGroup radioGroup = findViewById(R.id.group_sort_trans);
        switch (radioGroup.getCheckedRadioButtonId()){
            case R.id.latest_top:
                Toast.makeText(this,"Sorted on Descending Order",Toast.LENGTH_SHORT).show();
                break;

            case R.id.oldest_top:
                Toast.makeText(this,"Sorted on Ascending Order",Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
