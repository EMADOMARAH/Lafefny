package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Cinema extends AppCompatActivity  {
    private Button button_back_cinema;
    private Button button_home_cinema;
    private Button button_account_cinema;
    private Button button_vox_cinema;
    private Button button_pre_cinema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema);

        button_back_cinema = (Button) findViewById(R.id.btn_back_cinema); //button back
        button_back_cinema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEnt_Categories();
            }
        });

        button_home_cinema = (Button) findViewById(R.id.btn_home1_cinema); //button homepage
        button_home_cinema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomepage();
            }
        });

        button_vox_cinema = (Button) findViewById(R.id.btn_vox); //button vox cinema
      button_vox_cinema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVoxCinema();
            }
        });

        button_account_cinema = (Button) findViewById(R.id.btn_user_cinema); //button user account
        button_account_cinema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAccount5();
            }
        });

        button_pre_cinema = (Button) findViewById(R.id.btn_pre_cinema); //button preferences
        button_pre_cinema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openpreferences();
            }
        });
    }
    public void openEnt_Categories(){
        Intent intent = new Intent(this, Ent_Categories.class);  //open back
        startActivity(intent);
    }
    public void openHomepage(){
        startActivity(new Intent(getApplicationContext(), Homepage.class));  //open homepage

    }
    public void openVoxCinema(){
        Intent intent = new Intent(this, VoxCinema.class);  //open vox cinema
        startActivity(intent);
    }

    public void openAccount5(){
        Intent intent = new Intent(this, Account.class);  //open account
        startActivity(intent);
    }
    public void openpreferences(){
        Intent intent = new Intent(this, preferences.class);  //open preferences
        startActivity(intent);
    }


    public void goTo(View view) {
        openHomepage();
    }

}