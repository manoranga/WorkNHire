package com.example.heyshan.worknhire;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class JobListAdapter extends RecyclerView.Adapter<JobListAdapter.ViewHolder> {
    private List<UserModel> mUserModel;
    private Context mContext;

    public JobListAdapter(List<UserModel> mUserModel, Context mContext) {
        this.mUserModel = mUserModel;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.cusomercard,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        UserModel userModel = mUserModel.get(i);
        viewHolder.Name.setText(userModel.getFname());
        viewHolder.Email.setText(userModel.getFname());
        viewHolder.Mobile.setText(userModel.getFname());
        viewHolder.LastName.setText(userModel.getFname());




    }

    @Override
    public int getItemCount() {
        return mUserModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView Name;
        public TextView Email;
        public TextView Mobile;
        public TextView LastName;
        public CardView Card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.tv_c_name);
            Email = itemView.findViewById(R.id.tv_c_no);
            Mobile = itemView.findViewById(R.id.tv_status);
            LastName = itemView.findViewById(R.id.tv_phoneNumber);

        }
    }
}
