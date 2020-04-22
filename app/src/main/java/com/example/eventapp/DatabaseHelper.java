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
        super(context, "Event17.db", null, 1);
        db= this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table register (id integer primary key autoincrement, name text,email text, college text, mobile integer, password text)");
            db.execSQL("create table eventDetails (id integer primary key autoincrement, eventname text,dept text, pocname text, pocmobile text, pocemail text, date date, time text,location text, entryfee text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //inserting participants details
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

    //inserting event details
    public boolean insertEvent(String eventName,String desc,String poc, String pocmob,String pocemail,String date,String time, String location, String fee) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("eventname",eventName);
        contentValues.put("desc",desc);
        contentValues.put("pocname",poc);
        contentValues.put("pocmobile",pocmob);
        contentValues.put("pocemail",pocemail);
        contentValues.put("date",date);
        contentValues.put("time",time);
        contentValues.put("location",location);
        contentValues.put("entryfee",fee);
        long res=db.insert("eventDetails",null,contentValues);
        if(res== -1)
        {
            return false;
        }
        else
        {
            return  true;
        }
    }

    //getting  only id ,name , password
    public Cursor getRecords() {
        db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select id,name,password from register",null);
        return res;
    }


    //getting all records of user
    public Cursor getUserRecords() {
        db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from register",null);
        return res;
    }

    //get all event details
    public Cursor getEventRecords() {
        db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from eventDetails",null);
        return res;
    }


    //update user profile details
    public boolean updateUser(Integer id,String name, String email, String college, String mobile ,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        /*ContentValues contentValues= new ContentValues();
        contentValues.put("name",name);
        contentValues.put("email",email);
        contentValues.put("college",college);
        contentValues.put("mobile",mobile);
        contentValues.put("password",password);
        //res= db.execSQL("update  register set name='"+name+"', email='"+email+"', college='"+college+"', mobile='"+mobile+"',password='"+password+"'");
        long res=db.update("register",contentValues,"id=?",null);*/

        db.execSQL("UPDATE register SET name = '"+name+"',email='"+email+"',college='"+college+"',mobile='"+mobile+"',password='"+password+"' WHERE id = "+id);
        return true;

        /*if(res==-1)
            return false;
        else
            return true;*/
    }

    /*public  boolean deleteEvent()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DELETE FROM eventDetails WHERE eventname='gk quiz'");
        return true;

    }*/
}
