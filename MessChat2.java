package com.example.massa;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MessChat2 extends AppCompatActivity {
    TextView ketqua;
    ImageButton send;
    EditText Messtext;


    public FirebaseUser mAuth;
    public DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private final List<messChat> messageList = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private MessageAdapter messageAdapter;
    private RecyclerView userMessageList;





    private void writeNewUser(String text, String email) {
        messChat mess2 = new messChat(text, email);
        mDatabase.child("chat2").push().setValue(mess2);
    }
    private void getData(){
        messageAdapter = new MessageAdapter(messageList);
        userMessageList = (RecyclerView) findViewById(R.id.private_messages_list_of_users);
        linearLayoutManager = new LinearLayoutManager(this);
        userMessageList.setLayoutManager(linearLayoutManager);
        userMessageList.setAdapter(messageAdapter);
    }

    @Override
    protected void onStart(){
        super.onStart();
        mDatabase.child("chat2").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                messChat messages = dataSnapshot.getValue(messChat.class);
                messageList.add(messages);
                messageAdapter.notifyDataSetChanged();
                //userMessageList.smoothScrollToPosition(userMessageList.getAdapter().getItemCount());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_chat2);


        mAuth = FirebaseAuth.getInstance().getCurrentUser();

        ketqua = (TextView) findViewById(R.id.textView);
        final String result = getIntent().getStringExtra(userAdapter.kq);
        ketqua.setText(result);

        getData();


        FirebaseAuth auth = FirebaseAuth.getInstance();
        send = (ImageButton) findViewById(R.id.SendBtn);
        /////Send btn
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Messtext = (EditText) findViewById(R.id.Messtext);

                writeNewUser(Messtext.getText().toString(), result);

                ////////Clear Messtext
                Messtext.setText("");
            }
        });

    }
}
