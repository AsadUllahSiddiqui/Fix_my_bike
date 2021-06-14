package com.example.semester_project_lvl_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class findWorkshopScreen extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference reference = database.getReference("workshop");
    ImageButton back_Button;
    RecyclerView rv;
    EditText search;
    findworkshop_rv adapter;
    List<workshop> workshops;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_workshop_screen);
        Intent i=getIntent();
        workshops=new ArrayList<>();
        getdata();
        workshops.add(new workshop("Master Service","6th road street 17","03654271953"));
        rv=findViewById(R.id.workshops_rv);
        RecyclerView.LayoutManager lm= new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
         adapter=new findworkshop_rv(workshops,this);
        rv.setAdapter(adapter);
        configure_Button_Back_Button();
        search();
    }
    private void configure_Button_Back_Button(){
        back_Button= findViewById(R.id.Find_workshop_back_button);
        back_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                startActivity(new Intent(findWorkshopScreen.this, Home_Page_User.class));
                finish();
            }
        });
    }

    private void getdata() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    workshop w=new workshop();
                    w=snapshot.getValue(workshop.class);
                    workshops.add(w);

                    // Toast.makeText(findWorkshopScreen.this,dataSnapshot.getChildren().toString(),Toast.LENGTH_SHORT).show();
                    }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void filter(String string) {
        ArrayList<workshop> filteredList = new ArrayList<>();
        for (workshop item : workshops) {
            if (item.getWorkshop_name().toLowerCase().contains(string.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }

    private void search(){
        search = findViewById(R.id.search_bar);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
                adapter.notifyDataSetChanged();

            }
        });
    }

}