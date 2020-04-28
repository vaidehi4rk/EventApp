package com.example.eventapp;

import androidx.appcompat.app.AppCompatActivity;

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
                    Toast.makeText(getApplicationContext(), "Something Went wrong! please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
