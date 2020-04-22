package com.example.eventapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.ListIterator;

public class ViewEvents extends AppCompatActivity {

    DatabaseHelper db;
    ArrayList<eventlist> eventlists;
    ListView listView;
    eventlist event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_events);


        db = new DatabaseHelper(this);

        eventlists= new ArrayList<>();
        Cursor data = db.getEventRecords();
        int numRows= data.getCount();
        if(numRows==0)
        {
            Toast.makeText(ViewEvents.this, "No data in database :(", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while(data.moveToNext())
            {
                event = new eventlist(data.getString(1),data.getString(2),data.getString(3),data.getString(4),data.getString(5),data.getString(6),data.getString(7),data.getString(8),data.getString(9));
                eventlists.add(event);
            }
        }

        eventList_Adapter adapter = new eventList_Adapter(this,R.layout.viewevents_list_adapter,eventlists);
        listView = (ListView)findViewById(R.id.events_list);
        listView.setAdapter(adapter);

    }
}
