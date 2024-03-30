package com.example.xs_sport.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xs_sport.Adapter.GioHang_Adapter;
import com.example.xs_sport.DAO.GiayAD_Dao;
import com.example.xs_sport.DAO.GioHang_Dao;
import com.example.xs_sport.DAO.KhachHang_Dao;
import com.example.xs_sport.DAO.LichSuDonHang_Dao;
import com.example.xs_sport.ItemTouchHelperListener;
import com.example.xs_sport.Model.Giay_AD;
import com.example.xs_sport.Model.GioHang;
import com.example.xs_sport.Model.KhachHang;
import com.example.xs_sport.Model.LichSuDonHang;
import com.example.xs_sport.R;
import com.example.xs_sport.RecycleViewItemTouchHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class GioHang_Main extends AppCompatActivity implements GioHang_Adapter.OnQuantityUpClickListener, GioHang_Adapter.OnQuantityDownClickListener, ItemTouchHelperListener {

    RecyclerView recyclerView;
    TextView tv_sumPrice;
    Button btn_confirm;
    ImageView backBtn;
    GioHang_Dao gioHang_dao;
    GiayAD_Dao giayAD_dao;
    ArrayList<GioHang> listGioHang;
    ArrayList<Giay_AD> listGiayAD;
    ArrayList<LichSuDonHang> listHis;
    GioHang_Adapter gioHang_adapter;
    KhachHang_Dao khachHang_dao;
    LichSuDonHang_Dao lsDao;
    ArrayList<KhachHang> listKH;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

        recyclerView = findViewById(R.id.recy_fragment_cart_listFood);
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String InUsername1 = sharedPreferences.getString("USERNAME", "");

        gioHang_dao = new GioHang_Dao(this);
        listGioHang = gioHang_dao.getByUser(InUsername1);
        tv_sumPrice = findViewById(R.id.tv_fragment_cart_sumPrice);
        btn_confirm = findViewById(R.id.btn_fragment_cart_confirm);
        backBtn = findViewById(R.id.backBtn);

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog_confirm();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        ItemTouchHelper.SimpleCallback simpleCallback = new RecycleViewItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);
        reloadData();
        updateTotalSum();
    }


    private void reloadData(){
        gioHang_dao = new GioHang_Dao(this);
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String loggedInUserName = sharedPreferences.getString("USERNAME", "");
        listGioHang = gioHang_dao.getByUser(loggedInUserName);
        gioHang_adapter = new GioHang_Adapter(this, listGioHang, gioHang_dao);
        gioHang_adapter.setQuantityUpClickListener(this);
        gioHang_adapter.setOnQuantityDownClickListener(this);
        recyclerView.setAdapter(gioHang_adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
    }


    @Override
    public void onQuantityUpClick(int position) {
        updateTotalSum();
    }

    @Override
    public void onQuantityDownClick(int position) {
        updateTotalSum();
    }


    private void updateTotalSum(){
        int totaSum = calculateTotalSum();
        tv_sumPrice.setText(String.valueOf(totaSum) + "VND");
    }


    private int calculateTotalSum(){
        int totalSum =0;
        for (GioHang gioHang : listGioHang){
            totalSum += gioHang.getTong();
        }
        return totalSum;
    }


    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof GioHang_Adapter.ViewHolder){
            int id = listGioHang.get(viewHolder.getAdapterPosition()).getMa_giohang();
            if (gioHang_dao.delete(id) > 0){
                Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                reloadData();
                updateTotalSum();
            }else {
                Toast.makeText(this, "Xóa không thành công", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void openDialog_confirm(){
        Dialog dialog = new Dialog(this);
        LichSuDonHang lsDH = new LichSuDonHang();

        khachHang_dao = new KhachHang_Dao(this);
        lsDao = new LichSuDonHang_Dao(this);

        dialog.setContentView(R.layout.dialog_confirm_hoadon);

        EditText ed_address, ed_phone;
        TextView tvDateTime, tvInvSum, tvContent, tvUsername, tvType, tvSize;
        Button btnDialogAddCancel, btnDialogAddSubmit;

        ed_address = dialog.findViewById(R.id.ed_dialog_invoice_confirm_address);
        ed_phone = dialog.findViewById(R.id.ed_dialog_invoice_confirm_phone);
        tvContent = dialog.findViewById(R.id.tv_dialog_invoice_confirm_content);
        tvDateTime = dialog.findViewById(R.id.tv_dialog_invoice_confirm_date);
        tvUsername = dialog.findViewById(R.id.tv_dialog_invoice_confirm_user);
        tvInvSum = dialog.findViewById(R.id.tv_dialog_invoice_confirm_priceSum);

        SharedPreferences sharedPreferences = this.getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String loggedInUserName = sharedPreferences.getString("TEN", "");

        listKH = khachHang_dao.getUsersByName(loggedInUserName);
        for (KhachHang khachHang: listKH){
            if (khachHang.getTEN_KH().equals(loggedInUserName)){
                loggedInUserName = khachHang.getTEN_KH();
                break;
            }
        }

        tvUsername.setText(loggedInUserName);
        Calendar calendar = Calendar.getInstance();

        Date currentDate = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

        String formattedDate = dateFormat.format(currentDate);
        String formattedTime = dateFormat.format(currentDate);

        tvDateTime.setText(formattedTime + " " + formattedDate);
        double totalSum = calculateTotalSum();
        tvInvSum.setText(String.valueOf(totalSum));

        String InUsername2 = sharedPreferences.getString("USERNAME", "");
        listGioHang = gioHang_dao.getByUser(InUsername2);
        giayAD_dao = checkGiayDao();
        listGiayAD = giayAD_dao.getAllData();
        String cartData = "";
        for (GioHang gioHang : listGioHang){
            for (Giay_AD giayAd : listGiayAD){
                if (giayAd.getMa_giay() == gioHang.getMa_giay()){
                    cartData += " - " + giayAd.getTen_giay() + "(" +gioHang.getTong() + " VND)" + "\n" + "Số lượng: " + gioHang.getSoLuong() + "\n" + " - " + "Loại: " +giayAd.getLoaiGiay_tenGiay() + "\n" + " - " + "Size: " + giayAd.getSize() + "\n";
                    break;
                }
            }
        }

        String content = cartData;
        tvContent.setText(content);

        btnDialogAddSubmit = dialog.findViewById(R.id.btn_dialog_invoice_add_add);

        btnDialogAddSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String addrs = ed_address.getText().toString();
                String phoneString = ed_phone.getText().toString();
                String dateTime = tvDateTime.getText().toString();
                String content= tvContent.getText().toString();

                SharedPreferences sharedPreferences = getApplication().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String name = sharedPreferences.getString("TEN", "");
                String name1 = sharedPreferences.getString("USERNAME", "");

                if (addrs.trim().isEmpty()){
                    Toast.makeText(GioHang_Main.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else if (phoneString.trim().isEmpty()) {
                    Toast.makeText(GioHang_Main.this, "Vui lòng nhập só điện thoại", Toast.LENGTH_SHORT).show();
                } else if (!phoneString.matches("^[0-9]{10}$")) {
                    Toast.makeText(GioHang_Main.this, "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
                } else if (content.isEmpty()) {
                    Toast.makeText(GioHang_Main.this, "Hãy chọn trước khi đặt hàng", Toast.LENGTH_SHORT).show();
                } else {
                    int phone = 0;
                    try {
                        phone = Integer.parseInt(phoneString);
                    }catch (NumberFormatException e){
                        Toast.makeText(GioHang_Main.this, "Số điện thoại phải là một số", Toast.LENGTH_SHORT).show();
                        return;
                    }


                    lsDH.setName(name);
                    lsDH.setDiaChi(addrs);
                    lsDH.setSdt(phone);
                    lsDH.setThoiGian(dateTime);
                    lsDH.setTong(calculateTotalSum());
                    lsDH.setNoiDung(content);
                    lsDH.setTrangThai("Đang chuẩn bị hàng");


                    if (lsDao.insert(lsDH) >=0){
                        Toast.makeText(GioHang_Main.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        gioHang_dao.DeleteCart(name1);
                        listGioHang = gioHang_dao.getAllData();
                        gioHang_adapter.setData(listGioHang);
                        dialog.dismiss();
                        Intent i = new Intent(GioHang_Main.this, DatHangThanhCon_Main.class);
                        i.putExtra("diaChi", lsDH.getDiaChi());
                        startActivity(i);
                        updateTotalSum();
                    }else {
                        Toast.makeText(GioHang_Main.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
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

    private GiayAD_Dao checkGiayDao(){
        if (giayAD_dao == null){
            giayAD_dao = new GiayAD_Dao(this);
        }
        return giayAD_dao;
    }
}