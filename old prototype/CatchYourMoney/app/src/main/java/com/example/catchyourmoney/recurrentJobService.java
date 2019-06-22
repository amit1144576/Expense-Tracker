package com.example.catchyourmoney;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.PersistableBundle;
import android.util.Log;

import java.util.List;
import java.util.UUID;

public class recurrentJobService extends JobService {
    private static final String TAG = "Recurrent started";
    private boolean jobCancelled = false;
    private int first = 0;

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG,"Recurrent Transaction");
        Log.d(TAG,"2");
        final Transactions copy;
        String transId;
        PersistableBundle extras = params.getExtras();
        transId = extras.getString("Id");
        int isIt= extras.getInt("first");
        if (first == 0){
            first = isIt;
        }
        if (first == 1){
            jobFinished(params, false);
            first ++;
        }
        else{
        copy = Home.appDB.appDao().getTransactions(transId);
        if (jobCancelled){
            return true;
        }
        String newid = UUID.randomUUID().toString();
        copy.setTransactionID(newid);
        Home.appDB.appDao().addTransaction(copy);
        jobFinished(params, false);}

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {

        Log.d(TAG, "Recurrent cancelled before completion");
        jobCancelled = true;
        return false;
    }
}
