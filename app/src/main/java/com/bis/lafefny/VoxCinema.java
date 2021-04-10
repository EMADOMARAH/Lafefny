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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class VoxCinema extends AppCompatActivity {
    TextView voxTitle,voxDescription,voxLocation1,voxLocation2,voxLocation3,voxPhone,voxOpening,voxTypeOfCinema;
    TextView m1Name , m1Genre , m1RunningTime , m1ReleaseDate , m1Regular , m1Vip;
    TextView m2Name , m2Genre , m2RunningTime , m2ReleaseDate , m2Regular , m2Vip;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference cenimaDoRef = db.document("entertainment/categories/Cinema/VoxCinema");

    @Override
    protected void onStart() {
        super.onStart();
        cenimaDoRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            voxTitle.setText(documentSnapshot.getString("name"));
                            voxDescription.setText(documentSnapshot.getString("description"));
                            voxLocation1.setText(documentSnapshot.getString("location1"));
                            voxLocation2.setText(documentSnapshot.getString("location2"));
                            voxLocation3.setText(documentSnapshot.getString("location3"));
                            voxPhone.setText(documentSnapshot.getString("phone"));
                            voxOpening.setText(documentSnapshot.getString("opening"));
                            voxTypeOfCinema.setText(documentSnapshot.getString("cinemaType"));
                            m1Name.setText(documentSnapshot.getString("m1name"));
                            m1Genre.setText(documentSnapshot.getString("m1genre"));
                            m1RunningTime.setText(documentSnapshot.getString("m1runningtime"));
                            m1ReleaseDate.setText(documentSnapshot.getString("m1resaledate"));
                            m1Regular.setText(documentSnapshot.getString("m1regular"));
                            m1Vip.setText(documentSnapshot.getString("m1vip"));
                            m2Name.setText(documentSnapshot.getString("m2name"));
                            m2Genre.setText(documentSnapshot.getString("m2genre"));
                            m2RunningTime.setText(documentSnapshot.getString("m2runningtime"));
                            m2ReleaseDate.setText(documentSnapshot.getString("m2resaledate"));
                            m2Regular.setText(documentSnapshot.getString("m2regular"));
                            m2Vip.setText(documentSnapshot.getString("m2vip"));

                        }else {
                            Toast.makeText(getApplicationContext(), "DataBase is Empty", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(VoxCinema.this, "Faild To Get Data", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vox_cinema);

        voxInitilViews();
    }

    public void voxInitilViews(){
        voxTitle        = findViewById(R.id.txt_vox_cinema);
        voxDescription  = findViewById(R.id.txt_vox_cinema_desc1);
        voxLocation1    = findViewById(R.id.vix_location1);
        voxLocation2    = findViewById(R.id.vix_location2);
        voxLocation3    = findViewById(R.id.vix_location3);
        voxPhone        = findViewById(R.id.vox_phone);
        voxOpening      = findViewById(R.id.vox_opening_hours);
        voxTypeOfCinema = findViewById(R.id.vox_type_of_cinema);
        m1Name          = findViewById(R.id.m1_name);
        m1Genre         = findViewById(R.id.m1_genre);
        m1RunningTime   = findViewById(R.id.m1_running_time);
        m1ReleaseDate   = findViewById(R.id.m1_release_date);
        m1Regular       = findViewById(R.id.m1_regular);
        m1Vip           = findViewById(R.id.m1_vip);
        m2Name          = findViewById(R.id.m2_name);
        m2Genre         = findViewById(R.id.m2_genre);
        m2RunningTime   = findViewById(R.id.m2_running_time);
        m2ReleaseDate   = findViewById(R.id.m2_release_date);
        m2Regular       = findViewById(R.id.m2_regular);
        m2Vip           = findViewById(R.id.m2_vip);
    }

    public void VoxCinemaOnClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_back_vox:
                startActivity(new Intent(getApplicationContext(), Cinema.class));
                break;
            case R.id.btn_transportation_vox:
                startActivity(new Intent(getApplicationContext() , Transportation.class));
                break;
            case R.id.btn_book1:
                startActivity(new Intent(getApplicationContext() , Booking.class));
                break;
            case R.id.btn_book2:
                startActivity(new Intent(getApplicationContext() , Booking.class));
        }
    }
}