package com.clrzr.googlealc.eko;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SelectorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);
    }

    public void toLandmark (View view){
        Intent toLandmarks = new Intent(SelectorActivity.this, LandMarksActivity.class);
        startActivity(toLandmarks);
    }
    public void toCulture (View view){
        Intent toCultures = new Intent(SelectorActivity.this,CultureActivity.class);
        startActivity(toCultures);
    }
    public void toHotel (View view){
        Intent toHotels = new Intent(SelectorActivity.this,HotelsActivity.class);
        startActivity(toHotels);
    }
    public void toMuseum (View view){
        Intent toMuseums = new Intent(SelectorActivity.this,MuseumActivity.class);
        startActivity(toMuseums);
    }
}
