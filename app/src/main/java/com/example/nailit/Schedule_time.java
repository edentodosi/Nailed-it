package com.example.nailit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.nailit.NailTypes.AcrylicPolish;
import com.example.nailit.NailTypes.Gel;
import com.example.nailit.NailTypes.INailOptions;
import com.example.nailit.NailTypes.Polish;
import com.example.nailit.NailTypes.PolishRemover;

import java.util.Calendar;
import java.util.Timer;

public class Schedule_time extends AppCompatActivity {
    private Button button;
    TextView tv;
    String st;
    TextView mtv;
    TextView htv;

    INailOptions[] nailOptions = { new Polish(), new Gel(), new AcrylicPolish(), new PolishRemover()};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_time);
        button= (Button) findViewById(R.id.button);
        tv=findViewById(R.id.client_title_name);
        st=getIntent().getStringExtra("value");
        tv.setText("Hey " +st +", pick your wanted time");

        TextView priceTv = findViewById(R.id.price_summary);
        int nailType=getIntent().getIntExtra("nailPolish", 0);
        int price = nailOptions[nailType].getPrice();
        priceTv.setText("Order price: " + String.valueOf(price));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();
            }
        });

        mtv=(TextView)findViewById(R.id.date_picker_title);
        ImageButton bt=(ImageButton)findViewById(R.id.imageButton2);
        htv=(TextView)findViewById(R.id.houer_picker_title);
        ImageButton hbt=(ImageButton)findViewById(R.id.clock2);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);
                DatePickerDialog dpd = new DatePickerDialog(Schedule_time.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mtv.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, year,month, day);
                dpd.show();
            }
        });

        hbt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar c = Calendar.getInstance();
                    int hour=c.get(Calendar.HOUR_OF_DAY);
                    int minutes=c.get(Calendar.MINUTE);
                    TimePickerDialog tpd=new TimePickerDialog(Schedule_time.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            htv.setText(hourOfDay+":"+ minute);
                        }
                    },hour,minutes,true);
                    tpd.show();
                }
            });

        }
        public void openActivity3(){
            CheckBox agree = findViewById(R.id.agree);
            if(!agree.isChecked()){
                Toast toast = Toast.makeText(this, "You must agree the terms first", Toast.LENGTH_LONG);
                TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                v.setTextColor(Color.RED);
                toast.show();
                return;
            }
            Intent intent= new Intent(this, contact_option.class);
            startActivity(intent);
            finish();
        }
    }