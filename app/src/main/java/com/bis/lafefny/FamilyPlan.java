package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FamilyPlan extends AppCompatActivity {
    private Button button_back;
    private Button button_home;
    private Button button_egyp_museum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_plan);

        button_back = (Button) findViewById(R.id.btn_back_fam); //button back
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlans();
            }
        });

        button_home = (Button) findViewById(R.id.btn_home1_fam); //button homepage
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomepage();
            }
        });

        button_egyp_museum = (Button) findViewById(R.id.btn_egyptian_museum_fam); //button EgyptianMuseum
        button_egyp_museum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEgyptianMuseum();
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

    public void openEgyptianMuseum(){
        Intent intent = new Intent(this, EgyptianMuseum.class);  //open EgyptianMuseum
        startActivity(intent);
    }
}