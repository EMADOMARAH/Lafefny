package com.bis.lafefny;

import androidx.annotation.NonNull;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Question6_7 extends AppCompatActivity {
    private Button button_back_QS4;
    private Button button_next_QS4;
//    private Button button_skip_QS4;
    private Button button_home_QS4;
    private Button button_pre_QS4;
    private Button button_account_QS4;

    RadioGroup Q6 , Q7;
    RadioButton answer6,answer7;
    private SharedPreferences Qanswers;
    String answer6String,answer7String;

    //All 7 answers on 7 Questions
    String A1,A2,A3,A4,A5,A6,A7;

    List<DocumentSnapshot> allPlaces;
    ArrayList<String> myPlaces = new ArrayList<>();
    Map<String, Object> place = new HashMap<>();

    DocumentReference pre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question6_7);

        Qanswers = getSharedPreferences("QAnswer" , Context.MODE_PRIVATE);
        Q6 = (RadioGroup) findViewById(R.id.Ques6_group);
        Q7 = (RadioGroup) findViewById(R.id.Ques7_group);

        button_home_QS4 = (Button) findViewById(R.id.btn_questionnaire4_home);           //button homepage
        button_home_QS4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openHomepage(); }
        });

        button_pre_QS4 = (Button) findViewById(R.id.btn_questionnaire4_pre);           //button preferences
        button_pre_QS4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openpreferences(); }
        });

        button_next_QS4 = (Button) findViewById(R.id.btn_questionnaire4_next);           //button next
        button_next_QS4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getChoises()){
                    saveAnswerToPref();
                }
            }
        });



        button_back_QS4=(Button) findViewById(R.id.btn_questionnaire4_back);            //button back
        button_back_QS4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openQuestion4_5(); }

        });

        button_account_QS4=(Button) findViewById(R.id.btn_questionnaire4_user);            //button account
        button_account_QS4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openAccount14(); }

        });

    }


    void getRecomendedIdsAndSaveThem(){
        FirebaseFirestore.getInstance()
                .collection("places")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            allPlaces = task.getResult().getDocuments();
                            for (int i = 0; i < allPlaces.size(); i++) {
                                String q1 = allPlaces.get(i).get("q1").toString();
                                String q2 = allPlaces.get(i).get("q2").toString();
                                String q3 = allPlaces.get(i).get("q3").toString();
                                String q4 = allPlaces.get(i).get("q4").toString();
                                String q5 = allPlaces.get(i).get("q5").toString();
                                String q6 = allPlaces.get(i).get("q6").toString();
                                String q7 = allPlaces.get(i).get("q7").toString();

                                if (q1.contains(A1) && q2.contains(A2) && q3.contains(A3) && q5.contains(A5) && q6.contains(A6) && q7.contains(A7)) {
                                    String myId = allPlaces.get(i).getId().toString();

                                    myPlaces.add(myId);
                                    System.out.println("Size : " + myPlaces.size());
                                    System.out.println("My Places :    " + myPlaces);
                                }
                            }

                            if (myPlaces.isEmpty()) {
                                Toast.makeText(Question6_7.this, "No Places Match", Toast.LENGTH_SHORT).show();
                            } else {
                                pre = FirebaseFirestore.getInstance().document("preferences/pre");
                                pre.get()
                                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                            @Override
                                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                if (documentSnapshot.exists()) {
                                                    pre.update("pref", myPlaces);
                                                }
                                                Intent intent = new Intent(getApplicationContext(),Recomended.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                startActivity(intent);


                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {

                                            }
                                        });
                            }
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {
                       Toast.makeText(Question6_7.this, "Failed", Toast.LENGTH_SHORT).show();
                   }
               });
    }

    private void saveAnswerToPref() {
        Qanswers.edit().putString("A6" , answer6String).apply();
        Qanswers.edit().putString("A7" , answer7String).apply();
        Qanswers.edit().commit();

        getAnswersFromPref();
    }

    void getAnswersFromPref(){
        A1 = Qanswers.getString("A1" , "");
        A2 = Qanswers.getString("A2" , "");
        A3 = Qanswers.getString("A3" , "");
        A4 = Qanswers.getString("A4" , "");
        A5 = Qanswers.getString("A5" , "");
        A6 = Qanswers.getString("A6" , "");
        A7 = Qanswers.getString("A7" , "");

        getRecomendedIdsAndSaveThem();
    }

    private boolean getChoises() {
        int answe6Id , answe7Id;
        try {
            answe6Id = Q6.getCheckedRadioButtonId();
            answe7Id = Q7.getCheckedRadioButtonId();
            answer6 = findViewById(answe6Id);
            answer7 = findViewById(answe7Id);
            //the user choises in Preferences Screen
            answer6String = answer6.getText().toString();
            answer7String = answer7.getText().toString();

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

    public void openQuestion8_9(){
        Intent intent = new Intent(this, Question8_9.class);  //open question8_9
        startActivity(intent);
    }

    public void openAccount14(){
        Intent intent = new Intent(this, Account.class);  //open account
        startActivity(intent);
    }
}