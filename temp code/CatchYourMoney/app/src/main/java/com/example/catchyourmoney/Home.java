package com.example.catchyourmoney;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.catchyourmoney.reports.Report;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Home extends AppCompatActivity implements View.OnClickListener {

    ImageButton btnAddTransaction, btnSettings, btnReport;
    public static AppDB appDB;
    public static CategoryDB categoryDB;
    public static final String SHARED_PREFS="sharedPrefs";
    public static final String CATEGORIES="categories";
    public static final String CURRENCY="currency";
    public static final String AUTHENTICATION="authentication";
    public static final String PASSWORD="password";
    public static final String JOBID = "jobId";


    TextView Budget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        appDB = Room.databaseBuilder(getApplicationContext(),AppDB.class,"Transactiondb").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        categoryDB = Room.databaseBuilder(getApplicationContext(),CategoryDB.class,"Categorydb").allowMainThreadQueries().fallbackToDestructiveMigration().build();

        String currencySign;
        if (getCurrency().equals("Euro")){
            currencySign="€";
        }
        else if (getCurrency().equals("Dollar")){
            currencySign="$";
        }
        else {
            currencySign="Peso";
        }

        /**
         * Filling initial database
         */

        List<Categories> categories = categoryDB.categoryDao().getCategories();
        if (categories.size() == 0){
            Categories helper = new Categories();
            String[] mCat = getResources().getStringArray(R.array.categories);
            for (int i=0; i<mCat.length; i++){
                helper.setCategoryName(mCat[i]);
                helper.setCategoryIcon("ic_android");
                categoryDB.categoryDao().addCategory(helper);
            }
        }



        Budget = findViewById(R.id.txtBudget);
        List<Transactions> expenses = appDB.appDao().getTransaction("Expense");
        List<Transactions> income = appDB.appDao().getTransaction("Income");
        float sumExpense=0, sumIncome = 0, total = 0;
        for (Transactions transactions : expenses){
            sumExpense += transactions.getAmount();
        }
        for (Transactions transactions : income){
            sumIncome += transactions.getAmount();
        }

        total = sumIncome - sumExpense;

        Budget.setText("Your current balance is: \n" + Float.toString(total)+" "+currencySign);


        btnAddTransaction = (ImageButton) findViewById(R.id.btnAddTransaction);
        btnSettings = (ImageButton) findViewById(R.id.btnSettings);
        btnReport = (ImageButton) findViewById(R.id.btnReport);

        btnAddTransaction.setOnClickListener(this);
        btnSettings.setOnClickListener(this);
        btnReport.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnAddTransaction:
                Intent transaction = new Intent(this,AddTransaction.class);
                startActivity(transaction);
                break;
            case R.id.btnSettings:
                Intent setting = new Intent(this,Settings.class);
                startActivity(setting);
                break;
            case R.id.btnReport:
                Intent report = new Intent(this, Report.class);

                startActivity(report);
                break;
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

    public String[] getCategories(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String[] array=sharedPreferences.getStringSet(CATEGORIES,new HashSet<String>(Arrays.asList(getResources().getStringArray(R.array.categories)))).toArray(new String[0]);
        Arrays.sort(array);
        return array;
    }
    private String getCurrency() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        return sharedPreferences.getString(CURRENCY, "Euro");
    }

}
