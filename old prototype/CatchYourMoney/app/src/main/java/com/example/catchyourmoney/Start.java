package com.example.catchyourmoney;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getAuthentication()){
            Intent login = new Intent(this,Login.class);
            startActivity(login);
        }
        else{
            Intent home = new Intent(this,Home.class);
            startActivity(home);
        }
    }

    private boolean getAuthentication(){
        SharedPreferences sharedPreferences = getSharedPreferences(Home.SHARED_PREFS, MODE_PRIVATE);
        boolean authentication = sharedPreferences.getBoolean(Home.AUTHENTICATION,false);
        return authentication;
    }
}
