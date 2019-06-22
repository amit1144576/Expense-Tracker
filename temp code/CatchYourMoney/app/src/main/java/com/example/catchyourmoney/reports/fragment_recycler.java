package com.example.catchyourmoney.reports;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.catchyourmoney.Home;
import com.example.catchyourmoney.R;
import com.example.catchyourmoney.Transactions;
import com.example.catchyourmoney.reportListAdapter;

import java.util.ArrayList;

public class fragment_recycler extends Fragment{

    private RecyclerView mRecyclerView;
    private reportListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    View view;
    private ArrayList<reportItems> reportList = new ArrayList<>();


    public fragment_recycler() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_recycler, container, false);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> fragment recycler invoked");
        System.out.println(reportList);
        buildRecyclerView(reportList);
        return view;

    }

    public void setReportList(ArrayList<reportItems> reportList) {
        this.reportList = reportList;
    }

    public void removeItem(int position){
        Transactions toDelete = new Transactions();
        String delete = reportList.get(position).getmId();
        toDelete = Home.appDB.appDao().getTransactions(delete);
        Home.appDB.appDao().deleteTransaction(toDelete);
        reportList.remove(position);
        mAdapter.notifyItemRemoved(position);
        //Toast.makeText(view.getBaseContext(), "Transaction deleted", Toast.LENGTH_LONG).show();
        //onRestart();
    }

    public void buildRecyclerView(ArrayList<reportItems> reportList){
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
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
}