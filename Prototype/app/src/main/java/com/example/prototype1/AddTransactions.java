package com.example.prototype1;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.prototype1.DatabaseClasses.MyDataBaseClass;
import com.google.android.gms.tasks.OnCompleteListener;


import com.google.firebase.auth.FirebaseAuth;

import static com.example.prototype1.R.id.buttonFilter;
import static com.example.prototype1.R.id.fab;

public class AddTransactions extends AppCompatActivity {


    MyDataBaseClass myDB1;
    private FirebaseAuth firebaseAuth;

    Button buttonShowAddedTrans;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transactions);
        myDB1 = new MyDataBaseClass(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        //buttonShowAddedTrans = (Button) findViewById(R.id.buttonShowAddedTrans);
        button = (Button) findViewById(R.id.buttonFilter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent filterIntent = new Intent(AddTransactions.this,filterByDate.class);
                startActivity(filterIntent);
            }
        });


        // FloatingActionButton fab = findViewById(R.id.fab);
        setSupportActionBar(toolbar);
        firebaseAuth = FirebaseAuth.getInstance();

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
            case R.id.setting: {
                //Toast.makeText(this, "setting is clicked", Toast.LENGTH_SHORT).show();
                break;
            }

            case R.id.currency: {
                //Toast.makeText(this, "Currency is clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, currency.class));
                break;
            }
            case R.id.filter: {
                //Toast.makeText(this, "Currency is clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, filterByDate.class));
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



      /*private void viewTransactions(){


        buttonShowAddedTrans.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Cursor res = myDB.getAllData();

                        if(res.getCount()==0){
                           showMessage("Error","No data found.");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();

                        while (res.moveToNext()){

                            buffer.append("Amount : " + res.getString(1)+"\n");
                            buffer.append("Payment : " + res.getString(2)+"\n");
                            buffer.append("Category : " + res.getString(3)+"\n");
                            buffer.append("Date : " + res.getString(5)+"\n");
                            buffer.append("Comments : " + res.getString(4)+"\n\n");

                        }

                        //Show all data
                       showMessage("Transactions",buffer.toString());

                    }

                }
        );


        }
*/

/*
    public void showMessage(String title,String Message){

        AlertDialog.Builder builder =  new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }
*/


/*    @Override
    public void onClick(View v) {

        switch (v.getId()) {



            case R.id.buttonShowAddedTrans: {

                Toast.makeText(AddTransactions.this,"Button clicked",Toast.LENGTH_LONG).show();

                DatabaseHelper db1 = new DatabaseHelper(this);

                Toast.makeText(AddTransactions.this,"Button clicked",Toast.LENGTH_LONG).show();

                Cursor res = db1.getAllData();

                if (res.getCount() == 0) {
                    showMessage("Error", "No data found.");
                    return;
                }

                StringBuffer buffer = new StringBuffer();

                while (res.moveToNext()) {

                    buffer.append("ID : " + res.getString(0) + "\n");
                    buffer.append("Amount : " + res.getString(1) + "\n");
                    buffer.append("Payment : " + res.getString(2) + "\n");
                    buffer.append("Category : " + res.getString(3) + "\n");
                    buffer.append("Date : " + res.getString(5) + "\n");
                    buffer.append("Recurring : " + res.getString(6) + "\n");
                    buffer.append("Comments : " + res.getString(4) + "\n\n");

                }

                //Show all data
                showMessage("Transactions", buffer.toString());
                break;

            }
        }

}*/
}

