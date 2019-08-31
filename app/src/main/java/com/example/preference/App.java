package com.example.preference;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;


public class App extends Application {

    public static final String MY_PREFS_NAME = "MyPrefsFile";


    @Override
    public void onCreate() {
        super.onCreate();

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        boolean isLoged = prefs.getBoolean("login", false);
        Intent intent;

        Log.e("Preference", String.valueOf(isLoged));

        if (isLoged) {
            intent = new Intent(this, LoginActivity.class);
        } else {
            intent = new Intent(this, MainActivity.class);
        }

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

}
