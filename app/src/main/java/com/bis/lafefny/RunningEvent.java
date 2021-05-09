package com.bis.lafefny;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class RunningEvent extends AppCompatActivity {

    TextView title_txt,description_txt , location_txt, fee_txt,openHours_txt,date_txt, info1_txt,info2_txt;
    TextView info3_txt,info4_txt;

    String location,openHourse , date;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference runningDoRef = db.document("events/catrgories/sportsEvents/running");

    private SharedPreferences runningPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running_event);

        initRunningViews();
        getDataFromFireBase();

        runningPref = getSharedPreferences("Running_Event_pref" , Context.MODE_PRIVATE);

    }

    private void getDataFromFireBase() {
        runningDoRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            title_txt.setText(documentSnapshot.getString("name"));
                            description_txt.setText(documentSnapshot.getString("description"));
                            location = documentSnapshot.getString("location");
                            location_txt.setText(location);
                            fee_txt.setText(documentSnapshot.getString("fee"));
                            openHourse = documentSnapshot.getString("time");
                            openHours_txt.setText(openHourse);
                            date = documentSnapshot.getString("date");
                            date_txt.setText(date);
                            info1_txt.setText(documentSnapshot.getString("info1"));
                            info2_txt.setText(documentSnapshot.getString("info2"));
                            info3_txt.setText(documentSnapshot.getString("info3"));
                            info4_txt.setText(documentSnapshot.getString("info4"));
                        }else {
                            Toast.makeText(RunningEvent.this, "DataBase Is Empty", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RunningEvent.this, "Failed To Get Data", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void initRunningViews() {
        title_txt       = findViewById(R.id.txt_running);
        description_txt = findViewById(R.id.txt_running_desc1);
        location_txt    = findViewById(R.id.running_location);
        fee_txt         = findViewById(R.id.running_fee);
        openHours_txt   = findViewById(R.id.running_openhours);
        date_txt        = findViewById(R.id.running_date);
        info1_txt       = findViewById(R.id.running_info1);
        info2_txt       = findViewById(R.id.running_info2);
        info3_txt       = findViewById(R.id.running_info3);
        info4_txt       = findViewById(R.id.running_info4);
    }

    public void RunningEventOnClick(View view) {
        switch (view.getId()){
//            case R.id.btn_back_run:
//                onBackPressed();
//                break;
            case R.id.btn_book_run:
                Intent i =new Intent(this, Booking.class);
                SaveRunningDataToPref();
                i.putExtra("screen" , "runningevent");
                startActivity(i);
                break;
            case R.id.btn_transportation_run:
                startActivity(new Intent(this, Transportation.class));
                break;


        }
    }

    private void SaveRunningDataToPref() {
        runningPref.edit().putInt("vipPrice" , 0).apply();
        runningPref.edit().putInt("regularPrice",50).apply();
        runningPref.edit().putString("source","Running Event").apply();
        runningPref.edit().putString("runningTime" , openHourse).apply();
        runningPref.edit().putString("startDate" , date).apply();
        runningPref.edit().putString("location" , location).apply();
        runningPref.edit().commit();

    }

    @Override
    public void onBackPressed() {
        SharedPreferences.Editor editor = runningPref.edit();
        editor.clear();
        editor.commit();
        super.onBackPressed();
    }
}