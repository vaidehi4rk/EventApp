package com.example.eventapp;

public class Upcoming {

    private  String ename;
    private String edate;


    public Upcoming(String name, String date) {
        ename=name;
        edate=date;


    }

    public String getEname() {
        return ename;
    }

    public String getEdate() {
        return edate;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public void setEdate(String edate) {
        this.edate = edate;
    }
}
