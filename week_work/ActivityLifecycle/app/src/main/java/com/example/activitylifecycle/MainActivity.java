package com.example.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private String name = "No name";
    private int age = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        if(savedInstanceState != null){
//            //read the name and age there from tha data bundle.
//
//        }
        TextView Nameactivity = (TextView)findViewById(R.id.nameactivity);
        Nameactivity.setText("Name: " + name + "Age: " + age);
    }
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("NAME1", name);
        savedInstanceState.putInt("AGE1", age);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        name = savedInstanceState.getString("NAME1");
        age = savedInstanceState.getInt("AGE1");

        TextView Nameactivity = (TextView)findViewById(R.id.nameactivity);
        Nameactivity.setText("Name: " + name + "Age: " + age);
    }


    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    protected void onPause() {
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

    public void functionchange(View view) {
        //now name and age will be change and update in the GUI
        name = "Smart Bayezid  ";
        age = 21;
        TextView Nameactivity = (TextView)findViewById(R.id.nameactivity);
        Nameactivity.setText("Name: " + name + "Age: " + age);
    }
}