package com.example.xs_sport.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xs_sport.DAO.LoaiGiay_Dao;
import com.example.xs_sport.Model.LoaiGiay;
import com.example.xs_sport.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class LoaiGiay_Adapter extends BaseAdapter {

    private ArrayList<LoaiGiay> list;
    private Context context;
    private LoaiGiay_Dao loaiGiay_dao;
    LoaiGiay loaiGiay;
    TextInputLayout ip_ten_loai;
    TextInputEditText ed_ten_loai;

    int check = 0;
    int a= 0;


    public LoaiGiay_Adapter(ArrayList<LoaiGiay> list, Context context, LoaiGiay_Dao loaiGiay_dao) {
        this.list = list;
        this.context = context;
        this.loaiGiay_dao = loaiGiay_dao;
    }

    public void setData(ArrayList<LoaiGiay> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_recycler_loaigiay, null);
            holder = new Holder();
            holder.txt_ten_loai_giay = view.findViewById(R.id.item_name_loai_coffee);
            holder.delete_loai_giay = view.findViewById(R.id.item_coffee_delete);
            holder.edit_loai_giay = view.findViewById(R.id.item_coffee_edit);

            view.setTag(holder);
        }else {
            holder = (Holder) view.getTag();
        }

        loaiGiay = list.get(i);
        holder.txt_ten_loai_giay.setText(loaiGiay.getTenLoai());

        holder.delete_loai_giay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loaiGiay_dao.delete(String.valueOf(loaiGiay.getMaLoai()))>0){
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(loaiGiay_dao.getAll());
                    notifyDataSetChanged();
                }else {
                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });


        holder.edit_loai_giay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(Gravity.CENTER, i);
            }
        });
        return view;
    }

    public class Holder{
        TextView txt_ten_loai_giay;
        ImageButton delete_loai_giay, edit_loai_giay;
    }


    public void openDialog(int gravity, int position){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_loai_giay);
        Window window = dialog.getWindow();
        if (window == null){
            return;
        }

        if (Gravity.CENTER == gravity){
            dialog.setCancelable(true);
        }else {
            dialog.setCancelable(false);
        }
        TextView lcTile = (TextView) dialog.findViewById(R.id.title_loai_coffee);

        ed_ten_loai=dialog.findViewById(R.id.ed_name_loai_coffee);
        ip_ten_loai=dialog.findViewById(R.id.input_name_loai_coffee);

        Button btnadd = dialog.findViewById(R.id.btn_dialog_add_loai_coffee);
        Button btncancel = dialog.findViewById(R.id.btn_dialog_cancle_loai_coffee);

        loaiGiay_dao = new LoaiGiay_Dao(context);
        loaiGiay = list.get(position);
        lcTile.setText("Sửa loại giày");
        btnadd.setText("Sửa");
        btncancel.setText("Hủy");
        ed_ten_loai.setText(loaiGiay.getTenLoai());

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
                if (check == 0){
                    loaiGiay.setTenLoai(ed_ten_loai.getText().toString());
                    if (loaiGiay_dao.update(loaiGiay) > 0){
                        Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        list.clear();
                        list.addAll(loaiGiay_dao.getAll());
                        notifyDataSetChanged();
                    }else {
                        Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Hủy sửa", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }

    private void validate() {
        if (ed_ten_loai.getText().length() == 0) {
            ip_ten_loai.setError("Tên loại giày không được để trống");
            check++;
        } else {
            ip_ten_loai.setError("");
        }
    }
}
