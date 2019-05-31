package com.ISEE2019_CODEPROS_team.MoneyCTRL.Model;
import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;


import java.util.List;
public class TransRepo {
    private TransactionsDao transactionsDao;
    private LiveData<List<Transactions>> allTransOrderByAmount;

    public TransRepo(Application application){
        Database database = Database.getInstance(application);
        transactionsDao = database.transactionsDao();
        allTransOrderByAmount = transactionsDao.getAllTransOrderByAmount();
    }

    public void insert(Transactions transactions){
        new InsertTransactionsAsyncTask(transactionsDao).execute(transactions);
    }

    public void update(Transactions transactions){
        new UpdateTransactionsAsyncTask(transactionsDao).execute(transactions);
    }

    public void delete(Transactions transactions){
        new DeleteTransactionsAsyncTask(transactionsDao).execute(transactions);
    }

    public void deleteAllTrans(){
        new DeleteAllTransactionsAsyncTask(transactionsDao).execute();
    }

    public LiveData<List<Transactions>> getAllTransOrderByAmount() {
        return allTransOrderByAmount;
    }

    private static class InsertTransactionsAsyncTask extends AsyncTask<Transactions, Void, Void> {
        private TransactionsDao transactionsDao;
        private InsertTransactionsAsyncTask (TransactionsDao transactionsDao){
            this.transactionsDao = transactionsDao;
        }

        @Override
        protected Void doInBackground(Transactions... transactions) {
            transactionsDao.insert(transactions[0]);
            return null;
        }
    }

    private static class UpdateTransactionsAsyncTask extends AsyncTask<Transactions, Void, Void> {

        private TransactionsDao transactionsDao;
        private UpdateTransactionsAsyncTask(TransactionsDao transactionsDao){
            this.transactionsDao = transactionsDao;
        }

        @Override
        protected Void doInBackground(Transactions... transactions) {
            transactionsDao.update(transactions[0]);
            return null;
        }
    }

    private static class DeleteTransactionsAsyncTask extends AsyncTask<Transactions, Void, Void> {

        private TransactionsDao transactionsDao;
        private DeleteTransactionsAsyncTask(TransactionsDao transactionsDao){
            this.transactionsDao = transactionsDao;
        }

        @Override
        protected Void doInBackground(Transactions... transactions) {
            transactionsDao.delate(transactions[0]);
            return null;
        }
    }

    private static class DeleteAllTransactionsAsyncTask extends AsyncTask<Void, Void, Void> {

        private TransactionsDao transactionsDao;
        private DeleteAllTransactionsAsyncTask(TransactionsDao transactionsDao){
            this.transactionsDao = transactionsDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            transactionsDao.deleteAllTrans();
            return null;
        }
    }
}
