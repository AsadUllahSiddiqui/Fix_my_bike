package com.example.semester_project_lvl_1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdminProfile extends AppCompatActivity {
    private StorageReference storageReference;
    Button back_Button;
    CircleImageView profile_pic;
    TextView name,phone,email,address;
    workshop_profile_veriable p=new workshop_profile_veriable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);
        name=findViewById(R.id.profile_workshop_name);
        phone=findViewById(R.id.profile_workshop_phone);
        email=findViewById(R.id.profile_workshop_email);
        address=findViewById(R.id.profile_workshop_address);
        profile_pic=findViewById(R.id.profile_workshop_pic);
        name.setText(workshop_profile_veriable.myData.getWorkshop_name());
        phone.setText(workshop_profile_veriable.myData.getWorkshop_number());
        email.setText(workshop_profile_veriable.myData.getWorkshop_address());
        address.setText(workshop_profile_veriable.myData.getWorkshop_address());
        configure_Button_Back_Button();
        setpic();
    }
    private void configure_Button_Back_Button(){
        back_Button= findViewById(R.id.Admin_profile_save_button);
        back_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {


                finish();
            }
        });
    }

    private void setpic()
    {
        storageReference= FirebaseStorage.getInstance().getReference().child("images/"+p.myData.getId());
        try {
            final File localfile=File.createTempFile("profile","jpeg");
            storageReference.getFile(localfile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap= BitmapFactory.decodeFile(localfile.getAbsolutePath());
                            ((CircleImageView)findViewById(R.id.profile_workshop_pic)).setImageBitmap(bitmap);
                            // Toast.makeText(home_page_admin.this,"o_yes",Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (IOException e) {
            // Toast.makeText(home_page_admin.this,"o_noo",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

}