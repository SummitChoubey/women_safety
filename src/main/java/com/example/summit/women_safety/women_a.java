package com.example.summit.women_safety;

import java.util.HashMap;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class women_a extends AppCompatActivity {
    UserSessionManager session;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_women_a);

        // Session class instance
        session = new UserSessionManager(getApplicationContext());



        Toast.makeText(getApplicationContext(),
                "User Login Status: " + session.isUserLoggedIn(),
                Toast.LENGTH_LONG).show();


        if(session.isUserLoggedIn()==true)
        {
            Intent intent=new Intent(getApplicationContext(),login_a.class);
            startActivity(intent);
        }
    }


    public void signUp(View view)
    {
        Intent intent = new Intent(getApplicationContext(),signUp.class);
        startActivity(intent);
    }


    public void logIn(View view)
    {
        Intent intent = new Intent(getApplicationContext(),welcome.class);
        startActivity(intent);
    }
}
