package com.example.ritu.project404.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.ritu.project404.Activity.LoginActivity;

import java.util.HashMap;

/**
 * Created by ritu on 1/11/2018.
 */

public class Userloginsession  {
    Context _context;
    public static final String Is_Sno = "sno";
    public static final String Is_User_Login = "isuserloggedin";
    public static final String Is_Username = "userloginname";
    public static final String Is_email = "email";

    static SharedPreferences user_details;
    SharedPreferences.Editor user_details_editor;

    public Userloginsession(){

    }

    public Userloginsession(final Context applicationContext) {
        user_details = applicationContext.getSharedPreferences("usersession", 0);
        user_details_editor = user_details.edit();
        user_details_editor.apply();
    }

    public static boolean isuserLoggedIn() {
        return user_details.getBoolean(Is_User_Login, false);
    }

    public void createuserLogin(String username, String sno, String email,boolean checklogin) {
        user_details_editor.putBoolean(Is_User_Login, checklogin);
        user_details_editor.putString(Is_Username, username);
        user_details_editor.putString(Is_Sno, sno);
        user_details_editor.putString(Is_email, email);
        user_details_editor.commit();
    }

    public HashMap<String, String> isGetuserDetails() {
        // i am using hashmap to store user credentials
        final HashMap<String, String> userdetailsmap = new HashMap<>();
        userdetailsmap.put(Is_Username, user_details.getString(Is_Username, null));
        userdetailsmap.put(Is_email, user_details.getString(Is_email, null));
        userdetailsmap.put(Is_Sno, user_details.getString(Is_Sno, null));
        return userdetailsmap;
    }

    public void logoutUser(){
        user_details_editor = user_details.edit();
        user_details_editor.clear();
        user_details_editor.apply();
        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }
    public void checkLogin(){
        // Check login status
        if(!this.isuserLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }
}
