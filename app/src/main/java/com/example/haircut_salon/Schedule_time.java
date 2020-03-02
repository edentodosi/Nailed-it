package com.example.haircut_salon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Schedule_time extends AppCompatActivity {
    private Button button;
    TextView tv;
    String st;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_time);
        button= (Button) findViewById(R.id.button);
        tv=findViewById(R.id.getname);
        st=getIntent().getExtras().getString("Value");
        tv.setText(st);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();
            }
        });
    }
        public void openActivity3(){
            Intent intent= new Intent(this, contact_option.class);
            startActivity(intent);
        }
}