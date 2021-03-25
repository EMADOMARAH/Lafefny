package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Events extends AppCompatActivity {
    private Button button_back;
    private Button button_home;
    private Button button_sports;
    private Button button_book_fair;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        button_back = (Button) findViewById(R.id.btn_back); //button back
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomepage();
            }
        });

        button_home = (Button) findViewById(R.id.btn_home1); //button homepage
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomepage();
            }
        });

        button_sports = (Button) findViewById(R.id.btn_sports_events); //button sports events
        button_sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSport_Events();
            }
        });
        button_book_fair = (Button) findViewById(R.id.btn_cairo_international_book_fair); //button cairo book fair
        button_book_fair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCairoBookFair();
            }
        });
    }
    public void openHomepage(){
        Intent intent = new Intent(this, Homepage.class);  //open homepage
        startActivity(intent);
    }
    public void openSport_Events(){
        Intent intent = new Intent(this, Sport_Events.class);  //open sports events
        startActivity(intent);
    }
    public void openCairoBookFair(){
        Intent intent = new Intent(this, CairoBookFair.class);  //open cairo book faire
        startActivity(intent);
    }
}