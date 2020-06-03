package com.example.eventapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class myProfile extends AppCompatActivity {

    EditText uName,uEmail,uCollege,uMobile,uPass;
    Button updateBtn;
    LinearLayout linearLayout;
    Cursor rec;
    DatabaseHelper db;
    Integer userid;
    String name;
    TextView Goback;
    //Integer id;
    //public  static int id=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);



        //bindings
        uName=(EditText)findViewById(R.id.uName);
        uEmail=(EditText)findViewById(R.id.uEmail);
        uCollege=(EditText)findViewById(R.id.uCollege);
        uMobile=(EditText)findViewById(R.id.uMobile);
        uPass=(EditText)findViewById(R.id.uPass);
        updateBtn=(Button)findViewById(R.id.updateBtn);
       linearLayout=(LinearLayout)findViewById(R.id.contentPanel);
       Goback=(TextView)findViewById(R.id.Goback);


        //session

        final User user=new User(myProfile.this);
        final int id=getIntent().getExtras().getInt("id");
        name=getIntent().getExtras().getString("name");

        //databasehelper
        db = new DatabaseHelper(this);

        rec=db.getUserRecords();
        while(rec.moveToNext())
        {
            userid= Integer.parseInt(rec.getString(0));
            if(id == userid)
            {
                uName.setText(rec.getString(1));
                uEmail.setText(rec.getString(2));
                uCollege.setText(rec.getString(3));
                uMobile.setText(rec.getString(4));
                uPass.setText(rec.getString(5));
            }

        }

        //Disabled Edit Text before updating
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            View view = linearLayout.getChildAt(i);
            if (view instanceof EditText)
            {
                EditText editText = (EditText) view;
                editText.setEnabled(false);
            }
        }

        //Update Details on Button Click
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                //Enabling Edit texts to write
                for (int i = 0; i < linearLayout.getChildCount(); i++)
                {
                    View view = linearLayout.getChildAt(i);
                    if (view instanceof EditText) {
                        EditText editText = (EditText) view;
                        editText.setEnabled(true);
                    }
                }

                if(updateBtn.getText().equals("Edit"))
                {
                        updateBtn.setText("Update");
                }


                else if(updateBtn.getText().equals("Update"))

                {

                    //validating if user keeps the text field empty
                    boolean checkFields = validate(new EditText[]
                            {
                                uName,uEmail,uCollege,uMobile,uPass
                            });
                    if (checkFields == true)
                    {
                        boolean res = db.updateUser(id, uName.getText().toString(), uEmail.getText().toString(), uCollege.getText().toString(), uMobile.getText().toString(), uPass.getText().toString());
                        if (res == true)
                        {
                            Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else
                            {
                                Toast.makeText(getApplicationContext(), "Update Unsuccessful", Toast.LENGTH_SHORT).show();
                            }
                        updateBtn.setText("Edit");
                        for (int i = 0; i < linearLayout.getChildCount(); i++) {
                            View view = linearLayout.getChildAt(i);
                            if (view instanceof EditText)
                            {
                                EditText editText = (EditText) view;
                                editText.setEnabled(false);
                            }
                        }
                    }

                    else
                        {
                            Toast.makeText(getApplicationContext(), "Please fill all the details.", Toast.LENGTH_SHORT).show();
                        }
                }

            }
            private boolean validate(EditText[] editTexts)
            {
                for (int i = 0; i < editTexts.length; i++)
                {
                    EditText currentField = editTexts[i];
                    if (currentField.getText().toString().length() <= 0)
                    {
                        return false;
                    }
                }
                return true;
            }
        });

        Goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),FinalParticipation.class);
                i.putExtra("id",id);
                i.putExtra("name",name);
                startActivity(i);
            }
        });


    }
   
}
