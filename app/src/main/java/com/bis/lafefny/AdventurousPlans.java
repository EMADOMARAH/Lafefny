package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdventurousPlans extends AppCompatActivity {
//    private Button button_back;
    private Button button_siwa;
    private Button button_home;
    private Button button_AP_pre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventurous_plans);


//        button_back = (Button) findViewById(R.id.btn_back_adv); //button back
//        button_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openPlans();
//            }
//        });
        button_siwa = (Button) findViewById(R.id.btn_siwa_oasis); //button siwa
        button_siwa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSiwaOasis_AdvPlan();
            }
        });

     button_home = (Button) findViewById(R.id.btn_home1_adv); //button homepage
     button_home.setOnClickListener(new View.OnClickListener() {
           @Override
          public void onClick(View v) {
              openHomepage();
           }
        });

        button_AP_pre = (Button) findViewById(R.id.btn_pre_adv); //button preferences
        button_AP_pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openpreferences();
            }
        });

    }
    public void openPlans(){
        Intent intent = new Intent(this, Plans.class);  //open plans
        startActivity(intent);
    }
    public void openSiwaOasis_AdvPlan(){
        Intent intent = new Intent(this, SiwaOasis_AdvPlan.class);  //open siwa
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

    public void AdventureOnClick(View view) {
        switch (view.getId()){
            case R.id.txt_location_siwa_oasis:
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com/maps/place/Siwa+Oasis,+Siwa,+Matrouh+Governorate/@29.2057953,25.4567976,12z/data=!3m1!4b1!4m5!3m4!1s0x147aaface8f3a523:0x6f335df8f19a074d!8m2!3d29.2031708!4d25.5195451"));
                startActivity(intent);

                break;
        }
    }
}