package com.example.barchart;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "transactions_table")
public class Transactions {

    @PrimaryKey(autoGenerate = true)
    private int transID;

    private Double transactionAmount;

    private String description;

    //private Boolean transTypeIncome;

    private String transCategory;

    //private String transType;

    //private Boolean repeatative;

    //private Date transDate;

    //private Date transTime;


    public Transactions(double transactionAmount, String description, String transCategory) {
        this.transactionAmount = transactionAmount;
        this.description = description;
        this.transCategory = transCategory;

    }

    public void setTransID(int transID) {
        this.transID = transID;
    }

    public int getTransID() {
        return transID;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public String getDescription() {
        return description;
    }

    public String getTransCategory() {
        return transCategory;
    }

}
