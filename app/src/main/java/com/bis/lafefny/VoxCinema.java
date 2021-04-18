package com.bis.lafefny;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class VoxCinema extends AppCompatActivity {
    TextView voxTitle,voxDescription,voxLocation1,voxLocation2,voxLocation3,voxPhone,voxOpening,voxTypeOfCinema;
    TextView m1Name , m1Genre , m1RunningTime , m1ReleaseDate , m1Regular , m1Vip;
    TextView m2Name , m2Genre , m2RunningTime , m2ReleaseDate , m2Regular , m2Vip;

    String movieName1,runningTime1,releaseDate1,phone,movieName2,runningTime2,releaseDate2;
    int vipPrice1,regularPrice1,vipPrice2,regularPrice2;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference cenimaDoRef = db.document("entertainment/categories/Cinema/VoxCinema");

    private SharedPreferences voxCinemaPref;

    @Override
    protected void onStart() {
        super.onStart();
        cenimaDoRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            voxOpening.setText(documentSnapshot.getString("opening"));
                            voxTypeOfCinema.setText(documentSnapshot.getString("cinemaType"));
                            voxTitle.setText(documentSnapshot.getString("name"));
                            voxDescription.setText(documentSnapshot.getString("description"));
                            voxLocation1.setText(documentSnapshot.getString("location1"));
                            voxLocation2.setText(documentSnapshot.getString("location2"));
                            voxLocation3.setText(documentSnapshot.getString("location3"));
                            m1Genre.setText(documentSnapshot.getString("m1genre"));
                            m2Genre.setText(documentSnapshot.getString("m2genre"));


                            phone      = documentSnapshot.getString("phone");
                            movieName1 = documentSnapshot.getString("m1name");
                            runningTime1 = documentSnapshot.getString("m1runningtime");
                            releaseDate1 = documentSnapshot.getString("m1resaledate");
                            regularPrice1 = Integer.parseInt(documentSnapshot.getString("m1regular"));
                            vipPrice1     = Integer.parseInt(documentSnapshot.getString("m1vip"));

                            movieName2 = documentSnapshot.getString("m2name");
                            runningTime2  =documentSnapshot.getString("m2runningtime");
                            releaseDate2 = documentSnapshot.getString("m2resaledate");
                            regularPrice2 = Integer.parseInt(documentSnapshot.getString("m2regular"));
                            vipPrice2 = Integer.parseInt(documentSnapshot.getString("m2vip"));

                            voxPhone.setText(phone.toString());
                            m1Name.setText(movieName1.toString());
                            m1RunningTime.setText(runningTime1.toString());
                            m1ReleaseDate.setText(releaseDate1.toString());
                            m1Regular.setText(String.valueOf(regularPrice1) + " LE");
                            m1Vip.setText(String.valueOf(vipPrice1) + " LE");

                            m2Name.setText(movieName2.toString());
                            m2RunningTime.setText(runningTime2.toString());
                            m2ReleaseDate.setText(releaseDate2.toString());
                            m2Regular.setText(String.valueOf(regularPrice2) + " LE");
                            m2Vip.setText(String.valueOf(vipPrice2) + " LE");

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

        voxCinemaPref = getSharedPreferences("vox_cinema_pref" , Context.MODE_PRIVATE);

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
                onBackPressed();
                break;
            case R.id.btn_transportation_vox:
                startActivity(new Intent(getApplicationContext() , Transportation.class));
                break;
            case R.id.btn_book1:
                Intent i = new Intent(getApplicationContext() , Booking.class);
                saveMovieDataToPref(movieName1,runningTime1,releaseDate1,vipPrice1,regularPrice1);
                i.putExtra("screen" , "voxcinema");
                startActivity(i);
                break;
            case R.id.btn_book2:
                Intent k = new Intent(getApplicationContext() , Booking.class);
                saveMovieDataToPref(movieName2 , runningTime2,releaseDate2,vipPrice2,regularPrice2);
                k.putExtra("screen" , "voxcinema");
                startActivity(k);
        }
    }

    public void saveMovieDataToPref(String movieName,String runningTime,String releaseDate,int vipPrice,int regularPrice){
        voxCinemaPref.edit().putString("location","Mall Of Egypt , City Center Almaza ,City Center Alexandria").apply();
        voxCinemaPref.edit().putString("contact" , "16002").apply();
        voxCinemaPref.edit().putString("source" , "Vox Cinema");


        voxCinemaPref.edit().putString("movieName" , movieName).apply();
        voxCinemaPref.edit().putString("runningTime" , runningTime).apply();
        voxCinemaPref.edit().putString("releaseDate" , releaseDate).apply();
        voxCinemaPref.edit().putInt("vipPrice" , vipPrice).apply();
        voxCinemaPref.edit().putInt("regularPrice" , regularPrice).apply();
        voxCinemaPref.edit().commit();
    }

    @Override
    public void onBackPressed() {
        SharedPreferences.Editor editor = voxCinemaPref.edit();
        editor.clear();
        editor.commit();
        super.onBackPressed();
    }
}