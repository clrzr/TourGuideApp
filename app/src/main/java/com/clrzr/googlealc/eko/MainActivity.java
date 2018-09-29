package com.clrzr.googlealc.eko;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    float x1,y1;
    float x2,y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean onTouchEvent(MotionEvent touchEvent)
    {
    switch (touchEvent.getAction())
    {
    // when user first touches the screen we get x and y coordinate
    case MotionEvent.ACTION_DOWN:
    {
    x1 = touchEvent.getX();
    y1 = touchEvent.getY();
    break;
    }
    case MotionEvent.ACTION_UP:
    {
    x2 = touchEvent.getX();
    y2 = touchEvent.getY();
    //if left to right sweep event on screen
    if (x1 < x2)
    {
    Toast.makeText(this,"Welcome To Lagos", Toast.LENGTH_LONG).show();
    Intent toSelector = new Intent(MainActivity.this,SelectorActivity.class);
    startActivity(toSelector);
    }
    // if right to left sweep event on screen
    if (x1 > x2)
    {
    Toast.makeText(this, "Swipe right to begin", Toast.LENGTH_SHORT).show();
    }
    }
    }
    return false;
    }
}
