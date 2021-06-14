package com.example.semester_project_lvl_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class signup_options extends AppCompatActivity {
    Button customer,owner;
    String text1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_options);
        Intent intent=getIntent();
        text1=intent.getStringExtra(first_login_signup.EXTRA_TEXT1);

        configure_Button_SignUp_customer();
        configure_Button_SignUp_owner();
    }
    private void configure_Button_SignUp_customer(){
        customer= findViewById(R.id.signUp_user);
        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                if(text1.equals("1")){
                    startActivity(new Intent(signup_options.this,login.class));
                }
                else {
                    startActivity(new Intent(signup_options.this, SignUp_user.class));
                }
            }
        });
    }
    private void configure_Button_SignUp_owner(){
        owner= findViewById(R.id.SignUp_admin);
        owner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

                if(text1.equals("1")){
                    startActivity(new Intent(signup_options.this,login_admin.class));
                }
                else {
                    startActivity(new Intent(signup_options.this, SignUp_Admin.class));
                }
            }
        });
    }
}