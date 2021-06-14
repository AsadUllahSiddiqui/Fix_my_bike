package com.example.semester_project_lvl_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ScheduleService extends AppCompatActivity {
    ImageButton back_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_service);
        configure_Button_Back_Button();
    }
    private void configure_Button_Back_Button(){
        back_Button= findViewById(R.id.schedule_service_back_Button);
        back_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                finish();
            }
        });
    }
}