package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Question2_3 extends AppCompatActivity {
    private Button button_back;
    private Button button_next;
    private Button button_skip;
    private Button button_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2_3);

        button_home = (Button) findViewById(R.id.btn_home1);           //button homepage
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openHomepage(); }
        });

        button_next = (Button) findViewById(R.id.btn_next);           //button next
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openQuestion4_5(); }
        });

        button_skip = (Button) findViewById(R.id.btn_skip);           //button skip
        button_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openQuestion4_5(); }
        });

        button_back=(Button) findViewById(R.id.btn_back);            //button back
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openQuestion1(); }

        });

    }

    public void openHomepage(){
        Intent intent = new Intent(this, Homepage.class);  //open homepage
        startActivity(intent);
    }

    public void  openQuestion4_5(){
        Intent intent = new Intent(this, Question4_5.class);  //open Question4_5
        startActivity(intent);
    }

    public void openQuestion1(){
        Intent intent = new Intent(this, Questionnaire1.class);  //open questionnaire1
        startActivity(intent);
    }
}