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

public class EgyptianMuseum extends AppCompatActivity {
    private TextView egyName, egyDisc, egyLocation, egyOpenNormal, egyOpenFriday1, egyOpenFriday2;
    private TextView egyTicketType1, egyTicketType2, info1, info2, info3, info4;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference MuseumDoRef = db.document("entertainment/categories/tourism/EgyptianMuseum");
    private SharedPreferences egyptianPref;
    String location , openHourse;

    @Override
    protected void onStart() {
        super.onStart();
        MuseumDoRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            egyName.setText(documentSnapshot.getString("name"));
                            egyDisc.setText(documentSnapshot.getString("description"));
                            location = documentSnapshot.getString("location");
                            egyLocation.setText(location);
                            openHourse = documentSnapshot.getString("openNormal");
                            egyOpenNormal.setText(openHourse);
                            egyOpenFriday1.setText(documentSnapshot.getString("openFriday1"));
                            egyOpenFriday2.setText(documentSnapshot.getString("openFriday2"));
                            egyTicketType1.setText(documentSnapshot.getString("ticket1"));
                            egyTicketType2.setText(documentSnapshot.getString("ticket2"));
                            info1.setText(documentSnapshot.getString("importantInfo1"));
                            info2.setText(documentSnapshot.getString("importantInfo2"));
                            info3.setText(documentSnapshot.getString("importantInfo3"));
                            info4.setText(documentSnapshot.getString("importantInfo4"));

                        } else {
                            Toast.makeText(EgyptianMuseum.this, "DataBase is Empty", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(EgyptianMuseum.this, "Faild To Get Data", Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egyptian_museum);
        egyptianPref =  getSharedPreferences("egyptian_museum_pref" , Context.MODE_PRIVATE);


        EgyptianMuseumInitViews();

    }

    private void EgyptianMuseumInitViews() {
        egyName = findViewById(R.id.txt_egyptian_museum_egyM);
        egyDisc = findViewById(R.id.txt_egyM_desc1);
        egyLocation = findViewById(R.id.egy_location);
        egyOpenNormal = findViewById(R.id.open_alldays);
        egyOpenFriday1 = findViewById(R.id.open_friday1);
        egyOpenFriday2 = findViewById(R.id.open_friday2);
        egyTicketType1 = findViewById(R.id.egy_ticket_egy);
        egyTicketType2 = findViewById(R.id.egy_ticket_forg);
        info1 = findViewById(R.id.egy_important_info1);
        info2 = findViewById(R.id.egy_important_info2);
        info3 = findViewById(R.id.egy_important_info3);
        info4 = findViewById(R.id.egy_important_info4);
    }


    public void EgyptionMuseumOnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back_egyM:
                onBackPressed();
                break;
            case R.id.btn_transportation_egyM:
                startActivity(new Intent(getApplicationContext(), Transportation.class));
                break;
            case R.id.btn_ticket_egyM:
                Intent i = new Intent(getApplicationContext(), Booking.class);
                saveMuseumDataToPref();
                i.putExtra("screen" , "egyptianmuseum");
                startActivity(i);
                break;
        }
    }

    private void saveMuseumDataToPref() {
        egyptianPref.edit().putString("location" , location).apply();
        egyptianPref.edit().putString("source" , "Egyptian Museum").apply();
        egyptianPref.edit().putString("runningTime" , openHourse).apply();
        egyptianPref.edit().putInt("regularPrice" , 30).apply();
        egyptianPref.edit().putInt("vipPrice" , 160).apply();
        egyptianPref.edit().commit();
    }

    @Override
    public void onBackPressed() {
        SharedPreferences.Editor editor = egyptianPref.edit();
        editor.clear();
        editor.commit();
        super.onBackPressed();
    }
}