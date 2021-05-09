package com.bis.lafefny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bis.lafefny.R;

public class Promocode extends AppCompatActivity {
    private Button button_payment; // return from promo to payment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promocode);

        button_payment = (Button) findViewById(R.id.btn_return_to_payment); //button booking icon
        button_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    public void PromoCodeOnClick(View view) {
        switch (view.getId()){
            case R.id.btn_promo1:
                setClipboard(getApplicationContext() , "Lafefny66");
                break;
            case R.id.btn_promo3:
                setClipboard(getApplicationContext() , "Lafefny98");
                break;
            case R.id.btn_promo4:
                setClipboard(getApplicationContext() , "Lafefny99");
                break;

        }
    }
    private void setClipboard(Context context, String text) {
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(text);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
            clipboard.setPrimaryClip(clip);
        }
        Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show();
    }
}