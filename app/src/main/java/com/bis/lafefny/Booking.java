package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Booking extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Map<String, Object> ticket = new HashMap<>();




   TextView title,ticketId,regularPrice,vipPrice;
   EditText comments_edittxt, regularcount_edittxt, vipCount_edittxt;

   String source;
    String randTicketId;
    String startTime;
    String startDate;
    String vipCountString;
    String regularCountString;
    String commentString;
   int vipTicketPrice , regularTicketPrice , vipCount=0 , regularCount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        InitBookingViews();

        GetDataFromIntent();
        randTicketId = getRandomNumberString();
        ticketId.setText(randTicketId);
        regularPrice.setText(Integer.toString(regularTicketPrice));
        vipPrice.setText(Integer.toString(vipTicketPrice));




    }


    public  String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }

    private void GetDataFromIntent() {
        Bundle extraData = getIntent().getExtras();
        if (extraData !=null){
            vipTicketPrice = extraData.getInt("vipPrice");
            regularTicketPrice = extraData.getInt("regularPrice");
            source = extraData.getString("source");
            startTime = extraData.getString("startTime");
            startDate = extraData.getString("startDate");
        }
    }

    private void InitBookingViews() {
        title = findViewById(R.id.txt_booking);
        ticketId = findViewById(R.id.txt_ticket_id_booking);
        regularPrice = findViewById(R.id.txt_ticket_regular_price_booking);
        vipPrice = findViewById(R.id.txt_ticket_vip_price_booking);
        //comments_edittxt = findViewById(R.id.pt_comment_booking);
        regularcount_edittxt = findViewById(R.id.txt_ticket_regular_quantity_booking);
        vipCount_edittxt = findViewById(R.id.txt_ticket_vip_quantity_booking);
    }

    public void BookingOnClick(View view) {
        switch (view.getId()){
            case R.id.btn_home1_booking:
                Intent intent = new Intent(this, Homepage.class);  //open sginin
                startActivity(intent);
                finish();
                break;
            case R.id.btn_booking_booking:
                if (checkIfDataValid()){
                    Intent i = new Intent(this, Payment.class);  //open home icon
                    i.putExtra("ticketID" , randTicketId);
                    i.putExtra("regularPrice" ,regularTicketPrice);
                    i.putExtra("regularCount" ,regularCount );
                    i.putExtra("vipPrice" , vipTicketPrice);
                    i.putExtra("vipCount" ,vipCount );
                    i.putExtra("comment" , commentString);
                    i.putExtra("source" , source);
                    i.putExtra("startTime" , startTime);
                    i.putExtra("startDate"  , startDate);
                    startActivity(i);
                }

                break;
        }
    }

    private boolean checkIfDataValid() {
        if (!vipCount_edittxt.getText().toString().matches("")){
            vipCountString = String.valueOf(vipCount_edittxt.getText());
            vipCount = Integer.parseInt(vipCountString);
        }

        if (!regularcount_edittxt.getText().toString().matches("")){
            regularCountString = regularcount_edittxt.getText().toString();
            regularCount = Integer.parseInt(regularCountString);
        }


        if (vipCount == 0 && regularCount ==0){
            Toast.makeText(this, "You should enter amount of tickets", Toast.LENGTH_SHORT).show();
            return false;
        }else {

            //commentString = comments_edittxt.getText().toString();
            return true;
        }

    }

}