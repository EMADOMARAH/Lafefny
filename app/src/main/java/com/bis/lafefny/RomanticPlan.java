package com.bis.lafefny;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
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

public class RomanticPlan extends AppCompatActivity {
    private Button button_back;
    private Button button_funtopia;
    private Button button_smokery;
    private Button button_vox;
    private Button button_home;
    private Button button_pre;

    TextView txt_romantic,txt_desc_romantic;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference romanticPlanDoRef = db.document("plans/categories/romanticPlan/romanticPlan");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_romantic_plan);

        txt_romantic = findViewById(R.id.txt_romantic);
        txt_desc_romantic = findViewById(R.id.txt_desc_romantic);
        getDataFromFireBase();



        button_back = (Button) findViewById(R.id.btn_back_rom); //button back
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        button_pre = (Button) findViewById(R.id.btn_pre_rom); //button preferences
        button_pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openpreferences();
            }
        });

        button_home = (Button) findViewById(R.id.btn_home1_rom); //button homepage
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomepage();
            }
        });

        button_funtopia = (Button) findViewById(R.id.btn_fun_topia_rom); //button funtopia
        button_funtopia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFunTopia();
            }
        });

        button_smokery = (Button) findViewById(R.id.btn_smokery_rom); //button smokery
        button_smokery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTheSmokery();
            }
        });

        button_vox = (Button) findViewById(R.id.btn_vox_rom); //button vox
        button_vox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVoxCinema();
            }
        });
    }

    private void getDataFromFireBase() {
        romanticPlanDoRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            txt_romantic.setText(documentSnapshot.getString("name"));
                            txt_desc_romantic.setText(documentSnapshot.getString("description"));
                        }else {
                            Toast.makeText(RomanticPlan.this, "Database Empty", Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RomanticPlan.this, "Failed To Get Data", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void openPlans(){
        Intent intent = new Intent(this, Plans.class);  //open back
        startActivity(intent);
    }

    public void openpreferences(){
        Intent intent = new Intent(this, preferences.class);  //open preferences
        startActivity(intent);
    }
    public void openHomepage(){
        Intent intent = new Intent(this, Homepage.class);  //open homepage
        startActivity(intent);
    }

    public void openFunTopia(){
        Intent intent = new Intent(this, FunTopia.class);  //open funtopia
        startActivity(intent);
    }

    public void openTheSmokery(){
        Intent intent = new Intent(this, TheSmokery.class);  //open smokery
        startActivity(intent);
    }

    public void openVoxCinema(){
        Intent intent = new Intent(this, VoxCinema.class);  //open vox
        startActivity(intent);
    }

    public void RomanticOnClick(View view) {
        switch (view.getId()){
            case R.id.txt_location_fun_topia_romantic:
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com/maps/place/Funtopia+-+Mazar+Mall/@30.0534609,30.9604134,15z/data=!4m2!3m1!1s0x0:0x9bb07872950039f0?sa=X&ved=2ahUKEwiuwMGKs4_wAhUCyoUKHUIbBXsQ_BIwFHoECB0QBQ"));
                startActivity(intent);
                break;
            case R.id.txt_location_smokery_rom:
                Intent i = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com/maps/search/the+smokery/@30.0428723,31.0839895,11z/data=!3m1!4b1"));
                startActivity(i);
                break;
            case R.id.txt_location_vox_rom:
                Intent voxLocation = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com/search?q=vox%20cinema%20location&oq=vox+cenima+location&aqs=edge..69i57j0i13j0i13i30l2j0i22i30j0i5i13i30l2.6487j0j4&sourceid=chrome&ie=UTF-8&tbs=lf:1,lf_ui:1&tbm=lcl&sxsrf=ALeKk02GhQIjO-VSKJRXokh4dzfpMQ2nuw:1618313718001&rflfq=1&num=10&rldimm=6822194390240675982&lqi=ChN2b3ggY2luZW1hIGxvY2F0aW9uSOvq2ZqFsICACFogCgp2b3ggY2luZW1hEAAQARgAGAEiCnZveCBjaW5lbWGSAQ1tb3ZpZV90aGVhdGVy&phdesc=Sn3gZtF5Smg&ved=2ahUKEwi_oa-ykPvvAhV0sXEKHeEGAscQvS4wAHoECAgQJg&rlst=f#rlfi=hd:;si:;mv:[[30.088468899999995,31.3837675],[29.964886799999995,30.997308300000004]]"));
                startActivity(voxLocation);
                break;
        }
    }
}