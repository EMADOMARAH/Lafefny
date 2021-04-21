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

public class SiwaOasis_AdvPlan extends AppCompatActivity {

    TextView name__txt,description_txt,program1_txt,program2_txt,program3_txt,price1_txt,price2_txt,price3_txt,price4_txt,price5_txt;
    TextView req1_txt,req2_txt,req3_txt,info1_txt,info2_txt;

    int vipPrice,regularPrice;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference siwaDoRef = db.document("plans/categories/adventurousPlans/siwaOasis");

    private SharedPreferences siwaPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siwa_oasis__adv_plan);

        initSiwaOasisViews();
        getDataFromFireBase();

        siwaPref = getSharedPreferences("siwa_pref" , Context.MODE_PRIVATE);


    }

    private void getDataFromFireBase() {
        siwaDoRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            name__txt.setText(documentSnapshot.getString("name"));
                            description_txt.setText(documentSnapshot.getString("description"));
                            program1_txt.setText(documentSnapshot.getString("program1"));
                            program2_txt.setText(documentSnapshot.getString("program2"));
                            program3_txt.setText(documentSnapshot.getString("program3"));
                            price1_txt.setText(documentSnapshot.getString("tripPrices1"));
                            price2_txt.setText(documentSnapshot.getString("tripPrices2"));
                            price3_txt.setText(documentSnapshot.getString("tripPrices3"));
                            price4_txt.setText(documentSnapshot.getString("tripPrices4"));
                            price5_txt.setText(documentSnapshot.getString("tripPrices5"));
                            req1_txt.setText(documentSnapshot.getString("tripRequirements1"));
                            req2_txt.setText(documentSnapshot.getString("tripRequirements2"));
                            req3_txt.setText(documentSnapshot.getString("tripRequirements3"));
                            info1_txt.setText(documentSnapshot.getString("info1"));
                            info2_txt.setText(documentSnapshot.getString("info2"));
                            vipPrice = Integer.parseInt(documentSnapshot.getString("vipPrice"));
                            regularPrice = Integer.parseInt(documentSnapshot.getString("regularPrice"));

                        }else {
                            Toast.makeText(SiwaOasis_AdvPlan.this, "DataBase Empty", Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SiwaOasis_AdvPlan.this, "Failed To Get Data", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void initSiwaOasisViews() {
        name__txt = findViewById(R.id.txt_siwa_oasis);
        description_txt = findViewById(R.id.txt_desc_siwa_intro);
        program1_txt = findViewById(R.id.siwa_program1);
        program2_txt = findViewById(R.id.siwa_program2);
        program3_txt = findViewById(R.id.siwa_program3);
        price1_txt = findViewById(R.id.siwa_price1);
        price2_txt = findViewById(R.id.siwa_price2);
        price3_txt = findViewById(R.id.siwa_price3);
        price4_txt = findViewById(R.id.siwa_price4);
        price5_txt = findViewById(R.id.siwa_price5);
        req1_txt = findViewById(R.id.siwa_req1);
        req2_txt = findViewById(R.id.siwa_req2);
        req3_txt = findViewById(R.id.siwa_req3);
        info1_txt = findViewById(R.id.siwa_info1);
        info2_txt = findViewById(R.id.siwa_info2);
    }


    public void SiwaOasisOnClick(View view) {
        switch (view.getId()){
            case R.id.btn_back_siwa_intro:
                onBackPressed();
                break;
            case R.id.btn_oasis_more_details:
                startActivity( new Intent(this, SiwaOasis_AdvPlan2.class));
                break;
            case R.id.btn_transportation_siwa_intro:
                startActivity(new Intent(this, Transportation.class));
                break;
            case R.id.btn_ticket_siwa_intro:
                Intent intent = new Intent(this, Booking.class);  //open Ticket
                SaveRunningDataToPref();
                intent.putExtra("screen" , "siwa");
                startActivity(intent);
                break;
        }
    }

    private void SaveRunningDataToPref() {
        siwaPref.edit().putInt("vipPrice" , vipPrice).apply();
        siwaPref.edit().putInt("regularPrice",regularPrice).apply();
        siwaPref.edit().putString("source","Siwa Oasis").apply();
        siwaPref.edit().putString("runningTime" , "All Day").apply();
        siwaPref.edit().putString("startDate" , "All Year").apply();
        siwaPref.edit().putString("location" , "Siwa Oasis, Siwa, Matrouh Governorate").apply();
        siwaPref.edit().commit();
    }


    @Override
    public void onBackPressed() {
        SharedPreferences.Editor editor = siwaPref.edit();
        editor.clear();
        editor.commit();
        super.onBackPressed();
    }
}