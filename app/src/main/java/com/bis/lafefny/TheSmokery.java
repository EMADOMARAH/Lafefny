package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TheSmokery extends AppCompatActivity {

    private Button button_back_smokery; //button back
    private Button button_transportation_smokery; //button transportation
    private Button button_booking_smokery; //button booking


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_smokery);

        button_back_smokery = (Button) findViewById(R.id.btn_back_smokery); //button back
        button_back_smokery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRestaurants();
            }
        });

        button_transportation_smokery = (Button) findViewById(R.id.btn_transportation_smokery); //button transportation
        button_transportation_smokery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTransportation();
            }
        });

        button_booking_smokery = (Button) findViewById(R.id.btn_ticket_smokery); //button booking
        button_booking_smokery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBooking();
            }
        });
    }
    public void openRestaurants(){
        Intent intent = new Intent(this, Restaurants.class);  //button back
        startActivity(intent);
    }

    public void openTransportation(){
        Intent intent = new Intent(this, Transportation.class);  //button transportation
        startActivity(intent);
    }

    public void openBooking(){
        Intent intent = new Intent(this, Booking.class);  //button booking
        startActivity(intent);
    }
}