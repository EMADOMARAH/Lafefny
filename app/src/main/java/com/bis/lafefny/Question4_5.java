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

public class Question4_5 extends AppCompatActivity {
    private Button button_back_QS3;
    private Button button_next_QS3;
//    private Button button_skip_QS3;
    private Button button_home_QS3;
    private Button button_pre_QS3;
    private Button button_account_QS3;

    RadioGroup Q4 , Q5;
    RadioButton answer4,answer5;
    private SharedPreferences Qanswers;
    String answer4String,answer5String;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question4_5);

        Qanswers = getSharedPreferences("QAnswer" , Context.MODE_PRIVATE);
        Q4 = (RadioGroup) findViewById(R.id.Ques4_group);
        Q5 = (RadioGroup) findViewById(R.id.Ques5_group);

        button_home_QS3 = (Button) findViewById(R.id.btn_questionnaire3_home);           //button homepage
        button_home_QS3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openHomepage(); }
        });

        button_pre_QS3 = (Button) findViewById(R.id.btn_questionnaire3_pre);           //button preferences
        button_pre_QS3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openpreferences(); }
        });

        button_next_QS3 = (Button) findViewById(R.id.btn_questionnaire3_next);           //button next
        button_next_QS3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getChoises()){
                    saveAnswerToPref();
                }
            }
        });

//        button_skip_QS3 = (Button) findViewById(R.id.btn_questionnaire3_skip);           //button skip
//        button_skip_QS3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) { openQuestion6_7(); }
//        });

        button_back_QS3=(Button) findViewById(R.id.btn_questionnaire3_back);            //button back
        button_back_QS3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openQuestion2_3(); }

        });

        button_account_QS3=(Button) findViewById(R.id.btn_questionnaire3_user);            //button user account
        button_account_QS3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openAccount13(); }

        });

    }

    private void saveAnswerToPref() {
        Qanswers.edit().putString("A4" , answer4String).apply();
        Qanswers.edit().putString("A5" , answer5String).apply();
        Qanswers.edit().commit();

        openQuestion6_7();
    }

    private boolean getChoises() {
        int answe4Id , answe5Id;
        try {
            answe4Id = Q4.getCheckedRadioButtonId();
            answe5Id = Q5.getCheckedRadioButtonId();
            answer4 = findViewById(answe4Id);
            answer5 = findViewById(answe5Id);
            //the user choises in Preferences Screen
            answer4String = answer4.getText().toString();
            answer5String = answer5.getText().toString();

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

    public void  openQuestion6_7(){
        Intent intent = new Intent(this, Question6_7.class);  //open Question6_7
        startActivity(intent);
    }

    public void openQuestion2_3(){
        Intent intent = new Intent(this, Question2_3.class);  //open Question2_3
        startActivity(intent);
    }

    public void openAccount13(){
        Intent intent = new Intent(this, Account.class);  //open account
        startActivity(intent);
    }
}