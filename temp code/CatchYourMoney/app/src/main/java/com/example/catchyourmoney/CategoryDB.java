package com.example.catchyourmoney;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Categories.class},version = 1)
public abstract class CategoryDB extends RoomDatabase {

    public abstract CategoryDao categoryDao();
}
