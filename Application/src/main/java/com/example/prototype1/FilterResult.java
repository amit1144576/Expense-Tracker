package com.example.prototype1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.example.prototype1.DatabaseClasses.MyDataBaseClass;
import com.example.prototype1.RecyclerVIewClasses.DatabaseRecyclerAdapter;
import com.example.prototype1.RecyclerVIewClasses.ModelClass;

import java.util.ArrayList;

public class FilterResult extends AppCompatActivity {
    MyDataBaseClass db;
    RecyclerView objRecyclerView;
    ArrayList<ModelClass> objModelClassArrayList;
    DatabaseRecyclerAdapter objDatabaseRecyclerAdapter;
    //DatabaseHandler MyDatabaseClass = new DatabaseHandler(this);
    //List<Contact> allContacts = myDatabase.monthData();

    String EDate;
    String SDate;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_result);
        objRecyclerView=findViewById(R.id.dataRV1);
        objModelClassArrayList = new ArrayList<>();
        db = new MyDataBaseClass(this);

        String startDate = getIntent().getStringExtra("startDate");
        String endDate = getIntent().getStringExtra("endDate");
        showFilterValuesFromDatabase(startDate, endDate);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

            }
        }).attachToRecyclerView(objRecyclerView);

    }

    public void showFilterValuesFromDatabase(String startDate, String endDate) {

        Cursor valueCursor = db.monthData(startDate, endDate);

        int count = valueCursor.getCount();

        if (count == 0) {

            Toast.makeText(FilterResult.this, "Please pick appropriate dates with corresponding values!", Toast.LENGTH_SHORT).show();
        } else {


            while (valueCursor.moveToNext()) {

                objModelClassArrayList.add(new ModelClass(valueCursor.getInt(0),
                        valueCursor.getFloat(1),
                        valueCursor.getString(5),
                        valueCursor.getString(2),
                        valueCursor.getString(4),
                        valueCursor.getString(3)

                ));

            }

            objDatabaseRecyclerAdapter = new DatabaseRecyclerAdapter(objModelClassArrayList);
            objRecyclerView.hasFixedSize();
            objRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            objRecyclerView.setAdapter(objDatabaseRecyclerAdapter);


        }
    }

}
