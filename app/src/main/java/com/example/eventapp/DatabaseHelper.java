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
        super(context, "Demodatabase.db", null, 1);
        db= this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table register (rid integer primary key autoincrement, name text,email text, college text, mobile integer, password text)");
            db.execSQL("create table eventDetails (eid integer primary key autoincrement, eventname text,dept text, pocname text, pocmobile text, pocemail text, eventdate date, time text,location text, entryfee text)");
            db.execSQL("create table particpants (pid integer primary key autoincrement,rid integer,eid integer,status text DEFAULT 'PARTICPATED')");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //inserting user details
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

   /* //checking the login credentials
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
    }*/

    //inserting event details
    public boolean insertEvent(String eventName,String desc,String poc, String pocmob,String pocemail,String date,String time, String location, String fee) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("eventname",eventName);
        contentValues.put("dept",desc);
        contentValues.put("pocname",poc);
        contentValues.put("pocmobile",pocmob);
        contentValues.put("pocemail",pocemail);
        contentValues.put("eventdate",date);
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
    //login check
    public Cursor getRecords() {
        db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select rid,name,password from register",null);
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
        Cursor res=db.rawQuery("select * from eventDetails ",null);
        return res;
    }

    //Update Event details
    public  boolean updateEvents(Integer eid,String eventname,String dept,String pocname,String pocmobile,String pocemail,String eventdate,String time,String location,String entryfee )
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("UPDATE eventDetails SET eventname='"+eventname+"',dept='"+dept+"',pocname='"+pocname+"',pocmobile='"+pocmobile+"',pocemail='"+pocemail+"',eventdate='"+eventdate+"',time='"+time+"',location='"+location+"',entryfee='"+entryfee+"' WHERE eid="+eid);
        return true;
    }

    //delete event details
    public  boolean deleteEvent(Integer eid)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DELETE FROM eventDetails WHERE eid="+eid);
        return true;

    }


    //update user profile details
    public boolean updateUser(Integer rid,String name, String email, String college, String mobile ,String password)
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

        db.execSQL("UPDATE register SET name = '"+name+"',email='"+email+"',college='"+college+"',mobile='"+mobile+"',password='"+password+"' WHERE rid = "+rid);
        return true;

    }


    //insert the details of only participated users
    public boolean insertParticipate(Integer rid,Integer eid)
    {
        db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("rid",rid);
        contentValues.put("eid",eid);
        long res=db.insert("particpants",null,contentValues);
        if(res==-1)
            return false;
        else
            return true;
    }


    //get particpants details , event name in which the user has participated, and participation id
    public Cursor getParticipants()
    {
        db=this.getWritableDatabase();
        Cursor res1=db.rawQuery("select p.pid,r.name,e.eventname,r.email,r.college,r.mobile,e.eventdate from particpants p,register r,eventDetails e where p.rid=r.rid and p.eid=e.eid",null);
        return res1;
    }

    public Cursor showParticipants()
    {
        db=this.getWritableDatabase();
        Cursor rec=db.rawQuery("select * from particpants",null);
        return rec;
    }

    public Cursor showHistory(int rid)
    {
        db=this.getWritableDatabase();
        Cursor rec=db.rawQuery("select p.rid,e.eventname,e.eventdate from particpants p join eventDetails e on e.eid=p.eid where p.rid="+rid,null);
        return rec;
    }

//    public boolean deletep()
//    {
//        db=this.getWritableDatabase();
//        db.execSQL("DELETE from particpants where rid=1");
//        return true;
//    }


}
