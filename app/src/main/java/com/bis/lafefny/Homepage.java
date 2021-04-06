package com.bis.lafefny;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.bis.lafefny.R;
import com.google.android.material.navigation.NavigationView;

public class Homepage extends AppCompatActivity{

    //Buttons
    private Button button_ent;
    private Button button_event;
    private Button button_plan;
    private Button button_emg;
    private Button button_account_hp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Toast.makeText(getBaseContext(),"Process Success..",Toast.LENGTH_LONG);


        //Buttons
        button_ent = (Button) findViewById(R.id.btn_entertainment); //button amusement park
        button_ent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEnt_Categories();
            }
        });

        button_plan = (Button) findViewById(R.id.btn_plans); //button plans
        button_plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlans();
            }
        });

        button_event = (Button) findViewById(R.id.btn_events); //button events
        button_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEvents();
            }
        });

       button_emg = (Button) findViewById(R.id.btn_emg); //button emg
        button_emg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEmergency();
            }
        });

        button_account_hp = (Button) findViewById(R.id.btn_user_hp); //button account
        button_account_hp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAccount1();
            }
        });


    }

    public void openEnt_Categories(){
        Intent intent = new Intent(this, Ent_Categories.class); //open entertainment categories
        startActivity(intent);
    }
    public void openPlans(){
        Intent intent = new Intent(this, Plans.class); //open plans
        startActivity(intent);
    }
    public void openEvents(){
        Intent intent = new Intent(this, Events.class); //open events
        startActivity(intent);
    }

    public void  openEmergency(){
        Intent intent = new Intent(this, Emergency.class); //open emergency
        startActivity(intent);
    }

    public void  openAccount1(){
        Intent intent = new Intent(this, Account.class); //open account
        startActivity(intent);
    }


}