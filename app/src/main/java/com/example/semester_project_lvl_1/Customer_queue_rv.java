package com.example.semester_project_lvl_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Customer_queue_rv extends RecyclerView.Adapter<Customer_queue_rv.ViewHolder>  {
    List<order> ls;
    private Context context;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public Customer_queue_rv(List<order> ls, Context context) {
        this.ls = ls;
        this.context = context;
    }

    @NonNull
    @Override
    public Customer_queue_rv.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemrow= LayoutInflater.from(context).inflate(R.layout.activity_customer_queue_row,parent,false);
        return new ViewHolder(itemrow);
    }

    @Override
    public void onBindViewHolder(@NonNull Customer_queue_rv.ViewHolder holder, final int position) {
        holder.customer_id.setText(ls.get(position).getCustomer_name());
        String s=""+ls.get(position).getBike_company()+" "+ls.get(position).getBike_model()+" "+ls.get(position).getBike_number();
        holder.model.setText(s);
        holder.services.setText(ls.get(position).getServices_required());
        holder.address.setText(ls.get(position).getCustomer_address());
        final DatabaseReference done = database.getReference("order");
        holder.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                done.child(ls.get(position).getOrder_no()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists() && dataSnapshot.getValue(order.class).getCustomer_name()==ls.get(position).getCustomer_name()){
                                Map<String, Object> comentarios2 = new HashMap<>();
                              comentarios2.put("completion_status","done");
                                done.child(ls.get(position).getOrder_no()).updateChildren(comentarios2);
                            }

//                        if (ls.size() > 0) {
//                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                                if (ls.get(position).customer_id==snapshot.getValue(order.class).getCustomer_id()
//                                        && ls.get(position).workshop_id ==snapshot.getValue(order.class).getWorkshop_id()
//                                        && ls.get(position).getCustomer_name()==snapshot.getValue(order.class).getCustomer_name()
//                                    // && ls.get(position).getCompletion_status()!="Order approved"
//                                )
//                                {
//                                    ls.get(position).setCompletion_status("done");
//                                    DatabaseReference ref1=snapshot.getRef();
//                                    Map<String, Object> comentarios2 = new HashMap<>();
//                                    comentarios2.put("completion_status","done");
//                                    ref1.updateChildren(comentarios2);
//                                    break;
//                                }
////                                Toast.makeText(login.this,dataSnapshot.getChildren().toString(),Toast.LENGTH_SHORT).show();
//                            }
//                            ls.remove(ls.get(position));
//                            notifyItemRemoved(position);
//
//
//                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
            return ls.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView customer_id,model,services,address,picture_uri;
        RelativeLayout Row_layout;
        Button done;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            customer_id=itemView.findViewById(R.id.Row_Customer_id);
            model=itemView.findViewById(R.id.Row_Model_id);
            services=itemView.findViewById(R.id.Row_Service_id);
            address=itemView.findViewById(R.id.Row_Address_id);
            Row_layout=itemView.findViewById(R.id.Customer_queue_row_id);
            done=itemView.findViewById(R.id.done);

        }
    }
}
