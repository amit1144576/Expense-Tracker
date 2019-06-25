package com.example.applicationprototype;

import java.util.Date;

import static com.example.barchart.IncomeCategory.Bonus;
import static com.example.barchart.IncomeCategory.Revenue;
import static com.example.barchart.IncomeCategory.Salary;
import static com.example.barchart.IncomeCategory.TaxRefund;


public class Income extends Transactions {


    public Income(Float transactionAmount, String description, Boolean transTypeIncome, String transCategory, String transType, boolean repeatative, Date transDate, Date transTime) {
        super(transactionAmount, description, transTypeIncome, transCategory, transType, repeatative, transDate, transTime);
    }
}
