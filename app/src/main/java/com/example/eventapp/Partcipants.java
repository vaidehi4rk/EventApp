package com.example.eventapp;

public class Partcipants {

    private  Integer pid;
    private String pname;
    private  String ename;
    private  String email;
    private  String college;
    private  String contact;

    public Partcipants(Integer pid, String pname, String ename, String email, String college, String contact) {
        this.pid = pid;
        this.pname = pname;
        this.ename = ename;
        this.email = email;
        this.college = college;
        this.contact = contact;
    }

    public Integer getPid() {
        return pid;
    }

    public String getPname() {
        return pname;
    }

    public String getEname() {
        return ename;
    }

    public String getEmail() {
        return email;
    }

    public String getCollege() {
        return college;
    }

    public String getContact() {
        return contact;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}







