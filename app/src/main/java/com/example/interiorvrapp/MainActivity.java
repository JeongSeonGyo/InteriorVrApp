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

import java.net.MalformedURLException;

public class MainActivity extends AppCompatActivity {

    EditText IDInput, PWInput;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Boolean loginChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        IDInput = (EditText) findViewById(R.id.ID);
        PWInput = (EditText) findViewById(R.id.password);
        Button signupbtn = (Button) findViewById(R.id.Signupbtn);
        Button loginbtn = (Button) findViewById(R.id.loginbtn);

        String id = IDInput.getText().toString();
        String password = PWInput.getText().toString();

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
            private String result;
            private boolean ID_OK=false;
            private boolean PW_OK=false;
            private phpRequestSignup request;

            @Override
            public void onClick(View v){
                try{
                    request = new phpRequestSignup("http://13.124.5.176/login.php");

                }catch (MalformedURLException e){
                    e.printStackTrace();
                }
                if (IDInput.getText().toString().length() == 0) {
                    Toast.makeText(MainActivity.this, "Enter ID", Toast.LENGTH_LONG).show();
                    IDInput.requestFocus();
                    return;
                }
                else ID_OK = true;

                if (PWInput.getText().toString().length() == 0) {
                    Toast.makeText(MainActivity.this, "Enter Password", Toast.LENGTH_LONG).show();
                    PWInput.requestFocus();
                    return;
                }
                else PW_OK = true;

                if(ID_OK == true && PW_OK == true) {
                   // result = request.PhP_ID_check(String.valueOf(IDInput.getText()));
                    result = request.PhP_login_check(String.valueOf(IDInput.getText()),String.valueOf(PWInput.getText()));
                    // result = result.substring(0,1);
                    System.out.println("result " + result);

                    if (result.equals("0")) {
                        Toast.makeText(MainActivity.this, "Try Again", Toast.LENGTH_LONG).show();
                        return;
                    } else {
                        Toast.makeText(MainActivity.this, "Ok", Toast.LENGTH_LONG).show();
                        Intent login = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(login);
                    }
                }

                //go to login complete screen

            }

        });



    }
}