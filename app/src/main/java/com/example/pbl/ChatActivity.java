package com.example.pbl;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseDatabase database;
//    DatabaseReference messagedb;
DatabaseReference reference;
    MessageAdapter messageAdapter;
    Users u;
    List<Message> messages;

    RecyclerView rvMessage;
    EditText etMessage;
    ImageView imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_chat);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        etMessage = findViewById(R.id.etmessage);
        imageButton = findViewById(R.id.btnsend);
        rvMessage = findViewById(R.id.rvmessage);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(etMessage.getText().toString()))
                {
                    String msg = etMessage.getText().toString();
                    etMessage.setText("");
                    reference = database.getReference().child("Message").child(auth.getUid());
                    aman_msg msg1 = new aman_msg(msg,auth.getUid());
                    reference.push().setValue(msg1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(ChatActivity.this, "done", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(ChatActivity.this, "error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

/*
        init();
    }
    private void init()
    {
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        u = new Users();

        rvMessage = findViewById(R.id.rvmessage);
        etMessage = findViewById(R.id.etmessage);
        imageButton = findViewById(R.id.btnsend);

        imageButton.setOnClickListener(this);
        messages = new ArrayList<>();
    }

    @Override
    public void onClick(View v) {
        if (!TextUtils.isEmpty(etMessage.getText().toString()))
        {
            Message message = new Message(etMessage.getText().toString(),u.getName());
            etMessage.setText("");
//            DatabaseReference reference = database.getReference().child("Message");
//            reference.setValue("Aman");
//            messagedb.push().setValue("message");
            Toast.makeText(this, message.toString(), Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == R.id.menuLogout)
        {
            auth.signOut();
            finish();
            startActivity(new Intent(ChatActivity.this,Signup.class));
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onStart(View v) {
        super.onStart();
        final FirebaseUser currentUser = auth.getCurrentUser();

        u.setUid(currentUser.getUid());
        u.setEmail(currentUser.getEmail());

        database.getReference("User").child(currentUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                u = snapshot.getValue(Users.class);
                u.setUid(currentUser.getUid());
                ALLMethods.name = u.getName() ;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        messagedb = database.getReference("messages");
        messagedb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Message message = snapshot.getValue(Message.class);
                message.setKey(snapshot.getKey());
                messages.add(message);
                displayMessages(messages);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Message message = snapshot.getValue(Message.class);
                message.setKey(snapshot.getKey());

                List<Message> newMessages = new ArrayList<Message>();

                for (Message m:messages)
                {
                    if(m.getKey().equals(message.getKey()))
                    {
                        newMessages.add(message);
                    }
                    else
                    {
                        newMessages.add(m);
                    }
                }
                messages = newMessages;
                displayMessages(messages);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Message message = snapshot.getValue(Message.class);
                message.setKey(snapshot.getKey());
                List<Message> newMessages = new ArrayList<Message>();
                for (Message m:messages)
                {
                    if(!m.getKey().equals(message.getKey()))
                    {
                        newMessages.add(m);
                    }
                }
                messages = newMessages;
                displayMessages(messages);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        messages = new ArrayList<>();

    }
    private void displayMessages(List<Message> messages) {
        rvMessage.setLayoutManager(new LinearLayoutManager(ChatActivity.this));
        messageAdapter = new MessageAdapter(ChatActivity.this,messages,messagedb);
        rvMessage.setAdapter(messageAdapter);
    }

}

 */

    }
    public void displayMessages(List<Message> messages) {
        rvMessage.setLayoutManager(new LinearLayoutManager(ChatActivity.this));
        messageAdapter = new MessageAdapter(ChatActivity.this,messages,reference);
        rvMessage.setAdapter(messageAdapter);
    }
}