package com.example.cmp309_week2practical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    float[] hsv = new float[3];

    //Object Instantiation
    ConstraintLayout layoutRef; //layoutRef is an instance of ConstraintLayout object/class
    Button buttonMiddle; //Instance of Button object/class
    MotionEvent motionEvent; //Instance of MotionEvent object/class
    MotionEvent event; //Instance of MotionEvent object/class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Reference to the id given to ConstraintLayout on UI, is initialised
        //ID is "constraintLayoutRoot"
        layoutRef = findViewById(R.id.constraintLayoutRoot);
        
        hsv[0] = 0.0f; // Hue
        hsv[1] = 0.0f; // Saturation
        hsv[2] = 1.0f; // Value

        //onTouchListener Components
        buttonMiddle = findViewById(R.id.buttonMiddle); //Locate UI element
        buttonMiddle.setOnTouchListener(this);  //Create the onTouchListener

        final Button buttonLeft = findViewById(R.id.buttonLeft); //Locate UI element
        buttonLeft.setOnClickListener(new View.OnClickListener() { //Create the onClickListener
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Left Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        final Button buttonMiddle = findViewById(R.id.buttonMiddle); //Locate UI element
        buttonMiddle.setOnClickListener(new View.OnClickListener() { //Create the onClickListener
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Middle Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        final Button buttonRight = findViewById(R.id.buttonRight); //Locate UI element
        buttonRight.setOnClickListener(new View.OnClickListener() { //Create the onClickListener
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Right Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void changeBackgroundColour(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();
        float height = layoutRef.getHeight();   // make sure the ref is declared and initialised (this is a reference to your root layout)
        float width = layoutRef.getWidth();
        hsv[0] = eventY / height * 360; // (0 to 360)
        hsv[1] = eventX / width + 0.1f; // (0.1 to 1)
        layoutRef.setBackgroundColor(Color.HSVToColor(hsv)); //Function to change HSV values to colors
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                buttonMiddle.setText("DOWN");
                return true;
            case MotionEvent.ACTION_MOVE:
                buttonMiddle.setText("MOVE");
                changeBackgroundColour(event);    //Pass event to changeBackgroundColour function
            case MotionEvent.ACTION_UP:
                buttonMiddle.setText("UP");
                return true;
            default:
                return super.onTouchEvent(event);
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (view.getId() == R.id.buttonMiddle) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    buttonMiddle.setBackgroundColor(Color.GREEN);
                    return true;
                case MotionEvent.ACTION_MOVE:
                    buttonMiddle.setBackgroundColor(Color.RED);
                    return true;
                case MotionEvent.ACTION_UP:
                    buttonMiddle.setBackgroundColor(Color.YELLOW);
                    return true;
                default:
                    return false;
            }
        }
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) { //KeyCode determined what key was pressed
        if (event.getAction() == KeyEvent.ACTION_DOWN){ //Get action from event
            if(keyCode == KeyEvent.KEYCODE_VOLUME_UP && hsv[2] < 1.0f){ //If key pressed is volume up...
                hsv[2] += 0.1f; //Lower brightness; hsv[2] is the brightness portion of hsv
                layoutRef.setBackgroundColor(Color.HSVToColor(hsv));
            } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN && hsv[2] > 0.0f){ //If key pressed is volume down...
                hsv[2] -= 0.1f;
                layoutRef.setBackgroundColor(Color.HSVToColor(hsv));
            }
        }
        return true;
    }
}