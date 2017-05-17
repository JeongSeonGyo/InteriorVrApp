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

    private EditText IDInput, PWInput;
    private Button signupbtn, loginbtn;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Boolean loginChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NetworkUtil.setNetworkPolicy();

        IDInput = (EditText) findViewById(R.id.ID);
        PWInput = (EditText) findViewById(R.id.Password);
        signupbtn = (Button) findViewById(R.id.Signupbtn);
        loginbtn = (Button) findViewById(R.id.loginbtn);


        //when you click SIGN UP button
        signupbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //go to sign up
                Intent signup = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(signup);

            }
        });

        //when you click LOGIN button
        loginbtn.setOnClickListener(new View.OnClickListener(){

            EditText IDinput = (EditText)findViewById(R.id.ID);
            EditText PWinput = (EditText)findViewById(R.id.Password);
            private String result;


            @Override
            public void onClick(View v){
                try{
                    phpRequestSignup request = new phpRequestSignup("http://13.124.5.176/login.php");
                    result = request.PhP_login_check(String.valueOf(IDInput.getText()),String.valueOf(PWInput.getText()));
                    result = result.substring(0,1);

                    if(result.equals("0")){
                        Toast.makeText(MainActivity.this, "Try Again", Toast.LENGTH_LONG).show();
                        return;
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Ok", Toast.LENGTH_LONG).show();
                        Intent login = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(login);
                    }
                }catch (MalformedURLException e){
                    e.printStackTrace();
                }

                if (IDinput.getText().toString().length() == 0) {
                    Toast.makeText(MainActivity.this, "Enter ID", Toast.LENGTH_LONG).show();
                    IDinput.requestFocus();
                    return;
                }

                else if (PWinput.getText().toString().length() == 0) {
                    Toast.makeText(MainActivity.this, "Enter Password", Toast.LENGTH_LONG).show();
                    PWinput.requestFocus();
                    return;
                }
            }
        });
    }
}