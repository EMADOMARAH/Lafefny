package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Questionnaire1 extends AppCompatActivity {
    private Button button_questionnaire1_back;
    private Button button_next;
    private Button button_skip;
    private Button button_questionnaire1_home;
    private Button button_questionnaire1_account;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire1);

        button_questionnaire1_home = (Button) findViewById(R.id.btn_questionnaire1_home);           //button homepage
        button_questionnaire1_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openHomepage(); }
        });

        button_next = (Button) findViewById(R.id.btn_next);           //button next
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openQuestion2_3(); }
        });

        button_skip = (Button) findViewById(R.id.btn_skip);           //button skip
        button_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openQuestion2_3(); }
        });

        button_questionnaire1_back=(Button) findViewById(R.id.btn_questionnaire1_back);            //button back
        button_questionnaire1_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openProvideData(); }

        });

        button_questionnaire1_account=(Button) findViewById(R.id.btn_questionnaire1_user);      //button user account
        button_questionnaire1_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openAccount11(); }

        });


    }
    public void openHomepage(){
        Intent intent = new Intent(this, Homepage.class);  //open homepage
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