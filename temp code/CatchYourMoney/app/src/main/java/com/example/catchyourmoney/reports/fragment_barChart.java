package com.example.catchyourmoney.reports;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.catchyourmoney.Home;
import com.example.catchyourmoney.R;
import com.example.catchyourmoney.Transactions;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

public class fragment_barChart extends Fragment {

    View view;
    Bundle extras;
    public int dayFrom = 0, monthFrom = 0, yearFrom = 0, dayTo = 0, monthTo = 0, yearTo = 0;
    List<Transactions> transType = null;
    BarChart barChart;
    String[] dData;
    String[] cData;
    String currData;

    public fragment_barChart() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_bgraph, container, false);

        /////////////

        System.out.println(cData.length);

        barChart = view.findViewById(R.id.barchart);
        final ArrayList<BarEntry> barEntries = new ArrayList<>();

        transType = Home.appDB.appDao().getTransaction("Expense", 0);

        if (transType != null) {
            float toatlExpense = 0;
            String[] categories = cData;
            ArrayList<String> ctgList = new ArrayList<>();

            float[] ctgAmts = new float[categories.length];
            for (Transactions transactions1 : transType) {
                toatlExpense += transactions1.getAmount();
            }

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


            BarDataSet dataSet = new BarDataSet(barEntries, "Transactions in " + currData);

            BarData data = new BarData(ctgList, dataSet);
            barChart.setDescription("Total Expense: " + toatlExpense + currData + ", Current Balance: " + total + currData);
            barChart.setData(data);
        } else {
            Toast.makeText(getContext(), "Null Data", Toast.LENGTH_SHORT).show();
        }
        /////////////

        return view;
    }
}