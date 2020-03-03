package com.example.nailit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{    private static int RESULT_LOAD_IMAGE = 1111;

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

        ImageButton buttonLoadImage = (ImageButton) findViewById(R.id.imageButton4);
        buttonLoadImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        button= (Button) findViewById(R.id.next_button);
        et= findViewById(R.id.client_name_input);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner mySpinner = (Spinner) findViewById(R.id.nail_type_spinner);
                int nailPolishType = mySpinner.getSelectedItemPosition();

                Intent i=new Intent(MainActivity.this,Schedule_time.class);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();

            ImageView imageView = (ImageView) findViewById(R.id.imageView2);
            imageView.setImageURI(selectedImage);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        parent.setSelection(position);
        //Toast.makeText(this,parent.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
