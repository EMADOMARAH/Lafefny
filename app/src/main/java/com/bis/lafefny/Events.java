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
    private Button button_sound_light;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        button_back = (Button) findViewById(R.id.btn_back_event); //button back
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomepage();
            }
        });

        button_home = (Button) findViewById(R.id.btn_home1_event); //button homepage
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomepage();
            }
        });

        button_sports = (Button) findViewById(R.id.btn_sports_events_event); //button sports events
        button_sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSport_Events();
            }
        });
        button_book_fair = (Button) findViewById(R.id.btn_cairo_international_book_fair_event); //button cairo book fair
        button_book_fair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCairoBookFair();
            }
        });
        button_sound_light = (Button) findViewById(R.id.btn_sound_and_light_show_event); //button spund and light show
        button_sound_light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSoundLightEvent();
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
        Intent intent = new Intent(this, CairoBookFair.class);  //open cairo book fair
        startActivity(intent);
    }
    public void openSoundLightEvent(){
        Intent intent = new Intent(this, SoundLightEvent.class);  //open sound and light show
        startActivity(intent);
    }
}