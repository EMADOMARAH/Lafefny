package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
    private SharedPreferences dreamParkPref;
    private SharedPreferences voxCinemaPref;
    private SharedPreferences funtopiaPref;
    private SharedPreferences egyptianPref;
    private SharedPreferences smokeryPref;
    Map<String, Object> ticket = new HashMap<>();

    TextView title,ticketId,regularPrice,vipPrice;
    EditText comments_edittxt, regularcount_edittxt, vipCount_edittxt;

    String sourceScreen;
    String randTicketId;

    String vipCountString;
    String regularCountString;
    int  vipCount=0 , regularCount=0 , vipTicketPrice ,regularTicketPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        dreamParkPref = getSharedPreferences("Dream_park_pref" , Context.MODE_PRIVATE);
        voxCinemaPref = getSharedPreferences("vox_cinema_pref" , Context.MODE_PRIVATE);
        funtopiaPref  = getSharedPreferences("funtopia_pref" , Context.MODE_PRIVATE);
        egyptianPref  =  getSharedPreferences("egyptian_museum_pref" , Context.MODE_PRIVATE);
        smokeryPref =  getSharedPreferences("the_smokery_pref" , Context.MODE_PRIVATE);




        InitBookingViews();

        GetSourceScreen();

        switch (sourceScreen){
            case "dreampark":
                regularTicketPrice = dreamParkPref.getInt("regularPrice" , 150);
                vipTicketPrice     = dreamParkPref.getInt("vipPrice" , 560);
                break;
            case "voxcinema":
                regularTicketPrice = voxCinemaPref.getInt("regularPrice" , 80);
                vipTicketPrice     = voxCinemaPref.getInt("vipPrice" , 110);
                break;
            case "funtopia":
                regularTicketPrice = funtopiaPref.getInt("regularPrice" , 65);
                vipTicketPrice     = funtopiaPref.getInt("vipPrice" , 300);
                break;
            case "egyptianmuseum":
                regularTicketPrice = egyptianPref.getInt("regularPrice" , 30);
                vipTicketPrice     = egyptianPref.getInt("vipPrice" , 160);
                break;
            case "thesmokery":
                regularTicketPrice = smokeryPref.getInt("regularPrice" , 50);
                vipTicketPrice     = smokeryPref.getInt("vipPrice" , 100);
                break;

        }

        randTicketId = getRandomNumberString();
        ticketId.setText(randTicketId);
        regularPrice.setText(Integer.toString(regularTicketPrice));
        vipPrice.setText(Integer.toString(vipTicketPrice));

    }


    public void BookingOnClick(View view) {
        switch (view.getId()){
            case R.id.btn_home1_booking:
                Intent intent = new Intent(this, Homepage.class);  //open homepage
                startActivity(intent);
                finish();
                break;

            case R.id.btn_pre_booking:
                Intent intent2 = new Intent(this, preferences.class);  //open preferences
                startActivity(intent2);
                finish();
                break;

            case R.id.btn_booking_booking:
                Intent i = new Intent(getApplicationContext(), Payment.class);//open home icon
                if (checkIfDataValid()){

                    switch (sourceScreen){
                        case "dreampark":
                            dreamParkPref.edit().putString("ticketId" , randTicketId).apply();
                            dreamParkPref.edit().putInt("regularCount" , regularCount).apply();
                            dreamParkPref.edit().putInt("vipCount" , vipCount).apply();
                            dreamParkPref.edit().commit();
                            break;
                        case "voxcinema":
                            voxCinemaPref.edit().putString("ticketId" , randTicketId).apply();
                            voxCinemaPref.edit().putInt("regularCount" , regularCount).apply();
                            voxCinemaPref.edit().putInt("vipCount" , vipCount).apply();
                            voxCinemaPref.edit().commit();
                            break;
                        case "funtopia":
                            funtopiaPref.edit().putString("ticketId" , randTicketId).apply();
                            funtopiaPref.edit().putInt("regularCount" , regularCount).apply();
                            funtopiaPref.edit().putInt("vipCount" , vipCount).apply();
                            funtopiaPref.edit().commit();
                            break;
                        case "egyptianmuseum":
                            egyptianPref.edit().putString("ticketId" , randTicketId).apply();
                            egyptianPref.edit().putInt("regularCount" , regularCount).apply();
                            egyptianPref.edit().putInt("vipCount" , vipCount).apply();
                            egyptianPref.edit().commit();
                            break;
                        case "thesmokery":
                            smokeryPref.edit().putString("ticketId" , randTicketId).apply();
                            smokeryPref.edit().putInt("regularCount" , regularCount).apply();
                            smokeryPref.edit().putInt("vipCount" , vipCount).apply();
                            smokeryPref.edit().commit();
                            break;

                        default:
                            Toast.makeText(this, "Can't Know My Source Screen", Toast.LENGTH_SHORT).show();
                    }

                }
                i.putExtra("screen" , sourceScreen);
                startActivity(i);

                break;
            default:
                System.out.println("CAAAAAAAAAAK");

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

    public  String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
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

    private void GetSourceScreen() {
        Bundle extraData = getIntent().getExtras();
        if (extraData !=null){
            this.sourceScreen = extraData.getString("screen");
        }
    }

}