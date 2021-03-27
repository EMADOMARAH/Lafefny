package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Question14_15 extends AppCompatActivity {
    private Button button_back;
    private Button button_home;
    private Button button_close;
    private Button button_preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question14_15);

        button_home = (Button) findViewById(R.id.btn_home1);           //button homepage
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openHomepage(); }
        });

        button_back=(Button) findViewById(R.id.btn_back);            //button back
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openQuestion12_13(); }
        });

        button_close=(Button) findViewById(R.id.btn_close);           //button Save & Close
        button_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openHomepage(); }

        });

        button_preferences = (Button) findViewById(R.id.btn_go_to_preferences);           //button preferences
        button_preferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openPreferences(); }
        });

    }
    public void openHomepage(){
        Intent intent = new Intent(this, Homepage.class);  //open homepage
        startActivity(intent);
    }
    public void  openQuestion12_13(){
        Intent intent = new Intent(this, Question12_13.class);  //open Question12_13
        startActivity(intent);
    }
    public void openPreferences(){
        Intent intent = new Intent(this, preferences.class);  //open preferences
        startActivity(intent); // not working
    }

}