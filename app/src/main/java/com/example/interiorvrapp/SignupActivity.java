package com.example.interiorvrapp;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.MalformedURLException;

/**
 * Created by 쿄 on 2017.05.12 0012.
 */

public class SignupActivity extends AppCompatActivity {
    Boolean validation = false;
    private EditText Name, ID, Password;
    private Button joinbtn;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        NetworkUtil.setNetworkPolicy();

        Name = (EditText)findViewById(R.id.newName);
        ID = (EditText)findViewById(R.id.newID);
        Password = (EditText)findViewById(R.id.newPw);


        joinbtn = (Button) findViewById(R.id.join);
        Button cancelbtn = (Button) findViewById(R.id.cancel);

        //when you click join button
        joinbtn.setOnClickListener(new View.OnClickListener(){
            EditText newnameInput = (EditText) findViewById(R.id.newName);
            EditText newidInput = (EditText) findViewById(R.id.newID);
            EditText newpwInput = (EditText) findViewById(R.id.newPw);
            EditText newpwcheckInput = (EditText) findViewById(R.id.newPwCheck);

            @Override
            public void onClick(View v){
                try{
                    phpRequestSignup request = new phpRequestSignup("http://13.124.5.176/insertUser1.php");
                    String result = request.PhPtest(String.valueOf(Name.getText()),String.valueOf(ID.getText()),String.valueOf(Password.getText()));
                    
                }catch (MalformedURLException e){
                    e.printStackTrace();
                }


                if (newnameInput.getText().toString().length() == 0) {
                    Toast.makeText(SignupActivity.this, "Enter Name", Toast.LENGTH_LONG).show();
                    newnameInput.requestFocus();
                    return;
                }
                if (newidInput.getText().toString().length() == 0) {
                    Toast.makeText(SignupActivity.this, "Enter ID", Toast.LENGTH_LONG).show();
                    newidInput.requestFocus();
                    return;
                }

                if (newpwInput.getText().toString().length() == 0) {
                    Toast.makeText(SignupActivity.this, "Enter Password", Toast.LENGTH_LONG).show();
                    newpwInput.requestFocus();
                    return;
                }

                if (newpwcheckInput.getText().toString().length() == 0) {
                    Toast.makeText(SignupActivity.this, "Enter Password Again", Toast.LENGTH_LONG).show();
                    newpwcheckInput.requestFocus();
                    return;
                }

                //Check validation btw pw and pwchekck
                if (!pwvalidation(newpwInput.getText().toString(), newpwcheckInput.getText().toString())) {
                        Toast.makeText(SignupActivity.this, "Retype your password", Toast.LENGTH_LONG).show();
                        newpwcheckInput.setText("");
                        newpwcheckInput.requestFocus();
                        return;
                    } else {
                        //db 저장
                        Toast.makeText(SignupActivity.this, "Confirm", Toast.LENGTH_LONG).show();
                        Toast.makeText(SignupActivity.this, "Complete & Back to Login page", Toast.LENGTH_LONG).show();
                        finish();
                }
            }
        });

        cancelbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Toast.makeText(SignupActivity.this,"Cancel & Back to Login page",Toast.LENGTH_LONG).show();
                finish();
            }
        });


    }

    private boolean pwvalidation(String pw, String pwcheck){
        if(pwcheck.equals(pw)){
            //same as password that user input
            return true;
        }
        else{
            return false;
        }

    }
}
