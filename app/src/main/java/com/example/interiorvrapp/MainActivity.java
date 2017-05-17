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

//        String id = ID.getText().toString();
//        String password = Password.getText().toString();

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

            private boolean ID_OK=false;
            private boolean PW_OK=false;
            EditText IDinput = (EditText)findViewById(R.id.ID);
            EditText PWinput = (EditText)findViewById(R.id.Password);
            public String result, equal;


            @Override
            public void onClick(View v){
                try{
                    phpRequestSignup request = new phpRequestSignup("http://13.124.5.176/login.php");
                    result = request.PhP_login_check(String.valueOf(IDInput.getText()),String.valueOf(PWInput.getText()));
                    //System.out.println(result);
                }catch (MalformedURLException e){
                    e.printStackTrace();
                }

                if (IDinput.getText().toString().length() == 0) {
                    Toast.makeText(MainActivity.this, "Enter ID", Toast.LENGTH_LONG).show();
                    IDinput.requestFocus();
                    return;
                }
                //else ID_OK = true;
                else if (PWinput.getText().toString().length() == 0) {
                    Toast.makeText(MainActivity.this, "Enter Password", Toast.LENGTH_LONG).show();
                    PWinput.requestFocus();
                    return;
                }
                //else PW_OK = true;

                else if(IDinput.getText().toString().length() != 0 && PWinput.getText().toString().length() != 0) {

                    System.out.println(result);
                   // boolean com, com1;
                   // com = result.equals("failure");
                   // int i = Integer.parseInt(result);
                    // System.out.println(i);
                   // System.out.println(com);

                    if (result == ("0")) {
                        Toast.makeText(MainActivity.this, "Try Again", Toast.LENGTH_LONG).show();
                        return;

                    }
                    else if(result.equals("0")){
                        Toast.makeText(MainActivity.this, "Try Again", Toast.LENGTH_LONG).show();
                        return;
                    }
                    else {
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