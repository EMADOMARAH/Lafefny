package com.bis.lafefny;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.protobuf.Any;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class preferences extends AppCompatActivity {
    private Button button_home_preferences;
    private Button button_pre_preferences;
    private Button button_account_preferences;
    private Button button_close_preferences;
    private Button button_back_preferences;
    private Button button_questionnaire_preferences;
    RadioGroup locationGroup;
    RadioGroup categoryGroup;
    RadioGroup budgetGroup;
    RadioButton locationbtn;
    RadioButton categorybtn;
    RadioButton budgetbtn;

    String location, category, budget;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    List<DocumentSnapshot> allPlaces;
    ArrayList<String> myPlaces = new ArrayList<>();
    Map<String, Object> place = new HashMap<>();


    DocumentReference pre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        button_home_preferences = (Button) findViewById(R.id.btn_preferences_home);           //button homepage
        button_home_preferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomepage();
            }
        });

        button_close_preferences = (Button) findViewById(R.id.btn_close);           //button Save & Close
        button_close_preferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if(getChoises()){
                   getAllPlaces();
               }


            }

        });

        button_back_preferences = (Button) findViewById(R.id.btn_prefe_back);            //button back
        button_back_preferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }

        });

        button_questionnaire_preferences = (Button) findViewById(R.id.btn_go_to_questionnaire);           //button questionnaire
        button_questionnaire_preferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openquestionnaire();
            }
        });

        button_account_preferences = (Button) findViewById(R.id.btn_preferences_user);           //button user account
        button_account_preferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAccount9();
            }
        });


        addListenerOnButton();


    }

    private void addListenerOnButton() {
        locationGroup = (RadioGroup) findViewById(R.id.governorates_group);
        categoryGroup = (RadioGroup) findViewById(R.id.category_group);
        budgetGroup = (RadioGroup) findViewById(R.id.budget_group);
    }

    private boolean getChoises() {
        int selectedLocationId , selectedCategoryId ,selectedBudgetId;
        try {
             selectedLocationId = locationGroup.getCheckedRadioButtonId();
             selectedCategoryId = categoryGroup.getCheckedRadioButtonId();
             selectedBudgetId = budgetGroup.getCheckedRadioButtonId();

            locationbtn = findViewById(selectedLocationId);
            categorybtn = findViewById(selectedCategoryId);
            budgetbtn = findViewById(selectedBudgetId);

            //the user choises in Preferences Screen
            location = locationbtn.getText().toString();
            category = categorybtn.getText().toString();
            budget = budgetbtn.getText().toString();
        }catch (Exception e){
            Toast.makeText(this, "Answer All Questions", Toast.LENGTH_SHORT).show();
            return false;
        }

        //After implement Filter allPlaces List will be containing only user place list

        return true;
    }

    private void getAllPlaces() {
        FirebaseFirestore.getInstance()
                .collection("places")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            allPlaces = task.getResult().getDocuments();
                            for (int i = 0; i < allPlaces.size(); i++) {
                                String loc = allPlaces.get(i).get("p_gov").toString();
                                String cat = allPlaces.get(i).get("p_ucat").toString();
                                String bud = allPlaces.get(i).get("p_budget").toString();

                                if (loc.contains(location) && cat.contains(category) && bud.contains(budget)) {
                                    String myId = allPlaces.get(i).getId().toString();

                                    myPlaces.add(myId);

                                    System.out.println(allPlaces.get(i).get("name"));
                                    System.out.println("Size : " + myPlaces.size());
                                    System.out.println("My Places :    " + myPlaces);
                                }
                            }

                            if (myPlaces.isEmpty()) {
                                Toast.makeText(preferences.this, "No Places Match", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(preferences.this, "Failed" + e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void openHomepage() {
        Intent intent = new Intent(this, Homepage.class);  //open homepage
        startActivity(intent);
    }

    public void openpreferences() {
        Intent intent = new Intent(this, preferences.class);  //open preferences
        startActivity(intent);
    }

    public void openProvideData() {
        Intent intent = new Intent(this, ProvideData.class);  //open Providedata
        startActivity(intent);
    }

    public void openquestionnaire() {
        Intent intent = new Intent(this, Questionnaire1.class);  //open Questionnaire
        startActivity(intent);
    }

    public void openAccount9() {
        Intent intent = new Intent(this, Account.class);  //open account
        startActivity(intent);
    }


}
//Helped to Know where the error in DB
//                                try {
//                                     bud= allPlaces.get(i).get("p_budget").toString();
//                                    System.out.println( i  + allPlaces.get(i).get("name").toString() + bud);
//                                }catch (Exception e){
//                                   System.out.println("Problem In " + allPlaces.get(i).get("name").toString());
//                                   System.out.println("ERROR : "+ e.toString());
//                                }