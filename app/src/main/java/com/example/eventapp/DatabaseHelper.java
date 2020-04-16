package com.example.eventapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    SQLiteDatabase db;
    public DatabaseHelper(@Nullable Context context) {
        super(context, "EventApp.db", null, 1);
        db= this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table register (id integer primary key autoincrement, name text,email text, college text, mobile integer, password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public  boolean insertData(String name, String email, String college, String mobile, String password)
    {
     SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("name",name);
        contentValues.put("email",email);
        contentValues.put("college",college);
        contentValues.put("mobile",mobile);
        contentValues.put("password",password);
        long res=db.insert("register",null,contentValues);
        if(res== -1)
        {
            return false;
        }
        else
        {
            return  true;
        }
    }

    //checking the login credentials
    public Boolean logincheck(String uname,String pwd)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from register where name=? and password=?",new String[]{uname,pwd});

        if(cursor.getCount()>0)
        {
            return  true;
        }
        else
        {
            return  false;
        }
    }
}
