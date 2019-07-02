package com.example.prototype1;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.security.PublicKey;

public class currency extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public String currencyValue;

    private Spinner spinner;
    private static final String[] paths = {"EUR", "USD","POUND","AUD","CAD"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);
        spinner = (Spinner)findViewById(R.id.spinCurrency);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(currency.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {

        case 0:
        // Whatever you want to happen when the first item gets selected
            currencyValue= spinner.getItemAtPosition(position).toString();
            //Toast.makeText(this,currencyValue.toString(),Toast.LENGTH_SHORT).show();
        break;
        case 1:
            currencyValue= spinner.getItemAtPosition(position).toString();
            //Toast.makeText(this,currencyValue.toString(),Toast.LENGTH_SHORT).show();
        // Whatever you want to happen when the second item gets selected
        break;

    }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        spinner.setPrompt("Select currency");

    }
}
