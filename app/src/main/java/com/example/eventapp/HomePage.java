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
import java.util.List;

public class HomePage extends AppCompatActivity {
    User user;
    int id=0;
    String name;
    Cursor data;
    Button ongoing,upgoing;
    DatabaseHelper db;
    ArrayList<Ongoing> oEvents;
    ListView listView;
    Ongoing o;
       String date,name1;
    StringBuffer c1;
    LinearLayout layout;
    homepage_listadapter adpt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        ongoing=(Button)findViewById(R.id.ongoing);
        upgoing=(Button)findViewById(R.id.upcoming);
       layout=(LinearLayout)findViewById(R.id.linear);
        listView = (ListView)findViewById(R.id.listview1);




        db = new DatabaseHelper(this);
        oEvents= new ArrayList<>();

       //upcoming events
        upgoing.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //layout.removeView(listView);
//                if(!adpt.isEmpty()) {
//                    adpt.clear();
//                    adpt.notifyDataSetChanged();
//                }
//                if(adpt!=null)
//                {
//                    adpt.clear();
//                    adpt.notifyDataSetChanged();
//                    listView.setAdapter(null);
//                }
                //listView.setAdapter(null);
                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String getCurrentDateTime = sdf.format(c.getTime());
                data=db.getEventRecords();
                Integer compare;
                c1=new StringBuffer();
                while(data.moveToNext())

                {
                    name1=data.getString(1);
                    date = data.getString(6);
                    compare = getCurrentDateTime.compareTo(date);
                    if (compare < 0)
                    {
                        o = new Ongoing(name1,date);
                        oEvents.add(o);
                       // c1.append(name1+" "+date+"\n");
                    }

                }

               // Toast.makeText(HomePage.this, c1.toString(), Toast.LENGTH_SHORT).show();
                adpt=new homepage_listadapter(HomePage.this,R.layout.homepage_list_adapter,oEvents);
               listView.setAdapter(null);
               listView.setVisibility(View.GONE);
               listView.setAdapter(adpt);
                listView.setVisibility(View.VISIBLE);


            }
        });

        //ongoing events
        ongoing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get Current Date Time
                //adpt.clear();
                //listView.setAdapter(null);
//                if(adpt!=null)
//                {
//                    adpt.clear();
//                    adpt.notifyDataSetChanged();
//                    listView.setAdapter(null);
//                }
                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String getCurrentDateTime = sdf.format(c.getTime());
                data=db.getEventRecords();
                Integer compare;
                c1=new StringBuffer();
                while(data.moveToNext())

                {
                    name1=data.getString(1);
                    date = data.getString(6);
                    compare = getCurrentDateTime.compareTo(date);
                    if (compare >= 0)
                    {
                        o = new Ongoing(name1,date);
                        oEvents.add(o);
                        c1.append(name1+" "+date+"\n");
                    }

                }

                Toast.makeText(HomePage.this, c1.toString(), Toast.LENGTH_SHORT).show();
                adpt=new homepage_listadapter(HomePage.this,R.layout.homepage_list_adapter,oEvents);
                listView.setAdapter(adpt);
                ongoing.setEnabled(false);
                upgoing.setEnabled(true);


            }
        });

        //session
        user=new User(HomePage.this);
        id=getIntent().getExtras().getInt("id");
        name=getIntent().getExtras().getString("name");


    }

//title bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.options_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.myProfile)
        {
            Intent i = new Intent(this,myProfile.class);
            i.putExtra("id",id);
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
    private void showRegistersDialog1() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
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
        builder.setMessage("Do You Want To Logout?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();

    }
}

