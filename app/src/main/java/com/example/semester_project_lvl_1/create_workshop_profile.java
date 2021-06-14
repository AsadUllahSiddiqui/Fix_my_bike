package com.example.semester_project_lvl_1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static androidx.constraintlayout.motion.widget.Debug.getLocation;

public class create_workshop_profile extends AppCompatActivity {
    public workshop_profile_veriable p;
    private FusedLocationProviderClient client;
    private FirebaseAuth mAuth;
    CircleImageView img;
    EditText workshop_name, owner_name, bio, address, phone;
    Button save, get_location;
    String id;
    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;
    private static final int PICK_IMAGE = 1;
    private FirebaseStorage storage;
    private StorageReference StorageRef;
    workshop c=new workshop();
    Uri imguri;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference reference = database.getReference("workshop");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_workshop_profile);
        save = findViewById(R.id.save_workshop_profile_button);
        img = findViewById(R.id.workshop_profile_pic);
        client = LocationServices.getFusedLocationProviderClient(this);
        mAuth = FirebaseAuth.getInstance();
        id = mAuth.getCurrentUser().getUid();
        phone = findViewById(R.id.workshop_phone_number);
        address = findViewById(R.id.workshop_Address);
        workshop_name = findViewById(R.id.workshop_name);
        owner_name = findViewById(R.id.owner_name);
        bio = findViewById(R.id.workshop_bio);
        get_location = findViewById(R.id.get_current_location);
        configureimage();
        storage = FirebaseStorage.getInstance();
        StorageRef = storage.getReference();
        configureButton_save_button();
        get_location();
    }

    private void get_location() {
        get_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermission();
                if (ActivityCompat.checkSelfPermission(create_workshop_profile.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions

                    return;
                }
                client.getLastLocation().addOnSuccessListener(create_workshop_profile.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if(location!=null){
                            Toast.makeText(create_workshop_profile.this, location.toString(),
                                    Toast.LENGTH_LONG).show();
                            c.setLat(location.getLatitude());
                            c.setLng(location.getLongitude());

                        }
                    }
                });
            }
        });

    }


    private void configureimage() {
        img = findViewById(R.id.workshop_profile_pic);
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

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                c.setOwner_name(owner_name.getText().toString());
                c.setWorkshop_name(workshop_name.getText().toString());
                c.setWorkshop_address(address.getText().toString());
                c.setWorkshop_number(phone.getText().toString());
                c.setId(id);
                c.setBio(bio.getText().toString());
                c.setPic(imguri.toString());
                c.setId(mAuth.getCurrentUser().getUid());

                Toast.makeText(create_workshop_profile.this, "save",
                        Toast.LENGTH_SHORT).show();
                reference.push().setValue(c);

                uploadPicture();
                p=new workshop_profile_veriable();
                p.setMyData(c);
                startActivity(new Intent(create_workshop_profile.this,home_page_admin.class));
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

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...


                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progressPercentage=(100.00*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                pd.setMessage("Progress:"+(int)progressPercentage+"%");
            }
        });


    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(this,new String[]{ACCESS_FINE_LOCATION},1);
    }

}