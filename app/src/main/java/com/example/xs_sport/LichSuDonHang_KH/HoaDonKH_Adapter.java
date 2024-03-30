package com.example.xs_sport.LichSuDonHang_KH;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xs_sport.Activity.ChiTietDonHang_Main;
import com.example.xs_sport.DAO.GiayAD_Dao;
import com.example.xs_sport.DAO.HoaDonKH_Dao;
import com.example.xs_sport.Model.HoaHon_KH;
import com.example.xs_sport.R;

import java.util.ArrayList;

public class HoaDonKH_Adapter extends RecyclerView.Adapter<HoaDonKH_Adapter.VỉewHolder> {

    private ArrayList<HoaHon_KH> list;
    private Context context;
    private HoaDonKH_Dao hoaDonKH_dao;

    GiayAD_Dao dao;

    public HoaDonKH_Adapter(ArrayList<HoaHon_KH> list, Context context, HoaDonKH_Dao hoaDonKH_dao) {
        this.list = list;
        this.context = context;
        this.hoaDonKH_dao = hoaDonKH_dao;
        dao = new GiayAD_Dao(context);
    }

    public void setData(ArrayList<HoaHon_KH> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VỉewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_history_user, parent, false);

        return new VỉewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonKH_Adapter.VỉewHolder holder, @SuppressLint("RecyclerView") int position) {
        HoaHon_KH history = list.get(position);

        holder.name.setText(list.get(position).getName());
        holder.content.setText(list.get(position).getNoidung_hoadon());
        holder.time.setText(list.get(position).getThoigian_hoadon());
        holder.sum.setText(String.format("%.0f", list.get(position).getTong_hoadon()) + " VND");

        hoaDonKH_dao = new HoaDonKH_Dao(context);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ChiTietDonHang_Main.class);

                i.putExtra("ma_giohang", list.get(position).getMa_giohang());
                i.putExtra("sdt", list.get(position).getSdt_hoadon());
                i.putExtra("name", list.get(position).getName());
                i.putExtra("diachi", list.get(position).getDiachi_hoadon());
                i.putExtra("thoigian", list.get(position).getThoigian_hoadon());
                i.putExtra("noidung", list.get(position).getNoidung_hoadon());
                i.putExtra("trangthai", list.get(position).getTrangthai_hoadon());
                i.putExtra("tong", list.get(position).getTong_hoadon());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        return 0;    }

    public class VỉewHolder extends RecyclerView.ViewHolder {
        TextView name,sum,time,content;
        public VỉewHolder(@NonNull View itemView) {
            super(itemView);

            name =itemView.findViewById(R.id.id_hoten);
            sum =itemView.findViewById(R.id.id_sum);
            time =itemView.findViewById(R.id.id_time);
            content=itemView.findViewById(R.id.id_content);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        showDele(list.get(position).getMa_hoadon(), view.getContext());
                        return true;
                    }
                    return false;
                }
            });
        }
    }

    public void showDele(int id, Context context){
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_item_delete_hoadon);

        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        AppCompatButton btnSubmit, btnCancel;
        btnSubmit = dialog.findViewById(R.id.btn_dialog_item_delete_submit);
        btnCancel = dialog.findViewById(R.id.btn_dialog_item_delete_cancel);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HoaDonKH_Dao dao = new HoaDonKH_Dao(context);
                if (dao.delete(id) > 0){
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPreferences = dialog.getContext().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                    String loggedInUserName = sharedPreferences.getString("USERNAME", "");
                    list = dao.getByUser(loggedInUserName);
                    setData(list);
                } else {
                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }

                dialog.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
