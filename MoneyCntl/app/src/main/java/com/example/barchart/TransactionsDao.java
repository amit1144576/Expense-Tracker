package com.example.barchart;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TransactionsDao {

    @Insert
    void insert(Transactions transactions);

    @Update
    void update(Transactions transactions);

    @Delete
    void delate(Transactions transactions);

    @Query("DELETE FROM transactions_table")
    void deleteAllTrans();

    @Query("SELECT * FROM transactions_table ORDER BY transactionAmount DESC")
    LiveData<List<Transactions>> getAllTransOrderByAmount();

}
