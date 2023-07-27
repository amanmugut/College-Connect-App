package com.example.pbl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Calender extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_calender);
    }
}