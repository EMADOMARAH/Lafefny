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
    // -------------------- Shared Preferences (cache memory) --------------------------
    // created cache memory for a certain purpose then deleted afterwords when they are no longer needed
    // below are types of caches created from different screens, that hold information
    // Step (1)
    private SharedPreferences dreamParkPref;
    private SharedPreferences voxCinemaPref;
    private SharedPreferences funtopiaPref;
    private SharedPreferences egyptianPref;
    private SharedPreferences smokeryPref;
    private SharedPreferences runningPref;
    private SharedPreferences cairoBookPref;
    private SharedPreferences soundLightPref;
    private SharedPreferences siwaPref;



    Map<String, Object> ticket = new HashMap<>(); //create a map called ticket, since the info here must be called in the ticket screen, so this map holds all of the information from here, to payment info to ticket

    TextView title,ticketId,regularPrice,vipPrice; // connect the screen with the database (team db)
    EditText comments_edittxt, regularcount_edittxt, vipCount_edittxt;

    String sourceScreen; // A variable that holds the previous screen that booking screen was opened from. That is because we keep sending info between different screens in a certain order. So, an intent was sent determining what screen was booking opened from. Hence, the different cache files created.
    String randTicketId; // A variable holding random ticket ID, since we want to automatically generate random ticket ID.

    String vipCountString;
    String regularCountString;
    int  vipCount=0 , regularCount=0 , vipTicketPrice ,regularTicketPrice;

    // Step (2)
    // -------------- cache files --------------------------------------
    // call cache files in onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        dreamParkPref = getSharedPreferences("Dream_park_pref" , Context.MODE_PRIVATE);
        voxCinemaPref = getSharedPreferences("vox_cinema_pref" , Context.MODE_PRIVATE);
        funtopiaPref  = getSharedPreferences("funtopia_pref" , Context.MODE_PRIVATE);
        egyptianPref  =  getSharedPreferences("egyptian_museum_pref" , Context.MODE_PRIVATE);
        smokeryPref =  getSharedPreferences("the_smokery_pref" , Context.MODE_PRIVATE);
        runningPref = getSharedPreferences("Running_Event_pref" , Context.MODE_PRIVATE);
        cairoBookPref = getSharedPreferences("Cairo_Book_Fair_pref" , Context.MODE_PRIVATE);
        soundLightPref = getSharedPreferences("Sound_Light_pref" , Context.MODE_PRIVATE);
        siwaPref = getSharedPreferences("siwa_pref" , Context.MODE_PRIVATE);


// Step (4)
// ------------------------ Switch case on Source screen -------------------------

        InitBookingViews();

        GetSourceScreen(); // to know from where i came from (which screen)

        // Get vip and regular prices from cache memory

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
            case "runningevent":
                regularTicketPrice = runningPref.getInt("regularPrice" , 50);
                vipTicketPrice     = runningPref.getInt("vipPrice" , 0);
                break;
            case "cairobookfair":
                regularTicketPrice = cairoBookPref.getInt("regularPrice" , 5);
                vipTicketPrice     = cairoBookPref.getInt("vipPrice" , 0);
                break;
            case "soundandlight":
                regularTicketPrice = soundLightPref.getInt("regularPrice" , 300);
                vipTicketPrice     = soundLightPref.getInt("vipPrice" , 350);
                break;
            case "siwa":
                regularTicketPrice = siwaPref.getInt("regularPrice" , 3300);
                vipTicketPrice     = siwaPref.getInt("vipPrice" , 3900);
                break;

        }


        randTicketId = getRandomNumberString();
        ticketId.setText(randTicketId);

        // Step (6)
        // ------------- places w/o VIP tickets -----------------
        regularPrice.setText(Integer.toString(regularTicketPrice));
        if (sourceScreen.matches("cairobookfair") |sourceScreen.matches("runningevent") ){
            vipPrice.setText("-");
        }else {
            vipPrice.setText(Integer.toString(vipTicketPrice));
        }


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
// Step (7)
// ----------------------- On clicking SEND YOUR BOOKING BUTTON ----------------------------
            case R.id.btn_booking_booking:
                Intent i = new Intent(getApplicationContext(), Payment.class);//open payment screen
                if (checkIfDataValid()){ // created method

                    switch (sourceScreen){ // store three values
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
                        case "runningevent":
                            runningPref.edit().putString("ticketId" , randTicketId).apply();
                            runningPref.edit().putInt("regularCount" , regularCount).apply();
                            runningPref.edit().putInt("vipCount" , vipCount).apply();
                            runningPref.edit().commit();
                            break;
                        case "cairobookfair":
                            cairoBookPref.edit().putString("ticketId" , randTicketId).apply();
                            cairoBookPref.edit().putInt("regularCount" , regularCount).apply();
                            cairoBookPref.edit().putInt("vipCount" , vipCount).apply();
                            cairoBookPref.edit().commit();
                            break;
                        case "soundandlight":
                            soundLightPref.edit().putString("ticketId" , randTicketId).apply();
                            soundLightPref.edit().putInt("regularCount" , regularCount).apply();
                            soundLightPref.edit().putInt("vipCount" , vipCount).apply();
                            soundLightPref.edit().commit();
                            break;
                        case "siwa":
                            siwaPref.edit().putString("ticketId" , randTicketId).apply();
                            siwaPref.edit().putInt("regularCount" , regularCount).apply();
                            siwaPref.edit().putInt("vipCount" , vipCount).apply();
                            siwaPref.edit().commit();
                            break;


                        default:
                            Toast.makeText(this, "Can't Know My Source Screen", Toast.LENGTH_SHORT).show();
                    }

                }
                i.putExtra("screen" , sourceScreen); // 3shan a3rf 7ata b3d ma akml b3d el booking , ana knt gaya mneen fel asl
                startActivity(i); // da eli hay48l el activity w yro7 3al payment. BS el cache file haykon zad eli fat da.

                break;
            default:
                System.out.println("CAAAAAAAAAAK");

        }
    }

    // Step (8)
    // ---------------- get ticket count ----------------------------
    private boolean checkIfDataValid() {
        if (!vipCount_edittxt.getText().toString().matches("")){ //retrieve value in vipCount_editText, convert it into string. If not null, put its value in vipCountString, then convert the string into  integer in a variable called regularCount.
            vipCountString = String.valueOf(vipCount_edittxt.getText());
            vipCount = Integer.parseInt(vipCountString);
        }

        if (!regularcount_edittxt.getText().toString().matches("")){
            regularCountString = regularcount_edittxt.getText().toString();
            regularCount = Integer.parseInt(regularCountString);
        }


        if (vipCount == 0 && regularCount ==0){ // validation rule to ensure that either one or both of them have inputted data by the user. Otherwise, show message to the user.
            Toast.makeText(this, "You should enter amount of tickets", Toast.LENGTH_SHORT).show();
            return false;
        }else {

            //commentString = comments_edittxt.getText().toString();
            return true; // lao kol eli fat tmm w at72a2, haynfz el booking.Otherwise, nothing will happen
        }

    }

    // Step (5)
    // --------------------- Generate random ticket ID ------------------------

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
// Step (3)
// -------------------- Source Screen -----------------------------
    private void GetSourceScreen() {
        Bundle extraData = getIntent().getExtras();
        if (extraData !=null){
            this.sourceScreen = extraData.getString("screen");
        }
    }

}