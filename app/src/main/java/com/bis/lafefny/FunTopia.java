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

public class FunTopia extends AppCompatActivity {
    private TextView funTitle,funDesc1,funDesc2,funLocation,funPhone,funOpeningHours;
    private TextView funSafe1,funSafe2,funSafe3,funInfo1,funInfo2;
    private TextView funNotices1,funNotices2,funNotices3;
    private TextView funKids1,funKids2,funKids3;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference gamingDoRef = db.document("entertainment/categories/gaming/FunTopia");


    @Override
    protected void onStart() {
        super.onStart();
        gamingDoRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            funTitle.setText(documentSnapshot.getString("name"));
                            funDesc1.setText(documentSnapshot.getString("desc1"));
                            funDesc2.setText(documentSnapshot.getString("desc2"));
                            funLocation.setText(documentSnapshot.getString("location"));
                            funPhone.setText(documentSnapshot.getString("phone"));
                            funOpeningHours.setText(documentSnapshot.getString("open"));
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
            case R.id.btn_back_ft:
                startActivity(new Intent(getApplicationContext(), GamingArea.class));
                break;
            case R.id.btn_transportation_ft:
                startActivity(new Intent(getApplicationContext() , Transportation.class));
                break;
            case R.id.btn_ticket_ft:
                startActivity(new Intent(getApplicationContext() , Booking.class));
                break;
        }
    }
}