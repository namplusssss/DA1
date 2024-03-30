package com.example.xs_sport.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xs_sport.DAO.DanhGia_Dao;
import com.example.xs_sport.Model.BinhLuan;
import com.example.xs_sport.R;

import java.util.ArrayList;

public class BinhLuan_Adapter extends RecyclerView.Adapter<BinhLuan_Adapter.ViewHolder> {

    Context context;
    ArrayList<BinhLuan> list;

    public BinhLuan_Adapter(Context context, ArrayList<BinhLuan> list) {
        this.context = context;
        this.list = list;
    }

    public void setData(ArrayList<BinhLuan> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_binhluan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BinhLuan binhLuan = list.get(position);
        holder.tv_user.setText(binhLuan.getUser_name());
        holder.tv_cmt.setText(binhLuan.getComment_content());
        holder.ratingStarView.setRating(binhLuan.getXephang());    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_user, tv_cmt;
        DanhGia_Dao ratingStarView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_user=itemView.findViewById(R.id.tv_user_cmt);
            tv_cmt=itemView.findViewById(R.id.tv_content_cmt);
            ratingStarView=itemView.findViewById(R.id.ratingstarview);
        }
    }
}
