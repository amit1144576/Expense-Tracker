package com.example.barchart;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class DatabaseViewModel extends AndroidViewModel {

    private TransRepo transRepo;
    private LiveData<List<Transactions>> allTransactions;

    public DatabaseViewModel(@NonNull Application application) {
        super(application);
        transRepo = new TransRepo(application);
        allTransactions = transRepo.getAllTransOrderByAmount();
    }

    public void insert(Transactions transactions){
        transRepo.insert(transactions);
    }
    public void update(Transactions transactions){
        transRepo.update(transactions);
    }
    public void delete(Transactions transactions){
        transRepo.delete(transactions);
    }
    public void deleteAllTrans(){
        transRepo.deleteAllTrans();
    }
    public LiveData<List<Transactions>> getAllDatabase(){
        return allTransactions;
    }
}
