package com.example.barchart;

import java.util.Date;

public class Expendature extends Transactions {

    public ExpenseCategory expenseCategory;


    public Expendature(Float transactionAmount, String description, Boolean transTypeIncome, String transCategory, String transType, boolean repeatative, Date transDate, Date transTime) {
        super(transactionAmount, description, transTypeIncome, transCategory, transType, repeatative, transDate, transTime);
    }
}
