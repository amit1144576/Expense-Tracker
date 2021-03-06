package com.ISEE2019_CODEPROS_team.MoneyCTRL.View;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ISEE2019_CODEPROS_team.MoneyCTRL.R;

public class MainActivity extends AppCompatActivity {

    private Button btnReg;
    private Button btnSkipReg;
    private Button btnSignIn;
    EditText userNameText;
    EditText passwordText;


    // Back button press exit
    private long backPressedTime;
    private Toast backToast;
    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }

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
                 validate(userNameText.getText().toString(),passwordText.getText().toString());
                 openTransView();
            }
        });

        userNameText = findViewById(R.id.username);
        passwordText = findViewById(R.id.password);


    }

    public void openRegister(){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public void openTransView(){
        Intent intent = new Intent(this, TransView.class);
        startActivity(intent);
    }

    public MainActivity(Context context){

    }
    public String validate(String userName, String password){

        if(userName.equals("user") && password.equals("user"))
            return "Login was successful";
        else
            return "Invalid login!";
    }

    public void openDatePickerFragment(){
        Intent intent = new Intent(this, AddNewTrans.class);
        startActivity(intent);
    }
}
