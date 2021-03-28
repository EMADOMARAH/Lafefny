package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VoxCinema extends AppCompatActivity {
    private Button button_back; //button back
    private Button button_transportation; //button transportation
    private Button button_booking1; //button booking1
    private Button button_booking2; //button booking2
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vox_cinema);

        button_back = (Button) findViewById(R.id.btn_back_vox); //button back
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openCinema();}
        });

        button_transportation = (Button) findViewById(R.id.btn_transportation_vox); //button transportation
        button_transportation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openTransportation();}
        });

        button_booking1 = (Button) findViewById(R.id.btn_book1); //button booking1
        button_booking1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openBooking();}
        });
        button_booking2 = (Button) findViewById(R.id.btn_book2); //button booking2
        button_booking2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openBooking();}
        });
    }
    public void openCinema(){
        Intent intent = new Intent(this, Cinema.class);  //button back
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