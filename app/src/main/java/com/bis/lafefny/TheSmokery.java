package com.bis.lafefny;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class TheSmokery extends AppCompatActivity {

    private TextView resName,resDisc,resLocation1,resLocation2,resLocation3,resLocation4;
    private TextView resPhone1,resPhone2,resPhone3,resOpen,resWebsite,resEmail;
    private TextView resCuisines1,resCuisines2,resCuisines3,resCuisines4;
    private TextView resMeal1,resMeal2,resMeal3;
    private TextView resType1,resType2,resF1,resF2,resF3,resF4,resF5;
    private TextView resF6,resF7;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference TheSmokeryDoRef = db.document("entertainment/categories/restaurants/TheSmokery");


    @Override
    protected void onStart() {
        super.onStart();
        TheSmokeryDoRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            resName.setText(documentSnapshot.getString("name"));
                            resDisc.setText(documentSnapshot.getString("description"));
                            resLocation1.setText(documentSnapshot.getString("location1"));
                            resLocation2.setText(documentSnapshot.getString("location2"));
                            resLocation3.setText(documentSnapshot.getString("location3"));
                            resLocation4.setText(documentSnapshot.getString("location4"));
                            resPhone1.setText(documentSnapshot.getString("phone1"));
                            resPhone2.setText(documentSnapshot.getString("phone2"));
                            resPhone3.setText(documentSnapshot.getString("phone3"));
                            resOpen.setText(documentSnapshot.getString("openHours"));
                            resCuisines1.setText(documentSnapshot.getString("cuisines1"));
                            resCuisines2.setText(documentSnapshot.getString("cuisines2"));
                            resCuisines3.setText(documentSnapshot.getString("cuisines3"));
                            resCuisines4.setText(documentSnapshot.getString("cuisines4"));
                            resMeal1.setText(documentSnapshot.getString("meal1"));
                            resMeal2.setText(documentSnapshot.getString("meal2"));
                            resMeal3.setText(documentSnapshot.getString("meal3"));
                            resType1.setText(documentSnapshot.getString("type1"));
                            resType2.setText(documentSnapshot.getString("type2"));
                            resF1.setText(documentSnapshot.getString("feature1"));
                            resF2.setText(documentSnapshot.getString("feature2"));
                            resF3.setText(documentSnapshot.getString("feature3"));
                            resF4.setText(documentSnapshot.getString("feature4"));
                            resF5.setText(documentSnapshot.getString("feature5"));
                            resF6.setText(documentSnapshot.getString("feature6"));
                            resF7.setText(documentSnapshot.getString("feature7"));

                        }else {
                            Toast.makeText(TheSmokery.this, "DataBase is Empty", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(TheSmokery.this, "Faild To Get Data", Toast.LENGTH_SHORT).show();

                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_smokery);

        TheSmokeryInitViews();


    }

    private void TheSmokeryInitViews() {
        resName      = findViewById(R.id.txt_the_smokery);
        resDisc      = findViewById(R.id.txt_the_smokery_desc1);
        resLocation1 = findViewById(R.id.the_smokery_location1);
        resLocation2 = findViewById(R.id.the_smokery_location2);
        resLocation3 = findViewById(R.id.the_smokery_location3);
        resLocation4 = findViewById(R.id.the_smokery_location4);
        resPhone1    = findViewById(R.id.the_smokery_phone1);
        resPhone2    = findViewById(R.id.the_smokery_phone2);
        resPhone3    = findViewById(R.id.the_smokery_phone3);
        resOpen      = findViewById(R.id.the_smokery_open);
        resWebsite   = findViewById(R.id.the_smokery_site);
        resEmail     = findViewById(R.id.the_smokery_email);
        resCuisines1 = findViewById(R.id.the_smokery_cuisines1);
        resCuisines2 = findViewById(R.id.the_smokery_cuisines2);
        resCuisines3 = findViewById(R.id.the_smokery_cuisines3);
        resCuisines4 = findViewById(R.id.the_smokery_cuisines4);
        resMeal1     = findViewById(R.id.the_smokery_meal1);
        resMeal2     = findViewById(R.id.the_smokery_meal2);
        resMeal3     = findViewById(R.id.the_smokery_meal3);
        resType1     = findViewById(R.id.the_smokery_type1);
        resType2     = findViewById(R.id.the_smokery_type2);
        resF1        = findViewById(R.id.the_smokery_feature1);
        resF2        = findViewById(R.id.the_smokery_feature2);
        resF3        = findViewById(R.id.the_smokery_feature3);
        resF4        = findViewById(R.id.the_smokery_feature4);
        resF5        = findViewById(R.id.the_smokery_feature5);
        resF6        = findViewById(R.id.the_smokery_feature6);
        resF7        = findViewById(R.id.the_smokery_feature7);
    }

    public void TheSmokeryOnClick(View view) {

        switch (view.getId()) {
            case R.id.btn_back_smokery:
                startActivity(new Intent(getApplicationContext(), Restaurants.class));
                break;
            case R.id.btn_transportation_smokery:
                startActivity(new Intent(getApplicationContext(), Transportation.class));
                break;
            case R.id.btn_ticket_smokery:
                startActivity(new Intent(getApplicationContext(), Booking.class));
                break;
        }

    }
}
