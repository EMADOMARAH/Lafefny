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
            case R.id.btn_back_cinema:
                onBackPressed();
                break;
            case R.id.btn_vox:
                startActivity(new Intent(this, VoxCinema.class));
                break;
            case R.id.txt_location_vox:
                Intent voxLocation = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com/search?q=vox%20cinema%20location&oq=vox+cenima+location&aqs=edge..69i57j0i13j0i13i30l2j0i22i30j0i5i13i30l2.6487j0j4&sourceid=chrome&ie=UTF-8&tbs=lf:1,lf_ui:1&tbm=lcl&sxsrf=ALeKk02GhQIjO-VSKJRXokh4dzfpMQ2nuw:1618313718001&rflfq=1&num=10&rldimm=6822194390240675982&lqi=ChN2b3ggY2luZW1hIGxvY2F0aW9uSOvq2ZqFsICACFogCgp2b3ggY2luZW1hEAAQARgAGAEiCnZveCBjaW5lbWGSAQ1tb3ZpZV90aGVhdGVy&phdesc=Sn3gZtF5Smg&ved=2ahUKEwi_oa-ykPvvAhV0sXEKHeEGAscQvS4wAHoECAgQJg&rlst=f#rlfi=hd:;si:;mv:[[30.088468899999995,31.3837675],[29.964886799999995,30.997308300000004]]"));
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