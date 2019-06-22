package com.example.catchyourmoney;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;




public class Login extends AppCompatActivity {


    TextView tvPassword;
    EditText editPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editPassword=findViewById(R.id.editPassword);
        tvPassword=findViewById(R.id.tvPassword);

        editPassword.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent keyevent) {
                //If the keyevent is a key-down event on the "enter" button
                if ((keyevent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    String password = editPassword.getText().toString();
                    if (password.equals(getPW()) ) {
                        Intent home = new Intent(getBaseContext(),Home.class);
                        startActivity(home);
                    }
                    else
                        Toast.makeText(getBaseContext(),"Wrong Password or PIN",Toast.LENGTH_LONG).show();
                    return true;
                }
                return false;
            }
        });

    }

    private String getPW() {
        SharedPreferences sharedPreferences = getSharedPreferences(Home.SHARED_PREFS, MODE_PRIVATE);
        String password = sharedPreferences.getString(Home.PASSWORD,"");
        return password;
    }

}
