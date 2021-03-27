package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Question14_15 extends AppCompatActivity {
    private Button button_back_QS8;
    private Button button_home_QS8;
    private Button button_close_QS8;
    private Button button_preferences_QS8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question14_15);

        button_home_QS8 = (Button) findViewById(R.id.btn_questionnaire8_home);           //button homepage
        button_home_QS8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openHomepage(); }
        });

        button_back_QS8=(Button) findViewById(R.id.btn_questionnaire8_back);            //button back
        button_back_QS8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openQuestion12_13(); }
        });

        button_close_QS8=(Button) findViewById(R.id.btn_questionnaire8_close);           //button Save & Close
        button_close_QS8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openHomepage(); }

        });

        button_preferences_QS8 = (Button) findViewById(R.id.btn_go_to_preferences);           //button preferences
        button_preferences_QS8.setOnClickListener(new View.OnClickListener() {
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