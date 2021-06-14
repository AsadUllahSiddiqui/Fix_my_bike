package com.example.semester_project_lvl_1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Select_Services extends AppCompatActivity {
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    final DatabaseReference reference=database.getReference("order");
    user_profile_veriable p;
    String services="";
    order ord;
    Button select_bike,order_btn;
    TextView option;
    int c1=0;
    int c2=0;
    int c3=0;
    int c4=0;
    int c5=0;
    int c6=0;
    int totalcost=0;
    CheckBox basic_tuning,engine_repair,inspection,head_repair,power_tuning,others;
    TextView cost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select__services);
        cost=findViewById(R.id.cost);
        ord=new order();
        order_btn=findViewById(R.id.save_selection_button);
        p=new user_profile_veriable();
        option=findViewById(R.id.bike_option);
        basic_tuning=findViewById(R.id.checkBox_basicTuning);
        engine_repair=findViewById(R.id.checkBox_Engine);
        inspection=findViewById(R.id.checkBox_Inspection);
        head_repair=findViewById(R.id.checkBox_Head);
        power_tuning=findViewById(R.id.checkBox_Power);
        others=findViewById(R.id.checkBox_others);
        select_bike=findViewById(R.id.select_bike);
        Intent i=getIntent();

       // Toast.makeText(Select_Services.this,workshop_profile_veriable.myData.getId().toString(),Toast.LENGTH_SHORT).show();
        String company=i.getStringExtra("company");
        String model=i.getStringExtra("model");
        String bnumber=i.getStringExtra("bnumber");
        String c_id=i.getStringExtra("c_id");
        bike b=new bike(company,model,bnumber,c_id);
        if(b!=null) {
            option.setText(b.getNumber());
            ord.setBike_company(b.getCompany());
            ord.setBike_number(b.getNumber());
            ord.setBike_model(b.getModel());
        }
        order();
        configure_Button_select_bike();
        configure_order_button();
    }
    private void order(){
        basic_tuning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();
                if (checked){
                    services+="basic_tuning,";
                    c1=300;
                    totalcost=c1+c2+c3+c4+c5+c6;

                    cost.setText(totalcost+"Rs");
                }
                else{
                    c1=0;
                    totalcost=c1+c2+c3+c4+c5+c6;
                    cost.setText(totalcost+"Rs");
                }
            }
        });
        engine_repair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();
                if (checked){
                    services+="engine_repair,";
                    c2=800;
                    totalcost=c1+c2+c3+c4+c5+c6;

                    cost.setText(totalcost+"Rs");
                }
                else{
                    c2=0;
                    totalcost=c1+c2+c3+c4+c5+c6;
                    cost.setText(totalcost+"Rs");
                }
            }
        });
        inspection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();
                if (checked){
                    services+="inspection,";
                    c3=200;
                    totalcost=c1+c2+c3+c4+c5+c6;
                    cost.setText(totalcost+"Rs");
                }
                else{
                    c3=0;
                    totalcost=c1+c2+c3+c4+c5+c6;
                    cost.setText(totalcost+"Rs");
                }
            }
        });
        head_repair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();
                if (checked){
                    services+="head_repair,";
                    c4=500;
                    totalcost=c1+c2+c3+c4+c5+c6;
                    cost.setText(totalcost+"Rs");
                }
                else{
                    c4=0;
                    totalcost=c1+c2+c3+c4+c5+c6;
                    cost.setText(totalcost+"Rs");
                }
            }
        });
        power_tuning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();
                if (checked){
                    c5=500;
                    services=services+"power_tuning,";
                    totalcost=c1+c2+c3+c4+c5+c6;
                    cost.setText(totalcost+"Rs");
                }
                else{
                    c5=0;
                    totalcost=c1+c2+c3+c4+c5+c6;
                    cost.setText(totalcost+"Rs");
                }
            }
        });
        others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();
                if (checked){
                    services+="others,";
                    c6=100;
                    totalcost=c1+c2+c3+c4+c5+c6;
                    cost.setText(totalcost+"Rs");
                }
                else{
                    c6=0;
                    totalcost=c1+c2+c3+c4+c5+c6;
                    cost.setText(totalcost+"Rs");
                }
            }
        });
    }

    private void configure_Button_select_bike(){
        select_bike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

                startActivityForResult(new Intent(Select_Services.this,select_bike.class),1);

            }
        });
    }

    private void completeorder(){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat( );
        String currentDateTimeString = sdf.format(d);
        ord.setCustomer_name(p.myData.getFirst_name());
        ord.setCustomer_address(p.myData.getAddress());
        ord.setCustomer_id(p.myData.getId());
        ord.setWorkshop_id(workshop_profile_veriable.myData.getId().toString());
        ord.setServices_required(services);
        ord.setData(currentDateTimeString);
        ord.setOrder_no( UUID.randomUUID().toString());
        String t="";
        t+=totalcost;
        ord.setEstimated_price(t);
        String x=ord.getCustomer_id().substring(0,5);
        reference.child(ord.getOrder_no()).setValue(ord);
     //   reference.push().setValue(ord+);

         Toast.makeText(Select_Services.this,"Order has been placed !",Toast.LENGTH_SHORT).show();
        Toast.makeText(Select_Services.this,"please Wait for their response !",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Select_Services.this, Home_Page_User.class));
        finish();
    }

public void configure_order_button(){
    order_btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            completeorder();
        }
    });
}


}