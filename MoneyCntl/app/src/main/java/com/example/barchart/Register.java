package com.example.barchart;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Register extends AppCompatActivity {

    private Button btnRegInit;
    private Button btnLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnLogIn = findViewById(R.id.logInPage);
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });

        btnRegInit = findViewById(R.id.registerInit);
        btnRegInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTransView();
            }
        });

    }

    public void openLogin(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openTransView(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
