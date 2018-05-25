package com.example.summit.women_safety;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class signUp extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sign_up);
    }

    public void signUp(View view)
    {
        EditText editText1 = findViewById(R.id.e1);
        EditText editText2 = findViewById(R.id.e2);
        EditText editText3 = findViewById(R.id.e3);
        EditText editText4 = findViewById(R.id.e4);
        EditText editText5 = findViewById(R.id.e5);
        EditText editText6 = findViewById(R.id.e6);
        EditText editText7 = findViewById(R.id.e7);
        EditText editText8 = findViewById(R.id.e8);


        String name=editText1.getText().toString();
        String username=editText2.getText().toString();
        String email=editText3.getText().toString();
        String phone=editText4.getText().toString();
        String password=editText5.getText().toString();
        String phone1=editText6.getText().toString();
        String phone2=editText7.getText().toString();
        String phone3=editText8.getText().toString();

        MyDb myDb=new MyDb(getApplicationContext());
        long count=myDb.insert_db(name,username,email,phone,password);

        if(count>0)
        {
            Toast.makeText(getApplicationContext(),"SignUp Successful",Toast.LENGTH_LONG).show();
            Intent intent= new Intent(getApplicationContext(),welcome.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"SignUp UnSuccessful",Toast.LENGTH_LONG).show();
        }


    }
}
