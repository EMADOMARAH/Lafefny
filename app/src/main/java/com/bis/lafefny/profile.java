package com.bis.lafefny;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.InputStream;




public class profile extends AppCompatActivity {
//    private Button button_profile_back;
    private Button button_profile_home;
    private Button button_profile_acc;
    private Button button_profile_pre;
    Button btn_edit_profile;


    TextView userName,fullName,birthdate,phone,email,nationality, gender;
    ImageView profileImage;
    SharedPreferences authPreferences ;
    FirebaseStorage storage;
    StorageReference storageReference;
    String userId , imageNameOnFireBase;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference userData ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        authPreferences = getSharedPreferences("Lafefny_App" , Context.MODE_PRIVATE);
        userId = authPreferences.getString("userId","");
        imageNameOnFireBase = authPreferences.getString("imgName","");

        userData = db.document("users/"+userId);
        storageReference = FirebaseStorage.getInstance().getReference();


        initProfileViews();

//        button_profile_back = (Button) findViewById(R.id.btn_back_profile); //button back
//        button_profile_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });

        button_profile_home = (Button) findViewById(R.id.btn_profile_home); //button home
        button_profile_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openhomepage();
            }
        });

        button_profile_acc = (Button) findViewById(R.id.btn_profile_user); //button Account
        button_profile_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAccount();
            }
        });

        button_profile_pre = (Button) findViewById(R.id.btn_profile_pre); //button preferences
        button_profile_pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openpreferences();
            }
        });

        btn_edit_profile = findViewById(R.id.btn_edit_profile);
        btn_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , EditProfile.class));
            }
        });



        GetScreenData();
        LoadImage();

    }

    private void LoadImage() {
        storageReference.child("usersImages/"+ imageNameOnFireBase).getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(profileImage);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(profile.this, "Can't Load Profile Image", Toast.LENGTH_SHORT).show();
                        Toast.makeText(profile.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void GetScreenData() {
        userData.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            userName.setText(documentSnapshot.getString("userName"));
                            fullName.setText(documentSnapshot.getString("firstName")+" "+documentSnapshot.getString("lastName"));
                            birthdate.setText(documentSnapshot.getString("dateOfBirth"));
                            phone.setText(documentSnapshot.getString("mobile"));
                            email.setText(documentSnapshot.getString("email"));
                            nationality.setText(documentSnapshot.getString("nationality"));
                            gender.setText(documentSnapshot.getString("gender"));
                        }else {
                            Toast.makeText(profile.this, "Can't Find this user data", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(profile.this, "Failed To Get Data", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initProfileViews() {
        userName= findViewById(R.id.txt_user_of_user);
        fullName= findViewById(R.id.txt_name_profile);
        birthdate= findViewById(R.id.txt_DOB);
        phone= findViewById(R.id.txt_mobile);
        email= findViewById(R.id.txt_user_email);
        nationality= findViewById(R.id.txt_user_nationality);
        gender= findViewById(R.id.txt_user_gender);
        profileImage= findViewById(R.id.profileimage);
    }

    public void openAccount(){
        Intent intent = new Intent(this,Account.class);  //open account
        startActivity(intent);
    }

    public void openhomepage(){
        Intent intent = new Intent(this,Homepage.class);  //open home page
        startActivity(intent);
    }

    public void openpreferences(){
        Intent intent = new Intent(this,preferences.class);  //open preferences
        startActivity(intent);
    }


}