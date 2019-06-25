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

public class ShowValues extends AppCompatActivity {

    RecyclerView objRecyclerView;
    ArrayList<ModelClass> objModelClassArrayList;
    DatabaseRecyclerAdapter objDatabaseRecyclerAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_values);
        objRecyclerView=findViewById(R.id.dataRV);
        objModelClassArrayList = new ArrayList<>();
        showValuesFromDatabase();

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

    public void showValuesFromDatabase()
    {


        try {

            MyDataBaseClass objMyDataBaseClass = new MyDataBaseClass(this);


            SQLiteDatabase objSQLiteDatabase = objMyDataBaseClass.getReadableDatabase();

            if(objSQLiteDatabase!=null)
            {

                Cursor objCursor =  objSQLiteDatabase.rawQuery("select * from TransactionsTable",null);




                if(objCursor.getCount()==0){

                    Toast.makeText(this, "No data is returned", Toast.LENGTH_LONG).show();
                }

                else{

                    while (objCursor.moveToNext()) {

                        objModelClassArrayList.add(new ModelClass(objCursor.getInt(0),
                            objCursor.getFloat(1),
                                objCursor.getString(5),
                                objCursor.getString(2),
                                objCursor.getString(4),
                                objCursor.getString(3)

                        ));

                    }

                    objDatabaseRecyclerAdapter = new DatabaseRecyclerAdapter(objModelClassArrayList);
                    objRecyclerView.hasFixedSize();
                    objRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                    objRecyclerView.setAdapter(objDatabaseRecyclerAdapter);


                }

            }

            else   {
               
            }

        }

        catch (Exception e)
        {

        }
    }


}
