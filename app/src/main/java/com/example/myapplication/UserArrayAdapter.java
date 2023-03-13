package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class UserArrayAdapter extends ArrayAdapter<User> {
    private Context context;
    private int resource;
    private ArrayList<User> item;
    public TextView nameView;
    public TextView emailView;


    public UserArrayAdapter(@NonNull Context context, int resource, ArrayList<User> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.item = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(this.context).inflate(this.resource, parent, false);
        }
        User currentItem = this.item.get(position);
        nameView = convertView.findViewById(R.id.name);
        nameView.setText(currentItem.Name);

        emailView = convertView.findViewById(R.id.email);
        emailView.setText(currentItem.Email);



        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProfileActivity.class);
                intent.putExtra("Name", currentItem.Name.toString());
                intent.putExtra("Email", currentItem.Email.toString());
                intent.putExtra("Src", currentItem.Src.toString());
                context.startActivity(intent);
            }
        });

        return convertView;

    }
}


