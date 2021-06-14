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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class home_page_admin extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference reference = database.getReference("order");
    private FirebaseAuth auth;
    RecyclerView rv;
    private StorageReference storageReference;
    workshop_profile_veriable p;
    Customer_queue_rv adapter;
    ArrayList<order> ls;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageButton nav_button2, About_Today_Button, History_Button, Manage_Booking_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_admin);
        p=new workshop_profile_veriable();
        auth = FirebaseAuth.getInstance();
        drawerLayout = findViewById(R.id.drawer_layout_Admin_home);
        navigationView = findViewById(R.id.nav_view_admin_home);
        nav_button2 = findViewById(R.id.Navigation_Icon_button_admin_home);
        recyclerview();
        configure_navigation_button();
        configure_Button_AboutToday();
        configure_Button_History();
        setNavigationViewListener();
        configure_Button_Manage_Button();
    }

    private void configure_navigation_button(){
        nav_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(home_page_admin.this,"o_yes",Toast.LENGTH_SHORT).show();
                storageReference= FirebaseStorage.getInstance().getReference().child("images/"+workshop_profile_veriable.myData.getId());
                try {
                    final File localfile=File.createTempFile("profile","jpeg");
                    storageReference.getFile(localfile)
                            .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                    Bitmap bitmap= BitmapFactory.decodeFile(localfile.getAbsolutePath());
                                    ((CircleImageView)findViewById(R.id.workshop_profile)).setImageBitmap(bitmap);
                                   // Toast.makeText(home_page_admin.this,"o_yes",Toast.LENGTH_SHORT).show();
                                }
                            });
                } catch (IOException e) {
                   // Toast.makeText(home_page_admin.this,"o_noo",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                View headerView = navigationView.getHeaderView(0);
                TextView navUsername = (TextView) headerView.findViewById(R.id.www);
                navUsername.setText(p.myData.getWorkshop_name().toString());
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
    }
    private void configure_Button_Manage_Button() {
        Manage_Booking_Button = findViewById(R.id.Manage_button);
        Manage_Booking_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                startActivity(new Intent(home_page_admin.this, ManageBooking.class));
            }
        });
    }

    private void configure_Button_AboutToday() {
        About_Today_Button = findViewById(R.id.About_Today_button);
        About_Today_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

               // Toast.makeText(home_page_admin.this,p.myData.getWorkshop_name().toString(),Toast.LENGTH_SHORT).show();

                startActivity(new Intent(home_page_admin.this, AboutToday.class));
            }
        });
    }

    private void configure_Button_History() {
        History_Button = findViewById(R.id.History_admin_button);
        History_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                startActivity(new Intent(home_page_admin.this, Admin_History.class));
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.Profile_id) {
            startActivity(new Intent(home_page_admin.this, AdminProfile.class));
        } else if (id == R.id.About_today) {
            startActivity(new Intent(home_page_admin.this, AboutToday.class));

        } else if (id == R.id.History_id) {
            startActivity(new Intent(home_page_admin.this, Admin_History.class));

        } else if (id == R.id.Logout_id) {
            workshop w=new workshop();
            p.setMyData(w);
           auth.signOut();
            startActivity(new Intent(home_page_admin.this, first_login_signup.class));
            finish();

        } else if (id == R.id.Share_id) {

        } else if (id == R.id.Rate_id) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_Admin_home);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setNavigationViewListener() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_admin_home);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void recyclerview()
    {
        ls = new ArrayList<order>();
        ls.clear();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ls.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (p.myData.getId().equals(snapshot.getValue(order.class).getWorkshop_id()) && snapshot.getValue(order.class).getCompletion_status().equals("1")&& !snapshot.getValue(order.class).getCompletion_status().equals("done")
                    ) {
                        order t = new order();
                        t = (snapshot.getValue(order.class));
                        ls.add(t);
                    }
                }
                rv = findViewById(R.id.Customer_queue_rv);
                RecyclerView.LayoutManager lm = new LinearLayoutManager(home_page_admin.this);
                rv.setLayoutManager(lm);
                adapter = new Customer_queue_rv(ls, home_page_admin.this);
                rv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
    }
}