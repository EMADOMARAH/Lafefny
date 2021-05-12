package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GamingArea extends AppCompatActivity {
//    private Button button_back;
    private Button button_home;
    private Button button_pre_GA;
    private Button button_funtopia;
    private Button button_account_GA;
    Button location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaming_area);

//        button_back = (Button) findViewById(R.id.btn_back_ga); //button back
//        button_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openEnt_Categories();
//            }
//        });

        location = findViewById(R.id.txt_location_fun_topia);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com/maps/place/Funtopia+-+Mazar+Mall/@30.0491519,30.9405436,14z/data=!4m5!3m4!1s0x0:0x9bb07872950039f0!8m2!3d30.0534389!4d30.9603791"));
                startActivity(intent);
            }
        });

        button_home = (Button) findViewById(R.id.btn_home1_ga); //button homepage
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomepage();
            }
        });
        button_funtopia = (Button) findViewById(R.id.btn_fun_topia); //button funtopia
        button_funtopia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFunTopia();
            }
        });

        button_account_GA = (Button) findViewById(R.id.btn_user_ga); //button user account
        button_account_GA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAccount6();
            }
        });

        button_pre_GA = (Button) findViewById(R.id.btn_pre_ga); //button preferences
        button_pre_GA.setOnClickListener(new View.OnClickListener() {
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
        Intent intent = new Intent(this, Homepage.class);  //open homepage
        startActivity(intent);
    }
    public void openFunTopia(){
        Intent intent = new Intent(this, FunTopia.class);  //open funtopia
        startActivity(intent);
    }
    public void openAccount6(){
        Intent intent = new Intent(this, Account.class);  //open account
        startActivity(intent);
    }
    public void openpreferences(){
        Intent intent = new Intent(this, preferences.class);  //open preferences
        startActivity(intent);
    }
}