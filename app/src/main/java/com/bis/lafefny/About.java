package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class About extends AppCompatActivity {
    private Button button_back_About;
    private Button button_home_About;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        button_back_About = (Button) findViewById(R.id.btn_back); //button back
        button_back_About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomepage();
            }
        });

        button_home_About = (Button) findViewById(R.id.btn_home1_hp); //button homepage
        button_home_About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomepage();
            }
        });
    }


    public void openHomepage() {
        startActivity(new Intent(getApplicationContext(), Homepage.class));  //open homepage
    }
}