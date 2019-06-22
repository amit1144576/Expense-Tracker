package com.example.catchyourmoney;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Transactions {

    @NonNull
    @PrimaryKey
    private String TransactionID;

    private String Type;

    private String Category;

    private int Day;

    private int Month;

    private int Year;

    private String Method;

    private String Comment;

    private float Amount;

    private boolean Recurrent;

    private int RecurrentJob;

    public Transactions() {
    }

    public Transactions(String id, String mType, String mCategory, String mMethod, int mDay, int mMonth, int mYear, String mComment, float mAmount, boolean mRecurrent, int mRecurrentJob){
        this.TransactionID = id;
        this.Type = mType;
        this.Category = mCategory;
        this.Day = mDay;
        this.Month = mMonth;
        this.Year = mYear;
        this.Method = mMethod;
        this.Comment = mComment;
        this.Amount = mAmount;
        this.Recurrent = mRecurrent;
        this.RecurrentJob = mRecurrentJob;
    }

    public void setTransactionID(String transactionID) { TransactionID = transactionID; }

    public boolean isRecurrent() { return Recurrent;  }

    public void setRecurrent(boolean recurrent) {  Recurrent = recurrent;  }

    public void setType(String type) {
        Type = type;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public void setMethod(String method) {
        Method = method;
    }

    public int getDay() { return Day; }

    public void setDay(int day) { Day = day;  }

    public int getMonth() { return Month;  }

    public void setMonth(int month) {  Month = month;  }

    public int getYear() { return Year; }

    public void setYear(int year) { Year = year; }

    public void setComment(String comment) {
        Comment = comment;
    }

    public void setAmount(float amount) {
        Amount = amount;
    }

    public String getTransactionID() {
        return TransactionID;
    }

    public String getType() {
        return Type;
    }

    public String getCategory() {
        return Category;
    }

    public String getMethod() {
        return Method;
    }

    public String getComment() {
        return Comment;
    }

    public float getAmount() {
        return Amount;
    }

    public int getRecurrentJob() { return RecurrentJob; }

    public void setRecurrentJob(int recurrentJob) {  RecurrentJob = recurrentJob; }
}
