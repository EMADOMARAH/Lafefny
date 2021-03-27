package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Question12_13 extends AppCompatActivity {
    private Button button_back_QS7;
    private Button button_next_QS7;
    private Button button_skip_QS7;
    private Button button_home_QS7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question12_13);

        button_home_QS7 = (Button) findViewById(R.id.btn_questionnaire7_home);           //button homepage
        button_home_QS7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openHomepage(); }
        });

        button_back_QS7=(Button) findViewById(R.id.btn_questionnaire7_back);            //button back
        button_back_QS7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openQuestion10_11(); }
        });

        button_next_QS7 = (Button) findViewById(R.id.btn_questionnaire7_next);           //button next
        button_next_QS7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openQuestion14_15(); }
        });

        button_skip_QS7 = (Button) findViewById(R.id.btn_questionnaire7_skip);           //button skip
        button_skip_QS7.setOnClickListener(new View.OnClickListener() {
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