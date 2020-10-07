package com.example.massa;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import static com.example.massa.R.layout.activity_messagedetail;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {
    private List<messChat> Listtinnhan ;
    private FirebaseAuth mAuth;
    private DatabaseReference usersRef;



    public MessageAdapter(List<messChat> Listtinnhan){
        this.Listtinnhan= Listtinnhan;
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder{
        public TextView nguoiNhan,receiverMessageText,time;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);

            nguoiNhan = (TextView)itemView.findViewById(R.id.nguoinhan);

            receiverMessageText = (TextView)itemView.findViewById(R.id.receiverText);


        }
    }
    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(activity_messagedetail,parent,false);
        mAuth = FirebaseAuth.getInstance();
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MessageViewHolder holder, final int position) {
        messChat messages =Listtinnhan.get(position);
        usersRef = FirebaseDatabase.getInstance().getReference().child("chat");
        usersRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        holder.receiverMessageText.setVisibility(View.GONE);
        holder.nguoiNhan.setVisibility(View.GONE);
        if(messages.getMessageUser()!=null){
            holder.receiverMessageText.setVisibility(View.VISIBLE);
            holder.nguoiNhan.setVisibility(View.VISIBLE);
            holder.receiverMessageText.setTextColor(Color.BLACK);
            holder.receiverMessageText.setText(messages.getMessageText());
            holder.nguoiNhan.setText(messages.getMessageUser()+" :  ");
        }
    }

    @Override
    public int getItemCount() {
        return Listtinnhan.size();
    }




}
