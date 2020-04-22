package com.example.eventapp;

public class eventlist  {

    private  String  eName;
    private  String eDesc;
    private  String ePoc;
    private String epocmob;
    private  String epocemail;
    private String edate;
    private String etime;
    private String elocation;
    private String efee;


    public eventlist(String eventName,String desc,String poc, String pocmob,String pocemail,String date,String time, String location, String fee)
    {
        eName=eventName;
        eDesc=desc;
        ePoc=poc;
        epocmob=pocmob;
        epocemail=pocemail;
        edate=date;
        etime=time;
        elocation=location;
        efee=fee;
    }

    public String geteName() {
        return eName;
    }

    public String geteDesc() {
        return eDesc;
    }

    public String getePoc() {
        return ePoc;
    }

    public String getEpocmob() {
        return epocmob;
    }

    public String getEpocemail() {
        return epocemail;
    }

    public String getEdate() {
        return edate;
    }

    public String getEtime() {
        return etime;
    }

    public String getElocation() {
        return elocation;
    }

    public String getEfee() {
        return efee;
    }
}