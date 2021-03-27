package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Emergency extends AppCompatActivity {
    private Button button_Emg_homepage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        button_Emg_homepage = (Button) findViewById(R.id.btn_emg_home);  //button home icon
        button_Emg_homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomepage();
            }
        });
    }
    public void openHomepage(){
        Intent intent = new Intent(this, Homepage.class);  //open home page
        startActivity(intent);
    }
}