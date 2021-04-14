package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Question10_11 extends AppCompatActivity {
    private Button button_back_QS6;
    private Button button_next_QS6;
    private Button button_skip_QS6;
    private Button button_home_QS6;
    private Button button_pre_QS6;
    private Button button_account_QS6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question10_11);

        button_home_QS6 = (Button) findViewById(R.id.btn_questionnaire6_home);           //button homepage
        button_home_QS6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openHomepage(); }
        });

        button_pre_QS6 = (Button) findViewById(R.id.btn_questionnaire6_pre);           //button preferences
        button_pre_QS6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openpreferences(); }
        });

        button_back_QS6=(Button) findViewById(R.id.btn_questionnaire6_back);            //button back
        button_back_QS6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openQuestion8_9(); }
        });

        button_next_QS6 = (Button) findViewById(R.id.btn_questionnaire6_next);           //button next
        button_next_QS6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openQuestion12_13(); }
        });

        button_skip_QS6 = (Button) findViewById(R.id.btn_questionnaire6_skip);           //button skip
        button_skip_QS6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openQuestion12_13(); }
        });

        button_account_QS6 = (Button) findViewById(R.id.btn_questionnaire6_user);           //button account
        button_account_QS6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openAccount16(); }
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
    public void  openQuestion8_9(){
        Intent intent = new Intent(this, Question8_9.class);  //open Question8_9
        startActivity(intent);
    }

    public void openQuestion12_13(){
        Intent intent = new Intent(this, Question12_13.class);  //open Question12_13
        startActivity(intent);
    }

    public void openAccount16(){
        Intent intent = new Intent(this, Account.class);  //open account
        startActivity(intent);
    }
}