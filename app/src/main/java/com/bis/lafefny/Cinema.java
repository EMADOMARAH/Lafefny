package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Cinema extends AppCompatActivity  {
    private Button button_back;
    private Button button_home;
    // error in connecting cinema with voxCinema
    private Button button_vox_cinema;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema);

        button_back = (Button) findViewById(R.id.btn_back); //button back
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEnt_Categories();
            }
        });

        button_home = (Button) findViewById(R.id.btn_home1); //button homepage
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomepage();
            }
        });
        button_vox_cinema = (Button) findViewById(R.id.btn_vox); //button vox cinema
//      button_vox_cinema.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openVoxCinema();
//            }
//        });
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


    public void goTo(View view) {
        openHomepage();
    }

}