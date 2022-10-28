package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class serchcity extends AppCompatActivity {
    public String cityName;
    String weatherdescription = "";
    double windspeed;
    int temperature;
    String cityname = "";
    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serchcity);
    }
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("temp", temperature);
        savedInstanceState.putDouble("speed", windspeed);
        savedInstanceState.putString("city", cityname);
        savedInstanceState.putString("condition", weatherdescription);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // active = true;
        super.onRestoreInstanceState(savedInstanceState);
        temperature = savedInstanceState.getInt("temp");
        cityname = savedInstanceState.getString("city");
        windspeed = savedInstanceState.getDouble("speed");
        weatherdescription = savedInstanceState.getString("condition");
        TextView temperatureView = findViewById(R.id.temperature);
        temperatureView.setText("" + temperature + "°C");
        TextView city = findViewById(R.id.cityname);
        city.setText(cityname);
        TextView weatherdescriptionView = findViewById(R.id.condition);
        weatherdescriptionView.setText(weatherdescription);
        TextView windspeedView = findViewById(R.id.speed);
        windspeedView.setText("" + windspeed + " m/s");
    }
    public void searchit(View view) {
        EditText searchbar = findViewById(R.id.searchbar);
        cityName = searchbar.getText().toString();
        String url = "https://api.openweathermap.org/data/2.5/weather?q="+cityName+"&units=metric&appid=372f58cdd4578e431749980646541770";
        queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    Toast.makeText(this, response, Toast.LENGTH_LONG).show();
                    parseJsonAndUpdateUI(response);
                }, error -> {
            Toast.makeText(this, "Some error", Toast.LENGTH_LONG).show();

        });
        queue.add(stringRequest);
    }

    private void parseJsonAndUpdateUI(String response) {
        weatherdescription= "";
        windspeed = 0;
        temperature = 0;
        cityname = "";

        try {
            JSONObject weather = new JSONObject(response);
            temperature = weather.getJSONObject("main").getInt("temp");
            weatherdescription = weather.getJSONArray("weather").getJSONObject(0).getString("main");
            windspeed = weather.getJSONObject("wind").getDouble("speed");
            cityname = weather.getString("name");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        TextView city = findViewById(R.id.cityname);
        city.setText(cityname);
        TextView weatherdescriptionView = findViewById(R.id.condition);
        weatherdescriptionView.setText(weatherdescription);
        TextView windspeedView = findViewById(R.id.speed);
        windspeedView.setText("" + windspeed + " m/s");
        TextView temperatureView = findViewById(R.id.temperature);
        temperatureView.setText("" + temperature + "°C");
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    public void onPause() {
        super.onPause();
    }
    @Override
    protected void onStop() {
        super.onStop();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
