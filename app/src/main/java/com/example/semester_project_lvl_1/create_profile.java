package com.example.semester_project_lvl_1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class create_profile extends AppCompatActivity {
    private FirebaseAuth mAuth;
    user_profile_veriable p;
    CircleImageView img;
    EditText first_name, last_name, bio,address,phone;
    ImageButton male, female, other;
    Button save;
    String gendera,id;
    TextView tmale, tfemale, tother;
    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;
    private static final int PICK_IMAGE = 1;
    private FirebaseStorage storage;
    private StorageReference StorageRef;
    Uri imguri;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    final DatabaseReference reference=database.getReference("customer");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_profile);
        gender_selection();
        storage=FirebaseStorage.getInstance();
        StorageRef=storage.getReference();
        configureimage();
        configureButton_save_button();
    }

    private void configureButton_female() {

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tmale.setTextColor(Color.GREEN);
                tfemale.setTextColor(Color.RED);
                tother.setTextColor(Color.GREEN);
                gendera = "female";
                Toast.makeText(create_profile.this, "Female",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void configureButton_male() {

        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tmale.setTextColor(Color.RED);
                tfemale.setTextColor(Color.GREEN);
                tother.setTextColor(Color.GREEN);
                gendera = "male";
                Toast.makeText(create_profile.this, "Male",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void configureButton_other() {

        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tmale.setTextColor(Color.GREEN);
                tfemale.setTextColor(Color.GREEN);
                tother.setTextColor(Color.RED);
                gendera = "other";
                Toast.makeText(create_profile.this, "Other",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void gender_selection() {
        mAuth = FirebaseAuth.getInstance();
        id=mAuth.getCurrentUser().getUid();
        phone=findViewById(R.id.user_phone_number);
        address=findViewById(R.id.user_Address);
        first_name = findViewById(R.id.user_first_name);
        last_name = findViewById(R.id.user_last_name);
        bio = findViewById(R.id.user_bio);
        male = findViewById(R.id.gender_male);
        female = findViewById(R.id.gender_female);
        other = findViewById(R.id.gender_others);
        tmale = findViewById(R.id.text_male);
        tfemale = findViewById(R.id.text_female);
        tother = findViewById(R.id.text_other);
        configureButton_female();
        configureButton_male();
        configureButton_other();


    }


    private void configureimage() {
        img = findViewById(R.id.user_profile_pic);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                            PackageManager.PERMISSION_DENIED) {
                        //permission not granted, request it
                        String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        //show toast
                        requestPermissions(permission, PERMISSION_CODE);

                    } else {
                        //permission already granted
                        pickImageFromGallery();

                    }

                } else {
                    //system os is less than marshmallow
                    pickImageFromGallery();

                }
            }
        });

    }

    public void configureButton_save_button(){
        save=findViewById(R.id.save_user_profile);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customer c=new customer();
                c.setFirst_name(first_name.getText().toString());
                c.setLast_name(last_name.getText().toString());
                c.setAddress(address.getText().toString());
                c.setPhone(phone.getText().toString());
                c.setId(id);
                c.setBio(bio.getText().toString());
                c.setImg(imguri.toString());
                c.setGender(gendera);
                c.setEmail(mAuth.getCurrentUser().getEmail());
                p=new user_profile_veriable();
                p.setMyData(c);
                Toast.makeText(create_profile.this, "save",
                        Toast.LENGTH_SHORT).show();
                reference.push().setValue(c);
                uploadPicture();
                startActivity(new Intent(create_profile.this,Home_Page_User.class));
                finish();
            }
        });
    }
    private void pickImageFromGallery() {
        //intent to pic image
        Intent i = new Intent(Intent.ACTION_PICK);
        i.setType("image/*");
        startActivityForResult(i, IMAGE_PICK_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //permission was granted
                    pickImageFromGallery();
                } else {
                    Toast.makeText(this, "Permission denied..!", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
    //handle result to pick image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            img.setImageURI(data.getData());
            imguri = data.getData();
        }
    }
    private void uploadPicture() {
        final ProgressDialog pd=new ProgressDialog(this);
        pd.setTitle("Uploading Image.....");
        pd.show();
        //final String randomekey= UUID.randomUUID().toString();
        StorageReference riversRef = StorageRef.child("images/"+mAuth.getCurrentUser().getUid());

        riversRef.putFile(imguri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                   // pd.dismiss();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                       // pd.dismiss();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progressPercentage=(100.00*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                pd.setMessage("Progress:"+(int)progressPercentage+"%");
            }
        });


    }
}