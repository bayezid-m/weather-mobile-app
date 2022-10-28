package com.example.layeout22;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clicked(View view) {
        Button buttonCL = (Button)view;
        buttonCL.setBackgroundColor(Color.YELLOW);
        buttonCL.setTextColor(Color.BLACK);
        buttonCL.setText("Clicked");
    }
}