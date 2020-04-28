package com.example.eventapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
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


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos=position;
                TextView name=(TextView) view.findViewById(R.id.ename);
                String n=name.getText().toString();
                // Toast.makeText(getApplicationContext(), "position is: "+position+"eventname: "+n, Toast.LENGTH_SHORT).show();
                Intent i= new Intent(getApplicationContext(),Update.class);
                i.putExtra("Ename",n);
                startActivity(i);
            }
        });


    }
}
