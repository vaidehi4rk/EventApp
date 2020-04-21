package com.example.eventapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView Splash,btnwelcome;
    View bgpprogress,bgpprogresstop;;
    Animation imgpage,ltr,btntwo,btnthree;
    User user;

    //private static int SPLASH_SCREEN_TIME_OUT=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //This method is used so that your splash activity
        //can cover the entire screen.

        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(MainActivity.this,
                        AdminHome.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_SCREEN_TIME_OUT);
*/


        //binding
        Splash =(TextView)findViewById(R.id.splash);
        btnwelcome=(TextView)findViewById(R.id.btnwelcome);
        bgpprogress=(View)findViewById(R.id.bgpprogress);
        bgpprogresstop=(View)findViewById(R.id.bgpprogresstop);



        //load animation
        imgpage= AnimationUtils.loadAnimation(this,R.anim.imgpage);
        ltr= AnimationUtils.loadAnimation(this,R.anim.ltr);
        btntwo= AnimationUtils.loadAnimation(this,R.anim.bttwo);
        btnthree= AnimationUtils.loadAnimation(this,R.anim.btnthree);


        //start animation
        Splash.startAnimation(imgpage);
        btnwelcome.startAnimation(btnthree);
        bgpprogress.startAnimation(btntwo);
        bgpprogresstop.startAnimation(ltr);


        btnwelcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent= new Intent(MainActivity.this, Login.class);
                //startActivity(intent);

                if(user.getId()!=0  && user.getName() !="")
                {
                    Intent i= new Intent(MainActivity.this, HomePage.class);
                    i.putExtra("name",user.getName());
                    i.putExtra("id",user.getId());
                    startActivity(i);
                    finish();
                }

                else
                {
                    Intent i=new Intent(getApplicationContext(),Login.class);
                    startActivity(i);
                    finish();
                }
            }

            /*public  void run()
                {
                    if(user.getId()!=0)
                    {
                        Intent intent= new Intent(MainActivity.this, HomePage.class);
                        startActivity(intent);
                    }

                    else
                    {
                        Intent i=new Intent(getApplicationContext(),Login.class);
                        startActivity(i);
                        finish();
                    }

                }
*/

        });
        user= new User(MainActivity.this);



    }




}
