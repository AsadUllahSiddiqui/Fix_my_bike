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
import static com.example.semester_project_lvl_1.R.layout.admin_history_row;

public class Admin_History_rv extends RecyclerView.Adapter<Admin_History_rv.MyViewHolder> implements Filterable{
    List<order> ls;
    Context c;
    List<order> lsfull;
    public Admin_History_rv(List<order> ls, Context c) {
        this.c=c;
        this.ls=ls;
        this.lsfull=new ArrayList<>(ls);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemrow= LayoutInflater.from(c).inflate(admin_history_row,parent,false);
        return new  MyViewHolder(itemrow);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String s=""+ls.get(position).getBike_company()+" "+ls.get(position).getBike_model()+" "+ls.get(position).getBike_number();
        holder.bikeID.setText(s);
        holder.workshop.setText(ls.get(position).getCustomer_name());
        holder.phno.setText(ls.get(position).getCustomer_address());
        holder.moneySpent.setText(ls.get(position).getEstimated_price());
        holder.services.setText(ls.get(position).getServices_required());
        holder.dateID.setText(ls.get(position).getData());
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
            List<order> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(lsfull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (order item : lsfull) {
                    if (item.getCustomer_name().toLowerCase().contains(filterPattern)) {
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
        TextView bikeID,workshop,phno,moneySpent,services,dateID;
        LinearLayout rowww;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bikeID=itemView.findViewById(R.id.bike_id);
            workshop=itemView.findViewById(R.id.workshop);
            phno=itemView.findViewById(R.id.phno);
            moneySpent=itemView.findViewById(R.id.moneySpent);
            services=itemView.findViewById(R.id.services);
            dateID=itemView.findViewById(R.id.dateID);
            rowww=itemView.findViewById(R.id.Admin_History_layout);
        }
    }

}