package com.example.summit.women_safety;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class login_a extends AppCompatActivity implements LocationListener {

    Button getLocationBtn  , send_button, logout;
    TextView locationText;
    LocationManager locationManager;
    UserSessionManager userSessionManager;
    int clickCount = 0;
    long startTime;
    long duration;
    static final int MAX_DURATION = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login_a);

        getLocationBtn = (Button)findViewById(R.id.getLocation);
        locationText = (TextView)findViewById(R.id.text_view2);
        send_button = findViewById(R.id.send_button);
        logout=findViewById(R.id.logout);
        userSessionManager=new UserSessionManager(getApplicationContext());

    send_button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
            Toast.makeText(getApplicationContext(),"Sending your location",Toast.LENGTH_SHORT).show();
            sendSMS("9939323908", "Hi! There is an emergency");
        }
    });

        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                userSessionManager.logoutUser();
            }
        });

        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }


        getLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation();
            }
        });


    }

    void getLocation() {
        try
        {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        locationText.setText("Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude());

        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            locationText.setText(locationText.getText() + "\n"+addresses.get(0).getAddressLine(0));
        }catch(Exception e)
        {

        }

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(login_a.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider)
    {

    }


    public boolean onKeyDown(int keyDown , KeyEvent event)
    {
        if(event.getKeyCode()==KeyEvent.KEYCODE_POWER)
        {
            sendSMS("9939323908",String.valueOf(locationText));
        }
        return false;
    }

    private void sendSMS(String phoneNumber, String message)
    {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("9939323908", null, String.valueOf(locationText), null, null);
    }

}