package com.example.prototype1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.prototype1.RecyclerVIewClasses.ModelClass;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;

public class BudgetChart extends AppCompatActivity {

    private LineChart mChart;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_chart);

        //ModelClass modelClass = modelDatabse.get(i);





        mChart = findViewById(R.id.line_chart);
        //mChart.setOnChartGestureListener(BudgetChart.this);
        //mChart.setOnChartValueSelectedListener(BudgetChart.this);

        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(false);

        ArrayList<Entry>  yValues = new ArrayList<>();

        yValues.add(new Entry(0,40f));
        yValues.add(new Entry(1,30f));
        yValues.add(new Entry(2,50f));
        yValues.add(new Entry(3,60f));
        yValues.add(new Entry(4,70f));
        yValues.add(new Entry(5,65f));

        LineDataSet set1 =new LineDataSet(yValues,"Expense");

        set1.setFillAlpha(110);
        set1.setColor(Color.RED);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        LineData data = new LineData(dataSets);
        mChart.setData(data);

    }
}
