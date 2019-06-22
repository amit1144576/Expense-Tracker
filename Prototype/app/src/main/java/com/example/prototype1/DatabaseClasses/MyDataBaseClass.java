package com.example.prototype1.DatabaseClasses;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDataBaseClass extends SQLiteOpenHelper {

   private static final String DATABASE_NAME = "ExpenseTracker.db";
   private static final int DATABASE_VERSION =1;

   private String queryToCreateDatabase = "create table TransactionsTable (ID INTEGER PRIMARY KEY AUTOINCREMENT" +
           ",Amount Float,Payment VARCHAR(255),Category VARCHAR(255),date DATE,Description VARCHAR(255))";

        Context context;


    public MyDataBaseClass(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
        this.context = context;


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try
        {

        db.execSQL(queryToCreateDatabase);

            Toast.makeText(context, "Table created successfully", Toast.LENGTH_LONG).show();

        }

        catch (Exception e)
        {
            Toast.makeText(context, "Error while creating table", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
