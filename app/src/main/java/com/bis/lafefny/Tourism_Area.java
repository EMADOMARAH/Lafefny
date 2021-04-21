package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Tourism_Area extends AppCompatActivity {
    private Button button_back;
    private Button button_home;
    private Button button_egyptian_museum;
    private Button button_account_TA;
    private Button button_pre_TA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourism__area);

        button_back = (Button) findViewById(R.id.btn_back_tourism); //button back
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEnt_Categories();
            }
        });

        button_home = (Button) findViewById(R.id.btn_home1_tourism); //button homepage
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomepage();
            }
        });

        button_pre_TA = (Button) findViewById(R.id.btn_pre_tourism); //button preferences
        button_pre_TA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openpreferences();
            }
        });

        button_egyptian_museum = (Button) findViewById(R.id.btn_egyptian_museum); //button egyptian museum
        button_egyptian_museum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEgyptianMuseum();
            }
        });

        button_account_TA = (Button) findViewById(R.id.btn_user_tourism); //button user account
        button_account_TA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAccount7();
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

    public void openpreferences(){
        Intent intent = new Intent(this, preferences.class);  //open preferences
        startActivity(intent);
    }
    public void openEgyptianMuseum(){
        Intent intent = new Intent(this, EgyptianMuseum.class);  //open egyptian museum
        startActivity(intent);
    }

    public void openAccount7(){
        Intent intent = new Intent(this, Account.class);  //open account
        startActivity(intent);
    }

    public void TourismAreaOnClick(View view) {
        switch (view.getId()){
            case R.id.txt_location_tourism_area:
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com/maps/place/The+Egyptian+Museum/@30.0475781,31.2314252,17z/data=!3m1!4b1!4m5!3m4!1s0x145841885535bec3:0x520da52b3a7a660f!8m2!3d30.0475781!4d31.2336139"));
                startActivity(intent);
                break;
        }
    }
}