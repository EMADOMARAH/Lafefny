package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class About extends AppCompatActivity {
    private Button button_home_About;
    private Button button_pre_About;
    private Button button_account_About;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);



        button_home_About = (Button) findViewById(R.id.btn_home1_hp); //button homepage
        button_home_About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomepage();
            }
        });

        button_pre_About = (Button) findViewById(R.id.btn_pre_hp); //button preferences
        button_pre_About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openpreferences();
            }
        });

        button_account_About = (Button) findViewById(R.id.btn_user_hp); //button account
        button_account_About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAccount();
            }
        });
    }


    public void openHomepage() {
        startActivity(new Intent(getApplicationContext(), Homepage.class));  //open homepage
    }

    public void openpreferences() {
        startActivity(new Intent(getApplicationContext(), preferences.class));  //open preferences
    }
    public void openAccount() {
        startActivity(new Intent(getApplicationContext(), Account.class));  //open account
    }
}