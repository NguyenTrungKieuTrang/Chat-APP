package com.example.massa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    public FirebaseAuth mAuth;
    public DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    private void writeNewUser(String text) {
        user user = new user(text);
        mDatabase.child("user").push().setValue(user);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ///////////////////////////////////
        mAuth = FirebaseAuth.getInstance();

        ///////////////////////////////////

        final EditText email, pass, repass;


        email = findViewById(R.id.Email);
        pass = findViewById((R.id.Password));
        repass = findViewById(R.id.RePassword);
        Button btn_register = findViewById(R.id.btn_register);


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String mail = email.getText().toString();
                String repwd = repass.getText().toString();
                String pwd = pass.getText().toString();

                if (mail.isEmpty() || pwd.isEmpty() || repwd.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "please enter your email or password", Toast.LENGTH_SHORT).show();
                } else if (pwd.equals(repwd)) {
                    mAuth.createUserWithEmailAndPassword(mail, pwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                writeNewUser(email.getText().toString());
                                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                            } else {
                                Toast.makeText(RegisterActivity.this, "Failed to Created Account!", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                } else {
                    Toast.makeText(RegisterActivity.this, "please check your password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}