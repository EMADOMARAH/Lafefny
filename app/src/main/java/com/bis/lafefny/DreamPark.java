
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

import com.bis.lafefny.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class DreamPark extends AppCompatActivity {

    private TextView title,description,startTime,endTime,fridayStart,fridayEnd,regTicket,vipTicket,paidRideMin,paidRideMax,notes,extraInfo;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference parkDoRef = db.document("entertainment/categories/parks/DreamPark");

    private SharedPreferences dreamParkPref;

    @Override
    protected void onStart() {
        super.onStart();
        parkDoRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists())
                        {
                            title.setText(documentSnapshot.getString("name"));
                            description.setText(documentSnapshot.getString("description"));
                            startTime.setText(documentSnapshot.getString("normalStart"));
                            endTime.setText(documentSnapshot.getString("normalEnd"));
                            fridayStart.setText(documentSnapshot.getString("friStart"));
                            fridayEnd.setText(documentSnapshot.getString("friEnd"));
                            regTicket.setText(documentSnapshot.getString("regTickets"));
                            vipTicket.setText(documentSnapshot.getString("vipTicket"));
                            paidRideMin.setText(documentSnapshot.getString("paidRideMin"));
                            paidRideMax.setText(documentSnapshot.getString("paidRideMax"));
                            notes.setText(documentSnapshot.getString("notes"));
                            extraInfo.setText(documentSnapshot.getString("extraInfo"));

                        }else {
                            Toast.makeText(DreamPark.this, "DataBase is Empty", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(DreamPark.this, "Faild To Get Data", Toast.LENGTH_SHORT).show();

                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dream_park);

        dreamParkPref = getSharedPreferences("Dream_park_pref" , Context.MODE_PRIVATE);

        initViews();

    }

    private void initViews() {
        title       = findViewById(R.id.dreamParkName);
        description = findViewById(R.id.dreamParkDescription);
        startTime   = findViewById(R.id.dreamParkStartTime);
        endTime     = findViewById(R.id.dreamParkEndTime);
        fridayStart = findViewById(R.id.dreamParkFridayStart);
        fridayEnd   = findViewById(R.id.dreamParkFridayEnd);
        regTicket   = findViewById(R.id.dreamParkRegTicket);
        vipTicket   = findViewById(R.id.dreamParkVipticket);
        paidRideMin = findViewById(R.id.dreamParkMinPaid);
        paidRideMax = findViewById(R.id.dreamParkMaxPaid);
        notes       = findViewById(R.id.dreamParkNotes);
        extraInfo   = findViewById(R.id.extraInfo);
    }


    public void DreamParkOnClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_back_dreamP:
                startActivity(new Intent(getApplicationContext() , AmusementPark.class));
                break;
            case R.id.btn_transportation_dreamP:
                startActivity(new Intent(getApplicationContext() , Transportation.class));
                break;
            case R.id.btn_ticket_dreamP:
                Intent intent = new Intent(getApplicationContext() , Booking.class);
                dreamParkPref.edit().putInt("vipPrice",560).apply();
                dreamParkPref.edit().putInt("regularPrice",150).apply();
                dreamParkPref.edit().putString("source","Dream Park").apply();
                dreamParkPref.edit().putString("startTime" , "10:00 AM").apply();
                dreamParkPref.edit().putString("startDate" , "All Days").apply();
                dreamParkPref.edit().commit();

                intent.putExtra("screen" , "dreampark");
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        SharedPreferences.Editor editor = dreamParkPref.edit();
        editor.clear();
        editor.commit();
        super.onBackPressed();
    }
}