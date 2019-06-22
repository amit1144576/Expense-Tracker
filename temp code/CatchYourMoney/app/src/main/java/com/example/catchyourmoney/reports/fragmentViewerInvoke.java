package com.example.catchyourmoney.reports;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.catchyourmoney.R;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class fragmentViewerInvoke extends AppCompatActivity {

        //TabLayout information
    private TabLayout tablayout;
    private ViewPager viewPager;
    private String dateData, categories, currData;

    //Transfer data
    private String mCategory, mMethod, mType;
    private int dayFrom, dayTo, monthFrom, monthTo, yearFrom, yearTo;
    private float mAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<< FragmentViewerInvoked <<<<<<<<<<<<<<<<<<<<<<<");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);

        Bundle b = getIntent().getBundleExtra("tb");
        ArrayList<reportItems> reportItemsList = (ArrayList<reportItems>) b.getSerializable("repolist");
        dateData = getIntent().getStringExtra("dateData");
        categories = getIntent().getStringExtra("categories");
        currData = getIntent().getStringExtra("currData");


//        //TabLayout content
        tablayout = (TabLayout) findViewById(R.id.tablayout_id);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);
        report_ViewPageAdapter adapter = new report_ViewPageAdapter(getSupportFragmentManager(), reportItemsList, dateData, categories, currData);
        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);
//        //Tablayout content ends

    }
}
