package com.bis.lafefny;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.DateFormat;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.zip.DataFormatException;

public class Ticket extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference ticketDoRef;

    private String ticketId,cinemaName="";

    private SharedPreferences dreamParkPref;
    private SharedPreferences voxCinemaPref;
    private SharedPreferences funtopiaPref;
    private SharedPreferences egyptianPref;
    private SharedPreferences smokeryPref;
    private SharedPreferences runningPref;
    private SharedPreferences cairoBookPref;
    private SharedPreferences soundLightPref;
    private SharedPreferences siwaPref;



    private TextView ticketId_txt , placeName_txt , startDate_txt , startTime_txt , location_txt , regularCount_txt;
    private TextView vipCount_txt , totalPrice_txt , discount_txt;

    private Button btn_home;
    View view ;
    boolean doneClicked = false;
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        //---------------------------------------------------------------------------------------------
        this.dreamParkPref = this.getSharedPreferences("Dream_park_pref" , Context.MODE_PRIVATE);
        this.voxCinemaPref = this.getSharedPreferences("vox_cinema_pref" , Context.MODE_PRIVATE);
        this.funtopiaPref  = this.getSharedPreferences("funtopia_pref" , Context.MODE_PRIVATE);
        this.egyptianPref  =  getSharedPreferences("egyptian_museum_pref" , Context.MODE_PRIVATE);
        this.smokeryPref =  getSharedPreferences("the_smokery_pref" , Context.MODE_PRIVATE);
        //--------------------------------EVENTS---------------------------------------------------
        runningPref = getSharedPreferences("Running_Event_pref" , Context.MODE_PRIVATE);
        cairoBookPref = getSharedPreferences("Cairo_Book_Fair_pref" , Context.MODE_PRIVATE);
        soundLightPref = getSharedPreferences("Sound_Light_pref" , Context.MODE_PRIVATE);
        //------------------------------------------------------------------------------------------
        siwaPref = getSharedPreferences("siwa_pref" , Context.MODE_PRIVATE);


        verifyStoragePermission(this);
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
        view = findViewById(R.id.AllScreen);

    }
    public void GetTicketIdFromOntent(){
        Bundle extraDataFromPayment = getIntent().getExtras();
        if (extraDataFromPayment !=null){
            ticketId     = extraDataFromPayment.getString("ticketId" , "");
        }
    }

    private  File getScreen(View view , String fileName){
       Date date = new Date();
       CharSequence format = DateFormat.format("yyyy-mm-dd_hh:mm:ss" , date);

       try {
           String dirPath = Environment.getExternalStorageDirectory().toString() + "/LafefnyTickets";
           File fileDir = new File(dirPath);
           if (!fileDir.exists()){
               fileDir = new File(dirPath);
               fileDir.mkdir();
           }
           String path = dirPath+"/"+fileName+"-"+format+".jpeg";

           view.setDrawingCacheEnabled(true);
           Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
           view.setDrawingCacheEnabled(false);

           File imageFile = new File(path);

           FileOutputStream fileOutputStream = new FileOutputStream(imageFile);
           int quality =100;
           bitmap.compress(Bitmap.CompressFormat.JPEG , quality,fileOutputStream);
           fileOutputStream.flush();
           fileOutputStream.close();
           return imageFile;


       }catch (FileNotFoundException e){
           e.printStackTrace();
           Toast.makeText(this,"1"+ e.toString(), Toast.LENGTH_SHORT).show();
       } catch (IOException e) {
           e.printStackTrace();
           Toast.makeText(this,"2"+ e.toString(), Toast.LENGTH_SHORT).show();
       }
       return null;

    }
    private  final int REQUEST_EXTERNAL_STORAGE=1;
    private  String[] PERMISSION_STORAGE = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
    private void verifyStoragePermission(Activity activity){
        int permission = ActivityCompat.checkSelfPermission(activity,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity,
                    PERMISSION_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        }
    }


    public void TicketOnClick(View view) throws IOException {
        switch (view.getId()){
//            case R.id.btn_done:
//                getScreen();
//                break;
            case R.id.btn_transportation:
                Intent transIntent = new Intent(this, Transportation.class);  //open transportation
                transIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                if (doneClicked ==false){
                    getScreen(getWindow().getDecorView().getRootView(),"result");
                    Toast.makeText(this, "Screen shot saved", Toast.LENGTH_SHORT).show();
                }
                startActivity(transIntent);

                break;
            case R.id.btn_done:
                getScreen(getWindow().getDecorView().getRootView(),"result");
                doneClicked = true;
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (doneClicked ==false){
            getScreen(getWindow().getDecorView().getRootView(),"result");
            Toast.makeText(this, "Screen shot saved", Toast.LENGTH_SHORT).show();
        }
        deletePref(dreamParkPref);
        deletePref(voxCinemaPref);
        deletePref(funtopiaPref);
        deletePref(egyptianPref);
        deletePref(smokeryPref);
        deletePref(runningPref);
        deletePref(cairoBookPref);
        deletePref(soundLightPref);
        deletePref(siwaPref);
        Intent intent = new Intent(getApplicationContext(), Homepage.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void deletePref(SharedPreferences file){
        SharedPreferences.Editor editor = file.edit();
        editor.clear();
        editor.commit();
    }
    public void openHomepage(){
        Intent intent = new Intent(this, Homepage.class);  //open home page
        startActivity(intent);
    }
}