package com.example.semester_project_lvl_1;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import com.bumptech.glide.Glide;

        import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Add_bike_rv extends RecyclerView.Adapter<Add_bike_rv.ViewHolder> {
    List<My_Bikes> ls;
    private Context mContext;

    public Add_bike_rv(List<My_Bikes> ls, Context mContext) {
        this.ls = ls;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public Add_bike_rv.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemrow= LayoutInflater.from(mContext).inflate(R.layout.activity_add__bike__row,parent,false);
        return new  ViewHolder(itemrow);

    }

    @Override
    public void onBindViewHolder(@NonNull Add_bike_rv.ViewHolder holder, int position) {
        holder.Company.setText(ls.get(position).getCompany());
        holder.Model.setText(ls.get(position).getModel());
        holder.Number.setText(ls.get(position).getNumber());
    }

    @Override
    public int getItemCount() {
            return ls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Company,Model,Number;
        RelativeLayout Row_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Company=itemView.findViewById(R.id.Company_name_id);
            Model=itemView.findViewById(R.id.Model_id);
            Number=itemView.findViewById(R.id.BikeNumber_id);
            Row_layout=itemView.findViewById(R.id.add_bike_layout);
        }
    }
}

