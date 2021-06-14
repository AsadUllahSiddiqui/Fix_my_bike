package com.example.semester_project_lvl_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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

public class order_rv extends  RecyclerView.Adapter<order_rv.ViewHolder> {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseDatabase database2 = FirebaseDatabase.getInstance();
    List<order> ls;
    private Context mContext;

    public order_rv(List<order> ls, Context mContext) {
        this.ls = ls;
        this.ls.add(new order());
        this.mContext = mContext;


    }


    @NonNull
    @Override
    public order_rv.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemrow= LayoutInflater.from(mContext).inflate(R.layout.order_row,parent,false);
        return new order_rv.ViewHolder(itemrow);
    }

    @Override
    public void onBindViewHolder(@NonNull order_rv.ViewHolder holder, final int position) {
        final String s=""+ls.get(position).getBike_company()+" "+ls.get(position).getBike_model()+" "+ls.get(position).getBike_number();
        holder.bike.setText(s);
        holder.address.setText(ls.get(position).getCustomer_address());
        holder.services.setText(ls.get(position).getServices_required());
        holder.pickup_location.setText("null");
        holder.yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference yes = database2.getReference("order");
                yes.child(ls.get(position).getOrder_no()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists() && dataSnapshot.getValue(order.class).getOrder_no().equals(ls.get(position).getOrder_no()) ) {
                            Map<String, Object> comentarios2 = new HashMap<>();
                            comentarios2.put("completion_status","1");
                            yes.child(ls.get(position).getOrder_no()).updateChildren(comentarios2);
                           // ls.remove(ls.get(position));
                           // notifyItemRemoved(position);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        holder.no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference no = database2.getReference("order");
                no.child(ls.get(position).getOrder_no()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists() && dataSnapshot.getValue(order.class).getOrder_no().equals(ls.get(position).getOrder_no()) )  {
                            Map<String, Object> comentarios2 = new HashMap<>();
                            comentarios2.put("completion_status","-1");
                            no.child(ls.get(position).getOrder_no()).updateChildren(comentarios2);
                           // ls.remove(ls.get(position));
                           //  notifyItemRemoved(position);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
//        holder.yes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mContext,s+" Order approved!",Toast.LENGTH_SHORT).show();
//                final DatabaseReference yes = database2.getReference("order");
//                yes.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        if (ls.size() > 0) {
//                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                            if (ls.get(position).customer_id==snapshot.getValue(order.class).getCustomer_id()
//                                    && ls.get(position).workshop_id ==snapshot.getValue(order.class).getWorkshop_id()
//                                    && ls.get(position).getData()==snapshot.getValue(order.class).getData()
//                                   // && ls.get(position).getCompletion_status()!="Order approved"
//                                    )
//                            {
//                                ls.get(position).setCompletion_status("Order approved");
//                                DatabaseReference ref1=snapshot.getRef();
//                                Map<String, Object> comentarios2 = new HashMap<>();
//                                comentarios2.put("completion_status","1");
//                                ref1.updateChildren(comentarios2);
//                            }
////                                Toast.makeText(login.this,dataSnapshot.getChildren().toString(),Toast.LENGTH_SHORT).show();
//                        }
//                             ls.remove(ls.get(position));
//                             notifyItemRemoved(position);
//
//
//                    }}
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//            }
//     });
//
//        holder.no.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                final DatabaseReference No = database.getReference("order");
//               No.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                            if (ls.size()  >0) {
//                                if (ls.get(position).customer_id == snapshot.getValue(order.class).getCustomer_id()
//                                        && ls.get(position).workshop_id == snapshot.getValue(order.class).getWorkshop_id()
//                                        && ls.get(position).getData() == snapshot.getValue(order.class).getData()
//                                    // && ls.get(position).getCompletion_status()!="Order declined"
//                                ) {
//                                    ls.get(position).setCompletion_status("Order declined");
//                                    DatabaseReference ref = snapshot.getRef();
//                                    Map<String, Object> comentarios = new HashMap<>();
//                                    comentarios.put("completion_status", "-1");
//                                    ref.updateChildren(comentarios);
//                                    notifyDataSetChanged();
//                                    Toast.makeText(mContext, s + " Order declined!", Toast.LENGTH_SHORT).show();
//                                    //  ls.remove(ls.get(position));
//                                    //  notifyItemRemoved(ls.get(position));
//
//                                }
//
////                                Toast.makeText(login.this,dataSnapshot.getChildren().toString(),Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                         ls.remove(ls.get(position));
//                         notifyItemRemoved(position);
//
//
//
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder {
        TextView bike,address,services,pickup_location;
        ImageButton yes,no;
        RelativeLayout Row_layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bike=itemView.findViewById(R.id.row_order_bike);
            address=itemView.findViewById(R.id.row_order_address);
            services=itemView.findViewById(R.id.row_order_services);
            pickup_location=itemView.findViewById(R.id.row_order_pickup_location);
            yes=itemView.findViewById(R.id.yes);
            no=itemView.findViewById(R.id.no);
            Row_layout=itemView.findViewById(R.id.order_row);
        }
    }
}
