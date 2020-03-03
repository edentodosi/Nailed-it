package com.example.haircut_salon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.haircut_salon.NailTypes.AcrylicPolish;
import com.example.haircut_salon.NailTypes.Gel;
import com.example.haircut_salon.NailTypes.INailOptions;
import com.example.haircut_salon.NailTypes.Polish;
import com.example.haircut_salon.NailTypes.PolishRemover;

public class Schedule_time extends AppCompatActivity {
    private Button button;
    TextView tv;
    String st;

    INailOptions[] nailOptions = { new Polish(), new Gel(), new AcrylicPolish(), new PolishRemover()};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_time);
        button= (Button) findViewById(R.id.button);
        tv=findViewById(R.id.clientName);
        st=getIntent().getStringExtra("value");
        tv.setText(st);

        TextView priceTv = findViewById(R.id.finall_price);
        int nailType=getIntent().getIntExtra("nailPolish", 0);
        int price = nailOptions[nailType].getPrice();
        priceTv.setText(String.valueOf(price));
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