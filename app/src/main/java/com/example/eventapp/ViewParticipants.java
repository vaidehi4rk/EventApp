package com.example.eventapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewParticipants extends AppCompatActivity {

    DatabaseHelper db;
    LinearLayout layout;
    ListView viewPartcipants;
    ArrayList<Partcipants> part;
    Partcipants partcipants;
    ViewPartcipants_list_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_participants);

        db = new DatabaseHelper(this);
        layout=(LinearLayout)findViewById(R.id.linear);
        viewPartcipants = (ListView)findViewById(R.id.viewParticipants);


        part=new ArrayList<>();


        //join tables;
        Cursor data=db.getParticipants();
        int numRows=data.getCount();
        if(numRows==0)
        {
            Toast.makeText(getApplicationContext(), "There is no users", Toast.LENGTH_LONG).show();
        }
        else {
            while(data.moveToNext())
            {
                partcipants=new Partcipants(data.getInt(0),data.getString(1),data.getString(2),data.getString(3),data.getString(4),data.getString(5));
                part.add(partcipants);

            }
            adapter = new ViewPartcipants_list_adapter(ViewParticipants.this, R.layout.view_partcipants_list_adapter, part);
            viewPartcipants.setAdapter(adapter);
        }
    }
}
