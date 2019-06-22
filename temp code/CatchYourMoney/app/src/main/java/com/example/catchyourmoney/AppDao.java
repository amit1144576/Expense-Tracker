package com.example.catchyourmoney;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface AppDao {

    @Insert
    public void addTransaction(Transactions transactions);

    @Query("select * from Transactions")
    public List<Transactions> getTransactions();

    @Query("select * from Transactions where TransactionID = :transId")
    public Transactions getTransactions(String transId);

    @Query("select * from Transactions where Type = :mType")
    public List<Transactions> getTransaction(String mType);

    @Query("select * from Transactions where Type = :mType AND Category = :mCategory AND Method = :mMethod AND Amount >= :mAmount")
    public List<Transactions> getTransaction(String mType, String mCategory,String mMethod,float mAmount);

    @Query("select * from Transactions where Type = :mType AND Method = :mMethod AND Amount >= :mAmount")
    public List<Transactions> getTransaction(String mType,String mMethod,float mAmount);

    @Query("select * from Transactions where Type = :mType AND Category = :mCategory AND Amount >= :mAmount")
    public List<Transactions> getTransactionC(String mType,String mCategory,float mAmount);

    @Query("select * from Transactions where Type = :mType AND Amount >= :mAmount")
    public List<Transactions> getTransaction(String mType, float mAmount);

    @Query("select * from Transactions where Type = :mType AND Amount >= :mAmount " +
            "AND Day BETWEEN :dayFrom AND :dayTo AND Month BETWEEN :monthFrom AND :monthTo AND Year BETWEEN :yearFrom AND :yearTo")
    public List<Transactions> getTransaction(String mType, float mAmount, int dayFrom, int monthFrom, int yearFrom, int dayTo, int monthTo, int yearTo);

    @Query("select * from Transactions where Type = :mType AND Category = :mCategory AND Amount >= :mAmount " +
            "AND Day BETWEEN :dayFrom AND :dayTo AND Month BETWEEN :monthFrom AND :monthTo AND Year BETWEEN :yearFrom AND :yearTo")
    public List<Transactions> getTransactionC(String mType, String mCategory,float mAmount, int dayFrom, int monthFrom, int yearFrom, int dayTo, int monthTo, int yearTo);

    @Query("select * from Transactions where Type = :mType AND Method = :mMethod AND Amount >= :mAmount " +
            "AND Day BETWEEN :dayFrom AND :dayTo AND Month BETWEEN :monthFrom AND :monthTo AND Year BETWEEN :yearFrom AND :yearTo")
    public List<Transactions> getTransaction(String mType,String mMethod,float mAmount, int dayFrom, int monthFrom, int yearFrom, int dayTo, int monthTo, int yearTo);

    @Query("select * from Transactions where Type = :mType AND Category = :mCategory AND Method = :mMethod AND Amount >= :mAmount " +
            "AND Day BETWEEN :dayFrom AND :dayTo AND Month BETWEEN :monthFrom AND :monthTo AND Year BETWEEN :yearFrom AND :yearTo")
    public List<Transactions> getTransaction(String mType, String mCategory,String mMethod,float mAmount, int dayFrom, int monthFrom, int yearFrom, int dayTo, int monthTo, int yearTo);



    @Delete
    public void deleteTransaction(Transactions transactions);

}


