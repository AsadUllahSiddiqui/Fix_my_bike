package com.example.semester_project_lvl_1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static android.app.Activity.RESULT_OK;

public class bike_selection_rv extends RecyclerView.Adapter<bike_selection_rv.ViewHolder> {
    public static final String company="company";
    public static final String model="model";
    public static final String bnumber="bnumber";
    public static final String c_id="c_id";
    List<bike> ls;
    private Context c;

    public bike_selection_rv(List<bike> ls, Context mContext) {
        this.ls = ls;
        this.c = mContext;
    }


    @NonNull
    @Override
    public bike_selection_rv.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemrow= LayoutInflater.from(c).inflate(R.layout.bike_selection_row,parent,false);
        return new  ViewHolder(itemrow);

    }

    @Override
    public void onBindViewHolder(@NonNull bike_selection_rv.ViewHolder holder, final int position) {
        holder.Company.setText(ls.get(position).getCompany());
        holder.Model.setText(ls.get(position).getModel());
        holder.Number.setText(ls.get(position).getNumber());
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(c,Select_Services.class);
                intent.putExtra(company,ls.get(position).getCompany());
                intent.putExtra(model,ls.get(position).getModel());
                intent.putExtra(bnumber,ls.get(position).getNumber());
                intent.putExtra(c_id,ls.get(position).getCustomer_id());
                c.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Company,Model,Number;
        RelativeLayout ll;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Company=itemView.findViewById(R.id.bike_Company_name_id);
            Model=itemView.findViewById(R.id.bike_Model_id);
            Number=itemView.findViewById(R.id.bike_BikeNumber_id);
            ll=itemView.findViewById(R.id. selection_bike_row1);

        }
    }
}
