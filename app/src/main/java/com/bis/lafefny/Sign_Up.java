package com.bis.lafefny;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class Sign_Up extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    // Create a Cloud Storage reference from the app
    FirebaseStorage storage;
    StorageReference storageReference;
    //ProgressDialog progress ;
    Map<String, Object> user = new HashMap<>();
    //create filepath to upload profile image to sorage
    private Uri filePath;
    //store the new user id
    private String uId;
    private  String profileimageneme;

    //buttons for back ,adding image, signup
    private Button back_btn , add_img_btn ,signUp_btn;
    RadioGroup radioGroup;
    CheckBox agreeCheckBox;
    CheckBox rememberCheckBox;
    ImageView profile_image;
    EditText firstNameEditText,lastNameEditText,userNameEditText,emailEditText;
    EditText passwordEditText,confirmEditText,mobileEditText,dateOBEditText;
    EditText nationalityEditText;
    Spinner spinner;

    CheckBox checkboxagree;
    String firstName,lastName,userName,email,password,confirmPassword,mobile,dateOfBirth,nationality,gender;
    boolean allDataChecked = false;

    final static int CAPTURE_REQUEST_CORE = 1;


    SharedPreferences authPreferences ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);

        initViews();

       // progress= new ProgressDialog(getApplicationContext());

        mAuth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        authPreferences = getSharedPreferences("Lafefny_App" , Context.MODE_PRIVATE);
            //Nationality select
          ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.nationality, android.R.layout.simple_spinner_item);
          adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
          spinner.setAdapter(adapter);
          spinner.setOnItemSelectedListener(this);

          add_img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), CAPTURE_REQUEST_CORE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {  //image
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CAPTURE_REQUEST_CORE) {

                filePath = data.getData();


                Picasso.get()
                        .load(filePath)
                        .noPlaceholder()
                        .centerCrop()
                        .fit()
                        .into((ImageView) findViewById(R.id.profileimage));
            }

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String txt = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),txt,Toast.LENGTH_SHORT).show();

        Scanner scanner = new Scanner(System.in);               // ************NEEDS TO BE FIXED***********
        if(txt=="Other"){
            Toast.makeText(getApplicationContext(),"Enter your nationality:", Toast.LENGTH_LONG).show();
            String other = scanner.next();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void SignUpMethod(View view) {

            getDataFromViews();
            if (checkboxagree.isChecked()){
                checkForDataExist();
                if (allDataChecked == true){
                    createNewUser();
                    storeUserDataToFireStore();
                }
            }else {
                Toast.makeText(this, "Please accept terms and conditions", Toast.LENGTH_SHORT).show();
            }





    }

    private void storeUserDataToFireStore(){
        makeUserDataIntoMap();
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                      //  Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        uId = documentReference.getId();
                        authPreferences.edit().putString("userId" , uId).apply();
                        authPreferences.edit().commit();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                      //  Log.w(TAG, "Error adding document", e);
                    }
                });

        uploadProfileImage();

    }

    private void uploadProfileImage() {
        if(filePath != null)
        {
            profileimageneme = UUID.randomUUID().toString();
//            final ProgressDialog progressDialog = new ProgressDialog(this);
//            progressDialog.setTitle("Adding New member to our family...");
//            progressDialog.show();

            StorageReference ref = storageReference.child("usersImages/"+ profileimageneme);
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                          // progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                //    .getTotalByteCount());
                            //progressDialog.setMessage("Adding.... "+(int)progress+"%");
                        }
                    });
        }
    }

    private void makeUserDataIntoMap() {
        user.put("firstName" , firstName);
        user.put("lastName" , lastName);
        user.put("userName" , userName);
        user.put("email" , email);
        //user.put("password" , password);
        user.put("mobile" , mobile);
        user.put("dateOfBirth" , dateOfBirth);
        user.put("nationality" , nationality);
        user.put("gender" , gender);
        user.put("profileImageName" , profileimageneme);
    }

    private void createNewUser(){
        mAuth.createUserWithEmailAndPassword(email , password)
                .addOnSuccessListener(this, new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(Sign_Up.this, "New member to our family", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext() , Homepage.class));
                        finish();
                    }
                }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Sign_Up.this, e.toString(), Toast.LENGTH_SHORT).show();
                System.out.println(e.toString());
            }
        });
    }

    private void checkForDataExist() {
        if (firstName.isEmpty()){
            firstNameEditText.requestFocus();
            firstNameEditText.setError("Enter Valid Name");
        }else if (lastName.isEmpty()){
            lastNameEditText.requestFocus();
            lastNameEditText.setError("Enter Valid Name");
        }else if (userName.isEmpty()){
            userNameEditText.requestFocus();
            userNameEditText.setError("Enter Valid User Name");
        }else if (email.isEmpty()){
            emailEditText.requestFocus();
            emailEditText.setError("Enter Valid Email");
        }else if (password.isEmpty()){
            passwordEditText.requestFocus();
            passwordEditText.setError("Enter Valid Password");
        }else if (password.length()<6){
            passwordEditText.requestFocus();
            passwordEditText.setError("Enter Valid Password more than 6 chars");
        }else if (!confirmPassword.equals(password)){
            confirmEditText.requestFocus();
            confirmEditText.setError("Password Don't Match");
        }else if (mobile.isEmpty() | mobile.length() !=11 ){
            mobileEditText.requestFocus();
            mobileEditText.setError("Enter Valid mobile number");
        }else if (dateOfBirth.isEmpty()){
            dateOBEditText.requestFocus();
            dateOBEditText.setError("Enter Valid Date of birth");
        }else  if (gender.isEmpty()){
            radioGroup.requestFocus();
            Toast.makeText(this, "Choose Gender", Toast.LENGTH_SHORT).show();
        }else{
            allDataChecked =true ;
        }
    }

    private void getDataFromViews() {
        firstName = firstNameEditText.getText().toString().trim();;
        lastName = lastNameEditText.getText().toString().trim();;
        userName = userNameEditText.getText().toString().trim();
        email = emailEditText.getText().toString().trim();
        password = passwordEditText.getText().toString().trim();
        confirmPassword = confirmEditText.getText().toString().trim();
        mobile= mobileEditText.getText().toString().trim();
        dateOfBirth=dateOBEditText.getText().toString().trim();
        nationality=spinner.getSelectedItem().toString();
        getUserGender();



    }

    public  void  getUserGender(){
        int selectedId = radioGroup.getCheckedRadioButtonId();

        // find the radiobutton by returned id
            RadioButton radioButton = (RadioButton) findViewById(selectedId);
            gender = radioButton.getText().toString().trim();
    }

    public  void initViews(){
        firstNameEditText = findViewById(R.id.pt_firstname);
        lastNameEditText = findViewById(R.id.pt_lastname);
        userNameEditText = findViewById(R.id.pt_user_name_up);
        emailEditText = findViewById(R.id.pt_email_up);
        passwordEditText = findViewById(R.id.pt_passwords_up);
        confirmEditText = findViewById(R.id.pt_conpasswords);
        mobileEditText = findViewById(R.id.pt_mobile);
        dateOBEditText = findViewById(R.id.pt_dob);

        radioGroup = findViewById(R.id.gender_group);
        agreeCheckBox = findViewById(R.id.checkboxagree);
        rememberCheckBox = findViewById(R.id.checkboxremember);
        signUp_btn = (Button) findViewById(R.id.btn_signup);
        back_btn =  (Button) findViewById(R.id.btn_back);
        spinner = findViewById(R.id.spinnerNatio);

        add_img_btn = findViewById(R.id.btn_addphoto);                  //image
        profile_image = findViewById(R.id.profileimage);

        checkboxagree = findViewById(R.id.checkboxagree);
    }


    public void backScreen(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity2.class));

    }
}

