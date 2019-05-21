package com.example.barchart;

import java.util.Date;

public class Expendature extends Transactions {

    public ExpenseCategory expenseCategory;


    public Expendature(Float transactionAmount, String description, String transCategory) {
        super(transactionAmount, description, transCategory);
    }
}
