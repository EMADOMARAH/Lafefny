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

public class CairoBookFair extends AppCompatActivity {
    TextView title_txt,description_txt, location_txt,date_txt,price_txt,time_txt,organizedBy_txt;

    String location,date,time,price;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference cairoBookDoRef = db.document("events/catrgories/cairoInternationalBook/internationalBook");

    private SharedPreferences cairoBookPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cairo_book_fair);

        initCairoBookFairViews();
        getDataFromFireBase();

        cairoBookPref = getSharedPreferences("Cairo_Book_Fair_pref" , Context.MODE_PRIVATE);

    }

    private void getDataFromFireBase() {
        cairoBookDoRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            title_txt.setText(documentSnapshot.getString("name"));
                            description_txt.setText(documentSnapshot.getString("description"));
                            location = documentSnapshot.getString("location");
                            location_txt.setText(location);
                            time = documentSnapshot.getString("time");
                            time_txt.setText(time);
                            date = documentSnapshot.getString("date");
                            date_txt.setText(date);
                            organizedBy_txt.setText(documentSnapshot.getString("organizedBy"));
                            price_txt.setText(documentSnapshot.getString("price"));

                        }else {
                            Toast.makeText(CairoBookFair.this, "DataBase is empty", Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CairoBookFair.this, "Failed Get Data", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initCairoBookFairViews() {
        title_txt       = findViewById(R.id.txt_book_fair);
        description_txt = findViewById(R.id.txt_book_fair_desc1);
        location_txt    = findViewById(R.id.cairo_book_location);
        date_txt        = findViewById(R.id.cairo_book_date);
        price_txt       = findViewById(R.id.cairo_book_price);
        time_txt        = findViewById(R.id.cairo_book_time);
        organizedBy_txt = findViewById(R.id.cairo_book_organized_by);

    }

    public void CairoBookFairOnClick(View view) {
        switch (view.getId()){
//            case R.id.btn_back_cbf:
//                onBackPressed();
//                break;
            case R.id.btn_ticket_cbf:
                Intent i =new Intent(this, Booking.class);
                SaveCairoBookFairDataToPref();
                i.putExtra("screen" , "cairobookfair");
                startActivity(i);
                break;
            case R.id.btn_transportation_cbf:
                startActivity(new Intent(this, Transportation.class));
                break;

        }
    }

    private void SaveCairoBookFairDataToPref() {
        cairoBookPref.edit().putInt("vipPrice" , 0).apply();
        cairoBookPref.edit().putInt("regularPrice",5).apply();
        cairoBookPref.edit().putString("source","International Book Fair").apply();
        cairoBookPref.edit().putString("runningTime" , time).apply();
        cairoBookPref.edit().putString("startDate" , date).apply();
        cairoBookPref.edit().putString("location" , location).apply();
        cairoBookPref.edit().commit();
    }

    @Override
    public void onBackPressed() {
        SharedPreferences.Editor editor = cairoBookPref.edit();
        editor.clear();
        editor.commit();
        super.onBackPressed();
    }
}