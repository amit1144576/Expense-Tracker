package com.example.prototype1;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import static com.example.prototype1.R.id.fab;

public class AddTransactions extends AppCompatActivity {



    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transactions);
        Toolbar toolbar = findViewById(R.id.toolbar);
       // FloatingActionButton fab = findViewById(R.id.fab);
        setSupportActionBar(toolbar);
        firebaseAuth = FirebaseAuth.getInstance();


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
            case R.id.currency:

                Toast.makeText(this,"home clicked",Toast.LENGTH_SHORT).show();

            case R.id.logout: {

                    firebaseAuth.signOut();
                    finish();
                    startActivity(new Intent(this, MainActivity.class));
                    Toast.makeText(this, "User Logout success", Toast.LENGTH_SHORT).show();

            }
        }




        return true;
    }
}
