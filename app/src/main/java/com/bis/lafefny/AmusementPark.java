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
    private Button button_account_amuspark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amusement_park);

        button_back = (Button) findViewById(R.id.btn_back_amusPark); //button back
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

        button_homepage = (Button) findViewById(R.id.btn_home1_amusPark); //button back icon
        button_homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomepage();
            }
        });

        button_account_amuspark = (Button) findViewById(R.id.btn_user_amusPark); //button user account
        button_account_amuspark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAccount3();
            }
        });

    }

    public void openEnt_Categories(){
        Intent intent = new Intent(this, Ent_Categories.class);  //open entertainment
        startActivity(intent);
    }

    public void openDreamPark(){
        Intent intent = new Intent(this, DreamPark.class);  //open dream park
        startActivity(intent);
    }
    public void openHomepage(){
        Intent intent = new Intent(this, Homepage.class);  //open home page
        startActivity(intent);
    }

    public void openAccount3(){
        Intent intent = new Intent(this, Account.class);  //open account
        startActivity(intent);
    }


}