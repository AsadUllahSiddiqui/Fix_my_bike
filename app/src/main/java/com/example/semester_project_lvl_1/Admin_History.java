package com.example.semester_project_lvl_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Admin_History extends AppCompatActivity {
    workshop_profile_veriable p=new workshop_profile_veriable();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference reference = database.getReference("order");

    ImageButton back_Button;
    RecyclerView rv;
    List<order> adminshist;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__history);
        Intent i=getIntent();
        recyclerview();
        configure_Button_Back_Button();
    }
    private void configure_Button_Back_Button(){
        back_Button= findViewById(R.id.admin_history_back_button);
        back_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                finish();
            }
        });
    }
    private void recyclerview(){
        adminshist=new ArrayList<>();


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                adminshist.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (p.myData.getId().equals(snapshot.getValue(order.class).getWorkshop_id()) && snapshot.getValue(order.class).getCompletion_status().equals("done")
                    ) {
                        order t = new order();
                        t = (snapshot.getValue(order.class));
                        adminshist.add(t);
                        Toast.makeText(Admin_History.this," Order approved!",Toast.LENGTH_SHORT).show();
                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

        rv=findViewById(R.id.adminHistory_rv);
        RecyclerView.LayoutManager lm= new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        Admin_History_rv adapter=new Admin_History_rv(adminshist,Admin_History.this);
        rv.setAdapter(adapter);



    }
}