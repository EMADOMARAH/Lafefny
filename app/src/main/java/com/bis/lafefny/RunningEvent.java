package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RunningEvent extends AppCompatActivity {
    private Button button_back;
//    private Button button_home;
    private Button button_transportation; //button transportation
    private Button button_booking; //button booking
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running_event);

        button_back = (Button) findViewById(R.id.btn_back); //button back
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSport_Event();
            }
        });
        button_transportation = (Button) findViewById(R.id.btn_transportation); //button transportation
        button_transportation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTransportation();
            }
        });

        button_booking = (Button) findViewById(R.id.btn_ticket); //button booking
        button_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBooking();
            }
        });

//        button_home = (Button) findViewById(R.id.btn_home1); //button homepage
//        button_home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openHomepage();
//            }
//        });
    }
    public void openSport_Event(){
        Intent intent = new Intent(this, Sport_Events.class);  //open sport event
        startActivity(intent);
    }
//    public void openHomepage(){
//        Intent intent = new Intent(this, Homepage.class);  //open homepage
//        startActivity(intent);
//    }
public void openTransportation(){
    Intent intent = new Intent(this, Transportation.class);  //button transportation
    startActivity(intent);
}

    public void openBooking(){
        Intent intent = new Intent(this, Booking.class);  //button booking
        startActivity(intent);
    }
}