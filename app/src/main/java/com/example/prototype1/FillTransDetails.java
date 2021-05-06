package com.example.prototype1;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Transaction;

import java.util.Calendar;


public class FillTransDetails extends AppCompatActivity implements View.OnClickListener {

    //DatabaseReference databaseTransactions;
    DatabaseHelper myDB;



    private static final String TAG = "FillTransDetails";
     public String statusSwitch;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private Button buttonAdd;
    private Button buttonShowAddedTrans;
    private EditText editTextAmount;
    private Spinner spinnerCategory;
    private EditText editTextDescription;
    private Switch switchRecurring;
    private TextView textViewChooseDate;
    private Spinner spinnerPaymentType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        myDB = new DatabaseHelper(this);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_trans_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        spinnerCategory = (Spinner)findViewById(R.id.spinnerCategory);
        spinnerPaymentType = (Spinner)findViewById(R.id.spinnerPaymentType);

        mDisplayDate = (TextView) findViewById(R.id.textViewChooseDate);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);

        editTextAmount =(EditText) findViewById(R.id.editTextAmount);
        editTextDescription = (EditText) findViewById(R.id.editTextDescription);
        switchRecurring = (Switch) findViewById(R.id.switchRecurring);
        textViewChooseDate = (TextView) findViewById(R.id.textViewChooseDate);

        findViewById(R.id.buttonAdd).setOnClickListener(this);


        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        FillTransDetails.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };

      //addTransaction();
        //viewTransactions();



    }


    private void switchStatus(){

        if (switchRecurring.isChecked())
            statusSwitch = switchRecurring.getTextOn().toString();
        else
            statusSwitch = switchRecurring.getTextOff().toString();

    }

    private void addTransaction(){

                     Toast.makeText(FillTransDetails.this,"Add clicked",Toast.LENGTH_LONG).show();
                      boolean isInserted =  myDB.insertData(Float.parseFloat(editTextAmount.getText().toString()),
                               spinnerPaymentType.getSelectedItem().toString().trim(),
                               spinnerCategory.getSelectedItem().toString().trim(),
                               editTextDescription.getText().toString().trim(),
                               mDisplayDate.getText().toString(),
                              switchRecurring.isChecked());

                      if(isInserted=true)
                          Toast.makeText(FillTransDetails.this,"Transaction added.",Toast.LENGTH_LONG).show();

                      else

                          Toast.makeText(FillTransDetails.this,"Transaction not added.",Toast.LENGTH_LONG).show();


    }





    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case    R.id.buttonAdd:
                addTransaction();
                break;

        }
    }
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


    public void showMessage(String title,String Message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this );
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }*/

