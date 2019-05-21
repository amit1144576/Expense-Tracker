package com.example.barchart;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnReg;
    private Button btnSkipReg;
    private Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnReg = findViewById(R.id.register);
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegister();
            }
        });

        btnSkipReg = findViewById(R.id.skipRegisteration);
        btnSkipReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTransView();
            }
        });

        btnSignIn = findViewById(R.id.signIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTransView();
            }
        });

    }

    public void openRegister(){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public void openTransView(){
        Intent intent = new Intent(this, TransView.class);
        startActivity(intent);
    }
}
