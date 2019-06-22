package com.example.catchyourmoney;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import static android.app.PendingIntent.FLAG_CANCEL_CURRENT;
import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;

public class AddTransaction extends AppCompatActivity {

    Spinner spinner, spinner2, spinner3;
    EditText comments, amount;
    Button save;
    CalendarView calendarView;
    TextView Datetext;
    /// Helpers for date in database////
    int mDay=0, mMonth = 0, mYear = 0;
    CheckBox recurrent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        List<String> categories = Home.categoryDB.categoryDao().getCategoryNames();
        comments = findViewById(R.id.commentsText);
        amount = findViewById(R.id.amountText);
        save = findViewById(R.id.saveButton);
        Datetext = findViewById(R.id.Dateview);
        calendarView = findViewById(R.id.calendarView);
        recurrent = findViewById(R.id.recurrentBox);

        calendarView.setVisibility(View.GONE);

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.types, android.R.layout.simple_list_item_1);

        spinner2 = findViewById(R.id.spinner2);
        //ArrayAdapter<CharSequence> adapter2 = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_list_item_1, categories);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categories);

        spinner3 = findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.methods, android.R.layout.simple_list_item_1);

        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter2);
        spinner3.setAdapter(adapter3);

        Datetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View vC = calendarView;
                calendarView.setVisibility(View.VISIBLE);
                calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                        String date = dayOfMonth + "." + (month + 1) + "." + year;
                        mDay = dayOfMonth;
                        mMonth = month+1;
                        mYear = year;
                        Datetext.setText(date);
                        calendarView.setVisibility(View.GONE);
                    }
                });
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tranid = UUID.randomUUID().toString();
                String mtype = spinner.getSelectedItem().toString();
                String mCatego = spinner2.getSelectedItem().toString();
                String mMethod = spinner3.getSelectedItem().toString();
                String mcomment = comments.getText().toString();
                boolean mRecurrent = recurrent.isChecked();

                if (amount.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "You need to add amount", Toast.LENGTH_SHORT).show();
                }
                else{
                    float mamount = Float.parseFloat(amount.getText().toString());
                    Transactions transactions = new Transactions();
                    transactions.setTransactionID(tranid);
                    transactions.setType(mtype);
                    transactions.setCategory(mCatego);
                    transactions.setMethod(mMethod);
                    transactions.setDay(mDay);
                    transactions.setMonth(mMonth);
                    transactions.setYear(mYear);
                    transactions.setComment(mcomment);
                    transactions.setAmount(mamount);
                    if (mRecurrent){
                    transactions.setRecurrent(true);
                    int mJobId=getJobID();
                    transactions.setRecurrentJob(mJobId);
                    scheduleJob(tranid,mJobId);
                    mJobId++;
                    saveJobID(mJobId);
                    }
                    else { transactions.setRecurrent(false);
                    transactions.setRecurrentJob(0);}
                    Home.appDB.appDao().addTransaction(transactions);
                    Log.d("Recurrent?","1");
                    Toast.makeText(getApplicationContext(), "Transaction added", Toast.LENGTH_SHORT).show();
                }

                Datetext.setText("Click here to change the date");
                comments.setText("");
                amount.setText("");
                mDay = 0;
                mMonth = 0;
                mYear = 0;
            }
        });
    }

    public String[] getCategories() {
        SharedPreferences sharedPreferences = getSharedPreferences(Home.SHARED_PREFS, MODE_PRIVATE);
        String[] array = sharedPreferences.getStringSet(Home.CATEGORIES, new HashSet<String>(Arrays.asList(getResources().getStringArray(R.array.categories)))).toArray(new String[0]);
        Arrays.sort(array);
        return array;
    }

    public void scheduleJob (String tranid, int jobId){
        /*ComponentName componentName = new ComponentName(this,recurrentJobService.class);
        PersistableBundle pb = new PersistableBundle();
        pb.putString("Id",tranid);
        pb.putInt("first",1);
        JobInfo info = new JobInfo.Builder(jobId,componentName).setPersisted(true)
                        .setPeriodic(15*60*1000).setExtras(pb).build();
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.schedule(info);
        */
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getApplicationContext(),RecurrentAlarm.class);
        intent.putExtra("Id",tranid);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),jobId,intent,FLAG_CANCEL_CURRENT);
        long firstMillis = System.currentTimeMillis();
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,firstMillis + AlarmManager.INTERVAL_FIFTEEN_MINUTES,AlarmManager.INTERVAL_FIFTEEN_MINUTES,pendingIntent);

    }

    public void cancelJob(int jobId){
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.cancel(jobId);
    }

    private void saveJobID(int JobID) {
        SharedPreferences sharedPreferences = getSharedPreferences(Home.SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Home.JOBID, JobID);
        editor.apply();

    }

    private int getJobID() {
        SharedPreferences sharedPreferences = getSharedPreferences(Home.SHARED_PREFS, MODE_PRIVATE);
        int JobID = sharedPreferences.getInt(Home.JOBID, 1);
        return JobID;
    }
}

