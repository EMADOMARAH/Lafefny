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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Payment extends AppCompatActivity {

    TextView source_txt , totalPrice_txt ,startTime_txt,startDate_txt , promo_txt;
    private String ticketId ,comment , source , startTime,startDate , promo ;
    private int vipPrice,vipCount,regularPrice,regularCount , totalPrice, promoResult = 0;

    String dbTicketId;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Map<String, Object> ticketMap = new HashMap<>();

    SharedPreferences authPreferences ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        authPreferences = getSharedPreferences("Lafefny_App" , Context.MODE_PRIVATE);

        initPaymentViews();
        GetDataFromIntent();
        totalPrice = (vipPrice*vipCount) + (regularPrice*regularCount);
        source_txt.setText(source);

        totalPrice_txt.setText(Integer.toString(totalPrice) + " LE");
        startDate_txt.setText(startDate);
        startTime_txt.setText(startTime);

    }

    private void initPaymentViews() {
        source_txt= findViewById(R.id.txt_name);
        totalPrice_txt = findViewById(R.id.txt_total);
        startTime_txt = findViewById(R.id.txt_time);
        startDate_txt = findViewById(R.id.txt_date);
        promo_txt = findViewById(R.id.pt_promo);
    }
    private void GetDataFromIntent(){
        Bundle extraDataFromBooking = getIntent().getExtras();
        if (extraDataFromBooking !=null){
            ticketId     = extraDataFromBooking.getString("ticketID");
            vipPrice     = extraDataFromBooking.getInt("vipPrice");
            vipCount     = extraDataFromBooking.getInt("vipCount");
            regularPrice = extraDataFromBooking.getInt("regularPrice");
            regularCount = extraDataFromBooking.getInt("regularCount");
            //comment      = extraDataFromBooking.getString("comment");
            source       = extraDataFromBooking.getString("source");
            startTime    = extraDataFromBooking.getString("startTime");
            startDate    = extraDataFromBooking.getString("startDate");
        }
    }

    public void PaymentOnClick(View view) {
        switch (view.getId()){
            case R.id.btn_cancel:
                Intent cancelIntent = new Intent(this, Homepage.class);  //open home icon
                startActivity(cancelIntent);
                break;
            case R.id.btn_back_pay:
                Intent backIntent = new Intent(this, Booking.class);  //open booking icon
                startActivity(backIntent);
                break;
            case R.id.btn_promo:
                Intent promoIntent = new Intent(this, Promocode.class);  //open promo icon
                startActivity(promoIntent);
                break;
            case R.id.btn_pay_now:
                Intent payIntent = new Intent(this, Ticket.class);//open ticket
                if (promo_txt.getText().toString() != null){
                    promo = promo_txt.getText().toString();
                    CheckPromoCode(promo);
                }
                totalPrice = (vipPrice*vipCount) + (regularPrice*regularCount) - promoResult;
                payIntent.putExtra("DatabaseTicketId" , dbTicketId);
                payIntent.putExtra("ticketId" , ticketId);

                SaveToDB();
                startActivity(payIntent);
                break;
        }

    }

    public void CheckPromoCode(String promoCode){
        if (promoCode.matches("Lafefny66")){
            promoResult = 30;
            Toast.makeText(this, "30 LE Discount", Toast.LENGTH_SHORT).show();
        }else if (promoCode.matches("Lafefny98")){
            promoResult = 40;
            Toast.makeText(this, "40 LE Discount", Toast.LENGTH_SHORT).show();
        }else {
            promoResult = 0;
            Toast.makeText(this, "Unvalid Promocode", Toast.LENGTH_SHORT).show();
        }
    }

    public void SaveToDB(){

        MakeTicketDataIntoMap();
        db.collection("Tickets")
                .document(ticketId)
                .set(ticketMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Payment.this, "Ticket Addded", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Payment.this, e.toString(), Toast.LENGTH_SHORT).show();

                    }
                });
    }

    public void MakeTicketDataIntoMap(){
        ticketMap.put("ticketId" , ticketId);
        String userId = authPreferences.getString("userId" , "");
        ticketMap.put("userId" , userId);
        ticketMap.put("place" , source);
        ticketMap.put("regularTicketPrice" , Integer.toString(regularPrice));
        ticketMap.put("regularTicketCount" , Integer.toString(regularCount));
        ticketMap.put("vipTicketPrice" , Integer.toString(vipPrice));
        ticketMap.put("vipTicketCount" , Integer.toString(vipCount));
       // ticketMap.put("comment" ,comment );
        ticketMap.put("startDate" , startDate);
        ticketMap.put("startTime" , startTime);
        ticketMap.put("discount" , Integer.toString(promoResult));
        ticketMap.put("totalPrice" , Integer.toString(totalPrice));
    }


}
