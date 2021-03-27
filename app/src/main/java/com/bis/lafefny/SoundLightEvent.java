package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SoundLightEvent extends AppCompatActivity {
    private Button button_back;
    private Button button_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_light_event);

        button_back = (Button) findViewById(R.id.btn_back); //button back
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEvents();
            }
        });

        button_home = (Button) findViewById(R.id.btn_home1); //button homepage
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomepage();
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
}