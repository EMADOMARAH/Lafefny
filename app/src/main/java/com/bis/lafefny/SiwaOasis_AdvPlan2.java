package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SiwaOasis_AdvPlan2 extends AppCompatActivity {
    private Button button_back_siwa2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siwa_oasis__adv_plan2);

        button_back_siwa2 = (Button) findViewById(R.id.btn_back_siwa_prog); //button back
        button_back_siwa2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSiwaOasis_AdvPlan();
            }
        });


    }
    public void openSiwaOasis_AdvPlan(){
        Intent intent = new Intent(this, SiwaOasis_AdvPlan.class);  //open back
        startActivity(intent);
    }

}