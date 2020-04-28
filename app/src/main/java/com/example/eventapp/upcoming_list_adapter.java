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

public class upcoming_list_adapter extends ArrayAdapter<Upcoming> {

    private LayoutInflater layoutInflater;
    private ArrayList<Upcoming> uEvents;
    private int mViewResourceid;


    public upcoming_list_adapter(Context context,int texViewResourceID,ArrayList<Upcoming> uEvents)
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
            TextView eventname= (TextView) ConverView.findViewById(R.id.nameE);
            TextView date= (TextView) ConverView.findViewById(R.id.dateE);


            if(eventname  != null)
            {
                eventname.setText(og.getEname());
            }

            if(date  != null)
            {
                date.setText("Event Date:  "+ (og.getEdate()));
            }
        }
        return ConverView;
    }


}
