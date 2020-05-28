package com.example.eventapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class FinalParticipation extends AppCompatActivity {

    ListView history;
    User user;
    int userid=0;
    DatabaseHelper db;
    ArrayList<Upcoming> uEvents;
    Upcoming u;
    LinearLayout layout;
    history_adpater adpt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_participation);

        history=(ListView)findViewById(R.id.history);
        layout=(LinearLayout)findViewById(R.id.linear);
        uEvents=new ArrayList<>();
        db=new DatabaseHelper(this);

        //session
        user=new User(FinalParticipation.this);
        userid=getIntent().getExtras().getInt("id");

        Cursor recs=db.showHistory(userid);
        while(recs.moveToNext())
        {
            int rid=recs.getInt(0);
            String ename=recs.getString(1);
            String edate=recs.getString(2);
            u = new Upcoming(ename, edate);
            if(rid==userid)
            {
                uEvents.add(u);
            }
        }
        adpt = new history_adpater(getApplicationContext(), R.layout.activity_history_adpater, uEvents);
        history.setAdapter(adpt);
    }
}
