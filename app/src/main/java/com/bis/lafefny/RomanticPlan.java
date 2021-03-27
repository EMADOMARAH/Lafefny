package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RomanticPlan extends AppCompatActivity {
    private Button button_back;
    private Button button_funtopia;
    private Button button_smokery;
    private Button button_vox;
    private Button button_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_romantic_plan);



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

        button_funtopia = (Button) findViewById(R.id.btn_fun_topia_rom); //button funtopia
        button_funtopia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFunTopia();
            }
        });

        button_smokery = (Button) findViewById(R.id.btn_smokery_rom); //button smokery
        button_smokery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTheSmokery();
            }
        });

        button_vox = (Button) findViewById(R.id.btn_vox_rom); //button vox
        button_vox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVoxCinema();
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

    public void openFunTopia(){
        Intent intent = new Intent(this, FunTopia.class);  //open funtopia
        startActivity(intent);
    }

    public void openTheSmokery(){
        Intent intent = new Intent(this, TheSmokery.class);  //open smokery
        startActivity(intent);
    }

    public void openVoxCinema(){
        Intent intent = new Intent(this, VoxCinema.class);  //open vox
        startActivity(intent);
    }
}