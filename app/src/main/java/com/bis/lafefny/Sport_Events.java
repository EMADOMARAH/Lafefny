package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Sport_Events extends AppCompatActivity {
    private Button button_back_Sport;
    private Button button_home_Sport;
    private Button button_running_Sport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport__events);
        button_back_Sport = (Button) findViewById(R.id.btn_back_sport); //button back
        button_back_Sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEvents();
            }
        });

        button_home_Sport = (Button) findViewById(R.id.btn_home1_sport); //button homepage
        button_home_Sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomepage();
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
    public void openHomepage(){
        Intent intent = new Intent(this, Homepage.class);  //open homepage
        startActivity(intent);
    }
    public void openRunningEvent(){
        Intent intent = new Intent(this, RunningEvent.class);  //open running event
        startActivity(intent);
    }
}