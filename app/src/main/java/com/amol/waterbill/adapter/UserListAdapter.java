package com.amol.waterbill.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amol.waterbill.R;
import com.amol.waterbill.model.UserListModel;
import com.amol.waterbill.model.UserModel;

import java.util.List;


public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    private List<UserListModel> userListModels;


    public UserListAdapter(List<UserListModel> userListModels ) {
        this.userListModels = userListModels;

    }

    @NonNull
    @Override
    public UserListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final UserListAdapter.ViewHolder holder, final int position) {
        UserListModel userListModel = userListModels.get(position);
        holder.tvConnectionNumber.setText(userListModel.getConnection());
        holder.tvName.setText(userListModel.getName());
        holder.tvPendingBill.setText("₹ "+userListModel.getPendingBill());
    }


    @Override
    public int getItemCount() {
        return userListModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvConnectionNumber,tvPendingBill;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvConnectionNumber = (TextView) itemView.findViewById(R.id.tvConnectionNumber);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvPendingBill = (TextView) itemView.findViewById(R.id.tvPendingBill);
        }
    }
}
