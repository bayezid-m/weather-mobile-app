package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements LocationListener {
    double latitude = 00.0;
    double longitude = 00.0;
    String weatherdescription = "Rainy";
    double windspeed = 7;
    int temperature = 0;
    String cityname = "Tampere";
    private RequestQueue queue;
    boolean active = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ///////////

/////////////////////////here on oncreate methode the permission request is implemented. so that
//when the user open the app the permission is requested.
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    0
            );
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        }
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putFloat("lat", (float) latitude);
        savedInstanceState.putFloat("long", (float) longitude);
        savedInstanceState.putInt("temp", temperature);
        savedInstanceState.putDouble("speed", windspeed);
        savedInstanceState.putString("city", cityname);
        savedInstanceState.putString("condition", weatherdescription);
//this is the onSaveInstanceState to save the data while the UI goes landscape from portrait.
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
       // this restores all the data when the orientation is changed.
        super.onRestoreInstanceState(savedInstanceState);
        latitude = savedInstanceState.getFloat("lat");
        longitude = savedInstanceState.getFloat("long");
        temperature = savedInstanceState.getInt("temp");
        cityname = savedInstanceState.getString("city");
        windspeed = savedInstanceState.getDouble("speed");
        weatherdescription = savedInstanceState.getString("condition");
        TextView gpsTextView = findViewById(R.id.location);
        gpsTextView.setText("Lat: " + (Math.round(latitude * 100.00) / 100.00) + "\n" + "Long: " + (Math.round(longitude * 100.0) / 100.0));
        TextView temperatureView = findViewById(R.id.temperature);
        temperatureView.setText("" + temperature + "°C");
        TextView city = findViewById(R.id.cityname);
        city.setText(cityname);
        TextView weatherdescriptionView = findViewById(R.id.condition);
        weatherdescriptionView.setText(weatherdescription);
        TextView windspeedView = findViewById(R.id.speed);
        windspeedView.setText("" + windspeed + " m/s");
    }
    public void onLocationChanged(@NonNull Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        TextView gpsTextView = findViewById(R.id.location);
        gpsTextView.setText("Lat: " + (Math.round(latitude * 100.00) / 100.00) + "\n" + "Long: " + (Math.round(longitude * 100.0) / 100.0));
    }

    public void currentlocation(View view) {
        //on this function we got the latitude and longitude. so after pressing the button the url is cpmpleeted
        //with lat and long and the json data is parsed.
        String url = "https://api.openweathermap.org/data/2.5/weather?lat="+latitude+"&lon="+longitude+"&units=metric&appid=372f58cdd4578e431749980646541770";
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
        weatherdescription = "Rainy";
        windspeed = 0;
        temperature = 0;
        cityname = "Tampere";
//with this try catch methode the json data have been shown in the specific textview.
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

    public void citysearch(View view) {
        Intent intent = new Intent (this, serchcity.class);
        startActivity(intent);
    }

///bellow there, this things are the sequence of android lifecycle.
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