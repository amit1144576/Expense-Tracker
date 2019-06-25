package com.example.prototype1.DatabaseClasses;

import android.content.Context;
import android.database.Cursor;
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

    public Cursor monthData(String startdate, String enddate) {


        SQLiteDatabase db = this.getWritableDatabase();

        String table = "TransactionsTable";
        String[] columns = new String[]{
                "Amount", "Payment_method", "Category", "T_Date", "Description"
        };

        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = "Date DESC";
        String limit = "10";


        Cursor res = db.rawQuery("select * from " + table + " where Date BETWEEN '" + startdate + "' AND '" + enddate + "' ORDER BY Date ASC", null);
        return res;


    }
}
