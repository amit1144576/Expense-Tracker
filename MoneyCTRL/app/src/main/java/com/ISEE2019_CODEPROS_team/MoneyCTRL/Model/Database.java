package com.ISEE2019_CODEPROS_team.MoneyCTRL.Model;


import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@androidx.room.Database(entities = {Transactions.class}, version = 1)
public abstract class Database extends RoomDatabase {

    private static Database instance;
    // method transactionsDao() is abstract because we
    // are not providing method body and return type TransactionsDao
    public abstract TransactionsDao transactionsDao();

    public static synchronized Database getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    Database.class, "transactions_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask <Void, Void, Void> {

        private TransactionsDao transactionsDao;

        private PopulateDbAsyncTask(Database db){
            transactionsDao = db.transactionsDao();
        }

    @Override
    protected Void doInBackground(Void... voids) {
        transactionsDao.insert(new Transactions("12.0","Expense", "May 20, 2019","Snack after exercise // just to have a longer description"));
        transactionsDao.insert(new Transactions("15.0","Expense", "May 22, 2019","Snack after exercise"));
        transactionsDao.insert(new Transactions("20.0","Expense", "May 22, 2019","Online shopping using master card"));
        transactionsDao.insert(new Transactions("12.5","Expense", "May 24, 2019","Snack after exercise"));
        transactionsDao.insert(new Transactions("12.5","Expense", "May 24, 2019","Bike repairing cost"));
        transactionsDao.insert(new Transactions("20.0","Expense", "May 25, 2019", "Snack after exercise"));
        transactionsDao.insert(new Transactions("10.0","Expense", "May 26, 2019", "Bike repairing cost plus// just to have a longer description"));
        transactionsDao.insert(new Transactions("10.0","Expense", "May 27, 2019", "Snack after exercise"));
        transactionsDao.insert(new Transactions("15.5","Expense", "May 30, 2019", "gift to friend"));
        transactionsDao.insert(new Transactions("30.2","Expense", "May 30, 2019", "Online shopping using master card"));
        transactionsDao.insert(new Transactions("14.0","Expense", "Jun 1, 2019", "Online shopping using master card"));
        transactionsDao.insert(new Transactions("15.5","Expense", "Jun 1, 2019", "Snack after exercise"));
        transactionsDao.insert(new Transactions("13.2","Expense", "Jun 2, 2019", "Online shopping using master card"));
        transactionsDao.insert(new Transactions("15.5","Expense", "Jun 2, 2019", "Round Ticket to work"));
        transactionsDao.insert(new Transactions("10.0","Expense", "Jun 3, 2019", "Online shopping using master card"));
        transactionsDao.insert(new Transactions("15.5","Expense", "Jun 3, 2019", "Round ticket to berlin"));
        transactionsDao.insert(new Transactions("15.5","Expense", "Jun 4, 2019", "Snack after exercise // just to have a longer description"));
        return null;
    }
}


}
