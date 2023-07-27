package com.example.pbl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class choose extends AppCompatActivity {
    FirebaseAuth auth;
    ImageView entc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_choose);


        entc = findViewById(R.id.entc);


        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser()==null)
        {
            startActivity(new Intent(choose.this,Register.class));
            finish();
        }
        entc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(choose.this,years.class));
                finish();
            }
        });
    }
}