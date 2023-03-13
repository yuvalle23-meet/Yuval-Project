package com.example.myapplication;


import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    public String Name;
    public String Email;
    private String Password;
    public String Src;

    public User(){

    }
    public User(String name, String email, String password, String src) {
        this.Name = name;
        this.Email = email;
        this.Password = password;
        this.Src = src;
    }
}