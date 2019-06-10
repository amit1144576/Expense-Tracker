package com.ISEE2019_CODEPROS_team.MoneyCTRL.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import com.ISEE2019_CODEPROS_team.MoneyCTRL.R;
import com.ISEE2019_CODEPROS_team.MoneyCTRL.XTR_CLASSES_Constants.DatePickerFragment;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DateFormat;
import java.util.Calendar;


public class AddNewTrans extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    public static final String EXTRA_AMOUNT =
            "com.ISEE2019_CODEPROS_team.MoneyCTRL.EXTRA_AMOUNT";
    public static final String EXTRA_DATE =
            "com.ISEE2019_CODEPROS_team.MoneyCTRL.EXTRA_DATE";
    public static final String EXTRA_CATEGORY =
            "com.ISEE2019_CODEPROS_team.MoneyCTRL.EXTRA_CATEGORY";
    public static final String EXTRA_DESCRIPTION =
            "com.ISEE2019_CODEPROS_team.MoneyCTRL.EXTRA_DESCRIPTION";

    private EditText transAmount;
    private EditText transDescription;
    private EditText transDate;
    private EditText transCategory;

    //button to add new category
    Button button;
    ConstraintLayout addNewTransLayout;

    // back press exit
    private long backPressedTime;
    private Toast backToast;

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText(getBaseContext(), "Press back again to cancel", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }


    //defining date picker
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.MEDIUM).format(c.getTime());

        TextView textView = findViewById(R.id.transaction_date);
        textView.setText(currentDateString);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_trans);

        transAmount = findViewById(R.id.new_amount);
        transDescription = findViewById(R.id.new_trans_desc);
        transDate = findViewById(R.id.editText2);
        transCategory = findViewById(R.id.editText3);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("New Transaction");

        //button calls the date picker
        TextView transaction_date = findViewById(R.id.transaction_date);
        transaction_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datepicker = new DatePickerFragment();
                datepicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        // add new category as radiobutton
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton newCategory = new RadioButton(AddNewTrans.this);
                addNewTransLayout = findViewById(R.id.AddNewTransLayout);
                newCategory.setLayoutParams(new ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.MATCH_PARENT,
                        ConstraintLayout.LayoutParams.MATCH_PARENT
                ));
                addNewTransLayout.addView(newCategory);
            }
        });
    }

    // xxxxx
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_trans_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveTrans();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }


    private void saveTrans() {
        Double amount = Double.parseDouble(transAmount.getText().toString());
        String description = transDescription.getText().toString();
        String t_date = transDate.getText().toString();
        String category = transCategory.getText().toString();

        //if (amount.trim().isEmpty() || description.trim().isEmpty() || t_date.trim().isEmpty())
        if (description.trim().isEmpty() ) {
            Toast.makeText(this, "Please fill amount, description and pick a date", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_AMOUNT, amount);
        data.putExtra(EXTRA_CATEGORY, category);
        data.putExtra(EXTRA_DATE, t_date);
        data.putExtra(EXTRA_DESCRIPTION, description);
        setResult(RESULT_OK, data);
        finish();
    }


    //selecting categories of expenses, the toast messages need to be changed later into some other operations

    public void selectExpenseCategory(View view) {
        RadioGroup radioGroup = findViewById(R.id.group_expense_category);
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.expense_category_1:
                Toast.makeText(this, "No category selected", Toast.LENGTH_SHORT).show();
                break;

            case R.id.expense_category_2:
                Toast.makeText(this, "Food and Drinks selected", Toast.LENGTH_SHORT).show();
                break;

            case R.id.expense_category_3:
                Toast.makeText(this, "Shopping selected", Toast.LENGTH_SHORT).show();
                break;

            case R.id.expense_category_4:
                Toast.makeText(this, "Transportation selected", Toast.LENGTH_SHORT).show();
                break;

            case R.id.expense_category_5:
                Toast.makeText(this, "Leisure selected", Toast.LENGTH_SHORT).show();
                break;

            case R.id.expense_category_6:
                Toast.makeText(this, "Health selected", Toast.LENGTH_SHORT).show();
                break;

            case R.id.expense_category_7:
                Toast.makeText(this, "Utilities selected", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
