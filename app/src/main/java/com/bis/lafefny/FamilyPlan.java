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

public class FamilyPlan extends AppCompatActivity {
//    private Button button_back;
    private Button button_home;
    private Button button_pre_Fam;
    private Button button_egyp_museum;
    private Button button_account_FP;

    TextView txt_family,txt_desc;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference familyPlanDoRef = db.document("plans/categories/familyPlan/familyPlan");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_plan);

        txt_family = findViewById(R.id.txt_family);
        txt_desc = findViewById(R.id.txt_desc);
        
        getDataFromFireBase();


//        button_back = (Button) findViewById(R.id.btn_back_fam); //button back
//        button_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });

        button_home = (Button) findViewById(R.id.btn_home1_fam); //button homepage
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomepage();
            }
        });

        button_account_FP = (Button) findViewById(R.id.btn_use_famr); //button user account
        button_account_FP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openaccount();
            }
        });

        button_egyp_museum = (Button) findViewById(R.id.btn_egyptian_museum_fam); //button EgyptianMuseum
        button_egyp_museum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEgyptianMuseum();
            }
        });
        button_pre_Fam = (Button) findViewById(R.id.btn_pre_fam); //button preferences
        button_pre_Fam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openpreferences();
            }
        });

    }

    private void getDataFromFireBase() {
        familyPlanDoRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            txt_family.setText(documentSnapshot.getString("name"));
                            txt_desc.setText(documentSnapshot.getString("description"));
                        }else{
                            Toast.makeText(FamilyPlan.this, "Data Not Exist", Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(FamilyPlan.this, "Failed To Get Data", Toast.LENGTH_SHORT).show();

                    }
                });
    }

    public void openPlans(){
        Intent intent = new Intent(this, Plans.class);  //open back
        startActivity(intent);
    }

    public void openaccount(){
        Intent intent = new Intent(this, Account.class);  //open account
        startActivity(intent);
    }
    public void openHomepage(){
        Intent intent = new Intent(this, Homepage.class);  //open homepage
        startActivity(intent);
    }

    public void openEgyptianMuseum(){
        Intent intent = new Intent(this, EgyptianMuseum.class);  //open EgyptianMuseum
        startActivity(intent);
    }
    public void openpreferences(){
        Intent intent = new Intent(this, preferences.class);  //open preferences
        startActivity(intent);
    }

    public void FamilyPlanOnClick(View view) {
        switch (view.getId()){
            case R.id.txt_location_museum_fam:
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com/maps/place/The+Egyptian+Museum/@30.0475781,31.2314252,17z/data=!3m1!4b1!4m5!3m4!1s0x145841885535bec3:0x520da52b3a7a660f!8m2!3d30.0475781!4d31.2336139"));
                startActivity(intent);
                break;

        }
    }
}