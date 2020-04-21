package com.example.eventapp;

import android.content.Context;
import android.content.SharedPreferences;

public class User {

    Context context;
    private int id;
    private  String name;
    SharedPreferences sharedPreferences;

    public int getId() {
        id=sharedPreferences.getInt("userdata",0);
        return id;
    }

    public void setId(int id) {
        this.id = id;
        sharedPreferences.edit().putInt("userdata",id).commit();
    }

    public User(Context context) {
        this.context = context;
        sharedPreferences= context.getSharedPreferences("userinfo",Context.MODE_PRIVATE);

    }

    public String getName() {
        name=sharedPreferences.getString("username","");
        return name;
    }

    public void setName(String name) {
        this.name = name;
        sharedPreferences.edit().putString("username",name).commit();
    }

    public void removeUser()
    {
        sharedPreferences.edit().clear().commit();
    }
}
