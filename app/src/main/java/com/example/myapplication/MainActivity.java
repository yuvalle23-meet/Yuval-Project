package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;

public class MainActivity extends AppCompatActivity {
    private TextView SignInText;
    private EditText Email;
    private EditText Password;
    private Button signin;
    private Button signup;
    private FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SignInText = findViewById(R.id.welcome);
        Email = findViewById(R.id.Email);
        Password = findViewById(R.id.Password);
        signin = findViewById(R.id.button);
        signup = findViewById(R.id.button2);
        signin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                if (Email.getText().toString().contains("@") && Email.getText().toString().contains(".com") && Password.getText().toString().length() >= 6){
                    intent.putExtra("email",Email.getText().toString());
                    startActivity(intent);

                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Invalid Email or Password", Toast.LENGTH_SHORT);
                    toast.show();
                };
            }
        });
        signup.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
    public void singIn (String email, String password){
        {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
//                            Log.(TAG, "createUserWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);
                                Intent i = new Intent(MainActivity.this, ProfileActivity.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(MainActivity.this, "Authentication failed", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }
}
