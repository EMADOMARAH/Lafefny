package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Questionnaire1 extends AppCompatActivity {
    private Button button_questionnaire1_back;
    private Button button_next;
//    private Button button_skip;
    private Button button_questionnaire1_home;
    private Button button_questionnaire1_pre;
    private Button button_questionnaire1_account;

    RadioGroup Q1;
    RadioButton answer1;
    private SharedPreferences Qanswers;
    String answer1String;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire1);

        Qanswers = getSharedPreferences("QAnswer" , Context.MODE_PRIVATE);
        Q1 = (RadioGroup) findViewById(R.id.Ques1_group);

        button_questionnaire1_home = (Button) findViewById(R.id.btn_questionnaire1_home);           //button homepage
        button_questionnaire1_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openHomepage(); }
        });

        button_questionnaire1_pre = (Button) findViewById(R.id.btn_questionnaire1_pre);           //button preferences
        button_questionnaire1_pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openpreferences(); }
        });

        button_next = (Button) findViewById(R.id.btn_next);           //button next
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getChoises()){
                    saveAnswerToPref();
                }
            }
        });


        button_questionnaire1_back=(Button) findViewById(R.id.btn_questionnaire1_back);            //button back
        button_questionnaire1_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { onBackPressed(); }

        });

        button_questionnaire1_account=(Button) findViewById(R.id.btn_questionnaire1_user);      //button user account
        button_questionnaire1_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openAccount11(); }

        });




    }

    private void saveAnswerToPref() {
        Qanswers.edit().putString("A1" , answer1String).apply();
        Qanswers.edit().commit();

        openQuestion2_3();
    }

    private boolean getChoises() {
        int answe1Id;
        try {
            answe1Id = Q1.getCheckedRadioButtonId();
            answer1 = findViewById(answe1Id);
            //the user choises in Preferences Screen
            answer1String = answer1.getText().toString();
        }catch (Exception e){
            Toast.makeText(this, "Answer The Question", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void openHomepage(){
        Intent intent = new Intent(this, Homepage.class);  //open homepage
        startActivity(intent);
    }
    public void openpreferences(){
        Intent intent = new Intent(this, preferences.class);  //open preferences
        startActivity(intent);
    }

    public void openProvideData(){
        Intent intent = new Intent(this, ProvideData.class);  //open Providedata
        startActivity(intent);
    }

    public void openQuestion2_3(){
        Intent intent = new Intent(this, Question2_3.class);  //open openQuestion2_3
        startActivity(intent);
    }

    public void openAccount11(){
        Intent intent = new Intent(this, Account.class);  //open open Account
        startActivity(intent);
    }
}