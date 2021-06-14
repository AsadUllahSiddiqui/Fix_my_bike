package com.example.semester_project_lvl_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

public class login extends AppCompatActivity {
    Button login;

    user_profile_veriable p = new user_profile_veriable();
    EditText email, password;
    FirebaseUser firebaseuser;
    private FirebaseAuth auth;
    customer Profile;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference reference = database.getReference("customer");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.user_login_email);
        password = findViewById(R.id.user_login_password);
        auth = FirebaseAuth.getInstance();
        firebaseuser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseuser != null) {
            customer a = new customer(firebaseuser.getEmail(), firebaseuser.getUid());
            p.setMyData(a);
            getProfile();
            Intent i = new Intent(login.this, Home_Page_User.class);
            startActivity(i);

            finish();
        }
        configure_login_button();
    }

    private void configure_login_button() {
        login = findViewById(R.id.user_Login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                final String email_text = email.getText().toString();
                String password_text = password.getText().toString();
                //checking firebase
                if (TextUtils.isEmpty(email_text) || TextUtils.isEmpty(password_text)) {
                    Toast.makeText(login.this, "Please enter email and password",
                            Toast.LENGTH_SHORT).show();
                } else {
                    auth.signInWithEmailAndPassword(email_text, password_text)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        firebaseuser = FirebaseAuth.getInstance().getCurrentUser();
                                        customer a = new customer(firebaseuser.getEmail(), firebaseuser.getUid());
                                        p.setMyData(a);
                                        getProfile();
                                        startActivity(new Intent(login.this, Home_Page_User.class));
                                        finish();
                                    } else {
                                        Toast.makeText(login.this, "No user exist please register first!",
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
                    if (p.myData.getEmail().equals(snapshot.getValue(customer.class).getEmail())) {
                        Profile = (snapshot.getValue(customer.class));
                        p.setMyData(Profile);
                        Toast.makeText(login.this,dataSnapshot.getChildren().toString(),Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}