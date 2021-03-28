package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Tourism_Area extends AppCompatActivity {
    private Button button_back;
    private Button button_home;
    private Button button_egyptian_museum;

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

        button_egyptian_museum = (Button) findViewById(R.id.btn_egyptian_museum); //button egyptian museum
        button_egyptian_museum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEgyptianMuseum();
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
    public void openEgyptianMuseum(){
        Intent intent = new Intent(this, EgyptianMuseum.class);  //open egyptian museum
        startActivity(intent);
    }
}