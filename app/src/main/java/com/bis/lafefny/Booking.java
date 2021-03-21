package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bis.lafefny.R;

public class Booking extends AppCompatActivity {

    private Button button_home; //button home icon
    private Button button_payment; //button payment


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        button_home = (Button) findViewById(R.id.btn_home1); //button home icon
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomepage();
            }
        });

              button_payment = (Button) findViewById(R.id.btn_booking); //button payment
        button_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPayment();
            }
        });

    }
    public void openHomepage(){
        Intent intent = new Intent(this, Homepage.class);  //open home icon
        startActivity(intent);
    }

     public void openPayment(){
        Intent intent = new Intent(this, Payment.class);  //open sginin
        startActivity(intent);
    }
}