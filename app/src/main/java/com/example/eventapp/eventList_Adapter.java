package com.example.eventapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class eventList_Adapter extends ArrayAdapter<eventlist> {

    private LayoutInflater layoutInflater;
    private ArrayList<eventlist> events;
    private int mViewResourceid;

    public eventList_Adapter(Context context, int textViewResourceId, ArrayList<eventlist> events)
    {
        super(context,textViewResourceId,events);
        this.events=events;
        layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceid=textViewResourceId;
    }

    public View getView(int position , View ConverView, ViewGroup parents){
        ConverView=layoutInflater.inflate(mViewResourceid,null);
        eventlist e=events.get(position);

        if(e != null)
        {
            TextView eventname= (TextView) ConverView.findViewById(R.id.ename);
            TextView edesc= (TextView) ConverView.findViewById(R.id.edesc);
            TextView epoc= (TextView) ConverView.findViewById(R.id.pocname);
            TextView pocmob= (TextView) ConverView.findViewById(R.id.pocmob);
            TextView pocemail= (TextView) ConverView.findViewById(R.id.pocemail);
            TextView date= (TextView) ConverView.findViewById(R.id.date);
            TextView location= (TextView) ConverView.findViewById(R.id.location);
            TextView time= (TextView) ConverView.findViewById(R.id.time);
            TextView fee= (TextView) ConverView.findViewById(R.id.fee);


            if(eventname  != null)
            {
                eventname.setText("Event Name:  "+ (e.geteName()));
            }
            if(edesc!=null)
            {
                edesc.setText("Event Description:  "+(e.geteDesc()));
            }
            if(epoc!=null)
            {
                epoc.setText("POC name: "+e.getePoc());
            }
            if(pocmob!=null)
            {
                pocmob.setText("POC Mobile No: "+e.getEpocmob());
            }
            if(pocemail!=null)
            {
                pocemail.setText("POC Email: "+e.getEpocemail());
            }
            if(date!=null)
            {
                date.setText("Event Date: "+e.getEdate());
            }
            if(location!=null)
            {
                location.setText("Event Location: "+e.getElocation());
            }
            if(time!=null)
            {
                time.setText("Event Time: "+e.getEtime());
            }
            if(fee!=null)
            {
                fee.setText("Event Fee: "+e.getEfee());
            }

        }
        return ConverView;
    }
}
