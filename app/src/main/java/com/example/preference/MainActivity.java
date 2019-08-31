package com.example.preference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    private EditText editText_username;
    private EditText editText_mail;
    private Button btn_save;
    private Button btn_load;
    private Button btn_login;
    private Button btn_clear;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        onClick();


    }


    public void findView()
    {
        editText_username=findViewById(R.id.id_username);
        editText_mail=findViewById(R.id.id_email);
        btn_save=findViewById(R.id.id_save);
        btn_load=findViewById(R.id.id_load);
        btn_login=findViewById(R.id.id_login);
        btn_clear=findViewById(R.id.id_clear);
    }

    public void onClick()
    {
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usrname=editText_username.getText().toString();
                String mail=editText_mail.getText().toString();
                saveText(usrname,mail);
            }
        });

        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText_mail.setText(loadMail());
                editText_username.setText(loadUsername());
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putBoolean("login", true);
                editor.commit();
                Log.d("AAA","Login 1");
                Intent intent=new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearSharedPref();
            }
        });
    }


    public void saveText(String username, String mail) {
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("username", username);
        editor.putString("mail",mail);
        editor.apply();

    }

    public String loadUsername() {
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        //prefs = getSharedPreferences(MY_PREFS_NAME,MODE_PRIVATE);
        return prefs.getString("username", "");

    }

    public String loadMail() {
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        //prefs = getSharedPreferences(MY_PREFS_NAME,MODE_PRIVATE);
        return prefs.getString("mail", "");

    }

    public void clearSharedPref(){
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();
        editText_username.setText("");
        editText_mail.setText("");

    }

}
