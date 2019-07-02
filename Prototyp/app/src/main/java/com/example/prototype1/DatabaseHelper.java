package com.example.prototype1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.sql.Date;


public class DatabaseHelper extends SQLiteOpenHelper {



    public static final String DATABASE_NAME = "t.db";
    public static final String TABLE_NAME = "tdata_table";
    public static final String COL_1 = "TID";
    public static final String COL_2 = "Amount";
    public static final String COL_3 = "Payment_method";
    public static final String COL_4 = "Category";
    public static final String COL_5 = "Description";
    public static final String COL_6 = "T_Date";
    public static final String COL_7 = "Recurring";


    public DatabaseHelper(Context context) {


        super(context,DATABASE_NAME, null, 1);
       SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,Amount FLOAT ,Payment_method TEXT,Category TEXT,Description TEXT,T_Date DATE,Recurring BOOLEAN )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);


    }


    public boolean insertData(Float Amount, String Payment_method, String Category, String Description, String T_Date, Boolean Recurring){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,Amount);
        contentValues.put(COL_3,Payment_method);
        contentValues.put(COL_4,Category);
        contentValues.put(COL_5,Description);
        contentValues.put(COL_6,T_Date);
        contentValues.put(COL_7,Recurring);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result== -1)
            return false;
        else
            return true;

    }

    public Cursor getAllData(){


        SQLiteDatabase db = this.getWritableDatabase();

        String table = "tdata_table";
        String[] columns = new String[] {
                "Amount","Payment_method","Category","T_Date","Description"
        };

        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = "T_Date DESC";
        String limit = "10";


       Cursor res = db.rawQuery("select * FROM tdata_table",null );

       //Cursor res = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
        return res;

    }

    public Cursor monthData(String startdate,String enddate) {


        SQLiteDatabase db = this.getWritableDatabase();

        String table = "tdata_table";
        String[] columns = new String[]{
                "Amount", "Payment_method", "Category", "T_Date", "Description"
        };

        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = "T_Date DESC";
        String limit = "10";


        Cursor res = db.rawQuery("select * from " + table + " where T_date BETWEEN '" + startdate + "' AND '" + enddate + "' ORDER BY T_date ASC", null);
            return res;


    }



}
