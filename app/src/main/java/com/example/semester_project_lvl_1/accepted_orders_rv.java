package com.example.semester_project_lvl_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.example.semester_project_lvl_1.R.layout.user_history_row;


public class accepted_orders_rv extends RecyclerView.Adapter<accepted_orders_rv.MyViewHolder> implements Filterable {
    List<order> ls;
    Context c;
    List<order> lsfull;
    public accepted_orders_rv(List<order> ls, Context c) {
        this.c=c;
        this.ls=ls;
        this.lsfull=new ArrayList<>(ls);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemrow= LayoutInflater.from(c).inflate(user_history_row,parent,false);
        return new  MyViewHolder(itemrow);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bikeID.setText(ls.get(position).getBike_number());
        holder.modelANDcompany.setText(ls.get(position).getBike_company()+" "+ls.get(position).getBike_model());
        holder.ownerName.setText(ls.get(position).customer_name);
        holder.phno.setText("036183529");
        holder.costPaid.setText(ls.get(position).getEstimated_price());
        holder.services.setText(ls.get(position).getServices_required());
        holder.dateID.setText(ls.get(position).getDate());
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
                    if (item.getBike_number().toLowerCase().contains(filterPattern)) {
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
        TextView bikeID,modelANDcompany,ownerName,phno,costPaid,services,dateID;
        LinearLayout rowww;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bikeID=itemView.findViewById(R.id.bike_id);
            modelANDcompany=itemView.findViewById(R.id.companyANDmodel);
            ownerName=itemView.findViewById(R.id.ownerName);
            phno=itemView.findViewById(R.id.phno);
            costPaid=itemView.findViewById(R.id.costPaid);
            services=itemView.findViewById(R.id.services);
            dateID=itemView.findViewById(R.id.dateID);
            rowww=itemView.findViewById(R.id.User_History_layout);
        }
    }

}
