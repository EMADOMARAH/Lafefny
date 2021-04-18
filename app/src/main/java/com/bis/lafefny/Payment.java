package com.bis.lafefny;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class Payment extends AppCompatActivity {

    TextView source_txt , totalPrice_txt ,startTime_txt,startDate_txt ;

    EditText cardNumber_txt,expiredDate_txt,cvv_txt,cardHolderName_txt, promo_edit_txt;

    private String ticketId ,sourceScreen, promo;
    private String promoDiscountAmount = "0", promoLocation = "";

    private int vipPrice,vipCount ,regularPrice ,regularCount,totalPrice;

    boolean validPromo= false;
    boolean validPromoPlace = false;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Map<String, Object> ticketMap = new HashMap<>();

    private DocumentReference promoDoRef;

    SharedPreferences authPreferences ;
    private SharedPreferences dreamParkPref;
    private SharedPreferences voxCinemaPref;
    private SharedPreferences funtopiaPref;




    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_payment);

        this.authPreferences = this.getSharedPreferences("Lafefny_App" , Context.MODE_PRIVATE);
        this.dreamParkPref = this.getSharedPreferences("Dream_park_pref" , Context.MODE_PRIVATE);
        this.voxCinemaPref = this.getSharedPreferences("vox_cinema_pref" , Context.MODE_PRIVATE);
        this.funtopiaPref  = this.getSharedPreferences("funtopia_pref" , Context.MODE_PRIVATE);

        this.initPaymentViews();
        this.GetSourceScreen();


        switch (this.sourceScreen){
            case "dreampark":
                ticketId = dreamParkPref.getString("ticketId" , "123");
                this.source_txt.setText("Dream Park");
                this.startDate_txt.setText(this.dreamParkPref.getString("startDate" , "All Days"));
                this.startTime_txt.setText(this.dreamParkPref.getString("startTime" , "10:00 AM"));
                //---------------------Get Total Price Elements-----------------------------------------
                vipCount = this.dreamParkPref.getInt("vipCount" , 0);
                regularCount = this.dreamParkPref.getInt("regularCount" , 0);
                vipPrice = this.dreamParkPref.getInt("vipPrice" , 560);
                regularPrice = this.dreamParkPref.getInt("regularPrice",150);
                break;
            case "voxcinema":
                ticketId = voxCinemaPref.getString("ticketId" , "123");
                source_txt.setText("Vox Cinema");
                this.startDate_txt.setText("All Days");
                this.startTime_txt.setText( "12.00 PM : 3.00 AM");
                //---------------------Get Total Price Elements-----------------------------------------
                vipCount = this.voxCinemaPref.getInt("vipCount" , 0);
                regularCount = this.voxCinemaPref.getInt("regularCount" , 0);
                vipPrice = this.voxCinemaPref.getInt("vipPrice" , 560);
                regularPrice = this.voxCinemaPref.getInt("regularPrice",150);
                break;
            case "funtopia":
                ticketId = funtopiaPref.getString("ticketId" , "123");
                source_txt.setText("Fun Topia");
                this.startDate_txt.setText("All Days");
                this.startTime_txt.setText(funtopiaPref.getString("runningTime",""));
                //---------------------Get Total Price Elements-----------------------------------------
                vipCount = this.funtopiaPref.getInt("vipCount" , 0);
                regularCount = this.funtopiaPref.getInt("regularCount" , 0);
                vipPrice = this.funtopiaPref.getInt("vipPrice" , 300);
                regularPrice = this.funtopiaPref.getInt("regularPrice",65);
                break;

        }

        totalPrice = (vipPrice * vipCount) + (regularPrice * regularCount);

        totalPrice_txt.setText(totalPrice + " LE");

    }

    public void PaymentOnClick(final View view) {
        switch (view.getId()){
            case R.id.btn_cancel:
                final Intent cancelIntent = new Intent(this, Homepage.class);  //open home icon
                this.startActivity(cancelIntent);
                break;
            case R.id.btn_back_pay:
                final Intent backIntent = new Intent(this, Booking.class);  //open booking icon
                this.startActivity(backIntent);
                break;
            case R.id.btn_promo:
                final Intent promoIntent = new Intent(this, Promocode.class);  //open promo icon
                this.startActivity(promoIntent);
                break;

            case R.id.btn_pay_now:
                final Intent payIntent = new Intent(this, Ticket.class);//open ticket


                if (this.IsUserCreditCardExist()){

                    this.promo = this.promo_edit_txt.getText().toString();
                    if (!promo .isEmpty()){
                        this.CheckPromoCode(this.promo);
                        if (validPromoPlace){
                            payIntent.putExtra("ticketId" , ticketId);
                            this.SaveToDB();
                            this.startActivity(payIntent);
                        }
                    }else {
                        payIntent.putExtra("ticketId" , ticketId);

                        this.SaveToDB();
                        this.startActivity(payIntent);
                    }
                }
                break;
        }
    }

    public void CheckPromoCode(final String promoCode){
        //when promo comes it's already have text
        this.promoDoRef = this.db.document("promocodes/" + promoCode); //promocodes/newYear
        this.promoDoRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(final DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            Payment.this.promoDiscountAmount = documentSnapshot.getString("discount");
                            Payment.this.promoLocation = documentSnapshot.getString("place");
                            Payment.this.validPromo = true;

                        }else {
                            Payment.this.promo_edit_txt.setError("Enter Valid Promo");
                            Payment.this.promo_edit_txt.requestFocus();
                            validPromo = false;
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull final Exception e) {
                        Toast.makeText(Payment.this, "Error Ckicking Promo", Toast.LENGTH_SHORT).show();

                    }
                });

        if (validPromo){
            if (sourceScreen.toString().matches(promoLocation.toString())){
                validPromoPlace =true;
                this.totalPrice = (this.vipPrice * this.vipCount) + (this.regularPrice * this.regularCount) - Integer.parseInt(this.promoDiscountAmount);
            }else{
                validPromoPlace = false;
                Toast.makeText(this, "unvalid promo for ticket", Toast.LENGTH_SHORT).show();
                this.totalPrice = (this.vipPrice * this.vipCount) + (this.regularPrice * this.regularCount) ;
            }
        }

    }

    public void SaveToDB(){

        this.MakeTicketDataIntoMap();
        this.db.collection("Tickets")
                .document(this.ticketId)
                .set(this.ticketMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(final Void aVoid) {
                        Toast.makeText(Payment.this, "Ticket Addded", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull final Exception e) {
                        Toast.makeText(Payment.this, e.toString(), Toast.LENGTH_SHORT).show();

                    }
                });
    }
    public void SaveUserPromoInfoToPrefrences(){
        switch (sourceScreen){
            case "dreampark":
                dreamParkPref.edit().putString("discount" , promoDiscountAmount).apply();
                dreamParkPref.edit().putString("discountLocation" , promoLocation).apply();
                dreamParkPref.edit().commit();
                break;
            case "voxcinema":
                voxCinemaPref.edit().putString("discount" , promoDiscountAmount).apply();
                voxCinemaPref.edit().putString("discountLocation" , promoLocation).apply();
                voxCinemaPref.edit().commit();
                break;
            case "funtopia":
                funtopiaPref.edit().putString("discount" , promoDiscountAmount).apply();
                funtopiaPref.edit().putString("discountLocation" , promoLocation).apply();
                funtopiaPref.edit().commit();
                break;

        }
    }

    public void MakeTicketDataIntoMap(){
        final String userId = this.authPreferences.getString("userId" , "");
        this.ticketMap.put("userId" , userId);
        switch (this.sourceScreen){
            case "dreampark" :
                this.ticketMap.put("ticketId" , this.dreamParkPref.getString("ticketId", ""));
                this.ticketMap.put("place" , this.dreamParkPref.getString("source" , ""));
                this.ticketMap.put("regularTicketPrice" , Integer.toString(this.dreamParkPref.getInt("regularPrice" , 150)));
                this.ticketMap.put("regularTicketCount" , Integer.toString(this.dreamParkPref.getInt("regularCount" , 1)));
                this.ticketMap.put("vipTicketPrice" , Integer.toString(this.dreamParkPref.getInt("vipPrice" , 560)));
                this.ticketMap.put("vipTicketCount" , Integer.toString(this.dreamParkPref.getInt("vipCount" , 1)));
                this.ticketMap.put("startDate" , this.dreamParkPref.getString("startDate" , "All Days"));
                this.ticketMap.put("startTime" , this.dreamParkPref.getString("startTime" , "10:00 AM"));
                this.ticketMap.put("totalPrice" , Integer.toString(this.totalPrice));
                this.ticketMap.put("location" , "El Wahat Road, Giza Governorate");
                this.ticketMap.put("discount" , this.promoDiscountAmount);
                break;
            case "voxcinema":
                ticketMap.put("ticketId" , this.voxCinemaPref.getString("ticketId", ""));
                ticketMap.put("place" , voxCinemaPref.getString("source" , "Vox Cinema"));
                ticketMap.put("movieName" , voxCinemaPref.getString("movieName" , ""));
                this.ticketMap.put("regularTicketPrice" , Integer.toString(this.voxCinemaPref.getInt("regularPrice" , 80)));
                this.ticketMap.put("regularTicketCount" , Integer.toString(this.voxCinemaPref.getInt("regularCount" , 1)));
                this.ticketMap.put("vipTicketPrice" , Integer.toString(this.voxCinemaPref.getInt("vipPrice" , 110)));
                this.ticketMap.put("vipTicketCount" , Integer.toString(this.voxCinemaPref.getInt("vipCount" , 1)));
                this.ticketMap.put("startDate" , this.voxCinemaPref.getString("releaseDate" , ""));
                this.ticketMap.put("startTime" , this.voxCinemaPref.getString("runningTime" , ""));
                this.ticketMap.put("totalPrice" , Integer.toString(this.totalPrice));
                this.ticketMap.put("location" , voxCinemaPref.getString("location" , ""));
                this.ticketMap.put("discount" , this.promoDiscountAmount);
                break;
            case "funtopia":
                ticketMap.put("ticketId" , this.funtopiaPref.getString("ticketId", ""));
                ticketMap.put("place" , funtopiaPref.getString("source" , "Fun Topia"));
                ticketMap.put("movieName" , voxCinemaPref.getString("movieName" , ""));
                this.ticketMap.put("regularTicketPrice" , Integer.toString(this.funtopiaPref.getInt("regularPrice" , 65)));
                this.ticketMap.put("regularTicketCount" , Integer.toString(this.funtopiaPref.getInt("regularCount" , 1)));
                this.ticketMap.put("vipTicketPrice" , Integer.toString(this.funtopiaPref.getInt("vipPrice" , 300)));
                this.ticketMap.put("vipTicketCount" , Integer.toString(this.funtopiaPref.getInt("vipCount" , 1)));
                this.ticketMap.put("startDate" , "All Days");
                this.ticketMap.put("startTime" , this.funtopiaPref.getString("runningTime" , ""));
                this.ticketMap.put("totalPrice" , Integer.toString(this.totalPrice));
                this.ticketMap.put("location" , funtopiaPref.getString("location" , ""));
                this.ticketMap.put("discount" , this.promoDiscountAmount);
                break;

        }
        SaveUserPromoInfoToPrefrences();

    }

    public boolean IsUserCreditCardExist(){
        final String cardNumber = this.cardNumber_txt.getText().toString();
        final String expiredDate = this.expiredDate_txt.getText().toString();
        final String cvv = this.cvv_txt.getText().toString();
        final String cardHolderName = this.cardHolderName_txt.getText().toString();

        if (cardNumber.isEmpty()){
            this.cardNumber_txt.setError("Enter Valid Card Number");
            this.cardNumber_txt.requestFocus();
        }else if(expiredDate.isEmpty()){
            this.expiredDate_txt.requestFocus();
            this.expiredDate_txt.setError("Enter Date");
        }else if (cvv.isEmpty()){
            this.cvv_txt.setError("Enter CVV");
            this.cvv_txt.requestFocus();
        }else if (cardHolderName.isEmpty()){
            this.cardHolderName_txt.requestFocus();
            this.cardHolderName_txt.setError("Enter Name");
        }else {
            return true;
        }
        return false;
    }
    private void GetSourceScreen(){
        final Bundle extraDataFromBooking = this.getIntent().getExtras();
        if (extraDataFromBooking !=null){
            this.sourceScreen = extraDataFromBooking.getString("screen");

        }
    }
    private void initPaymentViews() {
        this.source_txt = this.findViewById(R.id.txt_name_pay);
        this.totalPrice_txt = this.findViewById(R.id.txt_total);
        this.startTime_txt = this.findViewById(R.id.txt_time);
        this.startDate_txt = this.findViewById(R.id.txt_date);
        //-------------------------------------------
        this.cardNumber_txt = this.findViewById(R.id.pt_card_number);
        this.expiredDate_txt = this.findViewById(R.id.pt_card_expiration_date);
        this.cvv_txt = this.findViewById(R.id.pt_card_security);
        this.cardHolderName_txt = this.findViewById(R.id.pt_card_holder_name);
        this.promo_edit_txt = this.findViewById(R.id.pt_promo);
    }


}
