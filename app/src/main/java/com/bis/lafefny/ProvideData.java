package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProvideData extends AppCompatActivity {
    private Button button_home;
    private Button button_questionnaire;
    private Button button_preference;
    private Button button_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provide_data);

        button_home = (Button) findViewById(R.id.btn_home1);           //button homepage
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openHomepage(); }
        });

        button_questionnaire = (Button) findViewById(R.id.btn_questionnaire);           //button questionnaire
        button_questionnaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openquestionnaire(); }
        });

        button_preference = (Button) findViewById(R.id.btn_preference);           //button preferences
        button_preference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openPreferences(); }
        });

        button_back = (Button) findViewById(R.id.btn_back);           //button back
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openHomepage(); }
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
}