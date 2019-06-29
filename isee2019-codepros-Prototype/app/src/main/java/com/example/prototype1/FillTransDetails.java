package com.example.prototype1;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;


import com.example.prototype1.DatabaseClasses.MyDataBaseClass;

import java.util.ArrayList;
import java.util.Calendar;


public class FillTransDetails extends AppCompatActivity {

    MyDataBaseClass objMyDataBaseClass;
    public static ArrayList<String> aList;
    private static final String TAG = "FillTransDetails";
    public String statusSwitch;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private Button buttonAdd;
    private Button buttonShowAddedTrans;
    private EditText editTextAmount;
    public static Spinner spinnerCategory;
    private EditText editTextDescription;
    private Switch switchRecurring;
    private TextView textViewChooseDate;
    private Spinner spinnerPaymentType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_fill_trans_details);
        objMyDataBaseClass = new MyDataBaseClass(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        spinnerCategory = (Spinner) findViewById(R.id.spinnerCategory);
        spinnerPaymentType = (Spinner) findViewById(R.id.spinnerPaymentType);

        mDisplayDate = (TextView) findViewById(R.id.textViewChooseDate);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        editTextAmount = (EditText) findViewById(R.id.editTextAmount);
        editTextDescription = (EditText) findViewById(R.id.editTextDescription);
        textViewChooseDate = (TextView) findViewById(R.id.textViewChooseDate);



       // findViewById(R.id.buttonAdd).setOnClickListener(this);


        ArrayList<String> aList = new ArrayList<String>();
        aList.add("Food \n");
        aList.add("Travel \n");
        aList.add("Shopping \n ");
        aList.add("Movies \n");
        //aList.add(Add_category.newCat+"\n");


        spinnerCategory.setAdapter(new SpinnerAdapter1(this, R.layout.spinner_row, aList, aList));
        aList.add(Add_category.newCat + "\n");
        // SpinnerAdapter1.notifyDataSetChanged();


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
                        year, month, day);
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


    }


    public void createDatabase(View view)

    {

        try
        {

            objMyDataBaseClass.getReadableDatabase();
            Toast.makeText(this, "Database created successfully", Toast.LENGTH_LONG).show();
        }

        catch (Exception e)
        {

            Toast.makeText(this, "Error while creating database", Toast.LENGTH_LONG).show();
        }

    }


    public void insertValuesIntoDatabases(View view)
    {

        try {


            SQLiteDatabase objSQLiteDatabase = objMyDataBaseClass.getWritableDatabase();

            if(objSQLiteDatabase != null)
            {
                if(!editTextAmount.getText().toString().trim().isEmpty()&&!mDisplayDate.getText().toString().trim().isEmpty())
                {

                    ContentValues objContentValues = new ContentValues();

                    objContentValues.put("Amount",Integer.parseInt(editTextAmount.getText().toString()));
                    objContentValues.put("Payment",spinnerPaymentType.getSelectedItem().toString());
                    objContentValues.put("Category",spinnerCategory.getSelectedItem().toString());
                    objContentValues.put("Date",mDisplayDate.getText().toString());
                    objContentValues.put("Description",editTextDescription.getText().toString());


                    Long checkIfQueryRuns= objSQLiteDatabase.insert("TransactionsTable",null,objContentValues);

                    if(checkIfQueryRuns!=-1)

                    {

                        Toast.makeText(this, "Values inserted into database successfully", Toast.LENGTH_LONG).show();
                        editTextAmount.setText(null);
                        editTextDescription.setText(null);
                        mDisplayDate.setText(null);

                    }

                    else
                    {
                        Toast.makeText(this, "Error while inserting values..", Toast.LENGTH_LONG).show();

                    }
                }

                else
                {

                    Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_LONG).show();
                }


            }

            else

            {
                Toast.makeText(this, "Database is null", Toast.LENGTH_LONG).show();

            }

        }

        catch (Exception e)

        {

        }
    }


    private void switchStatus() {

        if (switchRecurring.isChecked())
            statusSwitch = switchRecurring.getTextOn().toString();
        else
            statusSwitch = switchRecurring.getTextOff().toString();

    }

    private void addTransaction() {

        if(editTextAmount.getText().toString().trim().isEmpty()){

            editTextAmount.setError("Amount is required");
            editTextAmount.requestFocus();
            return;

        }




        if(mDisplayDate.getText().toString().trim().isEmpty()){

            mDisplayDate.setError("Transaction date is required");
            mDisplayDate.requestFocus();
            return;
        }



    }



    //selecting categories of expenses, the toast messages need to be changed later into some other operations

    public void selectExpenseCategory(View view) {
        RadioGroup radioGroup = findViewById(R.id.group_expense_category);
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.expense_category_1:
                Toast.makeText(this, "Expense category selected", Toast.LENGTH_SHORT).show();
                break;

            case R.id.expense_category_2:
                Toast.makeText(this, "Income category selected", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    //selecting categories of expenses, the toast messages need to be changed later into some other operations

    public void selectRecurring(View view) {
        RadioGroup radioGroup = findViewById(R.id.group_recurring);
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.recurring1:
                Toast.makeText(this, "will not repeat", Toast.LENGTH_SHORT).show();
                break;

            case R.id.recurring2:
                Toast.makeText(this, "will repeat daily", Toast.LENGTH_SHORT).show();
                break;

            case R.id.recurring3:
                Toast.makeText(this, "will repeat weekly", Toast.LENGTH_SHORT).show();
                break;

            case R.id.recurring4:
                Toast.makeText(this, "will repeat monthly", Toast.LENGTH_SHORT).show();
                break;

        }
    }

}




