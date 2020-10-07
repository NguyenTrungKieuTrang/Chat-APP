package com.example.massa;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;




public class MessageActivity extends AppCompatActivity {



    public static String kq= "RESUTL";
    private final List<messChat> messageList = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private MessageAdapter messageAdapter;
    private RecyclerView userMessageList;


    public DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();



    private void writeNewUser(String text, String email) {
        messChat chat = new messChat(text, email);
        mDatabase.child("chat").push().setValue(chat);
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
        mDatabase.child("chat").addChildEventListener(new ChildEventListener() {
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
        setContentView(R.layout.activity_message);

        getData();


        FirebaseAuth auth = FirebaseAuth.getInstance();


        final String result = getIntent().getStringExtra(MainActivity.ketqua);


       final TextView ketqua = (TextView)findViewById(R.id.textView) ;

        ketqua.setText(result);



    ////Logout btn
        ImageButton logoutBtn = (ImageButton)findViewById(R.id.imageButton);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent abc = new Intent(MessageActivity.this,ListActivity.class);
                abc.putExtra(kq,result);
                startActivity(abc);

            }
        });
        /////
        ImageButton sendBtn = (ImageButton)findViewById(R.id.SendBtn);
        /////Send btn
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText Messtext = (EditText) findViewById(R.id.Messtext);

                writeNewUser( Messtext.getText().toString(),result);

                ////////Clear Messtext
                Messtext.setText("");


            }
        });

        /////End Send Btn


    }












}
