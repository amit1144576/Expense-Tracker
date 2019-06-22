package com.example.catchyourmoney;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;


@Database(entities = {Transactions.class},version = 4)
public abstract class AppDB extends RoomDatabase {

    public abstract AppDao appDao();

}
