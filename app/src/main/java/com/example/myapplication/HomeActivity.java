package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private TextView welcome;
    private ListView listView;
    private ArrayList<User> user;
    private ArrayAdapter<User> arrayAdapter;
    private FirebaseDatabase database;
    private ArrayList<User> users;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance();
        setContentView(R.layout.activity_home);
        welcome = findViewById(R.id.welcome);
        Intent intent = getIntent();
//        String email = intent.getStringExtra("email");
//        welcome.setText(welcome.getText().toString()+' '+email.toString());
        listView = findViewById(R.id.user_list);

        database.getReference("Users").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users = new ArrayList<User>();
                for (DataSnapshot data:snapshot.getChildren()){
                    User user = data.getValue(User.class);
                    users.add(user);
                }
                arrayAdapter = new UserArrayAdapter(HomeActivity.this, R.layout.custom_row, users);
                listView.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();
        if (id == R.id.signout){
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        return true;
    };


}