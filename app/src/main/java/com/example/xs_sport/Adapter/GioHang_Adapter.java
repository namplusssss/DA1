package com.example.xs_sport.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xs_sport.Activity.GioHang_Main;
import com.example.xs_sport.DAO.GiayAD_Dao;
import com.example.xs_sport.DAO.GioHang_Dao;
import com.example.xs_sport.Model.Giay_AD;
import com.example.xs_sport.Model.GioHang;
import com.example.xs_sport.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class GioHang_Adapter extends RecyclerView.Adapter<GioHang_Adapter.ViewHolder>{
    Context context;
    List<GioHang> list;
    GioHang_Dao gioHang_dao;
    GiayAD_Dao dao;

    private OnQuantityUpClickListener quantityUpClickListener;
    private OnQuantityDownClickListener quantityDownClickListener;

    public GioHang_Adapter(Context context, List<GioHang> list, GioHang_Dao gioHang_dao) {
        this.context = context;
        this.list = list;
        this.gioHang_dao = gioHang_dao;
        this.dao = new GiayAD_Dao(context);
    }

    public void setData(ArrayList<GioHang> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public void setQuantityUpClickListener(OnQuantityUpClickListener listener){
        this.quantityUpClickListener = listener;
    }

    public void setOnQuantityDownClickListener(OnQuantityDownClickListener listener){
        this.quantityDownClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_giohang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GioHang gio_hang = list.get(position);
        Giay_AD giayAd = dao.getByID(gio_hang.getMa_giay());

        if (giayAd != null) {
            holder.tv_name.setText(giayAd.getTen_giay());
            Picasso.get().load(giayAd.getAnh_giay()).into(holder.iv_img);

            holder.tv_price.setText(String.valueOf(giayAd.getGia_giay()));

            holder.tv_size.setText("Size: "+ giayAd.getSize());

            holder.tv_type.setText("Loại: "+ giayAd.getLoaiGiay_tenGiay());
        }

        holder.tv_price.setText("Giá: "+ String.valueOf(giayAd.getGia_giay() * gio_hang.getSoLuong()) + " VND");

        holder.tv_quanti.setText(String.valueOf(gio_hang.getSoLuong()));

        holder.btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quatity = gio_hang.getSoLuong() + 1;
                holder.tv_quanti.setText(String.valueOf(quatity));
                holder.tv_price.setText(String.valueOf(giayAd.getGia_giay() * quatity) + "VND");
                gio_hang.setSoLuong(quatity);
                gio_hang.setTong(giayAd.getGia_giay() * quatity);
                gioHang_dao.upDateSum(gio_hang);

                if (quantityUpClickListener != null) {
                    quantityUpClickListener.onQuantityUpClick(holder.getAdapterPosition());
                }
            }
        });


        holder.btn_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = gio_hang.getSoLuong()  ;
                if (quantity >1){
                    quantity -=1;
                    holder.tv_quanti.setText(String.valueOf(quantity));
                    holder.tv_price.setText(String.valueOf(giayAd.getGia_giay() * quantity) + "VND");
                    gio_hang.setSoLuong(quantity);
                    gio_hang.setTong(giayAd.getGia_giay() * quantity);
                    gioHang_dao.upDateSum(gio_hang);


                    if (quantityDownClickListener != null){
                        quantityDownClickListener.onQuantityDownClick(holder.getAdapterPosition());
                    }else {
                        Toast.makeText(context, "Số lượng không được nhỏ hơn 1", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public View layout_foreground;
        ImageView iv_img;
        TextView tv_name, tv_des, tv_price, tv_quanti,tv_size,tv_type;
        ImageView btn_up, btn_down, btn_del;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_img = itemView.findViewById(R.id.iv_item_cart_foodImg);
            tv_name = itemView.findViewById(R.id.tv_item_cart_foodName);
            tv_price = itemView.findViewById(R.id.tv_item_cart_foodPrice);
            tv_quanti = itemView.findViewById(R.id.tv_item_cart_quantity);
            tv_size = itemView.findViewById(R.id.tv_item_cart_foodsize);
            tv_type = itemView.findViewById(R.id.tv_item_cart_foodType);
            btn_up = itemView.findViewById(R.id.btn_item_cart_quantity_up);
            btn_down = itemView.findViewById(R.id.btn_item_cart_quantity_down);
            btn_del = itemView.findViewById(R.id.btn_item_cart_delete);
            layout_foreground = itemView.findViewById(R.id.layout_foreground);
        }
    }




    public interface OnQuantityUpClickListener {
        void onQuantityUpClick(int position);

        void onSwiped(RecyclerView.ViewHolder viewHolder);
    }

    public interface OnQuantityDownClickListener {
        void onQuantityDownClick(int position);
    }
}
