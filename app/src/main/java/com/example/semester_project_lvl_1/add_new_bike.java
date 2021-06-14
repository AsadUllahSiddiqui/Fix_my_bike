package com.example.semester_project_lvl_1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_new_bike extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference reference = database.getReference("bike");
    EditText company,model,bikeno,bikeyear;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_bike);
        Intent intent = getIntent();
        company = findViewById(R.id.company);
        model = findViewById(R.id.model);
        bikeno = findViewById(R.id.bikeNumber);
        bikeyear = findViewById(R.id.bikeYear);
        save = findViewById(R.id.Add_Bike);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                My_Bikes bik=new My_Bikes();
                bik.setCompany(company.getText().toString());
                bik.setCustomer_id(user_profile_veriable.getMyData().getId());
                bik.setModel(model.getText().toString());
                bik.setNumber(bikeno.getText().toString());
                reference.push().setValue(bik);
                startActivity(new Intent(add_new_bike.this, Home_Page_User.class));
                finish();

            }
        });



    }
}

