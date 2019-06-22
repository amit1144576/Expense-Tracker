package com.example.catchyourmoney.reports;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.catchyourmoney.Home;
import com.example.catchyourmoney.PChart;
import com.example.catchyourmoney.R;
import com.example.catchyourmoney.Transactions;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class caller_report3_pieChart extends AppCompatActivity {

    Bundle extras;
    int dayFrom = 0, monthFrom = 0, yearFrom = 0, dayTo = 0, monthTo = 0, yearTo = 0;
    List<Transactions> transType = null;
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        System.out.println("<<<<<<<<<<<<<<<<<<<<<<< caller_report3_piechart invoked <<<<<<<<<<<<<<<<<<<<<<<");

        pieChart = findViewById(R.id.piechart);

        pieChart.setUsePercentValues(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);
        ArrayList<Entry> yValues = new ArrayList<>();
        ArrayList<String> xVals = new ArrayList<>();
        extras = getIntent().getExtras();

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
                float[] ctgAmts = new float[categories.length];
                for (Transactions transactions1 : transType) {
                    toatlExpense += transactions1.getAmount();
                }

                for (int i = 0; i < categories.length; i++){
                    for (Transactions transactions1 : transType) {
                        if(transactions1.getCategory().equalsIgnoreCase(categories[i])){
                            ctgAmts[i] += transactions1.getAmount();
                        }
                    }
                }

                for (int x = 0; x < categories.length; x++){
                    if(!categories[x].equalsIgnoreCase("")) {
                        yValues.add(new Entry(ctgAmts[x], x));
                        xVals.add(categories[x]);
                    }
                }

                List<Transactions> income = Home.appDB.appDao().getTransaction("Income");

                float sumIncome = 0, total;

                for (Transactions transactions : income){
                    sumIncome += transactions.getAmount();
                }
                total = sumIncome - toatlExpense;

                pieChart.setDescription("Total Expense: " + toatlExpense + getCurrency() + ", Current Balance: " + total + getCurrency());

                pieChart.animateY(1000, Easing.EasingOption.EaseInOutCubic);

                PieDataSet dataSet = new PieDataSet(yValues, "Transactions in " + getCurrency());
                dataSet.setSliceSpace(3f);
                dataSet.setSelectionShift(5f);
                dataSet.setColors(ColorTemplate.PASTEL_COLORS);

                PieData data = new PieData(xVals, dataSet);
                data.setValueFormatter(new DefaultValueFormatter(0));
                data.setValueTextSize(10f);
                data.setValueTextColor(Color.WHITE);
                pieChart.setData(data);
            }else {
                Toast.makeText(getApplicationContext(), "Null Data", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getApplicationContext(), "Error getting values", Toast.LENGTH_SHORT).show();
        }


        Intent chart = new Intent(this, PChart.class);
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
