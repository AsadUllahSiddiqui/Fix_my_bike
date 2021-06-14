package com.example.semester_project_lvl_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
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

public class ManageBooking extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference nrreference = database.getReference("order");
    workshop_profile_veriable p=new workshop_profile_veriable();
    ImageButton back_Button;
    RecyclerView rv;
    List<order> order_List=new ArrayList<>();
    order_rv adapter;
    order o;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_booking);
        configure_Button_Back_Button();
        o=new order();
        getallorders();

    }
    private void configure_Button_Back_Button(){
        back_Button= findViewById(R.id.Manage_Booking_Back_Button);
        back_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                finish();
            }
        });
    }

    private void getallorders() {
        order_List.clear();
        nrreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                order_List.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (p.myData.getId().equals(snapshot.getValue(order.class).getWorkshop_id()) && !snapshot.getValue(order.class).getCompletion_status().equals("1")&& !snapshot.getValue(order.class).getCompletion_status().equals("done")
                    )
                    {
                       order t=new order();
                       t=(snapshot.getValue(order.class));
                        order_List.add(t);
                    }
                }

                rv=findViewById(R.id.order_recycler_view);
                RecyclerView.LayoutManager lm= new LinearLayoutManager(ManageBooking.this);
                rv.setLayoutManager(lm);
                adapter =new order_rv(order_List,ManageBooking.this);
                rv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}