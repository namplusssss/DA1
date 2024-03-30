package com.example.xs_sport.Adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xs_sport.DAO.GiayAD_Dao;
import com.example.xs_sport.DAO.LoaiGiay_Dao;
import com.example.xs_sport.Model.Giay_AD;
import com.example.xs_sport.Model.LoaiGiay;
import com.example.xs_sport.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GiayAD_Adapter extends RecyclerView.Adapter<GiayAD_Adapter.ViewHoler> {

    Context context;
    private ArrayList<Giay_AD> list;
    private GiayAD_Dao dao;

    public GiayAD_Adapter(Context context, ArrayList<Giay_AD> list, GiayAD_Dao dao) {
        this.context = context;
        this.list = list;
        this.dao = dao;
    }

    public void setData(ArrayList<Giay_AD> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GiayAD_Adapter.ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_admin_giay, null);
        return new GiayAD_Adapter.ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, @SuppressLint("RecyclerView") int position) {

        holder.tv_name.setText(list.get(position).getTen_giay());
        String img = list.get(position).getAnh_giay();
        Picasso.get().load(img).into(holder.iv_img);
        holder.tv_des.setText(list.get(position).getMota_giay());
        holder.tv_type.setText(list.get(position).getLoaiGiay_tenGiay());
        holder.tv_size.setText(list.get(position).getSize());
        holder.tv_price.setText(String.valueOf(list.get(position).getGia_giay()) + "VND");

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public boolean onLongClick(View view) {
                @SuppressLint("RestrictedApi")MenuBuilder builder = new MenuBuilder(context);
                MenuInflater inflater = new MenuInflater(context);
                inflater.inflate(R.menu.menu_popup_edit_delete, builder);
                @SuppressLint("RestrictedApi")MenuPopupHelper optionmenu = new MenuPopupHelper(context, builder, view);
                builder.setCallback(new MenuBuilder.Callback() {
                    @Override
                    public boolean onMenuItemSelected(@NonNull MenuBuilder menu, @NonNull MenuItem item) {
                        if (item.getItemId() == R.id.option_edit){
                            updateDia(list.get(position), position);
                            return true;
                        } else if (item.getItemId() == R.id.option_delete) {
                            showDele(list.get(position).getMa_giay());
                            return true;
                        }else {
                            return false;
                        }
                    }

                    @Override
                    public void onMenuModeChange(@NonNull MenuBuilder menu) {

                    }
                });

                optionmenu.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {

        ImageView iv_img;
        TextView tv_name, tv_des, tv_price,tv_size,tv_type;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);

            iv_img = itemView.findViewById(R.id.iv_item_listFood_foodImg);
            tv_name = itemView.findViewById(R.id.tv_item_listFood_foodName);
            tv_des = itemView.findViewById(R.id.tv_item_listFood_foodContent);
            tv_price = itemView.findViewById(R.id.tv_item_listFood_foodPrice);
            tv_size = itemView.findViewById(R.id.tv_item_listFood_foodSize);
            tv_type = itemView.findViewById(R.id.tv_item_listFood_foodType);
        }
    }

    public void showDele(int id){
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_item_delete_hoadon);
        TextView content = dialog.findViewById(R.id.tv_dialog_delete);

        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        AppCompatButton btnSubmit, btnCancel;
        btnSubmit = dialog.findViewById(R.id.btn_dialog_item_delete_submit);
        btnCancel = dialog.findViewById(R.id.btn_dialog_item_delete_cancel);
        content.setText("Bạn có muốn xóa không");

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GiayAD_Dao dao = new GiayAD_Dao(context);
                if (dao.delete(id) > 0){
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    list = dao.getAllData();
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


    private void updateDia(Giay_AD giayAd, int id){
        Dialog dialog = new Dialog(context);
        GiayAD_Dao giayADDao = new GiayAD_Dao(context);
        LoaiGiay_Dao loaiGiay_dao = new LoaiGiay_Dao(context);
        dialog.setContentView(R.layout.dialog_listgiay_update);

        EditText ed_listgiay_img, ed_listgiay_name, ed_listgiay_price, ed_listgiay_des, ed_listgiay_size;
        Button btnDialogAddCancel, btnDialogAddSubmit;

        Spinner spn = dialog.findViewById(R.id.spn_dialog_listcoffee_update_type);
        ed_listgiay_img = dialog.findViewById(R.id.edt_dialog_listcoffee_update_img);
        ed_listgiay_name = dialog.findViewById(R.id.edt_dialog_listcoffee_update_name);
        ed_listgiay_price = dialog.findViewById(R.id.edt_dialog_listcoffee_update_price);
        ed_listgiay_des = dialog.findViewById(R.id.edt_dialog_listcoffee_update_des);
        ed_listgiay_size = dialog.findViewById(R.id.edt_dialog_listcoffee_update_size);


        ed_listgiay_img.setText(list.get(id).getAnh_giay());
        ed_listgiay_name.setText(list.get(id).getTen_giay());
        ed_listgiay_price.setText(String.valueOf(list.get(id).getGia_giay()));
        ed_listgiay_des.setText(list.get(id).getMota_giay());
        ed_listgiay_size.setText(list.get(id).getSize());


        btnDialogAddSubmit = dialog.findViewById(R.id.btn_dialog_listfood_update_add);
        ArrayList<LoaiGiay> listLoai = loaiGiay_dao.getAllData();

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, loaiGiay_dao.name());
        spn.setAdapter(adapter1);

        int spIndex = 0;
        for (LoaiGiay loai : listLoai){
            if (loai.getTenLoai().equals(giayAd.getLoaiGiay_tenGiay())){
                spn.setSelection(spIndex);
                break;
            }
            spIndex++;
        }

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String i = listLoai.get(position).getTenLoai();
                giayAd.setLoaiGiay_tenGiay(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btnDialogAddSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GiayAD_Dao giayADDao1 = new GiayAD_Dao(context);

                String img = ed_listgiay_img.getText().toString();
                String name = ed_listgiay_name.getText().toString();
                String priceString = ed_listgiay_price.getText().toString();
                String des = ed_listgiay_des.getText().toString();
                String size = ed_listgiay_size.getText().toString();

                if (img.trim().isEmpty() || name.trim().isEmpty() || priceString.trim().isEmpty() || des.trim().isEmpty()) {
                    Toast.makeText(context, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else if (!priceString.matches("\\d+")) {
                    Toast.makeText(context, "Giá tiền phải là một số", Toast.LENGTH_SHORT).show();
                } else {
                    int price = Integer.parseInt(priceString);

                    giayAd.setAnh_giay(img);
                    giayAd.setTen_giay(name);
                    giayAd.setGia_giay(price);
                    giayAd.setMota_giay(des);
                    giayAd.setSize(size);

                    if (giayADDao1.update(giayAd) > 0){
                        Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        list = giayADDao1.getAllData();
                        setData(list);
                        dialog.dismiss();
                    }else {
                        Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        dialog.show();

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.dialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }


}
