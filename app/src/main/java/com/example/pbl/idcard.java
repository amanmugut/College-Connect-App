package com.example.pbl;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class idcard extends AppCompatActivity {
ImageView id;
FirebaseAuth auth;
FirebaseStorage storage;
    public ActivityResultLauncher<String> image;
    Uri imageUri;
    String Imageuri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_idcard);

        id = findViewById(R.id.id);

        image = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                imageUri = result;
                id.setImageURI(result);
            }
        });


        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();

        id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StorageReference storageReference = storage.getReference().child("Upload").child(auth.getUid());

            }
        });
    }
}