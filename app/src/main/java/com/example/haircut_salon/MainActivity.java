package com.example.haircut_salon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    private Button button;
    EditText et;
    String st;

    @Override
    protected void onCreate(Bundle savedInstanceState)   {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner_nail=findViewById(R.id.spinner);
        spinner_nail.setOnItemSelectedListener(this);
        button= (Button) findViewById(R.id.button);
        et= findViewById(R.id.putnamehere);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Schedule_time.class);
                //openActivity2();
                st=et.getText().toString();
                i.putExtra("value",st);
                startActivity(i);
                finish();


            }
        });
    }

    //public void openActivity2(){
    //Intent intent= new Intent(this, Schedule_time.class);
     //   startActivity(intent);}

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,parent.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
