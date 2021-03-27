package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Question12_13 extends AppCompatActivity {
    private Button button_back;
    private Button button_next;
    private Button button_skip;
    private Button button_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question12_13);

        button_home = (Button) findViewById(R.id.btn_home1);           //button homepage
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openHomepage(); }
        });

        button_back=(Button) findViewById(R.id.btn_back);            //button back
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openQuestion10_11(); }
        });

        button_next = (Button) findViewById(R.id.btn_next);           //button next
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openQuestion14_15(); }
        });

        button_skip = (Button) findViewById(R.id.btn_skip);           //button skip
        button_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openQuestion14_15(); }
        });
    }
    public void openHomepage(){
        Intent intent = new Intent(this, Homepage.class);  //open homepage
        startActivity(intent);
    }
    public void  openQuestion10_11(){
        Intent intent = new Intent(this, Question10_11.class);  //open Question10_11
        startActivity(intent);
    }

    public void openQuestion14_15(){
        Intent intent = new Intent(this, Question14_15.class);  //open Question14_15
        startActivity(intent);
    }
}