package com.example.barchart;


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

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{

        private TransactionsDao transactionsDao;

        private PopulateDbAsyncTask(Database db){
            transactionsDao = db.transactionsDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            transactionsDao.insert(new Transactions(12.5,"first transaction", "expense"));
            transactionsDao.insert(new Transactions(20.0,"second transaction", "income"));
            transactionsDao.insert(new Transactions(15.5,"third transaction", "expense"));
            return null;
        }
    }


}
