package com.example.summit.women_safety;

import android.content.Intent;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by Summit on 2/21/2018.
 */

public class UserSessionManager
{
    SharedPreferences pref;

    // Editor reference for Shared preferences
    Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREFER_NAME = "AndroidExamplePref";

    // All Shared Preferences Keys
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";

    // User name (make variable public to access from outside)


    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";

    public static final String KEY_PASSWORD = "password";

    // Constructor
    public UserSessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    //Create login session
    public void createUserLoginSession(String email, String password){
        // Storing login value as TRUE
        editor.putBoolean(IS_USER_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_EMAIL, email);

        // Storing email in pref
        editor.putString(KEY_PASSWORD, password);

        // commit changes
        editor.commit();
    }

    /**
     * Check login method will check user login status
     * If false it will redirect user to login page
     * Else do anything
     * */
    public boolean checkLogin(){
        // Check login status
        if(!this.isUserLoggedIn()){

            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, welcome.class);

            // Closing all the Activities from stack
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);

            return true;
        }
        return false;
    }




    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){

        //Use hashmap to store user credentials
        HashMap<String, String> user = new HashMap<String, String>();

        // user name
        user.put(KEY_PASSWORD, pref.getString(KEY_PASSWORD, null));

        // user email id
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));

        // return user
        return user;
    }

    public boolean isUserLoggedIn()
    {
        return pref.getBoolean(IS_USER_LOGIN, false);
    }

    public void logoutUser()
    {

        editor.clear();
        editor.commit();

        // After logout redirect user to Login Activity
        Intent i = new Intent(_context, women_a.class);

        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);

    }
}
