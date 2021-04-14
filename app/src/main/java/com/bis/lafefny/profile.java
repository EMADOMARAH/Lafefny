package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class profile extends AppCompatActivity {
    private Button button_profile_back;
    private Button button_profile_home;
    private Button button_profile_acc;
    private Button button_profile_pre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        button_profile_back = (Button) findViewById(R.id.btn_back_profile); //button back
        button_profile_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAccount();
            }
        });

        button_profile_home = (Button) findViewById(R.id.btn_profile_home); //button home
        button_profile_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openhomepage();
            }
        });

        button_profile_acc = (Button) findViewById(R.id.btn_profile_user); //button Account
        button_profile_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAccount();
            }
        });

        button_profile_pre = (Button) findViewById(R.id.btn_profile_pre); //button preferences
        button_profile_pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openpreferences();
            }
        });


    }
    public void openAccount(){
        Intent intent = new Intent(this,Account.class);  //open account
        startActivity(intent);
    }

    public void openhomepage(){
        Intent intent = new Intent(this,Homepage.class);  //open home page
        startActivity(intent);
    }

    public void openpreferences(){
        Intent intent = new Intent(this,preferences.class);  //open preferences
        startActivity(intent);
    }
}