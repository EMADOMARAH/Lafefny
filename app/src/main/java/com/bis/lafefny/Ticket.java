package com.bis.lafefny;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class Ticket extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference ticketDoRef;

    private String ticketId,cinemaName="";

    private SharedPreferences dreamParkPref;
    private SharedPreferences voxCinemaPref;
    private SharedPreferences funtopiaPref;


    private TextView ticketId_txt , placeName_txt , startDate_txt , startTime_txt , location_txt , regularCount_txt;
    private TextView vipCount_txt , totalPrice_txt , discount_txt;
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        this.dreamParkPref = this.getSharedPreferences("Dream_park_pref" , Context.MODE_PRIVATE);
        this.voxCinemaPref = this.getSharedPreferences("vox_cinema_pref" , Context.MODE_PRIVATE);
        this.funtopiaPref  = this.getSharedPreferences("funtopia_pref" , Context.MODE_PRIVATE);

        GetTicketIdFromOntent();
        InitTicketViews();

        ticketDoRef= db.document("Tickets/" + ticketId);
        ticketDoRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){

                            String place = documentSnapshot.getString("place");
//                            String movieNAme ="" ;
//                            movieNAme = documentSnapshot.getString("movieName") ;
//                            cinemaName = documentSnapshot.getString("name");
                            ticketId_txt.setText(documentSnapshot.getString("ticketId"));
//                            if (cinemaName=="Vox Cinema" ){
//                                placeName_txt.setText(place +" ( "+movieNAme+ " ) ");
//                            }else {
//
//                            }
                            placeName_txt.setText(place);
                            startDate_txt.setText(documentSnapshot.getString("startDate"));
                            startTime_txt.setText(documentSnapshot.getString("startTime"));
                            location_txt.setText(documentSnapshot.getString("location"));
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

       //myScreen = findViewById(R.id.AllScreen);

    }
    public void GetTicketIdFromOntent(){
        Bundle extraDataFromPayment = getIntent().getExtras();
        if (extraDataFromPayment !=null){
            ticketId     = extraDataFromPayment.getString("ticketId" , "");
        }
    }

//    public void screenShot() throws IOException {
//        Bitmap bitmap;
//        View v1 = findViewById(R.id.AllScreen);// get ur root view id
//        v1.setDrawingCacheEnabled(true);
//        bitmap = Bitmap.createBitmap(v1.getDrawingCache());
//        v1.setDrawingCacheEnabled(false);
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 40, bytes);
//        File f = new File(Environment.getExternalStorageDirectory()
//                + File.separator + "test.jpg");
//        f.createNewFile();
//        FileOutputStream fo = new FileOutputStream(f);
//        fo.write(bytes.toByteArray());
//        fo.close();
//    }


    public void TicketOnClick(View view) throws IOException {
        switch (view.getId()){
//            case R.id.btn_done:
//                Intent doneIntent = new Intent(this, Homepage.class);  //open homepage
//                startActivity(doneIntent);
//                break;
            case R.id.btn_transportation:
                Intent transIntent = new Intent(this, Transportation.class);  //open transportation
                transIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(transIntent);
                break;
//            case R.id.btn_done:
//                try {
//                    screenShot();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                break;
        }
    }

    @Override
    public void onBackPressed() {
        deletePref(dreamParkPref);
        deletePref(voxCinemaPref);
        deletePref(funtopiaPref);
        Intent intent = new Intent(getApplicationContext(), Homepage.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void deletePref(SharedPreferences file){
        SharedPreferences.Editor editor = file.edit();
        editor.clear();
        editor.commit();
    }
}