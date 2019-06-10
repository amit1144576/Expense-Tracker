package com.ISEE2019_CODEPROS_team.MoneyCTRL.XTR_CLASSES_Constants;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ISEE2019_CODEPROS_team.MoneyCTRL.R;

public class InitialSetup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_setup);
    }


    // below method is for selecting one currency
    public void pickCurrency(View view) {
        RadioGroup radioGroup = findViewById(R.id.currency_category);
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.currency_aud:
                Toast.makeText(this, "AUD selected", Toast.LENGTH_SHORT).show();
                break;

            case R.id.currency_gbp:
                Toast.makeText(this, "GBP selected", Toast.LENGTH_SHORT).show();
                break;

            case R.id.currency_eur:
                Toast.makeText(this, "EUR selected", Toast.LENGTH_SHORT).show();
                break;

            case R.id.currency_jpy:
                Toast.makeText(this, "JPY selected", Toast.LENGTH_SHORT).show();
                break;

            case R.id.currency_chf:
                Toast.makeText(this, "CHF selected", Toast.LENGTH_SHORT).show();
                break;

            case R.id.currency_usd:
                Toast.makeText(this, "USD selected", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
