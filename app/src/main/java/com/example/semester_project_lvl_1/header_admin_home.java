package com.example.semester_project_lvl_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class header_admin_home extends AppCompatActivity {
    TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.header_admin_home);
        name=findViewById(R.id.www);
        name.setText(workshop_profile_veriable.myData.getEmail());
        Toast.makeText(header_admin_home.this,workshop_profile_veriable.myData.getWorkshop_name(),Toast.LENGTH_SHORT).show();
    }
}