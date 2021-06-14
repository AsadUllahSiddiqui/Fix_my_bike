package com.example.semester_project_lvl_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class AboutToday extends AppCompatActivity {
    ImageButton back_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_today);
        configure_Button_Back_Button();
    }
    private void configure_Button_Back_Button(){
        back_Button= findViewById(R.id.About_today_back_Button);
        back_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                finish();
            }
        });
    }
}