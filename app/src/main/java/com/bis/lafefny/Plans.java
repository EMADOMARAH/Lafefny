package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Plans extends AppCompatActivity {

    private Button button_back;
    private Button button_home;
    private Button button_adv;
    private Button button_rom;
    private Button button_family;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventurous_plans);


        button_back = (Button) findViewById(R.id.btn_back_plan_categ); //button back
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomepage();
            }
        });

        button_home = (Button) findViewById(R.id.btn_home1_plan_categ_plan_categ); //button homepage
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomepage();
            }
        });
//        button_adv = (Button) findViewById(R.id.btn_adventure); //button adventure plans
//        button_adv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openAdventurousPlans();
//            }
//        });
//        button_rom = (Button) findViewById(R.id.btn_romantic); //button romantic plans
//        button_rom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openRomanticPlan();
//            }
//        });
//        button_family = (Button) findViewById(R.id.btn_family); //button family plans
//        button_family.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openFamilyPlan();
//            }
//        });
    }
    public void openHomepage(){
        Intent intent = new Intent(this, Homepage.class);  //open homepage
        startActivity(intent);
    }
    public void openAdventurousPlans(){
        Intent intent = new Intent(this, AdventurousPlans.class);  //open adventure plans
        startActivity(intent);
    }
    public void openRomanticPlan(){
        Intent intent = new Intent(this, RomanticPlan.class);  //open romantic plans
        startActivity(intent);
    }
    public void openFamilyPlan(){
        Intent intent = new Intent(this, FamilyPlan.class);  //open family plans
        startActivity(intent);
    }
}