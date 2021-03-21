package com.bis.lafefny;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bis.lafefny.R;

import java.util.Scanner;

public class Sign_Up extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Button button;  //connection

    private Button button_capture;  //image
    ImageView iv_image;            //image

    private Button button2; //sign Up

    final static int CAPTURE_REQUEST_CORE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);

        button2 = (Button) findViewById(R.id.btn_signup); //sign Up
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomepage();
            }
        });

        Toast.makeText(getBaseContext(),"Start Signing up process...",Toast.LENGTH_LONG);

        button =  (Button) findViewById(R.id.btn_back);                 //connection
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity2();
            }
        });


        Spinner spinner = findViewById(R.id.spinnerNatio);            //Spinner for nationality (list)
          ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.nationality, android.R.layout.simple_spinner_item);
          adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
          spinner.setAdapter(adapter);
          spinner.setOnItemSelectedListener(this);

        button_capture = findViewById(R.id.btn_addphoto);                  //image
        iv_image = findViewById(R.id.profileimage);
        button_capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Sign_Up.this, "Camera button is clicked", Toast.LENGTH_LONG).show();
              Intent intentt = new Intent();
              intentt.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
              if(intentt.resolveActivity(getPackageManager()) != null)
                  startActivityForResult(intentt,CAPTURE_REQUEST_CORE);
            }
        });
    }

    public void openMainActivity2() {                                           //connection
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {  //image
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CAPTURE_REQUEST_CORE && requestCode==RESULT_OK){
            Bitmap b = (Bitmap) data.getExtras().get("data");
            iv_image.setImageBitmap(b);
        }
        else {
            Toast.makeText(this,"Result Cancelled", Toast.LENGTH_LONG).show();   //image
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String txt = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),txt,Toast.LENGTH_SHORT).show();

        Scanner scanner = new Scanner(System.in);               // ************NEEDS TO BE FIXED***********
        if(txt=="Other"){
            Toast.makeText(this,"Enter your nationality:", Toast.LENGTH_LONG).show();
            String other = scanner.next();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void openHomepage(){
        Intent intent = new Intent(this, Homepage.class); //Sign Up
        startActivity(intent);
    }

}