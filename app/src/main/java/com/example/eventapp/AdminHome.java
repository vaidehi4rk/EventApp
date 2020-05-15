package com.example.eventapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AdminHome extends AppCompatActivity {


    ImageView event,ViewEvents,viewPart;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);



        event=(ImageView)findViewById(R.id.addevents);
        ViewEvents=(ImageView)findViewById(R.id.ViewEvents);
        viewPart=(ImageView)findViewById(R.id.viewPart);


        //add events
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHome.this,AddEvent.class);
                startActivity(intent);
            }
        });

       //viewevents
        ViewEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHome.this,ViewEvents.class);
                startActivity(intent);
            }
        });

        viewPart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHome.this,ViewParticipants.class);
                startActivity(intent);
            }
        });

    }
}
