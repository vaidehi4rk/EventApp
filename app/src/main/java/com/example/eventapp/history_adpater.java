package com.example.eventapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class history_adpater extends ArrayAdapter<Upcoming> {

    private LayoutInflater layoutInflater;
    private ArrayList<Upcoming> uEvents;
    private int mViewResourceid;


   public history_adpater(Context context,int texViewResourceID,ArrayList<Upcoming> uEvents)
   {
       super(context,texViewResourceID,uEvents);
       this.uEvents=uEvents;
       layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       mViewResourceid=texViewResourceID;
   }

    public View getView(int position , View ConverView, ViewGroup parents) {
        ConverView = layoutInflater.inflate(mViewResourceid, null);
        Upcoming og=uEvents.get(position);

        if(og !=null)
        {
            TextView eventname= (TextView) ConverView.findViewById(R.id.historyName);
            TextView date= (TextView) ConverView.findViewById(R.id.historyDate);


            if(eventname  != null)
            {
                eventname.setText(og.getEname());
            }

            if(date  != null)
            {
                date.setText("Event Date: "+og.getEdate());
            }
        }
        return ConverView;
    }
}
