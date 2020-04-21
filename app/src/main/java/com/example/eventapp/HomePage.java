package com.example.eventapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class HomePage extends AppCompatActivity {

    TextView welcome,uname,logout;
    User user;
    int id=0;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);





        welcome=(TextView) findViewById(R.id.name);
        uname=(TextView)findViewById(R.id.username);
        logout=(TextView)findViewById(R.id.logout);

        //session
        user=new User(HomePage.this);
        id=getIntent().getExtras().getInt("id");
        name=getIntent().getExtras().getString("name");


        uname.setText(name);



    }

    /*public void logout(View view) {
        new User(HomePage.this).removeUser();
        Intent i= new Intent(getApplicationContext(), Login.class);
        startActivity(i);
        finish();
    }*/

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

