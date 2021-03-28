package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bis.lafefny.R;

public class Payment extends AppCompatActivity {

    private Button button_cancel; //button home icon
    private Button button_back_pay; //button booking icon
    private Button button_promo; //button promo icon
    private Button button_ticket; //button ticket


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        button_cancel = (Button) findViewById(R.id.btn_cancel); //button home icon
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomepage();
            }
        });

        button_back_pay = (Button) findViewById(R.id.btn_back_pay); //button booking icon
        button_back_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBooking();
            }
        });

        button_promo = (Button) findViewById(R.id.btn_promo); //button booking icon
        button_promo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPromocode();
            }
        });

        button_ticket = (Button) findViewById(R.id.btn_pay_now); //button open ticket
        button_ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTicket();
            }
        });


    }
    public void openHomepage(){
        Intent intent = new Intent(this, Homepage.class);  //open home icon
        startActivity(intent);
    }

    public void openBooking(){
        Intent intent = new Intent(this, Booking.class);  //open booking icon
        startActivity(intent);
    }

    public void openPromocode(){
        Intent intent = new Intent(this, Promocode.class);  //open promo icon
        startActivity(intent);
    }
    public void openTicket(){
        Intent intent = new Intent(this, Ticket.class);  //open ticket
        startActivity(intent);
    }
}
