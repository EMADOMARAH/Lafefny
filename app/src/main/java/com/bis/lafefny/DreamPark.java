package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bis.lafefny.R;

public class DreamPark extends AppCompatActivity {
    private Button button_back; //button back
    private Button button_transportation; //button transportation
    private Button button_booking; //button booking
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dream_park);

        button_back = (Button) findViewById(R.id.btn_back_dreamP); //button back
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAmusementPark();
            }
        });

        button_transportation = (Button) findViewById(R.id.btn_transportation_dreamP); //button transportation
        button_transportation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTransportation();
            }
        });

        button_booking = (Button) findViewById(R.id.btn_ticket_dreamP); //button booking
        button_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBooking();
            }
        });
    }
    public void openAmusementPark(){
        Intent intent = new Intent(this, AmusementPark.class);  //button back
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