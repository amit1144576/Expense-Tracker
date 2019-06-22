package com.example.catchyourmoney;


import android.app.slice.SliceItem;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.graphics.drawable.Icon;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnKeyListener;
import android.view.View.OnClickListener;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static com.example.catchyourmoney.Home.CURRENCY;


public class Settings extends AppCompatActivity implements View.OnClickListener, PopUpPW.DialogListener {

    TextView tvCurrency, tvAdd, tvDelete, tvAuthentication;
    Spinner spinCurrency, spinDelete, spinner_icons;
    EditText editAdd;
    Button categoryPlusIcon;
    Switch switchAuthentication;
    ImageButton iBtnDelete;
    Button emailBtn;

    private String currency;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


            initList();

            Spinner spinnerIcons = findViewById(R.id.spinner_icons);

            mAdapter = new IconAdapter(this, mIconList);
            spinnerIcons.setAdapter(mAdapter);

            spinnerIcons.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    IconItem clickedItem = (IconItem) parent.getItemAtPosition(position);

                    Toast.makeText(Settings.this, clickedItem + " selected", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


            tvCurrency = findViewById(R.id.tvCurrency);
        tvAdd = findViewById(R.id.tvAdd);
        tvDelete = findViewById(R.id.tvDelete);
        tvAuthentication = findViewById(R.id.tvAuthentication);
        spinCurrency = findViewById(R.id.spinCurrency);
        spinDelete = findViewById(R.id.spinDelete);
        editAdd = findViewById(R.id.editAdd);
        switchAuthentication = findViewById(R.id.switchAuthentication);
        switchAuthentication.setChecked(getSwitch());
        iBtnDelete = findViewById(R.id.iBtnDelete);
        categoryPlusIcon = findViewById(R.id.categoryPlusIcon);
        spinner_icons = findViewById(R.id.spinner_icons);
        emailBtn = findViewById(R.id.emailBtn);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.currencies, android.R.layout.simple_list_item_1);
        spinCurrency.setAdapter(adapter);
        spinner_icons.setAdapter(mAdapter);
        spinCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        currency = "Euro";
                        saveCurrency(currency);
                        Toast.makeText(getBaseContext(), "Your currency is now Euro", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        currency = "Dollar";
                        saveCurrency(currency);
                        Toast.makeText(getBaseContext(), "Your currency is now Dollar", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        currency = "Peso";
                        saveCurrency(currency);
                        Toast.makeText(getBaseContext(), "Your currency is now Peso", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        List<String> categories = Home.categoryDB.categoryDao().getCategoryNames();
        final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categories);
        spinDelete.setAdapter(adapter2);


        iBtnDelete.setOnClickListener(this);
        switchAuthentication.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    saveSwitch(isChecked);
                    openDialog();

                } else
                    saveSwitch(isChecked);


            }
        });
         categoryPlusIcon.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                      int mIcon = spinner_icons.getSelectedItemPosition();
                      String mIconData = adapter2.getItem(mIcon);
                     String mcategoryedit = editAdd.getText().toString();
                 if ((mIconData.equals("")) || mcategoryedit.equals("")) {
                     Toast.makeText(getApplicationContext(), "You need to type a category", Toast.LENGTH_SHORT).show();
                 }
                 else{
                     Categories cList = new Categories();
                     cList.setCategoryName(mcategoryedit);
                     cList.setCategoryIcon(mIconData);
                     Home.categoryDB.categoryDao().addCategory(cList);
                     editAdd.setText("");
                     Toast.makeText(getBaseContext(), "Category added", Toast.LENGTH_LONG).show();
                     onRestart();

                 }
             }
         });
        emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mType = "Expense";
                float mAmount;
                List<Transactions> transType;
                mAmount = 0;
                transType = Home.appDB.appDao().getTransaction(mType, mAmount);

                String Info = "";
                float sumExpenses = 0;
                for (Transactions transactions1 : transType) {
                    String id = transactions1.getTransactionID();
                    String type = transactions1.getType();
                    String categ = transactions1.getCategory();
                    int day = transactions1.getDay();
                    int month = transactions1.getMonth();
                    int year = transactions1.getYear();
                    String method = transactions1.getMethod();
                    String comment = transactions1.getComment();
                    float amount = transactions1.getAmount();

                    sumExpenses += amount;
                    Info += "\n\n" + "Id: " + id + "\n Type: " + type + "\n Category: " + categ + "\n Date: " + day + "." + month + "." + year +
                            "\n Method: " + method + "\n Comment: " + comment + "\n Amount: " + amount + getCurrency();
                }

                List<Transactions> income = Home.appDB.appDao().getTransaction("Income");

                float sumIncome = 0, total;

                for (Transactions transactions : income){
                    sumIncome += transactions.getAmount();
                }

                total = sumIncome - sumExpenses;

                Info += "\n\n\nINITIAL BALANCE: " + sumIncome + getCurrency();
                Info += "\nTOTAL EXPENSES: " + sumExpenses + getCurrency();
                Info += "\nCURRENT BALANCE: " + total + getCurrency();
                emailReport(Info);
            }
        });
    }

    private String getCurrency() {
        SharedPreferences sharedPreferences = getSharedPreferences(Home.SHARED_PREFS, MODE_PRIVATE);
        return sharedPreferences.getString(Home.CURRENCY, " €");
    }

    private String[] getCategories() {
        SharedPreferences sharedPreferences = getSharedPreferences(Home.SHARED_PREFS, MODE_PRIVATE);
        String[] array = sharedPreferences.getStringSet(Home.CATEGORIES, new HashSet<String>(Arrays.asList(getResources().getStringArray(R.array.categories)))).toArray(new String[0]);
        Arrays.sort(array);
        return array;
    }

    private void saveCategories(Set<String> set) {
        SharedPreferences sharedPreferences = getSharedPreferences(Home.SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(Home.CATEGORIES, set);
        editor.apply();

    }

    private void saveCurrency(String currency) {
        SharedPreferences sharedPreferences = getSharedPreferences(Home.SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(CURRENCY, currency);
        editor.apply();

    }

    private void saveSwitch(boolean switchState) {
        SharedPreferences sharedPreferences = getSharedPreferences(Home.SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Home.AUTHENTICATION, switchState);
        editor.apply();

    }

    private boolean getSwitch() {
        SharedPreferences sharedPreferences = getSharedPreferences(Home.SHARED_PREFS, MODE_PRIVATE);
        boolean switchState = sharedPreferences.getBoolean(Home.AUTHENTICATION, false);
        return switchState;
    }

    private void savePW(String pw) {
        SharedPreferences sharedPreferences = getSharedPreferences(Home.SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Home.PASSWORD, pw);
        editor.apply();
    }

    private void openDialog() {
        PopUpPW popUp = new PopUpPW();
        popUp.show(getSupportFragmentManager(), "popUp");
    }


    @Override
    public void onClick(View v) {
        String position = spinDelete.getSelectedItem().toString();
        Categories delete = Home.categoryDB.categoryDao().getCategories(position);
        Home.categoryDB.categoryDao().deleteCategory(delete);
        Toast.makeText(getBaseContext(), "Category deleted", Toast.LENGTH_LONG).show();
        onRestart();
    }

    @Override
    public void getPW(String password) {
        savePW(password);
    }

    @Override
    public void resetSwitch() {
        switchAuthentication.setChecked(false);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

    private void emailReport(String info){
        String subject = "Catch your Money Expense Report!";
        String message = info;

        Toast.makeText(getApplicationContext(), "Opening mail, please click send to complete operation!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"noobs.apk@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.setType("message/rfc822");

        try{
            startActivity(Intent.createChooser(intent, "Choose an email client:"));
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "No email client in device!!", Toast.LENGTH_SHORT).show();
        }
    }
    private ArrayList<IconItem> mIconList;
    private IconAdapter mAdapter;

    private void initList() {
        mIconList = new ArrayList<>();
        mIconList.add(new IconItem(R.drawable.education));
        mIconList.add(new IconItem(R.drawable.food));
        mIconList.add(new IconItem(R.drawable.car_insurance));
        mIconList.add(new IconItem(R.drawable.grocery));
        mIconList.add(new IconItem(R.drawable.health_insurance));
        mIconList.add(new IconItem(R.drawable.loan));
        mIconList.add(new IconItem(R.drawable.rent));
        mIconList.add(new IconItem(R.drawable.shopping));
        mIconList.add(new IconItem(R.drawable.toiletries));
        mIconList.add(new IconItem(R.drawable.travelling));
        mIconList.add(new IconItem(R.drawable.others));

    }

}
