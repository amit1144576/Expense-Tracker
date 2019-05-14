package com.example.application;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Expense.db";

    public static final String REGISTER_TABLE_NAME = "register";
    public static final String REGISTER_ID = "register_id";
    public static final String REGISTER_USERNAME = "username";
    public static final String REGISTER_PASSWORD = "password";

    public static final String CATEGORIES_TABLE_NAME = "categories";
    public static final String CATEGORIES_ID = "categories_id";
    public static final String CATEGORIES_NAME = "categories_Name";

    public static final String EXPENSES_TABLE_NAME = "expenses";
    public static final String EXPENSES_ID = "expenses_id";
    public static final String EXPENSES_NAME = "expenses_name";
    public static final String EXPENSES_Value = "expenses_value";
    public static final String EXPENSES_Date = "expenses_Date";
    public static final String EXPENSES_CATEGORIES_ID = "expense_categories_id";

    public static final String INCOME_TABLE_NAME = "income";
    public static final String INCOME_ID = "income_id";
    public static final String INCOME_NAME = "income_name";
    public static final String INCOME_Value = "income_value";
    public static final String INCOME_Date = "income_Date";
    public static final String INCOME_CATEGORIES_ID = "income_categories_id";




    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + REGISTER_TABLE_NAME + "(register_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT, password TEXT )");
        db.execSQL("CREATE TABLE " + CATEGORIES_TABLE_NAME + "(categories_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "categories_Name TEXT UNIQUE)");
        db.execSQL("CREATE TABLE " + EXPENSES_TABLE_NAME + "(expenses_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "expenses_value INTEGER,expense_name TEXT, expenses_Date DATE,FOREIGN KEY(expense_categories_id) REFERENCES CATEGORIES_TABLE_NAME(categories_id) )");
        db.execSQL("CREATE TABLE " + INCOME_TABLE_NAME + "(income_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "income_value INTEGER,income_name TEXT,income_Date DATE, FOREIGN KEY(income_categories_id) REFERENCES CATEGORIES_TABLE_NAME(categories_id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS "+ REGISTER_TABLE_NAME);
        db.execSQL(" DROP TABLE IF EXISTS "+ CATEGORIES_TABLE_NAME);
        db.execSQL(" DROP TABLE IF EXISTS "+ EXPENSES_TABLE_NAME);
        db.execSQL(" DROP TABLE IF EXISTS "+ INCOME_TABLE_NAME);
        onCreate(db);
    }

    public long addUser(String user, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", user);
        contentValues.put("password", password);
        long res = db.insert("register", null, contentValues);
        db.close();
        return res;
    }

    public boolean checkUser(String username, String password) {
        String[] columns = { REGISTER_ID };
        SQLiteDatabase db = getReadableDatabase();
        String selection = REGISTER_USERNAME + "m?" + " and " + REGISTER_PASSWORD + "m?";
        String [] selectionArgs = { username, password};
        Cursor cursor = db.query(REGISTER_TABLE_NAME, columns, selection, selectionArgs, null, null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if (count>0)
            return true;
        else
            return false;
    }

}

