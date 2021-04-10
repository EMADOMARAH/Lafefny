package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bis.lafefny.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity2 extends AppCompatActivity {


    private Button button;
    private Button buttonn;

    SharedPreferences preferences;

    @Override
    protected void onStart() {
        super.onStart();
        preferences = getSharedPreferences("Lafefny_App" , Context.MODE_PRIVATE);
        if (preferences.contains("userId")){
            startActivity(new Intent(getApplicationContext() , Homepage.class));
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // Toast.makeText(getBaseContext(),"Welcome to Lafefny, hope you enjoy <3", Toast.LENGTH_LONG).show();

        setContentView(R.layout.activity_main2);

        button =  (Button) findViewById(R.id.btn_creatAccount);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSign_Up();
            }
        });

        buttonn = (Button) findViewById(R.id.btn_signin);
        buttonn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSign_In();
            }
        });
    }

    public void openSign_In() {
        Intent intent = new Intent(getApplicationContext(), Sign_In.class);
        startActivity(intent);
    }
    public void openSign_Up(){
        Intent intent = new Intent(getApplicationContext(), Sign_Up.class);
        startActivity(intent);
    }


}
