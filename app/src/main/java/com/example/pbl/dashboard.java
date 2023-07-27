package com.example.pbl;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class dashboard extends AppCompatActivity {
ImageView maildas;
    ImageView logouthome;
    ImageView chat;
    ImageView idcarddash;
    ImageView calender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_dashboard);

        maildas = findViewById(R.id.maildas);
        logouthome = findViewById(R.id.logouthome);
        chat = findViewById(R.id.chat);
        idcarddash = findViewById(R.id.idcarddash);
        calender = findViewById(R.id.calender);

        maildas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dashboard.this,mail.class));
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dashboard.this,ChatActivity.class));
            }
        });
        idcarddash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dashboard.this,idcard.class));
            }
        });
        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dashboard.this,Calender.class));
            }
        });
        logouthome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(dashboard.this,R.style.Dialoge);
                dialog.setContentView(R.layout.dialog_layout);

                TextView yes_btn,no_btn;
                yes_btn=dialog.findViewById(R.id.yes_btn);
                no_btn=dialog.findViewById(R.id.no_btn);

                yes_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseAuth.getInstance().signOut();
                        Toast.makeText(dashboard.this, "Signed Out", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(dashboard.this,Register.class));
                        finish();
                    }
                });
                no_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

    }
}