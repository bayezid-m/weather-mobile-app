package com.example.week6;

import static android.graphics.Color.RED;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private boolean torchOn = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void torch(View view) {
        CameraManager torch = (CameraManager) getSystemService(CAMERA_SERVICE);
        try {
            for(String id: torch.getCameraIdList()){
                CameraCharacteristics cameraChar = torch.getCameraCharacteristics(id);
                if(cameraChar.get(CameraCharacteristics.FLASH_INFO_AVAILABLE)){
                    if(!torchOn){
                        torch.setTorchMode(id, true);
                    }
                    else{
                        torch.setTorchMode(id, false);
                    }
                    torchOn = !torchOn;
                }
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }


    public void sensing(View view) {
        //todo 1: connect to sensor manager
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //todo 2: register to listen to accelerometer sensor events from the HW
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        //todo 3: react to sensor events by uploading the text on the activity screen
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        //we get sensor events in sensorevent
        //read the sensor xyz values and show in the textview
        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];
        float z = sensorEvent.values[2];

        //update the ui
        TextView sensorValue = findViewById(R.id.showbar);
        sensorValue.setText("X: " + x + " Y: " + y + " Z: " + z);

        ConstraintLayout backgroudlayout = findViewById(R.id.background);
        if(z > -0.3 & z < 0.3){
            //if device is on level change the background to green to
            backgroudlayout.setBackgroundColor(Color.GREEN);
        }
        else{
            backgroudlayout.setBackgroundColor(Color.RED);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}