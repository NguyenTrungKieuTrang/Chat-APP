package com.example.massa;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class ListActivity extends AppCompatActivity {

    public static String result = "RESUTL";
    private final List<user> userList = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private userAdapter UserAdapter;
    private RecyclerView Listuser;
    public DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    Button btn_logout;
    TextView textView;



    private void getData(){
        UserAdapter = new userAdapter(userList);
        Listuser = (RecyclerView) findViewById(R.id.list_user);
        linearLayoutManager = new LinearLayoutManager(this);
        Listuser.setLayoutManager(linearLayoutManager);
        Listuser.setAdapter(UserAdapter);
    }

    @Override
    protected void onStart(){
        super.onStart();

        mDatabase.child("user").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                user user = dataSnapshot.getValue(user.class);
                userList.add(user);
                UserAdapter.notifyDataSetChanged();
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
        setContentView(R.layout.activity_list);

        final String result = getIntent().getStringExtra(MessageActivity.kq);


        final TextView ketqua = (TextView)findViewById(R.id.text) ;

        ketqua.setText(result);
        //
        btn_logout = findViewById(R.id.logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().child("login").removeValue();
                startActivity(new Intent(ListActivity.this, MainActivity.class));
            }
        });

        //
        getData();


        FirebaseAuth auth = FirebaseAuth.getInstance();
}}
