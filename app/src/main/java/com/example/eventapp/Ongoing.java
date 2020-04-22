package com.example.eventapp;

public class Ongoing {

    private  String ename;
    private String edate;


    public Ongoing(String name, String date) {
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
