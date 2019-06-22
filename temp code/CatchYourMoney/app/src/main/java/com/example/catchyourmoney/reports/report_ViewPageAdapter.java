package com.example.catchyourmoney.reports;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;
import java.util.ArrayList;

public class report_ViewPageAdapter extends FragmentPagerAdapter {

    //private final List<Fragment> fragmentList = new ArrayList<>();
    //private final List<String> fragmentListTitles = new ArrayList<>();
    private ArrayList<reportItems> r_reportItemsList;
    private String[] dData;
    private String[] cData;
    private String currData;

    public report_ViewPageAdapter(FragmentManager fm, ArrayList<reportItems> reportItemsList, String dateData, String categories, String currData) {
        super(fm);
        r_reportItemsList = reportItemsList;
        dData = dateData.split(",");
        cData = categories.split(",");
        this.currData = currData;

    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                fragment_recycler tab1 = new fragment_recycler();
                tab1.setReportList(r_reportItemsList);
                return tab1;
            case 1:
                fragment_barChart tab2 = new fragment_barChart();
                tab2.dData = this.dData;
                tab2.cData = this.cData;
                tab2.currData = this.currData;
                return tab2;
            case 2:
                fragment_pieChart tab3 = new fragment_pieChart();
                tab3.dData = this.dData;
                tab3.cData = this.cData;
                tab3.currData = this.currData;
                return tab3;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "List of data";
            case 1:
                return "Bar Graph";
            case 2:
                return "Pie Chart";
        }
        return null;
    }

}
