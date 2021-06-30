package com.example.feeasy.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feeasy.entities.Fee;
import com.example.feeasy.entities.FeeStatus;
import com.example.feeasy.entities.Group;
import com.example.feeasy.entities.GroupMember;
import com.example.feeasy.R;

import java.util.List;

public class AdapterFees extends RecyclerView.Adapter<AdapterFees.ViewHolder> {
    Group group;
    List<Fee> fees;
    Context context;


    public AdapterFees(Context ct, List<Fee> fees, Group group) {
        context = ct;
        this.fees = fees;
        this.group = group;
    }

    // TODO: Consider a better way!
    public void setDataSet(List<Fee> fees, Group group){
        this.fees = fees;
        this.group = group;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView memberName;
        TextView memberFees;
        TextView feeName;
        ImageView status;
        View layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            memberName = itemView.findViewById(R.id.member_name_fee);
            memberFees = itemView.findViewById(R.id.member_fee_fee);
            feeName = itemView.findViewById(R.id.fee_name_fee);
            status = itemView.findViewById(R.id.fee_list_item_status);
            layout = itemView.findViewById(R.id.fee_list_item_wrapper);
        }
    }

    @NonNull
    @Override
    public AdapterFees.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fee_list_view, parent, false);
        return new AdapterFees.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final AdapterFees.ViewHolder holder, final int position) {
        holder.memberName.setText(fees.get(position).groupMember.name);
        holder.memberFees.setText(fees.get(position).amount + "$");
        holder.feeName.setText(fees.get(position).name);
        if(fees.get(position).status == FeeStatus.ACCEPTED){
            ImageViewCompat.setImageTintList(holder.status, ColorStateList.valueOf(ContextCompat.getColor(context,R.color.colorStatusRed)));
        }
        else if(fees.get(position).status == FeeStatus.COMPLETED){
            ImageViewCompat.setImageTintList(holder.status, ColorStateList.valueOf(ContextCompat.getColor(context,R.color.colorStatusGreen)));
        }
        holder.layout.setOnClickListener(new View.OnClickListener() {
            Intent intent;
            @Override
            public void onClick(View view) {
                if(fees.get(position).status == FeeStatus.ACCEPTED){
                    fees.get(position).status = FeeStatus.COMPLETED;
                    notifyItemChanged(position);
                }
                else if(fees.get(position).status == FeeStatus.COMPLETED){
                    fees.get(position).status = FeeStatus.ACCEPTED;
                    notifyItemChanged(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return fees.size();
    }
}
