package com.example.eventapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.Snapshot;

import android.content.Intent;
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

public class AddEvent extends AppCompatActivity
{

    EditText eventName,Desc,poc,poc_mobile,poc_email,event_date,event_location,event_timing,eventFee;
    Button btn_addEvent;
    DatabaseHelper db;
   // long id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        db=new DatabaseHelper(this);

      
        eventName=(EditText)findViewById(R.id.eventName);
        Desc=(EditText)findViewById(R.id.Desc);
        poc=(EditText)findViewById(R.id.poc);
        poc_mobile=(EditText)findViewById(R.id.poc_mobile);
        poc_email=(EditText)findViewById(R.id.poc_email);
        event_date=(EditText)findViewById(R.id.event_date);
        event_location=(EditText)findViewById(R.id.event_location);
        event_timing=(EditText)findViewById(R.id.event_timing);
        eventFee=(EditText)findViewById(R.id.eventFee);
        btn_addEvent=(Button)findViewById(R.id.btn_addEvent);


        btn_addEvent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                boolean res=db.insertEvent(eventName.getText().toString(),Desc.getText().toString(),
                        poc.getText().toString(), poc_mobile.getText().toString(),
                        poc_email.getText().toString(),event_date.getText().toString(),
                        event_timing.getText().toString(),event_location.getText().toString(),
                        eventFee.getText().toString());
                if(res== true)
                {
                    Toast.makeText(AddEvent.this, "Event details added successfully!", Toast.LENGTH_LONG).show();
                    Intent intent= new Intent(AddEvent.this,Login.class);
                    startActivity(intent);

                }
                else
                    Toast.makeText(AddEvent.this, "Something's wrong! Please try again", Toast.LENGTH_LONG).show();
            }
        });

    }
}
