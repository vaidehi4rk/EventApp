package com.example.eventapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Update extends AppCompatActivity {

    DatabaseHelper db;
    String name,evname;
    EditText eN,eDes,eP,ePMob,ePEm,eD,eLoc,eTime,eFee;
    Button eUpdate,eDelete;
    Cursor record;
    Integer eventid;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), ViewEvents.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name=getIntent().getExtras().getString("Ename");

        eN=(EditText)findViewById(R.id.eN);
        eDes=(EditText)findViewById(R.id.eDes);
        ePMob=(EditText)findViewById(R.id.ePMob);
        ePEm=(EditText)findViewById(R.id.ePEm);
        eD=(EditText)findViewById(R.id.eD);
        eLoc=(EditText)findViewById(R.id.eLoc);
        eTime=(EditText)findViewById(R.id.eTime);
        eFee=(EditText)findViewById(R.id.eFee);
        eP=(EditText)findViewById(R.id.eP);
        eUpdate=(Button)findViewById(R.id.eUpdate);
        eDelete=(Button)findViewById(R.id.eDelete);


        db=new DatabaseHelper(this);

        Toast.makeText(getApplicationContext(),"value "+name+" "+name,Toast.LENGTH_LONG).show();

        record=db.getEventRecords();
        while(record.moveToNext())
        {
            evname=record.getString(1);
            if(name.equals(evname))
            {
                eventid=record.getInt(0);
                eN.setText(evname);
                eDes.setText(record.getString(2));
                eP.setText(record.getString(3));
                ePMob.setText(record.getString(4));
                ePEm.setText(record.getString(5));
                eD.setText(record.getString(6));
                eTime.setText(record.getString(7));
                eLoc.setText(record.getString(8));
                eFee.setText(record.getString(9));
            }

        }
        eUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean res=db.updateEvents(eventid,eN.getText().toString(),eDes.getText().toString(),eP.getText().toString(),ePMob.getText().toString(),
                ePEm.getText().toString(),eD.getText().toString(),eTime.getText().toString(),eLoc.getText().toString(),eFee.getText().toString());
                if(res==true)
                {
                    Toast.makeText(getApplicationContext(), "Event Details Updated Successfully!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Something Went Wrong! Please Try Again.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        eDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Update.this);
                builder.setMessage("Are you sure you want to delete this event details?");
                builder.setTitle("Delete Event Details");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        //delete event details
                         boolean res=db.deleteEvent(eventid);
                         if(res==true)
                           {
                               Toast.makeText(Update.this, "Event Details Deleted Successfully! ", Toast.LENGTH_LONG).show();
                           }
                        Intent intent=new Intent(getApplicationContext(),ViewEvents.class);
                        startActivity(intent);
                    }

                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });
    }
}
