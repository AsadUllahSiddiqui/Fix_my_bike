package com.example.semester_project_lvl_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp_user extends AppCompatActivity {
    Button signUp;
    EditText  email, password;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_user);
        configure_Button_SignUp();
        configure_authentication();
    }
    private void configure_authentication(){

        email=findViewById(R.id.user_email);
        password=findViewById(R.id.user_password);
        mAuth = FirebaseAuth.getInstance();
    }
    private void configure_Button_SignUp(){
        signUp= findViewById(R.id.user_signup_button);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                mAuth.createUserWithEmailAndPassword(email.getText().toString(),
                        password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            startActivity(new Intent(SignUp_user.this,create_profile.class));
                            Toast.makeText(SignUp_user.this, "successfully to register",
                                    Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignUp_user.this, "Failed to Register!",
                                Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null)
        {
            Toast.makeText(SignUp_user.this,currentUser.getEmail()+" is active !!!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}