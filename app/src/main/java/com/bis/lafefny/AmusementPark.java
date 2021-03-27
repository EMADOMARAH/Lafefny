package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bis.lafefny.R;

public class AmusementPark extends AppCompatActivity {

    private Button button_back; //button back
    private Button button_homepage; //button back icon
    private Button button_dream_park; //button dream park
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amusement_park);

        button_back = (Button) findViewById(R.id.btn_back); //button back
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEnt_Categories();
            }
        });

        button_dream_park = (Button) findViewById(R.id.btn_dreampark); //button dream park
        button_dream_park.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDreamPark();
            }
        });

        button_homepage = (Button) findViewById(R.id.btn_home1); //button back icon
        button_homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomepage();
            }
        });

    }

    public void openEnt_Categories(){
        Intent intent = new Intent(this, Ent_Categories.class);  //button back
        startActivity(intent);
    }

    public void openDreamPark(){
        Intent intent = new Intent(this, DreamPark.class);  //button dream park
        startActivity(intent);
    }
    public void openHomepage(){
        Intent intent = new Intent(this, Homepage.class);  //button dream park
        startActivity(intent);
    }


}