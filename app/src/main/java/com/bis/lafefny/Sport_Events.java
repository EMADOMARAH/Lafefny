package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Sport_Events extends AppCompatActivity {
//    private Button button_back_Sport;
    private Button button_home_Sport;
    private Button button_pre_Sport;
    private Button button_running_Sport;
    private Button button_account_sports_events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport__events);
//        button_back_Sport = (Button) findViewById(R.id.btn_back_sport); //button back
//        button_back_Sport.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openEvents();
//            }
//        });

        button_home_Sport = (Button) findViewById(R.id.btn_home1_sport); //button homepage
        button_home_Sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomepage();
            }
        });

        button_home_Sport = (Button) findViewById(R.id.btn_home1_sport); //button homepage
        button_home_Sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomepage();
            }
        });

        button_account_sports_events = (Button) findViewById(R.id.btn_user_sport); //button account
        button_account_sports_events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openaccount();
            }
        });

        button_running_Sport = (Button) findViewById(R.id.btn_running); //button running event
        button_running_Sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRunningEvent();
            }
        });


    }
    public void openEvents(){
        Intent intent = new Intent(this, Events.class);  //button back
        startActivity(intent);
    }

    public void openaccount(){
        Intent intent = new Intent(this, Account.class);  //button account
        startActivity(intent);
    }

    public void openpreferences(){
        Intent intent = new Intent(this, preferences.class);  //button preferences
        startActivity(intent);
    }
    public void openHomepage(){
        Intent intent = new Intent(this, Homepage.class);  //open homepage
        startActivity(intent);
    }
    public void openRunningEvent(){
        Intent intent = new Intent(this, RunningEvent.class);  //open running event
        startActivity(intent);
    }

    public void SportsEventsOnClick(View view) {
        switch (view.getId()){
            case R.id.txt_location_running:
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com/maps/place/Wadi+Degla+Protectorate/@29.9598739,31.330732,15z/data=!4m5!3m4!1s0x0:0xdaa75ea3a1e28aec!8m2!3d29.9598739!4d31.330732"));
                startActivity(intent);
                break;
        }
    }
}