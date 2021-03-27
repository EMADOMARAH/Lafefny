package com.bis.lafefny;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Sign_In extends AppCompatActivity {
    private Button back_btn ,signIn_btn; //back btn ,signIn
    public EditText emailEditText, passwordEditText;
    String  email , password;

    private FirebaseAuth mAuth;
    ProgressDialog progressDialog ;
    private String uId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__in);

        initViews();
        progressDialog= new ProgressDialog(getApplicationContext());
        mAuth = FirebaseAuth.getInstance();

        email = emailEditText.getText().toString().trim();
        password = emailEditText.getText().toString().trim();


        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , MainActivity2.class));
            }
        });

        signIn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("Email : " + email);
                System.out.println("Password : " + emailEditText.getText().toString());
                if (emailEditText.length()<3)
                {
                    emailEditText.requestFocus();
                    emailEditText.setError("Enter Valid Email");
                }else if (passwordEditText.length()<3)
                {
                    passwordEditText.requestFocus();
                    passwordEditText.setError("Enter Valid Password");
                }else {
                    progressDialog.setTitle("Welcome Home...");
                    //progressDialog.show();
                    mAuth.signInWithEmailAndPassword(emailEditText.getText().toString(),passwordEditText.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                   // progressDialog.dismiss();
                                    openHomepage();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                   // progressDialog.dismiss();
                                    Toast.makeText(Sign_In.this, e.toString(), Toast.LENGTH_SHORT).show();
                                }
                            });


                }



            }
        });

    }

    public void openHomepage(){       //sign In
        Intent intent = new Intent(this, Homepage.class);
        startActivity(intent);
    }

    void initViews(){
        back_btn =  (Button) findViewById(R.id.btn_back); //back btn
        signIn_btn = (Button) findViewById(R.id.btn_signin2); //sign In
        emailEditText = findViewById(R.id.pt_email_in);
        passwordEditText = findViewById(R.id.pt_passwords_in);


    }
}