package com.example.semester_project_lvl_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class first_login_signup extends AppCompatActivity {
    Button login;
    Button signup;
    public static final String EXTRA_TEXT1="key1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_login_signup_activity);
        configure_Button_Login();
        configure_Button_SignUp();
    }

    private void configure_Button_Login() {
        login = findViewById(R.id.Login_Button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent i = new Intent(first_login_signup.this, signup_options.class);
                i.putExtra(EXTRA_TEXT1, "1");
                startActivity(i);
                finish();
            }
        });
    }

    private void configure_Button_SignUp() {
        signup = findViewById(R.id.SignUp_Button);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

                Intent i=new Intent(first_login_signup.this,signup_options.class);
                i.putExtra(EXTRA_TEXT1, "0");
                startActivity(i);
                finish();
            }
        });
    }
}
