package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Account extends AppCompatActivity {
    private Button button_acc_profile;
    private Button button_acc_promocode;
    private Button button_acc_preferences;
    private Button button_acc_questionnaire;
  //  private Button button_acc_help;
    private Button button_acc_back;
    private Button button_acc_sign_out;
    private Button button_acc_home;

    SharedPreferences authPreferences ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        authPreferences = getSharedPreferences("Lafefny_App" ,Context.MODE_PRIVATE);

        button_acc_back = (Button) findViewById(R.id.btn_acc_back); //button back
        button_acc_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomepage();
            }
        });

        button_acc_profile = (Button) findViewById(R.id.btn_go_to_profile); //button profile
        button_acc_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openprofile();
            }
        });

        button_acc_promocode = (Button) findViewById(R.id.btn_go_to_promo); //button promocode
        button_acc_promocode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openpromocode();
            }
        });

        button_acc_preferences = (Button) findViewById(R.id.btn_go_to_pref); //button preferences
        button_acc_preferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openpreferences();
            }
        });

        button_acc_questionnaire = (Button) findViewById(R.id.btn_go_to_ques); //button questionnaire
        button_acc_questionnaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openquestionnaire();
            }
        });



        button_acc_home = (Button) findViewById(R.id.btn_acc_home); //button home
        button_acc_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomepage();
            }
        });
    }
    public void openHomepage(){
        Intent intent = new Intent(this, Homepage.class);  //open home page
        startActivity(intent);
    }
    public void openprofile(){
        Intent intent = new Intent(this, profile.class);  //open profile
        startActivity(intent);
    }

    public void openpromocode(){
        Intent intent = new Intent(this, Promocode.class);  //open promocode
        startActivity(intent);
    }

    public void openpreferences(){
        Intent intent = new Intent(this, preferences.class);  //open preferences
        startActivity(intent);
    }

    public void openquestionnaire(){
        Intent intent = new Intent(this, Questionnaire1.class);  //open questionnaire
        startActivity(intent);
    }


    public void openSignIn(){
        Intent intent = new Intent(this, Sign_In.class);  //open sign in
        startActivity(intent);
    }

    public void AccountOnClick(View view) {
        switch (view.getId()){
            case R.id.btn_sign_out:
                signOut();
                break;
        }
    }

    private void signOut() {
        SharedPreferences.Editor editor = authPreferences.edit();
        editor.clear();
        editor.commit();
        Toast.makeText(this, "HERE", Toast.LENGTH_SHORT).show();

        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getApplicationContext() , MainActivity2.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finishAffinity();



    }
}