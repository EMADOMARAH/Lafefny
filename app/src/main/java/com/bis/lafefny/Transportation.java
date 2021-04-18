package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bis.lafefny.R;

public class Transportation extends AppCompatActivity {
    private Button button_back; //button back
    private Button button_back_icon; //button back icon
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

        button_back_icon = (Button) findViewById(R.id.btn_home1_trans); //button back icon
        button_back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomepage();
            }
        });

    }
    public void openHomepage(){
        Intent intent = new Intent(this, Homepage.class);  //button back
        startActivity(intent);
    }

    public void TransportationOnClick(View view) {
        switch (view.getId()){
            case R.id.btn_car_ride:
                Intent uberCarIntent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://www.uber.com/eg/en/"));
                startActivity(uberCarIntent);
                break;
            case R.id.btn_bus_ride:
                Intent uberBusIntent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://www.uber.com/en-EG/blog/introducing-uber-bus-a-new-way-to-commute/"));
                startActivity(uberBusIntent);
                break;
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