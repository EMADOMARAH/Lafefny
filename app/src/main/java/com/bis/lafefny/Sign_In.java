package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bis.lafefny.R;

public class Sign_In extends AppCompatActivity {
    private Button button; //back btn
    private Button button2; //signIn
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__in);

        Toast.makeText(getBaseContext(),"Start Signing in process...",Toast.LENGTH_LONG).show();

        button =  (Button) findViewById(R.id.btn_back); //back btn
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity2();
            }
        });

        button2 = (Button) findViewById(R.id.btn_signin2); //sign In
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomepage();
            }
        });
    }
    public void openMainActivity2() {   //back btn
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
    public void openHomepage(){       //sign In
        Intent intent = new Intent(this, Homepage.class);
        startActivity(intent);
    }

}