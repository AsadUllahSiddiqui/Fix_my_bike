package com.example.semester_project_lvl_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
//import  android.support.design.widget.NavigationView;
//import com.google.android.material.navigation.NavigationView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Home_Page_User extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference reference = database.getReference("bike");
    RecyclerView rv;
    user_profile_veriable p;
    private StorageReference storageReference;
    private FirebaseAuth auth;
    ImageButton Add_bike_Button,Find_WorkShop_Button,Set_Reminder_Button;
    Button eme;
    Add_bike_rv adapter;
    List<My_Bikes> ls;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageButton nav_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__page__user);
        auth=FirebaseAuth.getInstance();
        eme=findViewById(R.id.Emergency_button);
        p=new user_profile_veriable();
        drawerLayout=findViewById(R.id.drawer_layout_user_home);
        navigationView=findViewById(R.id.nav_view_user_home);
        nav_button=findViewById(R.id.Navigation_Icon_button_user_home);
        ls=new ArrayList<My_Bikes>();
        rv=findViewById(R.id.Add_Bikes_rv);
        recyclerview();
        configure_Button_AddBike();
        configure_Button_FindWorkshop();
        configure_Button_Set_Reminder();
        configure_navigation_button();
        eme_button();
    }
    private void configure_navigation_button(){
        nav_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(home_page_admin.this,"o_yes",Toast.LENGTH_SHORT).show();
                storageReference= FirebaseStorage.getInstance().getReference().child("images/"+p.myData.getId());
                try {
                    final File localfile=File.createTempFile("profile","jpeg");
                    storageReference.getFile(localfile)
                            .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                    Bitmap bitmap= BitmapFactory.decodeFile(localfile.getAbsolutePath());
                                    ((CircleImageView)findViewById(R.id.client_profile)).setImageBitmap(bitmap);
                                    // Toast.makeText(home_page_admin.this,"o_yes",Toast.LENGTH_SHORT).show();
                                }
                            });
                } catch (IOException e) {
                    // Toast.makeText(home_page_admin.this,"o_noo",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                View headerView = navigationView.getHeaderView(0);
                TextView navUsername = (TextView) headerView.findViewById(R.id.client_name);
                navUsername.setText(p.myData.getFirst_name().toString());
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
    }
    private void configure_Button_AddBike(){
        Add_bike_Button= findViewById(R.id.Add_Bike_button);
        Add_bike_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

                startActivity(new Intent(Home_Page_User.this,add_new_bike.class));
            }
        });
    }
    private void configure_Button_FindWorkshop(){
        Find_WorkShop_Button= findViewById(R.id.Find_Workshop_button);
        Find_WorkShop_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                startActivity(new Intent(Home_Page_User.this,findWorkshopScreen.class));
                finish();
            }
        });
    }
    private void configure_Button_Set_Reminder(){
        Set_Reminder_Button= findViewById(R.id.Set_Calender_button);
        Set_Reminder_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

                startActivity(new Intent(Home_Page_User.this,ScheduleService.class));
            }
        });
        setNavigationViewListener();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.user_Profile_id) {
            startActivity(new Intent(Home_Page_User.this,UserProfile.class));
        } else if (id == R.id.user_History_id) {
            startActivity(new Intent(Home_Page_User.this,User_History.class));

        } else if (id == R.id.user_Reminder_id) {
            startActivity(new Intent(Home_Page_User.this,ScheduleService.class));

        } else if (id == R.id.user_Logout_id) {
            auth.signOut();
            startActivity(new Intent(Home_Page_User.this,first_login_signup.class));

        } else if (id == R.id.user_Share_id) {

        } else if (id == R.id.user_Rate_id) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_user_home);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void setNavigationViewListener() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_user_home);
        navigationView.setNavigationItemSelectedListener(this);
    }


    private void recyclerview(){
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ls.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (p.myData.getId().equals(snapshot.getValue(My_Bikes.class).getCustomer_id())
                    ) {
                    My_Bikes m=new My_Bikes();
                    m=snapshot.getValue(My_Bikes.class);
                        ls.add(m);
                       // Toast.makeText(Home_Page_User.this,m.getNumber(),Toast.LENGTH_SHORT).show();
                    }
                }

                rv=findViewById(R.id.Add_Bikes_rv);
                RecyclerView.LayoutManager lm= new LinearLayoutManager(Home_Page_User.this);
                rv.setLayoutManager(lm);
                Add_bike_rv adapter=new Add_bike_rv(ls,Home_Page_User.this);
                rv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });





    }

    private void eme_button(){
        eme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home_Page_User.this, show_accepted_orders.class));
                finish();
            }
        });
    }
}