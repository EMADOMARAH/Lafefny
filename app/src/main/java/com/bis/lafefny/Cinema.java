package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Cinema extends AppCompatActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema);
    }

    public void CenimaOnClick(View view) {
        switch (view.getId()){
//            case R.id.btn_back_cinema:
//                onBackPressed();
//                break;
            case R.id.btn_vox:
                startActivity(new Intent(this, VoxCinema.class));
                break;
            case R.id.txt_location_vox:
                Intent voxLocation = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com/maps/place/Vox+cinema/@30.0818484,31.3630643,15z/data=!4m5!3m4!1s0x0:0x5ead4da702f5488e!8m2!3d30.0818484!4d31.3630643"));
                startActivity(voxLocation);

                break;
            case R.id.btn_home1_cinema:
                startActivity(new Intent(getApplicationContext(), Homepage.class));
                break;
            case R.id.btn_user_cinema:
                startActivity(new Intent(this, Account.class));
                break;

        }
    }
}