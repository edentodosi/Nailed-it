package com.example.nailit;
//taken from https://developer.android.com/training/basics/intents/sending
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.net.URI;

import static java.net.Proxy.Type.HTTP;

public class contact_option extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_option);
        ImageButton bt=(ImageButton)findViewById(R.id.imageButton2_contact);
        ImageButton wbt=(ImageButton)findViewById(R.id.clock2_contact_rec);
        ImageButton ebt=(ImageButton)findViewById(R.id.clock2_contact);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri number = Uri.parse("tel:053-1234567");
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                startActivity(callIntent);
            }
        });

        wbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webpage = Uri.parse("https://www.google.com/search?q=nailed+it&rlz=1C1SQJL_enIL772IL772&oq=nai" +
                        "led+&aqs=chrome.1.69i57j0l7.5260j0j8&sourceid=chrome&ie=UTF-8");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(webIntent);
            }
        });

        ebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"Nailedit@gmail.com"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Special request");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "My special request is- ");
                startActivity(emailIntent);

            }
        });


    }
}
