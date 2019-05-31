package com.ISEE2019_CODEPROS_team.MoneyCTRL.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ISEE2019_CODEPROS_team.MoneyCTRL.Model.TransRepo;
import com.ISEE2019_CODEPROS_team.MoneyCTRL.Model.Transactions;

import java.util.List;

public class TransactionsViewModel extends AndroidViewModel {

    private TransRepo transRepo;
    private LiveData<List<Transactions>> allTransactions;

    public TransactionsViewModel(@NonNull Application application) {
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
    public LiveData<List<Transactions>> getAllTransOrderByAmount(){
        return allTransactions;
    }
}
