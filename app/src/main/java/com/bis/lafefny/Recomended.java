package com.bis.lafefny;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.bis.lafefny.Adapter.RecomendedAdapter;
import com.bis.lafefny.Model.RecomededModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Recomended extends AppCompatActivity {

    RecyclerView recomendedRecycler;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<RecomededModel> recomendedList;
    RecomededModel recomededModel;
    RecomendedAdapter recomendedAdapter;


    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference pref;
    CollectionReference places;

    //Contains My recomended places Id
    List<String> recomededId =new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomended);


        recomendedRecycler = findViewById(R.id.recomended_recycler);
        layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recomendedRecycler.setLayoutManager(layoutManager);

        recomendedList = new ArrayList<>();

        getRecomendedId();






    }



    void getRecomendedId(){
        pref = db.document("preferences/pre");
        pref.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            recomededId = (List<String>)documentSnapshot.get("pref");

                            getAllRecomended();
                        }
                        
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Recomended.this, "Failed To Recognize Recomended Places", Toast.LENGTH_SHORT).show();
                    }
                });

    }
    private void getAllRecomended() {
        places = db.collection("places");
        for (int i = 0 ; i<recomededId.size() ; i++){
            String item = recomededId.get(i);
            places.document(recomededId.get(i)).get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                           if (documentSnapshot.exists()){
                               String placeName = documentSnapshot.getString("name");
                               String placeCategory = documentSnapshot.getString("service_type");
                               String placeImage = documentSnapshot.getString("img");


                               recomededModel = new RecomededModel(placeImage, placeCategory,placeName);

                               recomendedList.add(recomededModel);
                               System.out.println("recomendlistSize : " + recomendedList.size());
                           }else{
                               System.out.println("Can't Find This Place" + item);
                           }
                        }
                    })
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            System.out.println("myList : " +recomendedList);
                            recomendedAdapter= new RecomendedAdapter(recomendedList);
                            recomendedRecycler.setAdapter(recomendedAdapter);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
        }



    }

    public void RecomendedOnClick(View view) {
        switch(view.getId()){
            case R.id.recomended_back_btn:
                startActivity(new Intent(getApplicationContext() ,Homepage.class ));
                break;

        }
    }
}