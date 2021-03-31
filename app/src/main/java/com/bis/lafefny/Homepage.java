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

public class Homepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Buttons
    private Button button_ent;
    private Button button_event;
    private Button button_plan;
    private Button button_emg;

    //Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Toast.makeText(getBaseContext(),"Process Success..",Toast.LENGTH_LONG);


        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();

                switch (id) {
                    case R.id.nav_home:

                        Intent aboutusintent = new Intent(getApplicationContext(), Cinema.class);
                        aboutusintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(aboutusintent);
                        break;
                    case R.id.nav_cycle:
                        Toast.makeText(getApplicationContext(), "Training Programmes", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();

                        break;


//                        SharedPreferences preferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
//                        if (preferences.contains("password")) {
//                            SharedPreferences.Editor editor = preferences.edit();
//                            editor.remove("password");
//                            editor.commit();
//                        }
                        //finish(); // Call once you redirect to another activity.


                }
                return true;
            }
        });

        //Menu

        /*--------------------Hooks-----------------------------*/
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        /*--------------------Tool Bar-----------------------------*/
        setSupportActionBar(toolbar);

        /*--------------------Navigation Drawer Menu-----------------------------*/
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        //Buttons
        button_ent = (Button) findViewById(R.id.btn_entertainment); //button amusement park
        button_ent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEnt_Categories();
            }
        });

        button_plan = (Button) findViewById(R.id.btn_plans_home); //button plans
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

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }
//Menu
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }
}