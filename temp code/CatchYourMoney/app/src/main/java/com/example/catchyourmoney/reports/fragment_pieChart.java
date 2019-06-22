package com.example.catchyourmoney.reports;


import android.graphics.Color;
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
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class fragment_pieChart extends Fragment {

    Bundle extras;
    int dayFrom = 0, monthFrom = 0, yearFrom = 0, dayTo = 0, monthTo = 0, yearTo = 0;
    List<Transactions> transType = null;
    BarChart barChart;
    View view;
    String[] dData;
    String[] cData;
    String currData;

    public fragment_pieChart() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_pie_chart, container, false);

        Bundle extras;
        int dayFrom = 0, monthFrom = 0, yearFrom = 0, dayTo = 0, monthTo = 0, yearTo = 0;
        List<Transactions> transType = null;
        PieChart pieChart;

        pieChart = view.findViewById(R.id.piechart);

        pieChart.setUsePercentValues(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);
        ArrayList<Entry> yValues = new ArrayList<>();
        ArrayList<String> xVals = new ArrayList<>();


        transType = Home.appDB.appDao().getTransaction("Expense", 0);

        System.out.println(transType.toString());

        if (transType != null) {
            float toatlExpense = 0;
            float[] ctgAmts = new float[cData.length];
            for (Transactions transactions1 : transType) {
                toatlExpense += transactions1.getAmount();
            }

            for (int i = 0; i < cData.length; i++) {
                for (Transactions transactions1 : transType) {
                    if (transactions1.getCategory().equalsIgnoreCase(cData[i])) {
                        ctgAmts[i] += transactions1.getAmount();
                    }
                }
            }

            for (int x = 0; x < cData.length; x++) {
                if (!cData[x].equalsIgnoreCase("")) {
                    yValues.add(new Entry(ctgAmts[x], x));
                    xVals.add(cData[x]);
                }
            }

            List<Transactions> income = Home.appDB.appDao().getTransaction("Income");

            float sumIncome = 0, total;

            for (Transactions transactions : income) {
                sumIncome += transactions.getAmount();
            }
            total = sumIncome - toatlExpense;

            pieChart.setDescription("Total Expense: " + toatlExpense + currData + ", Current Balance: " + total + currData);

            pieChart.animateY(1000, Easing.EasingOption.EaseInOutCubic);

            PieDataSet dataSet = new PieDataSet(yValues, "Transactions in " + currData);
            dataSet.setSliceSpace(3f);
            dataSet.setSelectionShift(5f);
            dataSet.setColors(ColorTemplate.PASTEL_COLORS);

            PieData data = new PieData(xVals, dataSet);
            data.setValueFormatter(new DefaultValueFormatter(0));
            data.setValueTextSize(10f);
            data.setValueTextColor(Color.WHITE);
            pieChart.setData(data);
        } else {
            Toast.makeText(getContext(), "Null Data", Toast.LENGTH_SHORT).show();
        }

        return view;
    }
}
