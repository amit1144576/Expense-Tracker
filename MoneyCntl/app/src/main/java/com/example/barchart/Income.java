package com.example.barchart;

import java.util.Date;

import static com.example.barchart.IncomeCategory.Bonus;
import static com.example.barchart.IncomeCategory.Revenue;
import static com.example.barchart.IncomeCategory.Salary;
import static com.example.barchart.IncomeCategory.TaxRefund;


public class Income extends Transactions {


    public Income(Float transactionAmount, String description, String transCategory) {
        super(transactionAmount, description, transCategory);
    }
}
