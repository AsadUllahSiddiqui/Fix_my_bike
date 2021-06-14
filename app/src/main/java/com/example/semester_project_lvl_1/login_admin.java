package com.example.semester_project_lvl_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login_admin extends AppCompatActivity {
    Button login;

   workshop_profile_veriable p = new workshop_profile_veriable();
    EditText email, password;
    FirebaseUser firebaseuser;
    private FirebaseAuth auth;
    workshop Profile;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference reference = database.getReference("workshop");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_admin);

        email = findViewById(R.id.admin_login_email);
        password = findViewById(R.id.admin_login_password);
        auth = FirebaseAuth.getInstance();
        firebaseuser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseuser != null) {
            workshop a = new workshop(firebaseuser.getEmail(), firebaseuser.getUid());
            p.setMyData(a);
            getProfile();
            Intent i = new Intent(login_admin.this, home_page_admin.class);
            startActivity(i);
            finish();
        }
        configure_login_button();
    }

    private void configure_login_button() {
        login = findViewById(R.id.admin_Login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                final String email_text = email.getText().toString();
                String password_text = password.getText().toString();
                //checking firebase
                if (TextUtils.isEmpty(email_text) || TextUtils.isEmpty(password_text)) {
                    Toast.makeText(login_admin.this, "Please enter email and password",
                            Toast.LENGTH_SHORT).show();
                } else {
                    auth.signInWithEmailAndPassword(email_text, password_text)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        firebaseuser = FirebaseAuth.getInstance().getCurrentUser();
                                        workshop a = new workshop(firebaseuser.getEmail(), firebaseuser.getUid());
                                        p.setMyData(a);
                                        getProfile();
                                        startActivity(new Intent(login_admin.this, home_page_admin.class));
                                        finish();
                                    } else {
                                        Toast.makeText(login_admin.this, "No user exist please register first!",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
            }
        });
    }

    private void getProfile() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    workshop w=new workshop();
                    w=snapshot.getValue(workshop.class);
                    //Toast.makeText(login_admin.this,w.getWorkshop_name(),Toast.LENGTH_SHORT).show();
                  //  Toast.makeText(login_admin.this,p.myData.getId(),Toast.LENGTH_SHORT).show();
                    if (p.myData.getId().equals(w.getId())) {
                        p.setMyData(w);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}