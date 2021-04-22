package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bis.lafefny.R;

public class Transportation extends AppCompatActivity {
    private Button button_back; //button back
    private Button button_home_icon; //button home icon
    private Button button_pre_icon;   //button preferences icon

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transportation);

        button_back = (Button) findViewById(R.id.btn_back_trans); //button back
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomepage();
            }
        });

        button_home_icon = (Button) findViewById(R.id.btn_home1_trans); //button back icon
        button_home_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomepage();
            }
        });
        button_pre_icon = (Button) findViewById(R.id.btn_pre_trans); //button preferences icon
        button_pre_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openpreferences();
            }
        });

    }
    public void openHomepage(){
        Intent intent = new Intent(this, Homepage.class);  //button back
        startActivity(intent);
    }

    public void openpreferences(){
        Intent intent = new Intent(this, preferences.class);  //button preferences
        startActivity(intent);
    }

    public void TransportationOnClick(View view) {
        switch (view.getId()){
            case R.id.btn_car_ride:
            case R.id.btn_bus_ride:
//                Intent uberCarIntent = new Intent(android.content.Intent.ACTION_VIEW,
//                        Uri.parse("https://www.uber.com/eg/en/"));
//                startActivity(uberCarIntent);
                PackageManager pm = getPackageManager();
                try {
                    pm.getPackageInfo("com.ubercab", PackageManager.GET_ACTIVITIES);
                    String uri = "uber://?action=setPickup&pickup=my_location";
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(uri));
                    startActivity(intent);
                } catch (PackageManager.NameNotFoundException e) {
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.uber.com/eg/en/")));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=com.ubercab")));
                    }
                }
                break;

//                Intent uberBusIntent = new Intent(android.content.Intent.ACTION_VIEW,
//                        Uri.parse("https://www.uber.com/en-EG/blog/introducing-uber-bus-a-new-way-to-commute/"));
//                startActivity(uberBusIntent);
//                break;
            case R.id.btn_steampship_ride:
                Intent sreampship = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://www.lookategypttours.com/"));
                startActivity(sreampship);
                break;
            case R.id.btn_airplane_ride:
                Intent airplaneIntent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://www.lookategypttours.com/"));
                startActivity(airplaneIntent);
                break;

        }
    }
}