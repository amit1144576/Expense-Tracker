package com.example.catchyourmoney;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface CategoryDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    public void addCategory(Categories categories);

    @Query("select categoryName from Categories")
    public List<String> getCategoryNames();

    @Query("select * from Categories")
    public List<Categories> getCategories();

    @Query("select categoryIcon from Categories where categoryName = :mName")
    public String getIcon(String mName);

    @Query("select * from Categories where categoryName = :mName")
    public Categories getCategories(String mName);

    @Delete
    public void deleteCategory(Categories categories);
}
