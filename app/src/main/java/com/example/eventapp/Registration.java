package com.example.eventapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    EditText name,mobile,email,collegeName,pwd;
    Button signup;
    TextView signinText;
    DatabaseHelper db;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        db=new DatabaseHelper(this);

        name=(EditText)findViewById(R.id.name);
        mobile=(EditText)findViewById(R.id.Mobile);
        email=(EditText)findViewById(R.id.email);
        collegeName=(EditText)findViewById(R.id.collegeName);
        pwd=(EditText)findViewById(R.id.pwd);
        signup=(Button)findViewById(R.id.signup);
        signinText=(TextView)findViewById(R.id.signinText);



        signinText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Registration.this,Login.class);
                startActivity(intent);
            }
        });



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean checkFields = validate(new EditText[]{
                        name,email,collegeName,mobile,pwd

                });

                if(checkFields==true){
                    boolean res=db.insertData(name.getText().toString(),email.getText().toString(),collegeName.getText().toString(),mobile.getText().toString(),pwd.getText().toString());
                    if(res== true)
                    {
                        Toast.makeText(Registration.this, "Registration done successfully!!", Toast.LENGTH_LONG).show();
                        Intent intent= new Intent(Registration.this,Login.class);
                        startActivity(intent);

                    }
                    else
                    {
                        Toast.makeText(Registration.this, "Registration not done!!", Toast.LENGTH_LONG).show();
                    }

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Please fill all the details", Toast.LENGTH_LONG).show();
                }


            }

            private boolean validate(EditText[] fields ) {
                for(int i=0; i<fields.length;i++){
                    EditText currentField=fields[i];
                    if(currentField.getText().toString().length()<=0){
                        return false;
                    }
                }
                return true;

            }

        });



    }
}
