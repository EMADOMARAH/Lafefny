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

public class FunTopia extends AppCompatActivity {
    private TextView funTitle,funDesc1,funDesc2,funLocation,funPhone,funOpeningHours;
    private TextView funSafe1,funSafe2,funSafe3,funInfo1,funInfo2;
    private TextView funNotices1,funNotices2,funNotices3;
    private TextView funKids1,funKids2,funKids3;

    String location,contact,openHourse;
    int regularPrice,vipPrice;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference gamingDoRef = db.document("entertainment/categories/gaming/FunTopia");

    private SharedPreferences funtopiaPref;

    @Override
    protected void onStart() {
        super.onStart();
        gamingDoRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            location = documentSnapshot.getString("location");
                            funTitle.setText(documentSnapshot.getString("name"));
                            funDesc1.setText(documentSnapshot.getString("desc1"));
                            funDesc2.setText(documentSnapshot.getString("desc2"));
                            funLocation.setText(location.toString());
                            contact = documentSnapshot.getString("phone");
                            funPhone.setText(contact);
                            openHourse = documentSnapshot.getString("open");
                            funOpeningHours.setText(openHourse.toString());
                            funSafe1.setText(documentSnapshot.getString("safety1"));
                            funSafe2.setText(documentSnapshot.getString("safety2"));
                            funSafe3.setText(documentSnapshot.getString("safety3"));
                            funInfo1.setText(documentSnapshot.getString("importantInfo1"));
                            funInfo2.setText(documentSnapshot.getString("importantInfo2"));
                            funNotices1.setText(documentSnapshot.getString("notices1"));
                            funNotices2.setText(documentSnapshot.getString("notices2"));
                            funNotices3.setText(documentSnapshot.getString("notices3"));
                            funKids1.setText(documentSnapshot.getString("kids1"));
                            funKids2.setText(documentSnapshot.getString("kids2"));
                            funKids3.setText(documentSnapshot.getString("kids3"));

                        }else {
                            Toast.makeText(FunTopia.this, "DataBase Is Empty", Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(FunTopia.this, "Faild To Get Data", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun_topia);

        funtopiaPref =  getSharedPreferences("funtopia_pref" , Context.MODE_PRIVATE);

        FunTopiaInitilViews();
    }

    public void FunTopiaInitilViews(){
        funTitle        = findViewById(R.id.txt_funtopia);
        funDesc1        = findViewById(R.id.txt_funtopia_desc1);
        funDesc2        = findViewById(R.id.txt_funtopia_desc2);
        funLocation     = findViewById(R.id.fun_location);
        funPhone        = findViewById(R.id.fun_phone);
        funOpeningHours = findViewById(R.id.fun_opening_hours);
        funSafe1        = findViewById(R.id.fun_safe1);
        funSafe2        = findViewById(R.id.fun_safe2);
        funSafe3        = findViewById(R.id.fun_safe3);
        funInfo1        = findViewById(R.id.fun_important_info1);
        funInfo2        = findViewById(R.id.fun_important_info2);
        funNotices1     = findViewById(R.id.fun_notices1);
        funNotices2     = findViewById(R.id.fun_notices2);
        funNotices3     = findViewById(R.id.fun_notices3);
        funKids1        = findViewById(R.id.fun_kids1);
        funKids2        = findViewById(R.id.fun_kids2);
        funKids3        = findViewById(R.id.fun_kids3);
    }

    public void FunTopiaOnClick(View view) {
        switch (view.getId())
        {
//            case R.id.btn_back_ft:
//                    onBackPressed();
//                break;
            case R.id.btn_transportation_ft:
                startActivity(new Intent(getApplicationContext() , Transportation.class));
                break;
            case R.id.btn_ticket_ft:
                Intent intent = new Intent(getApplicationContext() , Booking.class);
                saveFuntopiaDataToPref();
                intent.putExtra("screen" , "funtopia");
                startActivity(intent);
                break;
        }
    }

    private void saveFuntopiaDataToPref() {
        funtopiaPref.edit().putString("location" , location).apply();
        funtopiaPref.edit().putString("contact" , contact).apply();
        funtopiaPref.edit().putString("source" , "Fun Topia").apply();
        funtopiaPref.edit().putString("runningTime" , openHourse).apply();
        funtopiaPref.edit().putInt("regularPrice" , 65).apply();
        funtopiaPref.edit().putInt("vipPrice" , 300).apply();
        funtopiaPref.edit().commit();

    }

    @Override
    public void onBackPressed() {
        SharedPreferences.Editor editor = funtopiaPref.edit();
        editor.clear();
        editor.commit();
        super.onBackPressed();
    }
}