package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bis.lafefny.R;

public class Homepage extends AppCompatActivity {

    private Button button_ent;
    private Button button_event;
    private Button button_plan;
    private Button button_emg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Toast.makeText(getBaseContext(),"Process Success..",Toast.LENGTH_LONG);

        button_ent = (Button) findViewById(R.id.btn_entertainment); //button amusement park
        button_ent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEnt_Categories();
            }
        });


    }
    public void openEnt_Categories(){
        Intent intent = new Intent(this, Ent_Categories.class); //open entertainment categories
        startActivity(intent);
    }


}