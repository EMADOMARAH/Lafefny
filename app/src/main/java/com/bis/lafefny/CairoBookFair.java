package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CairoBookFair extends AppCompatActivity {
    private Button button_back;
    private Button button_transportation; //button transportation
    private Button button_booking; //button booking
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cairo_book_fair);

        button_back = (Button) findViewById(R.id.btn_back_cbf); //button back
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEvents();
            }
        });


        button_transportation = (Button) findViewById(R.id.btn_transportation_cbf); //button transportation
        button_transportation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTransportation();
            }
        });

        button_booking = (Button) findViewById(R.id.btn_ticket_cbf); //button booking
        button_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBooking();
            }
        });
           }
    public void openEvents(){
        Intent intent = new Intent(this, Events.class);  //button back
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