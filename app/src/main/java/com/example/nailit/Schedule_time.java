package com.example.nailit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

public class Schedule_time extends AppCompatActivity {
    private Button button;
    TextView tv;
    TextView weatherTv;
    String st;
    TextView mtv;
    TextView htv;

    INailOptions[] nailOptions = { new Polish(), new Gel(), new AcrylicPolish()};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        WeatherAsyncTask getWeather = new WeatherAsyncTask();
        getWeather.execute("https://api.darksky.net/forecast/a9f37e081e9648fac0fbafde5cb379f0/32.016110,34.773010?units=auto");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_time);
        button= (Button) findViewById(R.id.button);
        tv=findViewById(R.id.client_title_name);
        weatherTv = findViewById(R.id.weahter_title);
        st=getIntent().getStringExtra("value");
        tv.setText(getResources().getString(R.string.Hey) + st +", " + getResources().getString(R.string.date_picker));

        TextView priceTv = findViewById(R.id.price_summary);
        int nailType=getIntent().getIntExtra("nailPolish", 0);
        int price = nailOptions[nailType].getPrice();
        priceTv.setText(getResources().getString(R.string.order_price) + String.valueOf(price));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();
            }
        });

        mtv=(TextView)findViewById(R.id.date_picker_title);
        ImageButton bt=(ImageButton)findViewById(R.id.imageButton2);
        htv=(TextView)findViewById(R.id.hour_picker_title);
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
            Toast toast = Toast.makeText(this, getResources().getString(R.string.agree_terms_error), Toast.LENGTH_LONG);
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            v.setTextColor(Color.RED);
            toast.show();
            return;
        }
        String hour = htv.getText().toString();
        String date = mtv.getText().toString();
        if(hour == getResources().getString(R.string.hour) || date == getResources().getString(R.string.date)){
            Toast toast = Toast.makeText(this,  getResources().getString(R.string.validate_date_houer_error), Toast.LENGTH_LONG);
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            v.setTextColor(Color.RED);
            toast.show();
            return;
        }
        Intent intent= new Intent(this, contact_option.class);
        startActivity(intent);
        finish();
    }

    private class WeatherAsyncTask extends AsyncTask<String, Integer, Double> {
        @Override
        protected Double doInBackground(String... params) {
            // call the server and return response
           HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n");
                }

                String result = buffer.toString();
                JSONObject obj = new JSONObject(result);
                JSONObject currentWeather = obj.getJSONObject("currently");
                Double temp = currentWeather.getDouble("temperature");
                return temp;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return -999.0;
        }

        @Override
        protected void onPostExecute(Double weather){
            if(weather == -999.0){
                return;
            }
            weatherTv.setText(getResources().getString(R.string.current_weather) + weather.toString());
        }
    }
}

