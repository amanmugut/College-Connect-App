package com.example.pbl;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    ImageView log_reg;
    ImageView sign_in;
    EditText name, mail, password, confirmpassword;
    FirebaseAuth auth;
    FirebaseDatabase database;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);
        log_reg = findViewById(R.id.log_reg);
        sign_in = findViewById(R.id.sign_in);
        name = findViewById(R.id.name);
        mail = findViewById(R.id.mail);
        password = findViewById(R.id.password);
        confirmpassword = findViewById(R.id.confirmpassword);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        log_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Signup.class));
                finish();
            }
        });
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_reg = name.getText().toString();
                String mail_reg = mail.getText().toString();
                String password_reg = password.getText().toString();
                String cpassword_reg = confirmpassword.getText().toString();

                if (TextUtils.isEmpty(name_reg))
                {
                    name.setError("Enter Name");
                }
                else if (TextUtils.isEmpty(mail_reg))
                {
                    mail.setError("Enter Email Address");
                }
                else if (TextUtils.isEmpty(password_reg))
                {
                    password.setError("Enter Password");
                }
                else if (TextUtils.isEmpty(cpassword_reg))
                {
                    confirmpassword.setError("Enter Confirm Password");
                }
                else  if (!mail_reg.matches(emailPattern))
                {
                    mail.setError("Invalid Email Address");
                }
                else if (password_reg.length()<8)
                {
                    password.setError("Invalid Password");
                    Toast.makeText(Register.this, "Password must be 8 characters", Toast.LENGTH_SHORT).show();
                }
                else if (!password_reg.equals(cpassword_reg))
                {
                    confirmpassword.setError("Password doesn't match");
                }
                else
                {
                    auth.createUserWithEmailAndPassword(mail_reg,password_reg).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                DatabaseReference reference = database.getReference().child("User").child(auth.getUid());
                                Users users = new Users(name_reg,auth.getUid(),password_reg,mail_reg);
                                reference.setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful())
                                        {
                                            startActivity(new Intent(Register.this,choose.class));
                                            finish();
                                        }
                                    }
                                });
                            }
                            else {
                                startActivity(new Intent(Register.this,choose.class));
                            }
                        }
                    });
                }
            }
        });


    }
}