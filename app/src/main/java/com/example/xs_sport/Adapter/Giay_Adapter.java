package com.example.xs_sport.Adapter;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xs_sport.ChiTietGiay;
import com.example.xs_sport.DAO.Giay_Dao;
import com.example.xs_sport.DAO.GioHang_Dao;
import com.example.xs_sport.Model.Giay;
import com.example.xs_sport.Model.GioHang;
import com.example.xs_sport.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Giay_Adapter extends RecyclerView.Adapter<Giay_Adapter.ViewHolder> {

    private Context context;
    private ArrayList<Giay> list;
    private Giay_Dao giayDao;

    public Giay_Adapter(Context context, ArrayList<Giay> list, Giay_Dao giayDao) {
        this.context = context;
        this.list = list;
        this.giayDao = giayDao;
    }

    public void setData(ArrayList<Giay> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recyclet_giay, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Giay giay = list.get(position);
        holder.tv_name.setText(list.get(position).getTen_giay());
        String img = list.get(position).getAnh_giay();
        Picasso.get().load(img).into(holder.iv_img);
        holder.tv_price.setText(String.valueOf(list.get(position).getGia_giay())  + "VND");
        holder.tv_size.setText(giay.getSize());
        holder.tv_type.setText(giay.getMota_giay());
        holder.btn_addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(view.getContext());
                dialog.setContentView(R.layout.dialog_item_cart);

                Window window = dialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                AppCompatButton btnSubmit, btnCancel;
                btnSubmit = dialog.findViewById(R.id.btn_dialog_item_add_cart);
                btnCancel = dialog.findViewById(R.id.btn_dialog_item_cancel_cart);

                btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        GioHang_Dao gioHang_dao = new GioHang_Dao(context);
                        GioHang gioHang = new GioHang();

                        gioHang.setMa_giay(giay.getMa_giay());
                        gioHang.setSoLuong(1);
                        gioHang.setTong(giay.getGia_giay());
                        gioHang.setSize(giay.getSize());
                        gioHang.setLoaiGiay(giay.getLoaiGiay_tenGiay());

                        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                        String usernameLogged = sharedPreferences.getString("USERNAME", "");
                        gioHang.setUsername(usernameLogged);

                        if (!gioHang_dao.GiayExists(gioHang.getMa_giay(), gioHang.getUsername())){
                            if (gioHang_dao.insert(gioHang) > 0){
                                Toast.makeText(context, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            } else {
                                Toast.makeText(context, "Không thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                            }

                        }else {
                            Toast.makeText(context, "Sản phẩm đã được chọn", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
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
        });

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent  i = new Intent(context, ChiTietGiay.class);
                        i.putExtra("ma_giay", list.get(position).getMa_giay());
                        i.putExtra("loaiGiay_tenGiay", list.get(position).getLoaiGiay_tenGiay());
                        i.putExtra("anh_giay", list.get(position).getAnh_giay());
                        i.putExtra("ten_giay", list.get(position).getTen_giay());
                        i.putExtra("mota_giay", list.get(position).getMota_giay());
                        i.putExtra("gia_giay", list.get(position).getGia_giay());
                        i.putExtra("size", list.get(position).getSize());

                        context.startActivity(i);
                    }
                });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_img;
        TextView tv_name, tv_price, tv_des,tv_size,tv_type;
        ImageView btn_addCart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_img = itemView.findViewById(R.id.iv_item_coffee_foodImg);
            tv_name = itemView.findViewById(R.id.tv_item_coffee_foodName);
            tv_price = itemView.findViewById(R.id.tv_item_coffee_foodPrice);
            btn_addCart = itemView.findViewById(R.id.btn_item_coffee_addCart);
            tv_des = itemView.findViewById(R.id.tv_item_coffee_des);
            tv_size = itemView.findViewById(R.id.tv_item_coffee_foodsize);
            tv_type = itemView.findViewById(R.id.tv_item_coffee_foodType);
        }
    }
}
