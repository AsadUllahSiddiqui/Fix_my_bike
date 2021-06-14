package com.example.semester_project_lvl_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class select_bike extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference reference = database.getReference("bike");
    RecyclerView rv;
    List<bike> ls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_bike);
        ls=new ArrayList<bike>();
        rv=findViewById(R.id.bike_rv);
       // RecyclerView.LayoutManager lm= new LinearLayoutManager(this);
        //rv.setLayoutManager(lm);
       // adapter=new bike_selection_rv(ls,this);
        //rv.setAdapter(adapter);
        recyclerview();
    }

    private void recyclerview(){
        ls=new ArrayList<>();


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ls.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (user_profile_veriable.myData.getId().equals(snapshot.getValue(My_Bikes.class).getCustomer_id())
                    ) {
                        ls.add(snapshot.getValue(bike.class));
                       // Toast.makeText(select_bike.this," Order approved!",Toast.LENGTH_SHORT).show();
                    }
                }

                rv=findViewById(R.id.bike_rv);
                RecyclerView.LayoutManager lm= new LinearLayoutManager(select_bike.this);
                rv.setLayoutManager(lm);
                bike_selection_rv adapter= new bike_selection_rv(ls,select_bike.this);
                rv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });





    }
}