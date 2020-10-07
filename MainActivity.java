package com.example.massa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.analytics.FirebaseAnalytics;
import android.content.Intent;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {
    public FirebaseAuth mAth;
    private FirebaseAuth.AuthStateListener mAthState;
    Button login,register,forget;
    private EditText editTextEmail, editTextPassword;



    public static String ketqua= "RESUTL";

    public DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();


    private void writeNewUser(String user) {
        login login = new login(user);
        mDatabase.child("login").push().setValue(login);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent abc = new Intent(MainActivity.this,MessageActivity.class);

        mAth =FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.Email);
        editTextPassword = findViewById((R.id.Password));






////////////////////////////////////////LOGIN
        login = findViewById(R.id.btn_login);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = editTextEmail.getText().toString();
                String pwd = editTextPassword.getText().toString();

                abc.putExtra(ketqua,email);

                if (email.isEmpty() || pwd.isEmpty()) {
                    Toast.makeText(MainActivity.this, "please enter your email or password", Toast.LENGTH_SHORT).show();
                } else {
                    mAth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful() ){
                                //Toast.makeText(MainActivity.this, "Wddawdawggggasssssssssssssssssssssss", Toast.LENGTH_SHORT).show();
                                writeNewUser(editTextEmail.getText().toString());
                                startActivity(abc);

                            }else{
                                Toast.makeText(MainActivity.this, "Wrong email or password", Toast.LENGTH_SHORT).show();

                            }
                            }
                    });

                }}});
//////////////////////////////////LOGIN
                register = findViewById(R.id.btn_register);

                register.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                    }
                });

            }


}