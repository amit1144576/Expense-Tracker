package com.ISEE2019_CODEPROS_team.MoneyCTRL.XTR_CLASSES_Constants;

import com.ISEE2019_CODEPROS_team.MoneyCTRL.Model.Transactions;
import com.ISEE2019_CODEPROS_team.MoneyCTRL.XTR_CLASSES_Constants.ExpenseCategory;

public class Expendature extends Transactions {

    public ExpenseCategory expenseCategory;


    public Expendature(Float transactionAmount, String description, String transCategory) {
        super(transactionAmount, description, transCategory);
    }
}
