package com.ISEE2019_CODEPROS_team.MoneyCTRL.Model;


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
    void delete(Transactions transactions);

    @Query("DELETE FROM transactions_table")
    void deleteAllTrans();

    // below query is tagged as comment and we will use it in future
    // @Query("SELECT * FROM transactions_table ORDER BY transactionAmount DESC")
    // LiveData<List<Transactions>> getAllTransOrderByAmount();

    @Query("SELECT * FROM transactions_table ORDER BY transID DESC")
    LiveData<List<Transactions>> getAllTransOrderByID();

}
