package com.example.catchyourmoney;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;

import java.util.UUID;

public class RecurrentAlarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        final Transactions copy;
        String transId = intent.getStringExtra("Id");
        copy = Home.appDB.appDao().getTransactions(transId);
        String newid = UUID.randomUUID().toString();
        copy.setTransactionID(newid);
        Home.appDB.appDao().addTransaction(copy);
    }

}
