package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SiwaOasis_AdvPlan extends AppCompatActivity {

    private Button button_program;
    private Button button_back;
    private Button button_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siwa_oasis__adv_plan);

        button_back = (Button) findViewById(R.id.btn_back); //button back
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlans();
            }
        });

        button_home = (Button) findViewById(R.id.btn_home1); //button homepage
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomepage();
            }
        });

        button_program = (Button) findViewById(R.id.btn_oasis_more_details); //button program
        button_program.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSiwaOasis_AdvPlan2();
            }
        });
    }
    public void openPlans(){
        Intent intent = new Intent(this, Plans.class);  //open back
        startActivity(intent);
    }
    public void openHomepage(){
        Intent intent = new Intent(this, Homepage.class);  //open homepage
        startActivity(intent);
    }
    public void openSiwaOasis_AdvPlan2(){
        Intent intent = new Intent(this, SiwaOasis_AdvPlan2.class);  //open program
        startActivity(intent);
    }
}