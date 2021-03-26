package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class preferences extends AppCompatActivity {
    private Button button_home;
    private Button button_close;
    private Button button_back;
    private Button button_questionnaire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        button_home = (Button) findViewById(R.id.btn_home1);           //button homepage
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openHomepage(); }
        });

        button_close=(Button) findViewById(R.id.btn_close);           //button Save & Close
        button_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openHomepage(); }

        });

        button_back=(Button) findViewById(R.id.btn_back);            //button back
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openProvideData(); }

        });

        button_questionnaire = (Button) findViewById(R.id.btn_questionnaire);           //button questionnaire
        button_questionnaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openquestionnaire(); }
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
}