package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Question4_5 extends AppCompatActivity {
    private Button button_back_QS3;
    private Button button_next_QS3;
    private Button button_skip_QS3;
    private Button button_home_QS3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question4_5);

        button_home_QS3 = (Button) findViewById(R.id.btn_questionnaire3_home);           //button homepage
        button_home_QS3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openHomepage(); }
        });

        button_next_QS3 = (Button) findViewById(R.id.btn_questionnaire3_next);           //button next
        button_next_QS3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openQuestion6_7(); }
        });

        button_skip_QS3 = (Button) findViewById(R.id.btn_questionnaire3_skip);           //button skip
        button_skip_QS3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openQuestion6_7(); }
        });

        button_back_QS3=(Button) findViewById(R.id.btn_questionnaire3_back);            //button back
        button_back_QS3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openQuestion2_3(); }

        });

    }

    public void openHomepage(){
        Intent intent = new Intent(this, Homepage.class);  //open homepage
        startActivity(intent);
    }

    public void  openQuestion6_7(){
        Intent intent = new Intent(this, Question6_7.class);  //open Question6_7
        startActivity(intent);
    }

    public void openQuestion2_3(){
        Intent intent = new Intent(this, Question2_3.class);  //open Question2_3
        startActivity(intent);
    }
}