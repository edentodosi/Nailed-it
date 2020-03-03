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

import com.example.haircut_salon.NailTypes.AcrylicPolish;
import com.example.haircut_salon.NailTypes.Gel;
import com.example.haircut_salon.NailTypes.INailOptions;
import com.example.haircut_salon.NailTypes.Polish;
import com.example.haircut_salon.NailTypes.PolishRemover;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    private Button button;
    EditText et;
    String st;
    String selection;

    @Override
    protected void onCreate(Bundle savedInstanceState)   {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner_nail=findViewById(R.id.nail_type_spinner);
        spinner_nail.setSelection(0);
        spinner_nail.setOnItemSelectedListener(this);
        button= (Button) findViewById(R.id.next_button);
        et= findViewById(R.id.client_name_input);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner mySpinner = (Spinner) findViewById(R.id.nail_type_spinner);
                int nailPolishType = mySpinner.getSelectedItemPosition();

                Intent i=new Intent(MainActivity.this,Schedule_time.class);
                //openActivity2();
                st=et.getText().toString();
                i.putExtra("value",st);
                i.putExtra("nailPolish", nailPolishType);
                startActivity(i);
            }
        });
    }

    //public void openActivity2(){
    //Intent intent= new Intent(this, Schedule_time.class);
     //   startActivity(intent);}

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        parent.setSelection(position);
        Toast.makeText(this,parent.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
