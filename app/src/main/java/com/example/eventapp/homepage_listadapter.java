package com.example.eventapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

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



    public View getView(int position , View ConvertView, ViewGroup parents) {
        ConvertView = layoutInflater.inflate(mViewResourceid, null);
        Ongoing og=oEvents.get(position);

        if(og !=null)
        {
            TextView eventname= (TextView) ConvertView.findViewById(R.id.nameE);
            TextView date= (TextView) ConvertView.findViewById(R.id.dateE);


            if(eventname  != null)
            {
                eventname.setText(og.getEname());
            }

            if(date  != null)
            {
                date.setText(og.getEdate());
            }
        }
        return ConvertView;
    }


}
