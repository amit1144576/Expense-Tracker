package com.example.prototype1;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prototype1.DatabaseClasses.MyDataBaseClass;
import com.google.android.gms.tasks.OnCompleteListener;


import com.google.firebase.auth.FirebaseAuth;

import static com.example.prototype1.R.id.fab;

public class AddTransactions extends AppCompatActivity implements View.OnClickListener{



    MyDataBaseClass objMyDataBaseClass;
    private FirebaseAuth firebaseAuth;

    private Button buttonShowAddedTrans;
    private TextView showValues;
    private Button categorical_chart;
    private Button bar_chart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_transactions);
        Toolbar toolbar = findViewById(R.id.toolbar);
        buttonShowAddedTrans = (Button) findViewById(R.id.buttonShowAddedTrans);

        setSupportActionBar(toolbar);


       //fab.setOnClickListener(this);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(AddTransactions.this, "fab is clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddTransactions.this,FillTransDetails.class);
                startActivity(intent);

            }
        });

        findViewById(R.id.buttonShowAddedTrans).setOnClickListener(this);



        bar_chart = findViewById(R.id.bar_chart);
        bar_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBarChart();
            }
        });


        categorical_chart = findViewById(R.id.categorical_chart);
        categorical_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCategoricalChart();
            }
        });
    }

    public void openBarChart(){
        Intent intent = new Intent(this,BudgetChart.class);
        startActivity(intent);
    }

    public void openViewTransactions(){
        Intent intent = new Intent(this,FillTransDetails.class);
        startActivity(intent);
    }

    public void openCategoricalChart(){
        Intent intent = new Intent(this, CategoricalChart.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_items,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id)
        {


            case R.id.category: {


               finish();
                Intent intent = new Intent(AddTransactions.this,Add_category.class);
                startActivity(intent);

                break;

            }


            case R.id.setting: {
                //Toast.makeText(this, "setting is clicked", Toast.LENGTH_SHORT).show();
                break;
            }

            case R.id.currency: {
                //Toast.makeText(this, "Currency is clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, currency.class));
                break;
            }

            case R.id.logout: {

                   firebaseAuth.signOut();
                   finish();
                   startActivity(new Intent(this, MainActivity.class));
                    Toast.makeText(this, "User Logout success", Toast.LENGTH_SHORT).show();
                    break;

            }
        }



        return true;
    }











    @Override
    public void onClick(View v) {

        switch (v.getId()) {



            case R.id.buttonShowAddedTrans: {

                Toast.makeText(this, "Button clicked", Toast.LENGTH_LONG).show();

                startActivity(new Intent(this, ShowValues.class));


                break;

            }
        }

}
}

