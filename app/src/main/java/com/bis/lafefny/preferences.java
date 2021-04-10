package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class preferences extends AppCompatActivity {
    private Button button_home_preferences;
    private Button button_account_preferences;
    private Button button_close_preferences;
    private Button button_back_preferences;
    private Button button_questionnaire_preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        button_home_preferences = (Button) findViewById(R.id.btn_preferences_home);           //button homepage
        button_home_preferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openHomepage(); }
        });

        button_close_preferences=(Button) findViewById(R.id.btn_close);           //button Save & Close
        button_close_preferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openHomepage(); }

        });

        button_back_preferences=(Button) findViewById(R.id.btn_prefe_back);            //button back
        button_back_preferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openProvideData(); }

        });

        button_questionnaire_preferences = (Button) findViewById(R.id.btn_go_to_questionnaire);           //button questionnaire
        button_questionnaire_preferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openquestionnaire(); }
        });

        button_account_preferences = (Button) findViewById(R.id.btn_preferences_user);           //button user account
        button_account_preferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openAccount9(); }
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
    public void openquestionnaire(){
        Intent intent = new Intent(this, Questionnaire1.class);  //open Questionnaire
        startActivity(intent);
    }

    public void openAccount9(){
        Intent intent = new Intent(this, Account.class);  //open account
        startActivity(intent);
    }
}