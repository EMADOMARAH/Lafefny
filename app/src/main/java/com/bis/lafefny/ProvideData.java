package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProvideData extends AppCompatActivity {
    private Button button_home_data;
    private Button button_pre_data;
    private Button button_account_data;
    private Button button_questionnaire_data;
    private Button button_preference_data;
    private Button button_back_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provide_data);

        button_home_data = (Button) findViewById(R.id.btn_data_home);           //button homepage
        button_home_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openHomepage(); }
        });

        button_questionnaire_data = (Button) findViewById(R.id.btn_questionnaire);           //button questionnaire
        button_questionnaire_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openquestionnaire(); }
        });

        button_preference_data = (Button) findViewById(R.id.btn_preference);           //button preferences
        button_preference_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openPreferences(); }
        });

        button_pre_data = (Button) findViewById(R.id.btn_data_pre);           //button preferences
        button_pre_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openPreferences(); }
        });

        button_back_data = (Button) findViewById(R.id.btn_data_back);           //button back
        button_back_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openHomepage(); }
        });

        button_account_data = (Button) findViewById(R.id.btn_data_user);           //button user account
        button_account_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openAccount10(); }
        });
    }
    public void openHomepage(){
        Intent intent = new Intent(this, Homepage.class);  //open homepage
        startActivity(intent);
    }
    public void openquestionnaire(){
        Intent intent = new Intent(this, Questionnaire1.class);  //open Questionnaire
        startActivity(intent);
    }
    public void openPreferences(){
        Intent intent = new Intent(this, preferences.class);  //open preferences
        startActivity(intent);
    }

    public void openAccount10(){
        Intent intent = new Intent(this, Account.class);  //open account
        startActivity(intent);
    }
}