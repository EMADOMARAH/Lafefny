package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Emergency extends AppCompatActivity {
    private Button button_Emg_homepage;
//    private Button button_Emg_back;
    private Button button_Emg_account;
    private Button button_Emg_pre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        button_Emg_homepage = (Button) findViewById(R.id.btn_emg_home);  //button home icon
        button_Emg_homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomepage();
            }
        });

//        button_Emg_back = (Button) findViewById(R.id.btn_back2);  //button back
//        button_Emg_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openHomepage();
//            }
//        });


        button_Emg_account = (Button) findViewById(R.id.btn_emg_user);  //button account icon
        button_Emg_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openaccount();
            }
        });

        button_Emg_pre = (Button) findViewById(R.id.btn_emg_pre);  //button preferences
        button_Emg_pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openpreferences();
            }
        });

    }
    public void openHomepage(){
        Intent intent = new Intent(this, Homepage.class);  //open home page
        startActivity(intent);
    }

    public void openaccount(){
        Intent intent = new Intent(this, Account.class);  //open account page
        startActivity(intent);
    }

    public void openpreferences(){
        Intent intent = new Intent(this, preferences.class);  //open preferences
        startActivity(intent);
    }
}