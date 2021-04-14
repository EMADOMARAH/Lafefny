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
import android.preference.Preference;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.bis.lafefny.R;
import com.google.android.material.navigation.NavigationView;

import java.util.prefs.Preferences;

public class Homepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Buttons
    private Button button_ent;
    private Button button_pre_HP;
    private Button button_event;
    private Button button_plan;
    private Button button_emg;
    private Button button_account_hp;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_homepage);

        initViews();
        setSupportActionBar( toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.nav_drawer_open,R.string.nav_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
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
                openPlans(v);
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

        button_pre_HP = (Button) findViewById(R.id.btn_pre_hp); //button preferences
        button_pre_HP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openpreferences();
            }
        });


    }

    public void openEnt_Categories(){
        Intent intent = new Intent(this, Ent_Categories.class); //open entertainment categories
        startActivity(intent);
    }
    public void openpreferences(){
        Intent intent = new Intent(this, preferences.class); //open preferences
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

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }

    }

    public void openPlans(View view) {
        Intent intent = new Intent(this, Plans.class); //open plans
        startActivity(intent);
    }

    public void initViews(){
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.home_page_tool_bar);
    }

    public void homePageOnClick(View view) {
        switch (view.getId()){

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                Intent homeIntent = new Intent(this, Homepage.class);  //open homepage
                startActivity(homeIntent);
                break;
            case R.id.nav_promocode:
                Intent promoIntent = new Intent(this, Promocode.class);  //open promocode
                startActivity(promoIntent);
                break;
            case R.id.nav_Acc:
                Intent accIntent = new Intent(this, Account.class);  //open account
                startActivity(accIntent);
                break;
//            case R.id.nav_About:
//                Intent aboutIntent = new Intent(this, About.class);  //open about
//                startActivity(aboutIntent);
//                break;
            case R.id.nav_PV:
                Intent pvIntent = new Intent(this, ProvideData.class);  //open provide data
                startActivity(pvIntent);
                break;
//            case R.id.nav_filter:
//                Intent filterIntent = new Intent(this, Filter.class);  //open filter
//                startActivity(filterIntent);
//                break;
            case R.id.nav_Questionnaire:
                Intent QIntent = new Intent(this, Questionnaire1.class);  //open questionnaire
                startActivity(QIntent);
                break;
            case R.id.nav_Preferences:
                Intent PIntent = new Intent(this, Preferences.class);  //open preferences
                startActivity(PIntent);
                break;
//            case R.id.nav_hotel:
//                Intent hotelIntent = new Intent(this, Hotel.class);  //open Hotel
//                startActivity(hotelIntent);
//                break;
//            case R.id.nav_sort:
//                Intent sortIntent = new Intent(this, Sort.class);  //open sort
//                startActivity(sortIntent);
//                break;
            case R.id.nav_Transportation:
                Intent transpIntent = new Intent(this, Transportation.class);  //open Transportation
                startActivity(transpIntent);
                break;
//            case R.id.nav_TG:
//                Intent TGIntent = new Intent(this, TourGuide.class);  //open Tour guide
//                startActivity(TGIntent);
//                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    }