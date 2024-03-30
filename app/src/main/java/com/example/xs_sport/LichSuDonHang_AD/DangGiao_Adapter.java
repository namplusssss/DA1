package com.example.xs_sport.LichSuDonHang_AD;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xs_sport.Activity.ChiTietDonHang_Main;
import com.example.xs_sport.DAO.HoaDon_Dao;
import com.example.xs_sport.Model.HoaDon;
import com.example.xs_sport.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DangGiao_Adapter extends RecyclerView.Adapter<DangGiao_Adapter.ViewHolder> {

    private ArrayList<HoaDon> list;
    private ArrayList<HoaDon> list1;
    private Context context;
    private HoaDon_Dao hoaDon_dao;


    public DangGiao_Adapter(ArrayList<HoaDon> list, Context context){
        this.list = list;
        this.context = context;
    }


    public void setData(ArrayList<HoaDon> list){
        this.list = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_hoadon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        hoaDon_dao = new HoaDon_Dao(context);
        HoaDon inv = list.get(position);

        holder.name.setText(list.get(position).getName());
        holder.time.setText(list.get(position).getThoigian_hoadon());
        holder.sum.setText(String.format("%.0f", list.get(position).getTong_hoadon()) + "VND");
        holder.content.setText(list.get(position).getNoidung_hoadon());
        holder.status.setText(list.get(position).getTrangthai_hoadon());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ChiTietDonHang_Main.class);

                i.putExtra("ma_giohang", list.get(position).getMa_hoadon());
                i.putExtra("sdt", list.get(position).getSdt_hoadon());
                i.putExtra("name", list.get(position).getName());
                i.putExtra("diachi", list.get(position).getDiachi_hoadon());
                i.putExtra("thoigian", list.get(position).getThoigian_hoadon());
                i.putExtra("tong", list.get(position).getTong_hoadon());
                i.putExtra("trangthai", list.get(position).getTrangthai_hoadon());
                context.startActivity(i);
            }
        });

        holder.status.setText(list.get(position).getTrangthai_hoadon());
        holder.status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.status.setText("Thanh Toán Thành Công");
                inv.setTrangthai_hoadon("Thanh Toán Thành Công");
                hoaDon_dao.update(inv);
                list = hoaDon_dao.SeLectDangGiao();
                setData(list);
                Calendar calendar = Calendar.getInstance();
                Date currentDate = calendar.getTime();

                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
                String formattedTime = timeFormat.format(currentDate);
                String name = inv.getName();
                String orderTime = inv.getThoigian_hoadon();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id_cart, phone, name, address, sum, time, status, content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            content=itemView.findViewById(R.id.id_content);
            name = itemView.findViewById(R.id.id_hoten);
            sum = itemView.findViewById(R.id.id_sum);
            time = itemView.findViewById(R.id.id_time);
            status = itemView.findViewById(R.id.status);
        }
    }
}
