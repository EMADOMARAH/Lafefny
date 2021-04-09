package com.bis.lafefny;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Ticket extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference ticketDoRef;

    private String ticketId;

    private TextView ticketId_txt , placeName_txt , startDate_txt , startTime_txt , location_txt , regularCount_txt;
    private TextView vipCount_txt , totalPrice_txt , discount_txt;
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        GetTicketIdFromOntent();
        InitTicketViews();

        ticketDoRef= db.document("Tickets/" + ticketId);
        ticketDoRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            ticketId_txt.setText(documentSnapshot.getString("ticketId"));
                            placeName_txt.setText(documentSnapshot.getString("place"));
                            startDate_txt.setText(documentSnapshot.getString("startDate"));
                            startTime_txt.setText(documentSnapshot.getString("startTime"));
                            location_txt.setText(documentSnapshot.getString("place"));
                            regularCount_txt.setText(documentSnapshot.getString("regularTicketCount"));
                            vipCount_txt.setText(documentSnapshot.getString("vipTicketCount"));
                            totalPrice_txt.setText(documentSnapshot.getString("totalPrice"));
                            discount_txt.setText(documentSnapshot.getString("discount"));
                        }else {
                            Toast.makeText(Ticket.this, "Data Not Exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Ticket.this, "Faild To Get Data", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public void InitTicketViews(){
        ticketId_txt = findViewById(R.id.txt_ticket_id);
        placeName_txt = findViewById(R.id.txt_ticket_name);
        startDate_txt = findViewById(R.id.txt_ticket_date);
        startTime_txt = findViewById(R.id.txt_ticket_time);
        location_txt = findViewById(R.id.txt_ticket_location_details);
        regularCount_txt = findViewById(R.id.integer_ticket_number_regular);
        vipCount_txt = findViewById(R.id.integer_ticket_number_vip);
        totalPrice_txt = findViewById(R.id.integer_ticket_total_payment);
        discount_txt = findViewById(R.id.integer_ticket_promotion);

    }
    public void GetTicketIdFromOntent(){
        Bundle extraDataFromPayment = getIntent().getExtras();
        if (extraDataFromPayment !=null){
            ticketId     = extraDataFromPayment.getString("ticketId");
        }
    }


    public void TicketOnClick(View view) {
        switch (view.getId()){
            case R.id.btn_done:
                Intent doneIntent = new Intent(this, Homepage.class);  //open homepage
                startActivity(doneIntent);
                break;
            case R.id.btn_transportation:
                Intent transIntent = new Intent(this, Transportation.class);  //open transportation
                startActivity(transIntent);
                break;
        }
    }
}