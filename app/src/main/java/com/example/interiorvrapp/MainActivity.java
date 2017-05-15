package com.example.interiorvrapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText idInput, passwordInput;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Boolean loginChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idInput = (EditText) findViewById(R.id.ID);
        passwordInput = (EditText) findViewById(R.id.password);
        Button signupbtn = (Button) findViewById(R.id.Signupbtn);
        Button loginbtn = (Button) findViewById(R.id.loginbtn);

        String id = idInput.getText().toString();
        String password = passwordInput.getText().toString();

        //when you click sign up button
        signupbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //go to sign up
                Intent signup = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(signup);

            }
        });

        //when you click login
        loginbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //go to vr
            }

        });



    }
}