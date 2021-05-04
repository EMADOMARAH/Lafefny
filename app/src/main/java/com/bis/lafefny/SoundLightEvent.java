package com.bis.lafefny;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class SoundLightEvent extends AppCompatActivity {

    TextView title_txt,description1_txt,description2_txt,regTicketPrice_txt,vipTicketPrice_txt;
    TextView day1_txt,day1_time1_txt,day1_time2_txt,day1_lang1_txt,day1_lang2_txt,day1_price1_txt,day1_price2_txt;
    TextView day2_txt,day2_time1_txt,day2_time2_txt,day2_lang1_txt,day2_lang2_txt,day2_price1_txt,day2_price2_txt;
    TextView day3_txt,day3_time1_txt,day3_time2_txt,day3_lang1_txt,day3_lang2_txt,day3_price1_txt,day3_price2_txt;
    TextView day4_txt,day4_time1_txt,day4_time2_txt,day4_lang1_txt,day4_lang2_txt,day4_price1_txt,day4_price2_txt;


    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference soundLightDoRef = db.document("events/catrgories/soundNlight/soundandLight");

    private SharedPreferences soundLightPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_light_event);
        
        initSoundLightViews();
        getDataFromFireBase();

        soundLightPref = getSharedPreferences("Sound_Light_pref" , Context.MODE_PRIVATE);


    }

    private void getDataFromFireBase() {
        soundLightDoRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            title_txt.setText(documentSnapshot.getString("name"));
                            description1_txt.setText(documentSnapshot.getString("description"));
                            description2_txt.setText(documentSnapshot.getString("description2"));
                            regTicketPrice_txt.setText(documentSnapshot.getString("regularPrice") + " EGP");
                            vipTicketPrice_txt.setText(documentSnapshot.getString("vipPrice") + " EGP");
                            //----------------------------------------------------------------------------------
                            day1_txt.setText(documentSnapshot.getString("day1"));
                            day1_time1_txt.setText(documentSnapshot.getString("day1time1"));
                            day1_lang1_txt.setText(documentSnapshot.getString("day1lang1"));
                            day1_price1_txt.setText(documentSnapshot.getString("day1price1") + " EGP");
                            day1_time2_txt.setText(documentSnapshot.getString("day1time2"));
                            day1_lang2_txt.setText(documentSnapshot.getString("day1lang2"));
                            day1_price2_txt.setText(documentSnapshot.getString("day1price2") + " EGP");
                            //----------------------------------------------------------------------------------
                            day2_txt.setText(documentSnapshot.getString("day2"));
                            day2_time1_txt.setText(documentSnapshot.getString("day2time1"));
                            day2_lang1_txt.setText(documentSnapshot.getString("day2lang1"));
                            day2_price1_txt.setText(documentSnapshot.getString("day2price1") + " EGP");
                            day2_time2_txt.setText(documentSnapshot.getString("day2time2"));
                            day2_lang2_txt.setText(documentSnapshot.getString("day2lang2"));
                            day2_price2_txt.setText(documentSnapshot.getString("day2price2") + " EGP");
                            //----------------------------------------------------------------------------------
                            day3_txt.setText(documentSnapshot.getString("day3"));
                            day3_time1_txt.setText(documentSnapshot.getString("day3time1"));
                            day3_lang1_txt.setText(documentSnapshot.getString("day3lang1"));
                            day3_price1_txt.setText(documentSnapshot.getString("day3price1") + " EGP");
                            day3_time2_txt.setText(documentSnapshot.getString("day3time2"));
                            day3_lang2_txt.setText(documentSnapshot.getString("day3lang2"));
                            day3_price2_txt.setText(documentSnapshot.getString("day3price2") + " EGP");
                            //----------------------------------------------------------------------------------
                            day4_txt.setText(documentSnapshot.getString("day4"));
                            day4_time1_txt.setText(documentSnapshot.getString("day4time1"));
                            day4_lang1_txt.setText(documentSnapshot.getString("day4lang1"));
                            day4_price1_txt.setText(documentSnapshot.getString("day4price1") + " EGP");
                            day4_time2_txt.setText(documentSnapshot.getString("day4time2"));
                            day4_lang2_txt.setText(documentSnapshot.getString("day4lang2"));
                            day4_price2_txt.setText(documentSnapshot.getString("day4price2") + " EGP");
                        }else {
                            Toast.makeText(SoundLightEvent.this, "Database Empty", Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SoundLightEvent.this, "Failed To Get Data", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initSoundLightViews() {
        title_txt          = findViewById(R.id.txt_sound_light_show);
        description1_txt   = findViewById(R.id.txt_desc);
        description2_txt   = findViewById(R.id.txt_desc2);
        regTicketPrice_txt = findViewById(R.id.txt_ticket_regular_price);
        vipTicketPrice_txt = findViewById(R.id.txt_ticket_vip_price);


        //------------------------------------------------------------
        day1_txt        = findViewById(R.id.day1);
        day1_time1_txt  = findViewById(R.id.day1_time1_txt);
        day1_time2_txt  = findViewById(R.id.day1_time2_txt);
        day1_lang1_txt  = findViewById(R.id.day1_lang1_txt);
        day1_lang2_txt  = findViewById(R.id.day1_lang2_txt);
        day1_price1_txt = findViewById(R.id.day1_price1_txt);
        day1_price2_txt = findViewById(R.id.day1_price2_txt);
        //------------------------------------------------------------
        day2_txt        = findViewById(R.id.day2);
        day2_time1_txt  = findViewById(R.id.day2_time1_txt);
        day2_time2_txt  = findViewById(R.id.day2_time2_txt);
        day2_lang1_txt  = findViewById(R.id.day2_lang1_txt);
        day2_lang2_txt  = findViewById(R.id.day2_lang2_txt);
        day2_price1_txt = findViewById(R.id.day2_price1_txt);
        day2_price2_txt = findViewById(R.id.day2_price2_txt);
        //------------------------------------------------------------
        day3_txt        = findViewById(R.id.day3);
        day3_time1_txt  = findViewById(R.id.day3_time1_txt);
        day3_time2_txt  = findViewById(R.id.day3_time2_txt);
        day3_lang1_txt  = findViewById(R.id.day3_lang1_txt);
        day3_lang2_txt  = findViewById(R.id.day3_lang2_txt);
        day3_price1_txt = findViewById(R.id.day3_price1_txt);
        day3_price2_txt = findViewById(R.id.day3_price2_txt);
        //------------------------------------------------------------
        day4_txt        = findViewById(R.id.day4);
        day4_time1_txt  = findViewById(R.id.day4_time1_txt);
        day4_time2_txt  = findViewById(R.id.day4_time2_txt);
        day4_lang1_txt  = findViewById(R.id.day4_lang1_txt);
        day4_lang2_txt  = findViewById(R.id.day4_lang2_txt);
        day4_price1_txt = findViewById(R.id.day4_price1_txt);
        day4_price2_txt = findViewById(R.id.day4_price2_txt);
        //------------------------------------------------------------


    }


    private void SavaSoundNLightDataToPref() {
        soundLightPref.edit().putInt("vipPrice" , 300).apply();
        soundLightPref.edit().putInt("regularPrice",350).apply();
        soundLightPref.edit().putString("source","Sound And Light Show").apply();
        soundLightPref.edit().putString("runningTime" , "Hour per Day").apply();
        soundLightPref.edit().putString("startDate" , "4 days aweek").apply();
        soundLightPref.edit().putString("location" , "Kornish Al Nile, Karnak, Luxor").apply();
        soundLightPref.edit().commit();

    }

    public void SoundNLightOnClick(View view) {
        switch (view.getId()){
//            case R.id.btn_back_SLshow:
//                onBackPressed();
//                break;
            case R.id.btn_transportation_SLshow:
                startActivity(new Intent(this, Transportation.class));
                break;
            case R.id.btn_ticket_SLshow:
                Intent i =new Intent(this, Booking.class);
                SavaSoundNLightDataToPref();
                i.putExtra("screen" , "soundandlight");
                startActivity(i);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        SharedPreferences.Editor editor = soundLightPref.edit();
        editor.clear();
        editor.commit();
        super.onBackPressed();
    }
}