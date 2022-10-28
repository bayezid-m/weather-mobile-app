package com.example.weather5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Forecast extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        // Read possible intend data.
        // if there is a city name read it and put it on the screen.
        String cityName = getIntent().getStringExtra("CITY_NAME");

        // let's put it on the screen.
        TextView weatherForcastCityName = findViewById(R.id.textView1);

        if (cityName != null) {
            weatherForcastCityName.setText(cityName);
        } else {
            weatherForcastCityName.setText("No city name given!");
        }
    }
}