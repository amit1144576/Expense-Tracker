package com.ISEE2019_CODEPROS_team.MoneyCTRL.Model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "transactions_table")
public class Transactions {


    @PrimaryKey(autoGenerate = true)
    private int transID;

    private String transactionAmount;

    private String transCategory;

    private String transDate;

    private String description;



    public Transactions(String transactionAmount, String transCategory, String transDate, String description) {
        this.transactionAmount = transactionAmount;
        this.transCategory = transCategory;
        this.transDate = transDate;
        this.description = description;
    }

    public void setTransID(int transID) {
        this.transID = transID;
    }

    public int getTransID() {
        return transID;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public String getDescription() {
        return description;
    }

    public String getTransCategory() {
        return transCategory;
    }

    public String getTransDate() {
        return transDate;
    }
}
