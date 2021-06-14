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

public class UserProfile extends AppCompatActivity {
    private StorageReference storageReference;
    Button back_Button;
    CircleImageView profile_pic;
    TextView name,phone,email,address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        name=findViewById(R.id.profile_customer_name);
        phone=findViewById(R.id.profile_customer_phone);
        email=findViewById(R.id.profile_customer_email);
        address=findViewById(R.id.profile_customer_address);
        profile_pic=findViewById(R.id.customer_profile_pic);
        name.setText(user_profile_veriable.myData.getFirst_name());
        phone.setText(user_profile_veriable.myData.getPhone());
        email.setText(user_profile_veriable.myData.getEmail());
        address.setText(user_profile_veriable.myData.getAddress());
        setpic();
        configure_Button_Back_Button();
    }
    private void configure_Button_Back_Button(){
        back_Button= findViewById(R.id.User_profile_save_button);
        back_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                finish();
            }
        });
    }

    private void setpic()
    {
        storageReference= FirebaseStorage.getInstance().getReference().child("images/"+user_profile_veriable.myData.getId());
        try {
            final File localfile=File.createTempFile("profile","jpeg");
            storageReference.getFile(localfile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap= BitmapFactory.decodeFile(localfile.getAbsolutePath());
                            ((CircleImageView)findViewById(R.id.customer_profile_pic)).setImageBitmap(bitmap);
                            // Toast.makeText(home_page_admin.this,"o_yes",Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (IOException e) {
            // Toast.makeText(home_page_admin.this,"o_noo",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

}