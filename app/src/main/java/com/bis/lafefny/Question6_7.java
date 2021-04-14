package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Question6_7 extends AppCompatActivity {
    private Button button_back_QS4;
    private Button button_next_QS4;
    private Button button_skip_QS4;
    private Button button_home_QS4;
    private Button button_pre_QS4;
    private Button button_account_QS4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question6_7);

        button_home_QS4 = (Button) findViewById(R.id.btn_questionnaire4_home);           //button homepage
        button_home_QS4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openHomepage(); }
        });

        button_pre_QS4 = (Button) findViewById(R.id.btn_questionnaire4_pre);           //button preferences
        button_pre_QS4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openpreferences(); }
        });

        button_next_QS4 = (Button) findViewById(R.id.btn_questionnaire4_next);           //button next
        button_next_QS4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openQuestion8_9(); }
        });

        button_skip_QS4 = (Button) findViewById(R.id.btn_questionnaire4_skip);           //button skip
        button_skip_QS4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openQuestion8_9(); }
        });

        button_back_QS4=(Button) findViewById(R.id.btn_questionnaire4_back);            //button back
        button_back_QS4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openQuestion4_5(); }

        });

        button_account_QS4=(Button) findViewById(R.id.btn_questionnaire4_user);            //button account
        button_account_QS4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openAccount14(); }

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

    public void openQuestion8_9(){
        Intent intent = new Intent(this, Question8_9.class);  //open question8_9
        startActivity(intent);
    }

    public void openAccount14(){
        Intent intent = new Intent(this, Account.class);  //open account
        startActivity(intent);
    }
}