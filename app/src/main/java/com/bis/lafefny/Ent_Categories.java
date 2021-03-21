package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bis.lafefny.R;

public class Ent_Categories extends AppCompatActivity {
    private Button button_home;
    private Button button_home_icon;
    private Button button_park;
    private Button button_gaming_area;
    private Button button_tourism_area;
    private Button button_restaurant;
    private Button button_cinema;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ent__categories);

        button_home = (Button) findViewById(R.id.btn_back); //button homepage
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomepage();
            }
        });

        button_park = (Button) findViewById(R.id.btn_amusement_park); //button amusement park
        button_park.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAmusementPark();
            }
        });

        button_home_icon = (Button) findViewById(R.id.btn_home1); //button homepage
        button_home_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomepage();
            }
        });

        button_gaming_area = (Button) findViewById(R.id.btn_gaming_area); //button gaming area
        button_gaming_area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGamingArea();
            }
        });

        button_tourism_area = (Button) findViewById(R.id.btn_toursim_area); //button tourism area
        button_tourism_area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTourism_Area();
            }
        });

        button_restaurant = (Button) findViewById(R.id.btn_restaurant); //button restaurants
        button_restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRestaurants();
            }
        });

        button_cinema = (Button) findViewById(R.id.btn_cinema); //button cinema
        button_cinema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCinema();
            }
        });
    }
    public void openHomepage(){
        Intent intent = new Intent(this, Homepage.class);  //open homepage
        startActivity(intent);
    }

    public void openAmusementPark(){
        Intent intent = new Intent(this, AmusementPark.class);  //open amusement park
        startActivity(intent);
    }

    public void openGamingArea(){
        Intent intent = new Intent(this, GamingArea.class);  //open gaming area
        startActivity(intent);
    }

    public void openTourism_Area(){
        Intent intent = new Intent(this, Tourism_Area.class);  //open tourism area
        startActivity(intent);
    }

    public void openRestaurants(){
        Intent intent = new Intent(this, Restaurants.class);  //open restaurants
        startActivity(intent);
    }
    public void openCinema(){
        Intent intent = new Intent(this, Cinema.class);  //open cinema
        startActivity(intent);
    }

}