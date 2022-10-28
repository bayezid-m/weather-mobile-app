package com.example.lifecycle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener  {
    private float x = 0;
    private float y = 0;
    private float z = 0;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    boolean active = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener((SensorEventListener) this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putFloat("X1", x);
        savedInstanceState.putFloat("Y1", y);
        savedInstanceState.putFloat("Z1", z);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        active = true;
        super.onRestoreInstanceState(savedInstanceState);
        x = savedInstanceState.getFloat("X1");
        y = savedInstanceState.getFloat("Y1");
        z = savedInstanceState.getFloat("Z1");
            TextView sensorValue = findViewById(R.id.dataview);
            sensorValue.setText("X: " + x + " Y: " + y + " Z: " + z);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this,accelerometer);
    }
    @Override
    protected void onStop() {
        super.onStop();
    }
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public void valuchange(View view) {
            sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener((SensorEventListener) this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        active = true;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
         x = sensorEvent.values[0];
         y = sensorEvent.values[1];
         z = sensorEvent.values[2];

        if(active != false) {
            TextView sensorValue = findViewById(R.id.dataview);
            sensorValue.setText("X: " + x + " Y: " + y + " Z: " + z);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}