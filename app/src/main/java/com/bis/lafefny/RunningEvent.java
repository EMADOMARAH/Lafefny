package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RunningEvent extends AppCompatActivity {
    private Button button_back;
    private Button button_transportation; //button transportation
    private Button button_booking; //button booking
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running_event);

        button_back = (Button) findViewById(R.id.btn_back_run); //button back
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSport_Event();
            }
        });
        button_transportation = (Button) findViewById(R.id.btn_transportation_run); //button transportation
        button_transportation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTransportation();
            }
        });

        button_booking = (Button) findViewById(R.id.btn_book_run); //button booking
        button_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBooking();
            }
        });


    }
    public void openSport_Event(){
        Intent intent = new Intent(this, Sport_Events.class);  //open sport event
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