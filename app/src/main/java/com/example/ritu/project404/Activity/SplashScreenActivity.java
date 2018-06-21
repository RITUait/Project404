package com.example.ritu.project404.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;

import com.example.ritu.project404.R;
import com.example.ritu.project404.util.Userloginsession;

/**
 * Created by ritu on 1/11/2018.
 */

public class SplashScreenActivity extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final EditText setname , setemail;
        final Userloginsession login = new Userloginsession(getApplicationContext());


        new Handler().postDelayed(new Runnable() {

			/*
			 * Showing splash screen with a timer. This will be useful when you
			 * want to show case your app logo / company
			 */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity

                if(login.isuserLoggedIn()){
                Intent i = new Intent(SplashScreenActivity.this, Dashboard.class);
                startActivity(i);

                // close this activity
                finish();
            }else{
                    startActivity(new Intent(SplashScreenActivity.this,LoginActivity.class));
                    finish();
                }
            }
        }, SPLASH_TIME_OUT);
    }

}
