package com.example.eventapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {


    EditText username,password;
    Button btnLogin;
    TextView signuptext;
    DatabaseHelper db;
    Cursor records;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db=new DatabaseHelper(this);

        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        btnLogin=(Button)findViewById(R.id.btnLogin);
        signuptext=(TextView)findViewById(R.id.signuptext);

       signuptext.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v)
           {
               Intent intent= new Intent(Login.this,Registration.class);
               startActivity(intent);
           }
       });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(username.getText().toString().equals("Admin") && password.getText().toString().equals("admin"))
                {
                    Toast.makeText(getApplicationContext(),"Login Successfully!",Toast.LENGTH_LONG).show();
                    Intent intent= new Intent(Login.this,AdminHome.class);
                    startActivity(intent);
                }
                else
                {
                    records = db.getRecords();
                    int flag=0;
                    int id=0;

                    while (records.moveToNext()) {
                        id=Integer.parseInt(records.getString(0));
                        String u = records.getString(1);
                        String p = records.getString(2);
                        if (username.getText().toString().equals(u) && password.getText().toString().equals(p)) {
                            flag = 1;

                            //session variables
                            User user=new User(Login.this);
                            user.setId(id);
                            user.setName(u);
                            Intent i = new Intent(getApplicationContext(), HomePage.class);
                            i.putExtra("id",id);
                            i.putExtra("name",u);
                            startActivity(i);
                        }
                    }
                    if (flag == 1) {

                        Toast.makeText(getApplicationContext(), " LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Username or password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                }

                    /*String uname=username.getText().toString();
                    String pwd=password.getText().toString();
                    Boolean result= db.logincheck(uname,pwd);
                    if(result==true)
                    {
                        Toast.makeText(getApplicationContext(),"Login Successfully!",Toast.LENGTH_LONG).show();
                        Intent intent= new Intent(Login.this,HomePage.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Wrong Email or Password! Please Try Again!",Toast.LENGTH_LONG).show();
                    }*/
                }


            });
        }

}
