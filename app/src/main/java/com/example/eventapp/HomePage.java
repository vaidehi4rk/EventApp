package com.example.eventapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomePage extends AppCompatActivity {
    User user;
    int userid=0;
    String name;
    Cursor data;
    Button ongoing,upgoing;
    DatabaseHelper db;
    ArrayList<Ongoing> oEvents;
    ArrayList<Upcoming> uEvents;
    ListView listView1,listView2;
    Ongoing o;
    Upcoming u;
    String name1;
    String date;
    StringBuffer c1;
    LinearLayout layout;
    homepage_listadapter adpt;
    upcoming_list_adapter adp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        ongoing=(Button)findViewById(R.id.ongoing);
        upgoing=(Button)findViewById(R.id.upcoming);
        layout=(LinearLayout)findViewById(R.id.linear);
        listView1 = (ListView)findViewById(R.id.listview1);
        listView2 = (ListView)findViewById(R.id.listview2);

        db = new DatabaseHelper(this);



        oEvents= new ArrayList<>();
        uEvents=new ArrayList<>();


        //session
        user=new User(HomePage.this);
        userid=getIntent().getExtras().getInt("id");
        //  name=getIntent().getExtras().getString("name");

        Toast.makeText(this, "id is"+userid, Toast.LENGTH_SHORT).show();

        //upcoming events
        upgoing.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                upgoing.setEnabled(false);
                ongoing.setEnabled(true);

                listView1.setVisibility(View.GONE);
                listView2.setVisibility(View.VISIBLE);

                if(listView2.getCount() == 0) {

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    String getCurrentDateTime = sdf.format(c.getTime());
                    String[] d1=getCurrentDateTime.split("/");
                    int[] x1= new int[d1.length];
                    data = db.getEventRecords();
                    Integer compare;
                    c1 = new StringBuffer();
                    while (data.moveToNext()) {
                        name1 = data.getString(1);
                        date = data.getString(6);
                        String[] d2=date.split("/");
                        u = new Upcoming(name1, date);
                       if (d1[2].compareTo(d2[2])==0 && d1[1].compareTo(d2[1])<0)
                        {
                           uEvents.add(u);
                           c1.append(date + " " + getCurrentDateTime.compareTo(date) + "\n");
                           //Toast.makeText(HomePage.this, "value   " + c1, Toast.LENGTH_LONG).show();
                        }
                       if(d1[1].compareTo(d2[1])==0)
                       {
                           if(d1[0].compareTo(d2[0])<0){
                               uEvents.add(u);
                               c1.append(date + " " + getCurrentDateTime.compareTo(date) + "\n");
                               //Toast.makeText(HomePage.this, "value   " + c1, Toast.LENGTH_LONG).show();
                           }

                       }
                        if(d1[2].compareTo(d2[2])<0)
                        {
                            uEvents.add(u);
                            c1.append(date + " " + getCurrentDateTime.compareTo(date) + "\n");
                            //Toast.makeText(HomePage.this, "value   " + c1, Toast.LENGTH_LONG).show();
                        }
                    }
                    adp = new upcoming_list_adapter(HomePage.this, R.layout.upcoming_list_adapter, uEvents);
                    listView2.setAdapter(adp);
                }
            }
        });

        //ongoing events
        ongoing.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ongoing.setEnabled(false);
                upgoing.setEnabled(true);
                listView2.setVisibility(View.GONE);
                listView1.setVisibility(View.VISIBLE);

                if(listView1.getCount() == 0) {

                    Date c = Calendar.getInstance().getTime();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    String getCurrentDateTime=sdf.format(c);
                    String[] d1=getCurrentDateTime.split("/");
                    Toast.makeText(HomePage.this, "date is: "+getCurrentDateTime, Toast.LENGTH_LONG).show();
                    data = db.getEventRecords();
                    Integer compare;
                    c1 = new StringBuffer();
                    while (data.moveToNext()) {
                        name1 = data.getString(1);
                        date = data.getString(6);
                        String[] d2=date.split("/");
                        o = new Ongoing(name1, date);
                        if (d1[2].compareTo(d2[2])==0 && d1[1].compareTo(d2[1])==0 ) {
                            if(d1[0].compareTo(d2[0])>=0) {
                                oEvents.add(o);
                                c1.append(date + " " + getCurrentDateTime.compareTo(date) + "\n");
                                Toast.makeText(HomePage.this, "value   " + c1, Toast.LENGTH_LONG).show();
                            }

                        }
                        if(d1[2].compareTo(d2[2])==0 && d1[1].compareTo(d2[1])>0){
                                oEvents.add(o);
                                c1.append(date + " " + getCurrentDateTime.compareTo(date) + "\n");
                                Toast.makeText(HomePage.this, "value   " + c1, Toast.LENGTH_LONG).show();

                        }
                        adpt = new homepage_listadapter(HomePage.this, R.layout.homepage_list_adapter, oEvents);
                        listView1.setAdapter(adpt);
                    }

                }

            }

        });

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int p=position;
                TextView nm=(TextView) view.findViewById(R.id.nameE);
                //TextView dt=(TextView) view.findViewById(R.id.nameE);
                String namee=nm.getText().toString();
                //Toast.makeText(getApplicationContext(), "position is: "+position+"eventname: "+namee, Toast.LENGTH_SHORT).show();
                Toast.makeText(HomePage.this, "listview1 id is"+userid, Toast.LENGTH_SHORT).show();
                Intent i= new Intent(getApplicationContext(),EventMoreDetails.class);
                i.putExtra("id",userid);
                i.putExtra("eventname",namee);
                startActivity(i);
            }
        });

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int p=position;
                TextView nm=(TextView) view.findViewById(R.id.nameE);
                String namee=nm.getText().toString();
                //Toast.makeText(getApplicationContext(), "position is: "+position+"eventname: "+namee, Toast.LENGTH_SHORT).show();
                Toast.makeText(HomePage.this, "listview2 id is"+userid, Toast.LENGTH_SHORT).show();
                Intent i= new Intent(getApplicationContext(),EventMoreDetails.class);
                i.putExtra("id",userid);
                i.putExtra("eventname",namee);
                startActivity(i);
            }
        });
   }

    //title bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.options_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        if(item.getItemId()==R.id.myProfile)
        {
            Intent i = new Intent(this,myProfile.class);
            i.putExtra("id",userid);
            i.putExtra("name",name);
            startActivity(i);
        }

        if(item.getItemId()==R.id.logout)
        {
            showRegistersDialog1();
        }


        return super.onOptionsItemSelected(item);
    }


    //logout
    private void showRegistersDialog1()
    {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener()
        {

            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                switch (which)
                {
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        new User(HomePage.this).removeUser();
                        Intent i= new Intent(getApplicationContext(), Login.class);
                        startActivity(i);
                        finish();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do You Want To Logout?").setPositiveButton("Yes", dialogClickListener).setNegativeButton("No", dialogClickListener).show();

    }
}

