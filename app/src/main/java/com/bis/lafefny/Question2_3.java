package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Question2_3 extends AppCompatActivity {
    private Button button_back_ques2;
    private Button button_next_ques2;
//    private Button button_skip_ques2;
    private Button button_home_ques2;
    private Button button_pre_ques2;
    private Button button_account_ques2;

    RadioGroup Q2 , Q3;
    RadioButton answer2,answer3;
    private SharedPreferences Qanswers;
    String answer2String,answer3String;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2_3);

        Qanswers = getSharedPreferences("QAnswer" , Context.MODE_PRIVATE);
        Q2 = (RadioGroup) findViewById(R.id.Ques2_group);
        Q3 = (RadioGroup) findViewById(R.id.Ques3_group);

        button_home_ques2 = (Button) findViewById(R.id.btn_questionnaire2_home);           //button homepage
        button_home_ques2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openHomepage(); }
        });

        button_next_ques2 = (Button) findViewById(R.id.btn_questionnaire2_next);           //button next
        button_next_ques2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getChoises()){
                    saveAnswerToPref();
                }

            }
        });


        button_back_ques2=(Button) findViewById(R.id.btn_questionnaire2_back);            //button back
        button_back_ques2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openQuestion1(); }

        });

        button_account_ques2=(Button) findViewById(R.id.btn_questionnaire2_user);            //button user account
        button_account_ques2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openAccount12(); }

        });

        button_pre_ques2=(Button) findViewById(R.id.btn_questionnaire2_pre);            //button preferences
        button_pre_ques2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openpreferences(); }

        });

    }


    private void saveAnswerToPref() {
        Qanswers.edit().putString("A2" , answer2String).apply();
        Qanswers.edit().putString("A3" , answer3String).apply();
        Qanswers.edit().commit();

        openQuestion4_5();
    }

    private boolean getChoises() {
        int answe2Id , answe3Id;
        try {
            answe2Id = Q2.getCheckedRadioButtonId();
            answe3Id = Q3.getCheckedRadioButtonId();
            answer2 = findViewById(answe2Id);
            answer3 = findViewById(answe3Id);
            //the user choises in Preferences Screen
            answer2String = answer2.getText().toString();
            answer3String = answer3.getText().toString();

        }catch (Exception e){
            Toast.makeText(this, "Answer The Questions", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void openHomepage(){
        Intent intent = new Intent(this, Homepage.class);  //open homepage
        startActivity(intent);
    }

    public void openpreferences(){
        Intent intent = new Intent(this, preferences.class);  //open preferences
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

    public void openAccount12(){
        Intent intent = new Intent(this, Account.class);  //open account
        startActivity(intent);
    }
}