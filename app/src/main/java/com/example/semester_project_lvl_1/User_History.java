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

public class User_History extends AppCompatActivity {
    user_profile_veriable p=new user_profile_veriable();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference reference = database.getReference("order");
    RecyclerView rv;
    ImageButton back_Button;
    List<order> usershist;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__history);
        Intent i=getIntent();
        usershist=new ArrayList<>();
        recyclerview();

        configure_Button_Back_Button();
    }
    private void configure_Button_Back_Button(){
        back_Button= findViewById(R.id.user_history_back_Button);
        back_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                finish();
            }
        });
    }


    private void recyclerview(){
        usershist=new ArrayList<>();


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usershist.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (p.myData.getId().equals(snapshot.getValue(order.class).getCustomer_id()) && snapshot.getValue(order.class).getCompletion_status().equals("done")
                    ) {
                        order t = new order();
                        t = (snapshot.getValue(order.class));
                        usershist.add(t);
                        Toast.makeText(User_History.this," Order approved!",Toast.LENGTH_SHORT).show();
                    }
                }
                rv=findViewById(R.id.userHistory_rv);
                RecyclerView.LayoutManager lm= new LinearLayoutManager(User_History.this);
                rv.setLayoutManager(lm);
                User_History_rv adapter=new User_History_rv(usershist,User_History.this);
                rv.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });




    }
}