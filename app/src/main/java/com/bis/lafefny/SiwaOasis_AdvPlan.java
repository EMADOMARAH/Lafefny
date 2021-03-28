package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SiwaOasis_AdvPlan extends AppCompatActivity {

    private Button button_program;
    private Button button_back;
    private Button button_transportation;
    private Button button_ticket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siwa_oasis__adv_plan);

        button_back = (Button) findViewById(R.id.btn_back_siwa_intro); //button back
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlans();
            }
        });



        button_program = (Button) findViewById(R.id.btn_oasis_more_details); //button program
        button_program.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSiwaOasis_AdvPlan2();
            }
        });

        button_transportation = (Button) findViewById(R.id.btn_transportation_siwa_intro); //button transportation
        button_transportation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTransportation();
            }
        });

        button_ticket = (Button) findViewById(R.id.btn_ticket_siwa_intro); //button ticket
        button_ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTicket();
            }
        });
    }
    public void openPlans(){
        Intent intent = new Intent(this, Plans.class);  //open back
        startActivity(intent);

    }
    public void openSiwaOasis_AdvPlan2(){
        Intent intent = new Intent(this, SiwaOasis_AdvPlan2.class);  //open program
        startActivity(intent);
    }
    public void openTransportation(){
        Intent intent = new Intent(this, Transportation.class);  //open Transportation
        startActivity(intent);
    }

    public void openTicket(){
        Intent intent = new Intent(this, Ticket.class);  //open Ticket
        startActivity(intent);
    }
}