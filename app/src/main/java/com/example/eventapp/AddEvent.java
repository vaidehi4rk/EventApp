package com.example.eventapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.Snapshot;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddEvent extends AppCompatActivity {

    EditText eventid,eventName,Dept,poc,poc_mobile,poc_email,event_date,event_location,event_timing;
    Button btn_addEvent;
    long id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        eventid=(EditText)findViewById(R.id.eventid);
        eventName=(EditText)findViewById(R.id.eventName);
        Dept=(EditText)findViewById(R.id.Dept);
        poc=(EditText)findViewById(R.id.poc);
        poc_mobile=(EditText)findViewById(R.id.poc_mobile);
        poc_email=(EditText)findViewById(R.id.poc_email);
        event_date=(EditText)findViewById(R.id.event_date);
        event_location=(EditText)findViewById(R.id.event_location);
        event_timing=(EditText)findViewById(R.id.event_timing);
        btn_addEvent=(Button)findViewById(R.id.btn_addEvent);


    }
}
