package com.example.massa;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
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

import static com.example.massa.R.layout.userdetail;

public class userAdapter extends RecyclerView.Adapter<userAdapter.UserViewHolder> {
    private List<user> List ;
    private FirebaseAuth mAuth;
    private DatabaseReference usersRef;
    Context context;
    public static String kq= "RESUTL";
    public  static String result ="RESULT";
    public String us;

    public userAdapter(List<user> List){
        this.List= List;


    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        public TextView nguoinhan ;
        public ImageButton btn;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            nguoinhan = (TextView)itemView.findViewById(R.id.send);
            btn = itemView.findViewById(R.id.imageButton);


        }
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(userdetail,parent,false);
        mAuth = FirebaseAuth.getInstance();
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder holder, final int position) {
        final user user =List.get(position);
        usersRef = FirebaseDatabase.getInstance().getReference().child("user");
        usersRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context = v.getContext();
                Intent intent = new Intent(context, MessChat2.class);
                intent.putExtra(kq,user.getUser());
                context.startActivity(intent);
            }
        });
        holder.nguoinhan.setTextColor(Color.BLACK);
        holder.nguoinhan.setText(user.getUser());

    }

    @Override
    public int getItemCount() {
        return List.size();
    }


    private class ViewHolder {
    }
}
