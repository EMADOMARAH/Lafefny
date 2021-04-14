package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Question2_3 extends AppCompatActivity {
    private Button button_back_ques2;
    private Button button_next_ques2;
    private Button button_skip_ques2;
    private Button button_home_ques2;
    private Button button_pre_ques2;
    private Button button_account_ques2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2_3);

        button_home_ques2 = (Button) findViewById(R.id.btn_questionnaire2_home);           //button homepage
        button_home_ques2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openHomepage(); }
        });

        button_next_ques2 = (Button) findViewById(R.id.btn_questionnaire2_next);           //button next
        button_next_ques2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openQuestion4_5(); }
        });

        button_skip_ques2 = (Button) findViewById(R.id.btn_questionnaire2_skip);           //button skip
        button_skip_ques2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openQuestion4_5(); }
        });

        button_back_ques2=(Button) findViewById(R.id.btn_questionnaire2_back);            //button back
        button_back_ques2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openQuestion1(); }

        });

        button_account_ques2=(Button) findViewById(R.id.btn_questionnaire2_user);            //button user account
        button_account_ques2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openAccount12(); }

        });

        button_pre_ques2=(Button) findViewById(R.id.btn_questionnaire2_pre);            //button preferences
        button_pre_ques2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openpreferences(); }

        });

    }

    public void openHomepage(){
        Intent intent = new Intent(this, Homepage.class);  //open homepage
        startActivity(intent);
    }

    public void openpreferences(){
        Intent intent = new Intent(this, preferences.class);  //open preferences
        startActivity(intent);
    }

    public void  openQuestion4_5(){
        Intent intent = new Intent(this, Question4_5.class);  //open Question4_5
        startActivity(intent);
    }

    public void openQuestion1(){
        Intent intent = new Intent(this, Questionnaire1.class);  //open questionnaire1
        startActivity(intent);
    }

    public void openAccount12(){
        Intent intent = new Intent(this, Account.class);  //open account
        startActivity(intent);
    }
}