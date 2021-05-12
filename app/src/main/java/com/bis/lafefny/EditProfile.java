package com.bis.lafefny;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.UUID;

public class EditProfile extends AppCompatActivity {

    private static final int CAPTURE_REQUEST_CORE =  1 ;
    private Uri filePath;
    ImageView editImageView;
    EditText first_name_edit,last_name_edit,user_name_edit,email_edit,password_edit,mobile_edit;
    String firstName,lastName,email,mobile,userName,password;
    boolean allDataChecked = false;

    SharedPreferences authPreferences ;
    FirebaseStorage storage;
    StorageReference storageReference;
    String userId ;
    private  String profileimageneme;

    String oldPassword;


    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference userData ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        initalEditProfileViews();

        authPreferences = getSharedPreferences("Lafefny_App" , Context.MODE_PRIVATE);
        userId = authPreferences.getString("userId","");
        profileimageneme = authPreferences.getString("imgName" , "");

        userData = db.document("users/"+userId);
        storageReference = FirebaseStorage.getInstance().getReference();

        SetDataToScreen();



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
                        .into((ImageView) findViewById(R.id.edit_image_view));
            }

        }
    }




    private void SetDataToScreen() {
        userData.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            user_name_edit.setText(documentSnapshot.getString("userName"));
                            first_name_edit.setText(documentSnapshot.getString("firstName"));
                            last_name_edit.setText(documentSnapshot.getString("lastName"));
                            mobile_edit.setText(documentSnapshot.getString("mobile"));
                            email_edit.setText(documentSnapshot.getString("email"));
                            oldPassword = documentSnapshot.getString("password");
                            password_edit.setText(oldPassword);

                        }else {
                            Toast.makeText(EditProfile.this, "Can't Find this user data", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditProfile.this, "Failed To Get Data", Toast.LENGTH_SHORT).show();
                    }
                });

        LoadImage();
    }

    private void LoadImage() {
        storageReference.child("usersImages/"+ profileimageneme).getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(editImageView);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditProfile.this, "Can't Load Profile Image", Toast.LENGTH_SHORT).show();
                        Toast.makeText(EditProfile.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void EditProfileOnClick(View view) {
        switch (view.getId()){
            case R.id.edit_image_btn:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), CAPTURE_REQUEST_CORE);
                break;
            case R.id.edit_btn:
                getDataFromViews();
                if (allDataChecked ==true){
                    updateUser();
                    onBackPressed();
                }

                break;
        }
    }

   void updateUserPassword(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

// Get auth credentials from the user for re-authentication. The example below shows
// email and password credentials but there are multiple possible providers,
// such as GoogleAuthProvider or FacebookAuthProvider.
        AuthCredential credential = EmailAuthProvider
                .getCredential(email, oldPassword);

// Prompt the user to re-provide their sign-in credentials
        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            user.updatePassword(password).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        db.collection("users").document(userId)
                                                .update("password" , password);
                                        Toast.makeText(EditProfile.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(EditProfile.this, "Error password not updated", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(EditProfile.this, "Error password not updated", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void updateUser() {
        uploadProfileImage();

        db.collection("users").document(userId)
                .update(
                "email" , email,
                "firstName" , firstName,
                "lastName" , lastName,
                "mobile" , mobile,
                "userName" , userName
                );
        updateUserPassword();

    }
    private void uploadProfileImage() {
        if(filePath != null)
        {


            StorageReference ref = storageReference.child("usersImages/"+ profileimageneme);
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "ImageUpdated", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void checkForDataExist() {
        if (firstName.isEmpty()){
            first_name_edit.requestFocus();
            first_name_edit.setError("Enter Valid Name");
        }else if (lastName.isEmpty()){
            last_name_edit.requestFocus();
            last_name_edit.setError("Enter Valid Name");
        }else if (userName.isEmpty()){
            user_name_edit.requestFocus();
            user_name_edit.setError("Enter Valid User Name");
        }else if (email.isEmpty()){
            email_edit.requestFocus();
            email_edit.setError("Enter Valid Email");
        }else if (password.isEmpty()){
            password_edit.requestFocus();
            password_edit.setError("Enter Valid Password");
        }else if (password.length()<6){
            password_edit.requestFocus();
            password_edit.setError("Enter Valid Password more than 6 chars");
        }else if (mobile.isEmpty() | mobile.length() !=11 ){
            mobile_edit.requestFocus();
            mobile_edit.setError("Enter Valid mobile number");
        }else{
            allDataChecked =true ;
        }
    }

    private void getDataFromViews() {
        firstName = first_name_edit.getText().toString().trim();;
        lastName = last_name_edit.getText().toString().trim();;
        userName = user_name_edit.getText().toString().trim();
        email = email_edit.getText().toString().trim();
        password = password_edit.getText().toString().trim();
        mobile= mobile_edit.getText().toString().trim();

        checkForDataExist();
    }
    private void initalEditProfileViews() {
        editImageView = findViewById(R.id.edit_image_view);

        first_name_edit = findViewById(R.id.edit_first);
        last_name_edit = findViewById(R.id.edit_last);
        user_name_edit = findViewById(R.id.edit_username);
        email_edit = findViewById(R.id.edit_email);
        password_edit = findViewById(R.id.edit_password);
        mobile_edit = findViewById(R.id.edit_mobile);
    }
}