package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SoundLightEvent extends AppCompatActivity {
    private Button button_back_SL;     //button back
    private Button button_transportation_SL; //button transportation
    private Button button_booking_SL; //button booking
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_light_event);

        button_back_SL = (Button) findViewById(R.id.btn_back_SLshow); //button back
        button_back_SL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEvents();
            }
        });
        button_transportation_SL = (Button) findViewById(R.id.btn_transportation_SLshow); //button transportation
        button_transportation_SL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTransportation();
            }
        });

        button_booking_SL = (Button) findViewById(R.id.btn_ticket_SLshow); //button booking
        button_booking_SL.setOnClickListener(new View.OnClickListener() {
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