package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Question8_9 extends AppCompatActivity {
    private Button button_back_QS5;
    private Button button_next_QS5;
//    private Button button_skip_QS5;
    private Button button_home_QS5;
    private Button button_pre_QS5;
    private Button button_account_QS5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question8_9);

        button_home_QS5 = (Button) findViewById(R.id.btn_questionnaire5_home);           //button homepage
        button_home_QS5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openHomepage(); }
        });

        button_pre_QS5 = (Button) findViewById(R.id.btn_questionnaire5_pre);           //button preferences
        button_pre_QS5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openpreferences(); }
        });

        button_back_QS5=(Button) findViewById(R.id.btn_questionnaire5_back);            //button back
        button_back_QS5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openQuestion6_7(); }

        });

        button_next_QS5 = (Button) findViewById(R.id.btn_questionnaire5_next);           //button next
        button_next_QS5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openQuestion10_11(); }
        });

//        button_skip_QS5 = (Button) findViewById(R.id.btn_questionnaire5_skip);           //button skip
//        button_skip_QS5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) { openQuestion10_11(); }
//        });

        button_account_QS5 = (Button) findViewById(R.id.btn_questionnaire5_user);           //button account
        button_account_QS5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openAccount15(); }
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

    public void  openQuestion6_7(){
        Intent intent = new Intent(this, Question6_7.class);  //open Question6_7
        startActivity(intent);
    }

    public void openQuestion10_11(){
        Intent intent = new Intent(this, Question10_11.class);  //open Question10_11
        startActivity(intent);
    }

    public void openAccount15(){
        Intent intent = new Intent(this, Account.class);  //open account
        startActivity(intent);
    }
}