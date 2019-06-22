package com.example.catchyourmoney;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class PChart extends AppCompatActivity {

    Bundle extras;
    int dayFrom = 0, monthFrom = 0, yearFrom = 0, dayTo = 0, monthTo = 0, yearTo = 0;
    List<Transactions> transType = null;
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        pieChart = findViewById(R.id.piechart);

        pieChart.setUsePercentValues(false);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);
        ArrayList<PieEntry> yValues = new ArrayList<>();
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
                    yValues.add(new PieEntry(ctgAmts[x], categories[x] +" in "+ getCurrency()));
                }

                List<Transactions> income = Home.appDB.appDao().getTransaction("Income");

                float sumIncome = 0, total;

                for (Transactions transactions : income){
                    sumIncome += transactions.getAmount();
                }
                total = sumIncome - toatlExpense;

                Description description = new Description();
                description.setText("Total Expense: " + toatlExpense + getCurrency() + ", Current Balance: " + total + getCurrency());
                description.setTextSize(10f);
                pieChart.setDescription(description);

                pieChart.animateY(1000, Easing.EaseInOutCubic);

                PieDataSet dataSet = new PieDataSet(yValues, "Transactions");
                dataSet.setSliceSpace(3f);
                dataSet.setSelectionShift(5f);
                dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
                PieData data = new PieData(dataSet);
                data.setValueTextSize(10f);
                data.setValueTextColor(Color.WHITE);
                pieChart.setData(data);
            }else {
                Toast.makeText(getApplicationContext(), "Null Data", Toast.LENGTH_SHORT).show();
            }
        }else{
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
