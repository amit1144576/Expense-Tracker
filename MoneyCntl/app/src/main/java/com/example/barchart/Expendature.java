package com.example.barchart;

import java.util.Date;

public class Expendature extends Transactions {

    public ExpenseCategory expenseCategory;


    public Expendature(Double transactionAmount, String description, String transCategory) {
        super(transactionAmount, description, transCategory);
    }
}
