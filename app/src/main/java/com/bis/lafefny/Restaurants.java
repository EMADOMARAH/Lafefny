package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Restaurants extends AppCompatActivity {
    private Button button_back;
    private Button button_home;
    private Button button_the_smokery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        button_back = (Button) findViewById(R.id.btn_back_res); //button back
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEnt_Categories();
            }
        });

        button_home = (Button) findViewById(R.id.btn_home1_res); //button homepage
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomepage();
            }
        });
        button_the_smokery = (Button) findViewById(R.id.btn_smokery); //button smokery
        button_the_smokery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTheSmokery();
            }
        });
    }
    public void openEnt_Categories(){
        Intent intent = new Intent(this, Ent_Categories.class);  //open back
        startActivity(intent);
    }
    public void openHomepage(){
        Intent intent = new Intent(this, Homepage.class);  //open homepage
        startActivity(intent);
    }
    public void openTheSmokery(){
        Intent intent = new Intent(this, TheSmokery.class);  //open smokery
        startActivity(intent);
    }
}