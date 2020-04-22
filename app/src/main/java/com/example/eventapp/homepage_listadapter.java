package com.example.eventapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class homepage_listadapter extends ArrayAdapter<Ongoing> {

    private LayoutInflater layoutInflater;
    private ArrayList<Ongoing> oEvents;
    private int mViewResourceid;


    public homepage_listadapter(Context context,int texViewResourceID,ArrayList<Ongoing> oEvents)
    {
        super(context,texViewResourceID,oEvents);
        this.oEvents=oEvents;
        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceid=texViewResourceID;
    }


    /*public homepage_listadapter(Context context, int textViewResourceId, ArrayList<Ongoing> oEvents) {
       super(context,textViewResourceId,oEvents);
        this.oEvents = oEvents;
        layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceid=textViewResourceId;


    }*/
    public View getView(int position , View ConverView, ViewGroup parents) {
        ConverView = layoutInflater.inflate(mViewResourceid, null);
        Ongoing og=oEvents.get(position);

        if(og !=null)
        {
            TextView eventname= (TextView) ConverView.findViewById(R.id.nameE);
            TextView date= (TextView) ConverView.findViewById(R.id.dateE);


            if(eventname  != null)
            {
                eventname.setText("Event Name:  "+ (og.getEname()));
            }

            if(date  != null)
            {
                date.setText("Event Date:  "+ (og.getEdate()));
            }
        }
        return ConverView;
    }


}
