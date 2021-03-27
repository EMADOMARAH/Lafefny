package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FunTopia extends AppCompatActivity {
    private Button button_back; //button back
    private Button button_transportation; //button transportation
    private Button button_booking; //button booking

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun_topia);
        button_back = (Button) findViewById(R.id.btn_back); //button back
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGamingArea();
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
    }
    public void openGamingArea(){
        Intent intent = new Intent(this, GamingArea.class);  //button back
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