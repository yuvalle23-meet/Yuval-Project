package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    private Button submit;
    private EditText Email;
    private EditText Password;
    private EditText Name;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private TextView signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        database = FirebaseDatabase.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        signin = findViewById(R.id.welcome);
        Email = findViewById(R.id.Email);
        Password = findViewById(R.id.Password);
        Name = findViewById(R.id.Name);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (Email.getText().toString().contains("@") && Email.getText().toString().contains(".com") && Password.getText().toString().length() >= 6){
                    create_user(Email.getText().toString(), Password.getText().toString(), Name.getText().toString());
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Invalid Email or Password", Toast.LENGTH_SHORT);
                    toast.show();
                };
            }
        });
    }
    public void create_user(String email, String password, String name){
        String src = "";
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "createUserWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);

                            User user = new User(email,password,name,src);
                            database.getReference("Users").push().setValue(user);

                            Intent i = new Intent(SignUpActivity.this, HomeActivity.class);
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
//                            updateUI(null);
                            System.out.print(task.getException());
                            signin.setText(task.getException().toString());
                            Toast.makeText(SignUpActivity.this, "Authentication failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}