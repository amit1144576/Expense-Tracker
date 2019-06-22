package com.example.catchyourmoney;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class BGraph extends AppCompatActivity {

    Bundle extras;
    int dayFrom = 0, monthFrom = 0, yearFrom = 0, dayTo = 0, monthTo = 0, yearTo = 0;
    List<Transactions> transType = null;
    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bgraph);

        extras = getIntent().getExtras();
        barChart = findViewById(R.id.barchart);
        final ArrayList<BarEntry> barEntries = new ArrayList<>();

        if (extras != null) {
            dayFrom = extras.getInt("dayFrom");
            dayTo = extras.getInt("dayTo");
            monthFrom = extras.getInt("monthFrom");
            monthTo = extras.getInt("monthTo");
            yearFrom = extras.getInt("yearFrom");
            yearTo = extras.getInt("yearTo");
            //transType = Home.appDB.appDao().getTransaction("Expense", "", "", 0, dayFrom, monthFrom,
            //yearFrom, dayTo, monthTo, yearTo);

            transType = Home.appDB.appDao().getTransaction("Expense", 0);

            if (transType != null) {
                float toatlExpense = 0;
                String[] categories = getCategories();
                ArrayList<String> ctgList = new ArrayList<>();

                float[] ctgAmts = new float[categories.length];
                for (Transactions transactions1 : transType) {
                    toatlExpense += transactions1.getAmount();
                }

                /*for (int i = 0; i < categories.length; i++) {
                    for (Transactions transactions1 : transType) {
                        if (transactions1.getCategory().equalsIgnoreCase(categories[i])) {
                            ctgAmts[i] += transactions1.getAmount();
                        }
                    }
                }

                for (int y = 0; y < categories.length; y++) {
                    if (!categories[y].equalsIgnoreCase("")) {
                        barEntries.add(new BarEntry(ctgAmts[y], y));
                    }
                }*/

                int bCount = 0;
                for (Transactions transactions1 : transType) {
                    String type = transactions1.getDay() + "/" + transactions1.getMonth() + "/" + transactions1.getYear();
                    ctgList.add(bCount, type);
                    barEntries.add(new BarEntry(transactions1.getAmount(), bCount));
                    bCount++;
                }

                List<Transactions> income = Home.appDB.appDao().getTransaction("Income");
                float sumIncome = 0, total;
                for (Transactions transactions : income) {
                    sumIncome += transactions.getAmount();
                }
                total = sumIncome - toatlExpense;


                BarDataSet dataSet = new BarDataSet(barEntries, "Transactions in " + getCurrency());

                BarData data = new BarData(ctgList, dataSet);
                barChart.setDescription("Total Expense: " + toatlExpense + getCurrency() + ", Current Balance: " + total + getCurrency());
                barChart.setData(data);
            } else {
                Toast.makeText(getApplicationContext(), "Null Data", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Error getting values", Toast.LENGTH_SHORT).show();
        }
    }

    private String[] getCategories() {
        SharedPreferences sharedPreferences = getSharedPreferences(Home.SHARED_PREFS, MODE_PRIVATE);
        String[] array = sharedPreferences.getStringSet(Home.CATEGORIES, new HashSet<String>(Arrays.asList(getResources().getStringArray(R.array.categories)))).toArray(new String[0]);
        Arrays.sort(array);
        return array;
    }

    private String getCurrency() {
        SharedPreferences sharedPreferences = getSharedPreferences(Home.SHARED_PREFS, MODE_PRIVATE);
        return sharedPreferences.getString(Home.CURRENCY, " €");
    }
}
