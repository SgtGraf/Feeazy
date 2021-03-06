package com.example.feeasy.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feeasy.activities.GroupMemberActivity;
import com.example.feeasy.dataManagement.AppDataManager;
import com.example.feeasy.dataManagement.DataManager;
import com.example.feeasy.entities.Group;
import com.example.feeasy.entities.GroupMember;
import com.example.feeasy.R;

import java.util.List;

public class AdapterMembers extends RecyclerView.Adapter<AdapterMembers.ViewHolder>{

    Group group;
    List<GroupMember> groupMembers;
    Context context;

    public AdapterMembers(Context ct, List<GroupMember> members, Group group) {
        context = ct;
        groupMembers = members;
        this.group = group;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView memberName;
        TextView memberFees;
        View clickableView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            memberName = itemView.findViewById(R.id.member_name_fee);
            memberFees = itemView.findViewById(R.id.member_fee);
            clickableView = itemView.findViewById(R.id.gm_clickable_view);
        }
    }

    @NonNull
    @Override
    public AdapterMembers.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.group_member, parent, false);
        return new AdapterMembers.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final AdapterMembers.ViewHolder holder, final int position) {
        holder.memberName.setText(groupMembers.get(position).name);
        holder.memberFees.setText(DataManager.getDataManager().getCombinedFeeOfUser(group.id,groupMembers.get(position).id));
        holder.clickableView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, GroupMemberActivity.class);
                intent.putExtra("memberId", groupMembers.get(position).id);
                intent.putExtra("groupId", group.id);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return groupMembers.size();
    }
}
