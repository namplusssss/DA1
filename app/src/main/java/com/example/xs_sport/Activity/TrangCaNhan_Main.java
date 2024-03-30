package com.example.xs_sport.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.xs_sport.DangNhap;
import com.example.xs_sport.LichSuDonHang_KH.LichSuDH;
import com.example.xs_sport.Model.LichSuDonHang;
import com.example.xs_sport.R;

public class TrangCaNhan_Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_ca_nhan_main);

        //
        LinearLayout lnDangXuat = findViewById(R.id.ln_dangxuat);
        LinearLayout lnThayDoiMatKhau = findViewById(R.id.ln_thaydoimatkhau);
        LinearLayout lnLienHeAdmin = findViewById(R.id.ln_lienhe);
        LinearLayout lnThongTinKh = findViewById(R.id.ln_thongtincanhan);
        LinearLayout lnLSDonHang = findViewById(R.id.ln_lich_su_don_hang);

        ImageView btnBack = findViewById(R.id.backBtn);

        //
        lnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLogoutConfirmationDialog();
            }
        });

        //
        lnThayDoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrangCaNhan_Main.this, ThayDoiMKKH_Main.class);
                startActivity(intent);
            }
        });

        //
        lnLienHeAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrangCaNhan_Main.this, LienHeAdmin_Main.class);
                startActivity(intent);
            }
        });

        //
        lnThongTinKh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrangCaNhan_Main.this, ThongTinKH_Main.class);
                startActivity(intent);
            }
        });

        //
        lnLSDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrangCaNhan_Main.this, LichSuDH.class);
                startActivity(intent);
            }
        });

        //
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void showLogoutConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận đăng xuất");
        builder.setMessage("Bạn có chắc chắn muốn đăng xuất?");

        //nút xác nhận
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                performLogout();
            }
        });

        //nút hủy bỏ
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private void performLogout() {
        SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("ISLOGIN", false);
        editor.apply();

        Intent intent = new Intent(TrangCaNhan_Main.this, DangNhap.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(intent);

        finish();

        showToast("Bạn đã đăng xuất thành công!!!");
    }

    private void showToast(String message) {
        // Hiển thị thông báo ngắn với nội dung được truyền vào
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}