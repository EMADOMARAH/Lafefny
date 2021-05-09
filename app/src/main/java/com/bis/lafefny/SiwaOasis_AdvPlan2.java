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

public class SiwaOasis_AdvPlan2 extends AppCompatActivity {
    private Button button_back_siwa2;

    TextView name_txt,day1_txt,day1info1_txt,day1info2_txt,day2_txt,day2info1_txt,day2info2_txt,day2info3_txt,day2info4_txt,day2info5_txt,day2info6_txt;
    TextView day3_txt,day3info1_txt,day3info2_txt,day3info3_txt,day3info4_txt,day3info5_txt,day3info6_txt;
    TextView include1_txt,include2_txt,include3_txt,include4_txt,include5_txt,include6_txt,include7_txt,include8_txt,include9_txt;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference siwaFullProgramDoRef = db.document("plans/categories/adventurousPlans/siwaOasisFullProgram");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siwa_oasis__adv_plan2);
        initSiwaOasisFullProgeamViews();
        getDataFromFireBase();
//        button_back_siwa2 = (Button) findViewById(R.id.btn_back_siwa_prog); //button back
//        button_back_siwa2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });


    }

    private void getDataFromFireBase() {
        siwaFullProgramDoRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            name_txt.setText(documentSnapshot.getString("name"));
                            day1_txt.setText(documentSnapshot.getString("day1"));
                            day1info1_txt.setText(documentSnapshot.getString("day1info1"));
                            day1info2_txt.setText(documentSnapshot.getString("day1info2"));
                            day2_txt.setText(documentSnapshot.getString("day2"));
                            day2info1_txt.setText(documentSnapshot.getString("day2info1"));
                            day2info2_txt.setText(documentSnapshot.getString("day2info_2"));
                            day2info3_txt.setText(documentSnapshot.getString("day2info2"));
                            day2info4_txt.setText(documentSnapshot.getString("day2info3"));
                            day2info5_txt.setText(documentSnapshot.getString("day2info4"));
                            day2info6_txt.setText(documentSnapshot.getString("day2info5"));
                            day3_txt.setText(documentSnapshot.getString("day3"));
                            day3info1_txt.setText(documentSnapshot.getString("day3info1"));
                            day3info2_txt.setText(documentSnapshot.getString("day3info2"));
                            day3info3_txt.setText(documentSnapshot.getString("day3info3"));
                            day3info4_txt.setText(documentSnapshot.getString("day3info4"));
                            day3info5_txt.setText(documentSnapshot.getString("day3info5"));
                            day3info6_txt.setText(documentSnapshot.getString("day3info6"));
                            include1_txt.setText(documentSnapshot.getString("include1"));
                            include2_txt.setText(documentSnapshot.getString("include2"));
                            include3_txt.setText(documentSnapshot.getString("include3"));
                            include4_txt.setText(documentSnapshot.getString("include4"));
                            include5_txt.setText(documentSnapshot.getString("include5"));
                            include6_txt.setText(documentSnapshot.getString("include6"));
                            include7_txt.setText(documentSnapshot.getString("include7"));
                            include8_txt.setText(documentSnapshot.getString("include8"));
                            include9_txt.setText(documentSnapshot.getString("include9"));

                        }else {
                            Toast.makeText(SiwaOasis_AdvPlan2.this, "Empty DataBase", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SiwaOasis_AdvPlan2.this, "Failed To Get Data", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void initSiwaOasisFullProgeamViews() {
        name_txt      = findViewById(R.id.txt_siwa_oasis_program);
        day1_txt      = findViewById(R.id.txt_desc9);
        day1info1_txt = findViewById(R.id.day1info1);
        day1info2_txt = findViewById(R.id.day1info2);
        day2_txt      = findViewById(R.id.txt_desc2);
        day2info1_txt = findViewById(R.id.day2_info1);
        day2info2_txt = findViewById(R.id.day2_info2);
        day2info3_txt = findViewById(R.id.day2_info3);
        day2info4_txt = findViewById(R.id.day2_info4);
        day2info5_txt = findViewById(R.id.day2_info5);
        day2info6_txt = findViewById(R.id.day2_info6);
        day3_txt      = findViewById(R.id.txt_desc4);
        day3info1_txt = findViewById(R.id.day3info1);
        day3info2_txt = findViewById(R.id.day3info2);
        day3info3_txt = findViewById(R.id.day3info3);
        day3info4_txt = findViewById(R.id.day3info4);
        day3info5_txt = findViewById(R.id.day3info5);
        day3info6_txt = findViewById(R.id.day3info6);
        include1_txt  = findViewById(R.id.include1);
        include2_txt  = findViewById(R.id.include2);
        include3_txt  = findViewById(R.id.include3);
        include4_txt  = findViewById(R.id.include4);
        include5_txt  = findViewById(R.id.include5);
        include6_txt  = findViewById(R.id.include6);
        include7_txt  = findViewById(R.id.include7);
        include8_txt  = findViewById(R.id.include8);
        include9_txt  = findViewById(R.id.include9);


    }

    public void openSiwaOasis_AdvPlan(){
        Intent intent = new Intent(this, SiwaOasis_AdvPlan.class);  //open back
        startActivity(intent);
    }

}