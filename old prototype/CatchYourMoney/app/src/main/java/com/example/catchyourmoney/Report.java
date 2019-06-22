package com.example.catchyourmoney;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

public class Report extends AppCompatActivity {
    private TextView TextInfo;
    private TextView Textamount;
    private EditText editAmount;
    private TextView Date1, Date2;
    private RecyclerView mRecyclerView;
    private reportListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<reportItems> reportList = new ArrayList<>();

    Spinner spinner, spinner2, spinner3;
    Button filter, showChart;

    DatePickerDialog dpd;

    int dayFrom = 0, monthFrom = 0, yearFrom = 0, dayTo = 0, monthTo = 0, yearTo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Textamount = findViewById(R.id.textView2);
        String[] categories = getCategories();
        editAmount = findViewById(R.id.editAmount);
        Date1 = findViewById(R.id.textDate1);
        Date2 = findViewById(R.id.textDate2);
        showChart = findViewById(R.id.showChart);


        /**
         * Filling the spinners with their respective values
         */

        spinner = findViewById(R.id.spinType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.types, android.R.layout.simple_list_item_1);

        spinner2 = findViewById(R.id.spinCategory);
        ArrayAdapter<CharSequence> adapter2 = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_list_item_1, categories);

        spinner3 = findViewById(R.id.spinMethod);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.methods, android.R.layout.simple_list_item_1);

        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter2);
        spinner3.setAdapter(adapter3);

        /**
         *  Calendar dates
         */

        Date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                int mMonth = c.get(Calendar.MONTH);
                int mYear = c.get(Calendar.YEAR);
                Calendar helper = Calendar.getInstance();
                helper.setTimeInMillis(System.currentTimeMillis());

                dpd = new DatePickerDialog(Report.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Date1.setText(dayOfMonth + "." + (month + 1) + "." + year);
                        dayFrom = dayOfMonth;
                        monthFrom = month + 1;
                        yearFrom = year;
                    }
                }, mDay, mMonth, mYear);
                dpd.updateDate(mYear, mMonth, mDay);
                helper.add(Calendar.DAY_OF_MONTH, -1);
                dpd.getDatePicker().setMaxDate(helper.getTimeInMillis());
                dpd.show();
            }
        });

        Date2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);
                Calendar helper = Calendar.getInstance();
                helper.setTimeInMillis(System.currentTimeMillis());

                dpd = new DatePickerDialog(Report.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Date2.setText(dayOfMonth + "." + (month + 1) + "." + year);
                        dayTo = dayOfMonth;
                        monthTo = month + 1;
                        yearTo = year;
                    }
                }, day, month, year);
                helper.set(Calendar.DAY_OF_MONTH, dayFrom);
                helper.set(Calendar.MONTH, monthFrom - 1);
                helper.set(Calendar.YEAR, yearFrom);
                dpd.getDatePicker().setMinDate(helper.getTimeInMillis());
                dpd.getDatePicker().setMaxDate(System.currentTimeMillis());
                dpd.show();
            }
        });

        /**
         * Filtering
         */
        filter = findViewById(R.id.filter);

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Helpers:
                String mType = spinner.getSelectedItem().toString(),
                        mCategory = spinner2.getSelectedItem().toString(),
                        mMethod = spinner3.getSelectedItem().toString();
                float mAmount;
                List<Transactions> transType = null;
                if (editAmount.getText().toString().equals("")) {
                    mAmount = 0;
                } else {
                    mAmount = Float.parseFloat(editAmount.getText().toString());
                }

                if (mCategory.equals("") && mMethod.equals("") && dayFrom == 0 && dayTo == 0) {
                    transType = Home.appDB.appDao().getTransaction(mType, mAmount);
                } else if (!mCategory.equals("") && mMethod.equals("") && dayFrom == 0 && dayTo == 0) {
                    transType = Home.appDB.appDao().getTransactionC(mType, mCategory, mAmount);
                } else if (mCategory.equals("") && !mMethod.equals("") && dayFrom == 0 && dayTo == 0) {
                    transType = Home.appDB.appDao().getTransaction(mType, mMethod, mAmount);
                } else if (mCategory.equals("") && mMethod.equals("") && dayFrom != 0 && dayTo != 0) {
                    transType = Home.appDB.appDao().getTransaction(mType, mAmount, dayFrom, monthFrom,
                            yearFrom, dayTo, monthTo, yearTo);
                } else if (!mCategory.equals("") && !mMethod.equals("") && dayFrom == 0 && dayTo == 0) {
                    transType = Home.appDB.appDao().getTransaction(mType, mCategory, mMethod, mAmount);
                } else if (!mCategory.equals("") && mMethod.equals("") && dayFrom != 0 && dayTo != 0) {
                    transType = Home.appDB.appDao().getTransactionC(mType, mCategory, mAmount, dayFrom, monthFrom,
                            yearFrom, dayTo, monthTo, yearTo);
                } else if (mCategory.equals("") && !mMethod.equals("") && dayFrom != 0 && dayTo != 0) {
                    transType = Home.appDB.appDao().getTransaction(mType, mMethod, mAmount, dayFrom, monthFrom,
                            yearFrom, dayTo, monthTo, yearTo);
                } else if (!mCategory.equals("") && !mMethod.equals("") && dayFrom != 0 && dayTo != 0) {
                    transType = Home.appDB.appDao().getTransaction(mType, mCategory, mMethod, mAmount, dayFrom, monthFrom,
                            yearFrom, dayTo, monthTo, yearTo);
                } else if ((dayFrom != 0 && dayTo == 0) || (dayFrom == 0 && dayTo != 0)) {
                    Toast.makeText(getApplicationContext(), "You need to add the range of the date", Toast.LENGTH_SHORT).show();
                }

                if (transType != null) {
                    reportList.clear();
                    float Sum = 0;
                    for (Transactions transactions1 : transType) {
                        String id = transactions1.getTransactionID();
                        String type = transactions1.getType();
                        String categ = transactions1.getCategory();
                        int day = transactions1.getDay();
                        int month = transactions1.getMonth();
                        int year = transactions1.getYear();
                        String method = transactions1.getMethod();
                        String comment = transactions1.getComment();
                        float amount = transactions1.getAmount();
                        int jobId = transactions1.getRecurrentJob();
                        String Date = day + "." + month + "." + year;
                        Sum += amount;

                        reportList.add(new reportItems(R.drawable.ic_android,type,"Category: \n" + categ,
                                "Payment: \n" + method,"Amount: \n" + amount,
                                Date,"Recurrent: " + jobId,comment,id));
                    }

                    buildRecyclerView(reportList);
                    Textamount.setText(Float.toString(Sum));
                }
                Date1.setText("Click to select");
                Date2.setText("Click to select");
                dayFrom = 0;
                dayTo = 0;
                monthFrom = 0;
                monthTo = 0;
                yearFrom = 0;
                yearTo = 0;
            }
        });

        showChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chart = new Intent(Report.this, PChart.class);
                if (dayFrom != 0 && dayTo != 0) {
                    chart.putExtra("dayFrom", dayFrom);
                    chart.putExtra("dayTo", dayTo);
                    chart.putExtra("monthFrom", monthFrom);
                    chart.putExtra("monthTo", monthTo);
                    chart.putExtra("yearFrom", yearFrom);
                    chart.putExtra("yearTo", yearTo);
                    startActivity(chart);
                }else{
                    Toast.makeText(getApplicationContext(), "You need to add the range of the date", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private String[] getCategories() {
        SharedPreferences sharedPreferences = getSharedPreferences(Home.SHARED_PREFS, MODE_PRIVATE);
        String[] array = sharedPreferences.getStringSet(Home.CATEGORIES, new HashSet<String>(Arrays.asList(getResources().getStringArray(R.array.categories)))).toArray(new String[0]);
        Arrays.sort(array);
        return array;
    }

    public void removeItem(int position){
        Transactions toDelete = new Transactions();
        String delete = reportList.get(position).getmId();
        toDelete = Home.appDB.appDao().getTransactions(delete);
        Home.appDB.appDao().deleteTransaction(toDelete);
        reportList.remove(position);
        mAdapter.notifyItemRemoved(position);
        Toast.makeText(getBaseContext(), "Transaction deleted", Toast.LENGTH_LONG).show();
        onRestart();
    }

    public void buildRecyclerView(ArrayList<reportItems> reportList){
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new reportListAdapter(reportList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new reportListAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}
