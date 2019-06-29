package com.example.prototype1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Add_category extends AppCompatActivity implements View.OnClickListener {


    static String newCat;
    public Button buttonAddCategory;
    public EditText editTextCategory;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        buttonAddCategory = (Button) findViewById(R.id.buttonAddCategory);
        editTextCategory = (EditText) findViewById(R.id.editTextCategory);
        findViewById(R.id.buttonAddCategory).setOnClickListener(this);


    }


    public void onClick(View v) {



        switch (v.getId()) {

            case R.id.buttonAddCategory: {
                newCat = editTextCategory.getText().toString().trim();
                finish();
                Intent intent = new Intent(Add_category.this,AddTransactions.class);
                startActivity(intent);
                break;


            }
        }

    }}


