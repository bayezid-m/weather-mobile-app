package com.example.weather5;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {
    private RequestQueue queue;
    String url = "https://api.openweathermap.org/data/2.5/weather?lat=60.23&lon=24.87&units=metric&appid=372f58cdd4578e431749980646541770";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);
    }

    public void update(View view) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    Toast.makeText(this, response, Toast.LENGTH_LONG).show();
                    parseJsonAndUpdateUI(response);
                }, error ->{
            Toast.makeText(this, "Some error", Toast.LENGTH_LONG).show();

        });
        queue.add(stringRequest);
    }
    private void parseJsonAndUpdateUI(String response) {
        String weatherdescription = "";
        double windspeed = 0;
        int temperature = 0;

        try {
            JSONObject weather = new JSONObject(response);
            temperature = weather.getJSONObject("main").getInt("temp");
            weatherdescription = weather.getJSONArray("weather").getJSONObject(0).getString("main");
            windspeed = weather.getJSONObject("wind").getDouble("speed");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        TextView weatherdescriptionView = findViewById(R.id.sunny);
        weatherdescriptionView.setText(weatherdescription);
        TextView windspeedView = findViewById(R.id.speed);
        windspeedView.setText("" + windspeed + " m/s");
        TextView temperatureView = findViewById(R.id.temperature);
        temperatureView.setText("" + temperature + "°C");


    }


    public void forecast(View view) {
        // Create an intent to open ForcastActivity (navigate into other page)
        Intent intent = new Intent(this, Forecast.class);

        // send some data with the other Activity with the intent.
        intent.putExtra("CITY_NAME", "Tampere");

        // start the activity through intent.
        startActivity(intent);
    }
}
