package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdventurousPlans extends AppCompatActivity {
    private Button button_back;
    private Button button_siwa;
//    private Button button_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventurous_plans);


        button_back = (Button) findViewById(R.id.btn_back); //button back
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlans();
            }
        });
        button_siwa = (Button) findViewById(R.id.btn_siwa_oasis); //button siwa
        button_siwa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSiwaOasis_AdvPlan();
            }
        });

//        button_home = (Button) findViewById(R.id.btn_home1); //button homepage
//        button_home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openHomepage();
//            }
//        });
    }
    public void openPlans(){
        Intent intent = new Intent(this, Plans.class);  //open plans
        startActivity(intent);
    }
    public void openSiwaOasis_AdvPlan(){
        Intent intent = new Intent(this, SiwaOasis_AdvPlan.class);  //open siwa
        startActivity(intent);
    }
//    public void openHomepage(){
//        Intent intent = new Intent(this, Homepage.class);  //open homepage
//        startActivity(intent);
//    }
}