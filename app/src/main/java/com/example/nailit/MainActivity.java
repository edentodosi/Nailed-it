package com.example.nailit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{    private static int RESULT_LOAD_IMAGE = 1111;

    private Button button;
    EditText et;
    String st;
    String selection;
    int nailColor = -1;
    Bitmap bitmap;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState)   {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner_nail=findViewById(R.id.nail_type_spinner);
        spinner_nail.setSelection(0);
        spinner_nail.setOnItemSelectedListener(this);

        final ImageView mImageview=findViewById(R.id.imageView2);
        final View mcolorView=findViewById(R.id.colorview);
        mImageview.setDrawingCacheEnabled(true);
        mImageview.buildDrawingCache(true);

        //Image view on touch listener
        mImageview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN || event.getAction()== MotionEvent.ACTION_MOVE){
                    bitmap=mImageview.getDrawingCache();

                    int pixel=bitmap.getPixel((int)event.getX(),(int)event.getY());

                    //getting RGB values
                    int r= Color.red(pixel);
                    int g= Color.green(pixel);
                    int b= Color.blue(pixel);

                    //set BG color according to choosen color
                    nailColor = Color.rgb(r,g,b);
                    mcolorView.setBackgroundColor(nailColor);
                }
                return true;
            }
        });

        button= (Button) findViewById(R.id.next_button);
        et= findViewById(R.id.client_name_input);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st=et.getText().toString();
                if(st.isEmpty() || nailColor == -1){
                    String errorMsg = st.isEmpty() ? "Please enter name first" : "Please choose nail color";
                    Toast toast = Toast.makeText(v.getContext(), errorMsg, Toast.LENGTH_LONG);
                    TextView toastView = (TextView) toast.getView().findViewById(android.R.id.message);
                    toastView.setTextColor(Color.RED);
                    toast.show();
                    return;
                }

                Spinner mySpinner = (Spinner) findViewById(R.id.nail_type_spinner);
                int nailPolishType = mySpinner.getSelectedItemPosition();

                Intent i=new Intent(MainActivity.this,Schedule_time.class);
                i.putExtra("value",st);
                i.putExtra("nailPolish", nailPolishType);
                startActivity(i);
            }
        });
    }

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
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
