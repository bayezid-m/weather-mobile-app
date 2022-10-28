package com.example.mbicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Calculate(View view) {
        EditText editHeight = findViewById(R.id.editHeight);
        EditText editWeight = findViewById(R.id.editWeight);
        String SHeight = editHeight.getText().toString();
        String SWeight = editWeight.getText().toString();
        double height = Double.parseDouble(SHeight);
        Double weight = Double.parseDouble(SWeight);
        double BMI = weight/(height/100*height/100);
        TextView result = findViewById(R.id.result);
        result.setText(String.format("%.2f",BMI));

    }
}