package com.example.eventapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
    String semail,spassword;
    String partEmailID,partDate;
    Integer userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_more_details);

        db=new DatabaseHelper(this);



        //session
        user=new User(EventMoreDetails.this);
        id=getIntent().getExtras().getInt("id");
        ename=getIntent().getExtras().getString("eventname");
        //edited
//        //Intent i = getIntent();
//        String abc=getIntent().getExtras().getString("OVER");
//        String xyz="over";

        details_name=(TextView)findViewById(R.id.details_name);
        details_desc=(TextView)findViewById(R.id.details_desc);
        details_poc=(TextView)findViewById(R.id.details_poc);
        details_poc_mob=(TextView)findViewById(R.id.details_poc_mob);
        details_date=(TextView)findViewById(R.id.details_date);
        details_location=(TextView)findViewById(R.id.details_location);
        details_time=(TextView)findViewById(R.id.details_time);
        details_fee=(TextView)findViewById(R.id.details_fee);
        participate=(Button)findViewById(R.id.participate);


        //sender email credentials
        semail="vaidehi4kulkarni@gmail.com";
        spassword="vaidehi4kulkarni@1991";

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
               // Cursor records=db.showHistory(id);
                //partEmailID=records.getString(3);
              //  partDate=records.getString(2);
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
                        //mail coding
                        Properties properties=new Properties();
                        properties.put("mail.smtp.auth","true");
                        properties.put("mail.smtp.starttls.enable","true");
                        properties.put("mail.smtp.host","smtp.gmail.com");
                        properties.put("mail.smtp.port","587");

                        //Initialize session
                        Session session=Session.getInstance(properties, new Authenticator() {
                            @Override
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return  new PasswordAuthentication(semail,spassword);

                            }
                        });
                        record=db.getEventRecords();
                        while(record.moveToNext()) {
                            evname = record.getString(1);
                            if (ename.equals(evname)) {

                               partDate=record.getString(6);
                            }
                        }
                        Cursor myrecords;
                        myrecords=db.getUserRecords();
                        while(myrecords.moveToNext())
                        {
                             userid= Integer.parseInt(myrecords.getString(0));
                            if(id == userid)
                            {

                                partEmailID= myrecords.getString(2);

                            }

                        }
                        try {

                            //Initialize email content
                            Message message= new MimeMessage(session);
                            //Sender Email
                            message.setFrom(new InternetAddress(semail));
                            //Receipent email
                            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(partEmailID.trim()));
                            //Email Subject
                            message.setSubject("SIMSR Event Particpation Details");
                            //Email Message

                            message.setText("You have partipated in "+evname+" Event dated on  "+partDate+"  In KJSIMSR \n Kindly be present at the location 15 minutes before .\n You will " +
                                    "  have to pay the entry fee at the college location itself. \n Please carry your respective College ID cards with you.\n Thanks! \n Regards, KJSIMSR ");
                            //send email
                            new SendEmail().execute(message);

                        } catch (MessagingException e) {
                            e.printStackTrace();
                        }


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

    private class SendEmail  extends AsyncTask<Message,String,String> {
        //Initialize Progress bar
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Create and show dialog
            progressDialog=ProgressDialog.show(EventMoreDetails.this,"Please Wait","Confirming Participation",true,false);

        }

        @Override
        protected String doInBackground(Message... messages) {
            try {
                //Wehn Success
                Transport.send(messages[0]);
                return  "Success";
            } catch (MessagingException e) {
                //When Error
                e.printStackTrace();
                return  "Error";
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Dismiss Progress Dialog Box
            progressDialog.dismiss();
            if(s.equals("Success"))
            {
                AlertDialog.Builder builder=new AlertDialog.Builder(EventMoreDetails.this);
                builder.setCancelable(false);
                builder.setMessage(Html.fromHtml("<font color='#509324'>Participation Confirmed</font>"));
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();

            }else {
                Toast.makeText(EventMoreDetails.this, "Something Went Wrong?", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
