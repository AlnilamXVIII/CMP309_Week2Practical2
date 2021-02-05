package com.example.cmp309_week2practical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    float[] hsv = new float[3];

    //layoutRef is an instance of ConstraintLayout object/class
    ConstraintLayout layoutRef;
    Button buttonLeft, buttonMiddle, buttonRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Reference to layout "constraintLayoutRoot", the id given to ConstraintLayout on UI, is initialised
        layoutRef = findViewById(R.id.constraintLayoutRoot);
        
        hsv[0] = 0.0f; // Hue
        hsv[1] = 0.0f; // Saturation
        hsv[2] = 1.0f; // Value

        //"final" defines a variable that cannot again be altered
        final Button buttonLeft = findViewById(R.id.buttonLeft);
        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Left Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        final Button buttonMiddle = findViewById(R.id.buttonMiddle);
        buttonMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Middle Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        final Button buttonRight = findViewById(R.id.buttonRight);
        buttonRight.setOnClickListener(new View.OnClickListener() {
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
        float width = layoutRef.getWidth();     // make sure the ref is declared and initialised (this is a reference to your root layout)
        hsv[0] = eventY / height * 360; // (0 to 360)
        hsv[1] = eventX / width + 0.1f; // (0.1 to 1)
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN: buttonLeft.setText("DOWN"); return true;
            case MotionEvent.ACTION_MOVE: buttonLeft.setText("MOVE"); changeBackgroundColour(event);    //Pass event to changeBackgroundColour function
            case MotionEvent.ACTION_UP: buttonLeft.setText("UP"); return true;
            default: return super.onTouchEvent(event);
        }
    }
}