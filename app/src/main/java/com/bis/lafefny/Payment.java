package com.bis.lafefny;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Payment extends AppCompatActivity {

    TextView source_txt , totalPrice_txt ,startTime_txt,startDate_txt , promo_txt;
    EditText cardNumber_txt,expiredDate_txt,cvv_txt,cardHolderName_txt;
    private String ticketId ,comment , source , startTime,startDate , promo ;
    private int vipPrice;
    private int vipCount;
    private int regularPrice;
    private int regularCount;
    private int totalPrice;
    private String promoDiscount_txt = "0",promoPlace_txt = "";

    String dbTicketId;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Map<String, Object> ticketMap = new HashMap<>();

    private DocumentReference promoDoRef;

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
        //-------------------------------------------
        cardNumber_txt = findViewById(R.id.pt_card_number);
        expiredDate_txt = findViewById(R.id.pt_card_expiration_date);
        cvv_txt = findViewById(R.id.pt_card_security);
        cardHolderName_txt = findViewById(R.id.pt_card_holder_name);
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
                if (promo_txt.getText().toString() != null ){
                    promo = promo_txt.getText().toString();
                    CheckPromoCode(promo);
                }

                if (source ==promoPlace_txt.toString()){
                    totalPrice = (vipPrice*vipCount) + (regularPrice*regularCount) - Integer.parseInt(promoDiscount_txt);
                    Toast.makeText(this, "Valid Promo", Toast.LENGTH_SHORT).show();
                }else{
                    totalPrice = (vipPrice*vipCount) + (regularPrice*regularCount);
                }
                payIntent.putExtra("DatabaseTicketId" , dbTicketId);
                payIntent.putExtra("ticketId" , ticketId);
                if (IsUserCreditCardExist()){
                    SaveToDB();
                    startActivity(payIntent);
                }

                break;
        }
    }

    public void CheckPromoCode(String promoCode){
        promoDoRef =db.document("promocodes/" + promoCode);
        promoDoRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            promoDiscount_txt = documentSnapshot.getString("discount");
                            promoPlace_txt = documentSnapshot.getString("place");

                            Toast.makeText(Payment.this, "vaild Promo", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(Payment.this, "Unvalid Promocode", Toast.LENGTH_SHORT).show();
                            promoDiscount_txt = "0";
                            promoPlace_txt = "";
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Payment.this, "Error Ckicking Promo", Toast.LENGTH_SHORT).show();
                    }
                });



//        if (promoCode.matches("Lafefny66")){
//            discount_txt = 30;
//            Toast.makeText(this, "30 LE Discount", Toast.LENGTH_SHORT).show();
//        }else if (promoCode.matches("Lafefny98")){
//            discount_txt = 40;
//            Toast.makeText(this, "40 LE Discount", Toast.LENGTH_SHORT).show();
//        }else if (){
//
//        } else {
//            discount_txt = 0;
//            Toast.makeText(this, "Unvalid Promocode", Toast.LENGTH_SHORT).show();
//        }
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
        ticketMap.put("discount" ,promoDiscount_txt);
        ticketMap.put("totalPrice" , Integer.toString(totalPrice));
    }

    public boolean IsUserCreditCardExist(){
        String cardNumber = cardNumber_txt.getText().toString();
        String expiredDate = expiredDate_txt.getText().toString();
        String cvv = cvv_txt.getText().toString();
        String cardHolderName = cardHolderName_txt.getText().toString();

        if (cardNumber.isEmpty()){
            cardNumber_txt.setError("Enter Valid Card Number");
            cardNumber_txt.requestFocus();
        }else if(expiredDate.isEmpty()){
            expiredDate_txt.requestFocus();
            expiredDate_txt.setError("Enter Date");
        }else if (cvv.isEmpty()){
            cvv_txt.setError("Enter CVV");
            cvv_txt.requestFocus();
        }else if (cardHolderName.isEmpty()){
            cardHolderName_txt.requestFocus();
            cardHolderName_txt.setError("Enter Name");
        }else {
            return true;
        }
        return false;
    }


}
