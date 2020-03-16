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
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, View.OnTouchListener {
    private Button button;
    EditText et;
    String st;
    int nailColor = -1;
    int BASE_ID_NUMBER = 998899;
    int lastColorPickerClicked = BASE_ID_NUMBER;
    Bitmap bitmap;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState)   {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner_nail=findViewById(R.id.nail_type_spinner);
        spinner_nail.setSelection(0);
        spinner_nail.setOnItemSelectedListener(this);

        Spinner color_spinner=findViewById(R.id.nail_color_count_spinner);
        color_spinner.setSelection(0);
        color_spinner.setOnItemSelectedListener(this);

        final ImageView mImageview=findViewById(R.id.imageView2);
        final View mcolorView=findViewById(R.id.colorview);
        mImageview.setDrawingCacheEnabled(true);
        mImageview.buildDrawingCache(true);

        //Image view on touch listener
        mImageview.setOnTouchListener(this);

        button= (Button) findViewById(R.id.next_button);
        et= findViewById(R.id.client_name_input);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st=et.getText().toString();
                if(st.isEmpty() || nailColor != 111){
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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        parent.setSelection(position);

        if(parent.getId() == R.id.nail_color_count_spinner){
            RelativeLayout rl = (RelativeLayout)findViewById(R.id.colorview);
            rl.removeAllViews();

            int numberOfButtons = position + 1;
            this.nailColor = 0;
            if(numberOfButtons == 2){
                this.nailColor = 100;
            }
            if(numberOfButtons == 1){
                this.nailColor = 110;
            }
            for(int i =0 ; i <  numberOfButtons; i++){
                Button btn = new Button(this);
                btn.setId(BASE_ID_NUMBER + i);
                RelativeLayout.LayoutParams rlparams = new RelativeLayout.LayoutParams(rl.getWidth()/ numberOfButtons, RelativeLayout.LayoutParams.WRAP_CONTENT);
                rlparams.bottomMargin = 3;
                rlparams.topMargin = 3;

                if( i !=0) {
                    rlparams.addRule(RelativeLayout.RIGHT_OF,BASE_ID_NUMBER + i - 1);
                }
                else {
                    rlparams.leftMargin = 0;
                }
                btn.setLayoutParams(rlparams);
                btn.setOnClickListener(this);
                rl.addView(btn);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    public void onClick(View v) {
        this.lastColorPickerClicked = v.getId();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final ImageView mImageview=findViewById(R.id.imageView2);

        try {
            if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
                bitmap = mImageview.getDrawingCache();

                int pixel = bitmap.getPixel((int) event.getX(), (int) event.getY());

                //getting RGB values
                int r = Color.red(pixel);
                int g = Color.green(pixel);
                int b = Color.blue(pixel);

                //set BG color according to choosen color
                int choosenColor = Color.rgb(r, g, b);
                Button button = (Button) findViewById(lastColorPickerClicked);
                button.setBackgroundColor(choosenColor);

                if(lastColorPickerClicked - this.BASE_ID_NUMBER == 0){
                    if(this.nailColor % 10 == 0){
                        this.nailColor += 1;
                    }
                }

                if(lastColorPickerClicked - this.BASE_ID_NUMBER == 1){
                    if(this.nailColor / 10 % 10 == 0){
                        this.nailColor += 10;
                    }
                }

                if(lastColorPickerClicked - this.BASE_ID_NUMBER == 2){
                    if(this.nailColor / 100 == 0){
                        this.nailColor += 100;
                    }
                }
            }
        }
        catch(IllegalArgumentException ex){
            //ignore on error of outside the box
        }
        return true;
    }
}
