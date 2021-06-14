package com.example.semester_project_lvl_1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import static com.example.semester_project_lvl_1.R.layout.find_workshop_row;

public class findworkshop_rv extends RecyclerView.Adapter<findworkshop_rv.MyViewHolder> implements Filterable{
    List<workshop> ls;

    Context c;
    List<workshop> lsfull;
    public static final String workshop_name="workshop_name";
    public static final String phone_no="phone_no";
    public static final String Address="Address";
    public static final String owner_name="owner_name";
    public static final String idx="idx";
    public static final String bio="bio";
    public static final String lat="lat";
    public static final String lng="lng";
    public findworkshop_rv(List<workshop> ls, Context c) {
        this.c=c;
        this.ls=ls;
        this.lsfull=new ArrayList<>(ls);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemrow= LayoutInflater.from(c).inflate(find_workshop_row,parent,false);
        return new  MyViewHolder(itemrow);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.name.setText(ls.get(position).getWorkshop_name());
        holder.address.setText(ls.get(position).getWorkshop_address());
        holder.phno.setText(ls.get(position).getWorkshop_number());
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(c,Select_Services.class);
               // intent.putExtra(workshop_name,ls.get(position).getWorkshop_address());
               // intent.putExtra(phone_no,ls.get(position).getWorkshop_number());
               // intent.putExtra(Address,ls.get(position).getWorkshop_address());
               // intent.putExtra(owner_name,ls.get(position).getOwner_name());
               // intent.putExtra(idx,ls.get(position).getId());
               // intent.putExtra(bio,ls.get(position).getBio());
               // intent.putExtra(lat,ls.get(position).getLat());
               // intent.putExtra(lng,ls.get(position).getLng());
                workshop_profile_veriable p=new workshop_profile_veriable();
                p.setMyData(ls.get(position));
                c.startActivity(intent);
            }
        });

    }



    @Override
    public int getItemCount() {
        return ls.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<workshop> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(lsfull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (workshop item : lsfull) {
                    if (item.getWorkshop_name().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ls.clear();
            ls.addAll((List) results.values);
            notifyDataSetChanged();
        }

    };

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,address,phno;
        RelativeLayout ll;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.workshop_name_id);
            address=itemView.findViewById(R.id.workshop_address_id);
            phno=itemView.findViewById(R.id.workshop_number_id);
            ll=itemView.findViewById(R.id.Find_Workshop_layout);
        }
    }
    public void filterList (ArrayList<workshop>filteredList){
        ls=filteredList;
        notifyDataSetChanged();
    }
}