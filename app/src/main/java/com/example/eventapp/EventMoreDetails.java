package com.example.eventapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class EventMoreDetails extends AppCompatActivity {

    DatabaseHelper db;
    User user;
    int id=0;
    int eid;
    Integer eventid;
    String ename,evname;
    TextView details_name,details_desc,details_poc,details_poc_mob,details_date,details_location,details_time,details_fee;
    Button participate;
    Cursor record;
    StringBuffer sb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_more_details);

        db=new DatabaseHelper(this);

        //session
        user=new User(EventMoreDetails.this);
        id=getIntent().getExtras().getInt("id");
        ename=getIntent().getExtras().getString("eventname");


        details_name=(TextView)findViewById(R.id.details_name);
        details_desc=(TextView)findViewById(R.id.details_desc);
        details_poc=(TextView)findViewById(R.id.details_poc);
        details_poc_mob=(TextView)findViewById(R.id.details_poc_mob);
        details_date=(TextView)findViewById(R.id.details_date);
        details_location=(TextView)findViewById(R.id.details_location);
        details_time=(TextView)findViewById(R.id.details_time);
        details_fee=(TextView)findViewById(R.id.details_fee);
        participate=(Button)findViewById(R.id.participate);

        record=db.getEventRecords();
        while(record.moveToNext())
        {
            evname=record.getString(1);
            if(ename.equals(evname))
            {
                eventid=record.getInt(0);
                details_name.setText(evname);
                details_desc.setText(record.getString(2));
                details_poc.setText(record.getString(3));
                details_poc_mob.setText(record.getString(4));
                details_date.setText(record.getString(6));
                details_time.setText(record.getString(7));
                details_location.setText(record.getString(8));
                details_fee.setText(record.getString(9));
            }

        }

        Toast.makeText(getApplicationContext(),"value "+ename+" "+ename,Toast.LENGTH_LONG).show();
        participate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag=0;
                Cursor rec1=db.showParticipants();
                while(rec1.moveToNext())
                {
                    int studid= rec1.getInt(1);
                    int eveid= rec1.getInt(2);
                    if(studid==id && eveid==eventid)
                    {
                        flag=1;
                    }
                }
                if(flag==0) {
                    boolean res = db.insertParticipate(id, eventid);
                    if (res == true) {

                        Toast.makeText(getApplicationContext(), "Participated", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Not Participated", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    //Toast.makeText(getApplicationContext(), "Already Participated", Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("Already Participated");
                    builder.setMessage("You have already participated in this event!");
                    builder.setCancelable(true);

                    final AlertDialog dlg = builder.create();

                    dlg.show();

                    final Timer t = new Timer();
                    t.schedule(new TimerTask() {
                        public void run() {
                            dlg.dismiss(); // when the task active then close the dialog
                            t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
                        }
                    }, 2000);
                }
            }
        });


    }
}
