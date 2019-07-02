package com.example.prototype1;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.security.PublicKey;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public EditText editTextEmail1;
    public EditText editTextPassword1;
    public TextView txtview;
    public TextView textViewLogin;
    public TextView textViewGuest;
    public ProgressDialog progressDialog;
    public static boolean guestFlag = false;
    private FirebaseAuth firebaseAuth;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);// activity_main is corresponding XML file

        txtview = (TextView) findViewById(R.id.textViewRegisterHere);
        textViewLogin = (TextView) findViewById(R.id.textViewLogin);
        editTextEmail1 = (EditText) findViewById(R.id.editTextEmail1);
        editTextPassword1 = (EditText) findViewById(R.id.editTextPassword1);
        textViewGuest = (TextView) findViewById(R.id.textViewGuest);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth .getCurrentUser()!=null){

            //go to add tranaction page if user is already loggedin
            startActivity(new Intent(getApplicationContext(), AddTransactions.class));
        }

        textViewLogin.setOnClickListener(this);
        textViewGuest.setOnClickListener(this);

        txtview.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                 Intent intent = new Intent(MainActivity.this,
                        Register.class);
                startActivity(intent); // startActivity allow you to move
            }
        });
    }



    private void userLogin(){

        String email = editTextEmail1.getText().toString().trim();
        String password = editTextPassword1.getText().toString().trim();


        if(email.isEmpty()){
            editTextEmail1.setError("Email is required");
            editTextEmail1.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword1.setError("Password is required");
            editTextPassword1.requestFocus();
            return;

        }

        progressDialog.setMessage("Authenticating..");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this,new OnCompleteListener<AuthResult>(){

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                progressDialog.dismiss();
                if(task.isSuccessful()){
                    guestFlag = false;
                    finish();
                    startActivity(new Intent(getApplicationContext(), AddTransactions.class));
                }


            }

        });





    }


    public void onClick(View view) {

        if (view == textViewLogin){
           userLogin();

        }
        if(view == txtview) {
            Intent intent = new Intent(MainActivity.this,
                    Register.class);
            startActivity(intent);
        }

        if(view == textViewGuest) {

            firebaseAuth.signOut();
            finish();
            guestFlag = true;
            Intent intent = new Intent(MainActivity.this,
                    AddTransactions.class);
            startActivity(intent);
        }
    }
}

